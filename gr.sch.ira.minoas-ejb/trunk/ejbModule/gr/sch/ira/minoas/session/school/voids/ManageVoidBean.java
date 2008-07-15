package gr.sch.ira.minoas.session.school.voids;

import gr.sch.ira.minoas.core.EventConstants;
import gr.sch.ira.minoas.model.TeacherType;
import gr.sch.ira.minoas.model.voids.TeachingResource;
import gr.sch.ira.minoas.model.voids.TeachingVoid;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;
import gr.sch.ira.minoas.session.school.BaseSchoolAware;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.RaiseEvent;
import org.jboss.seam.annotations.security.Restrict;

@Stateful
@Restrict("#{identity.loggedIn}")
@Name("manageVoid")
@Local( { ManageVoid.class, IBaseStatefulSeamComponent.class })
public class ManageVoidBean extends BaseSchoolAware implements ManageVoid {

	@Out(required = false, scope = ScopeType.CONVERSATION)
	private ConversationStatus conversationStatus;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@In(required = false)
	@Out(required = true)
	private TeachingVoid teachingVoid;

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#addTeachingResource()
	 */
	public void addTeachingResource() {
		TeachingResource new_resource = new TeachingResource();
		new_resource.setFillingVoid(getTeachingVoid());
		new_resource.setTeacherType(TeacherType.PERMANENT);
		new_resource.setTeachingHours(Long.valueOf(0L));
		getTeachingVoid().getTeachingResources().add(new_resource);
	}

	@Begin(join=true, pageflow = "createTeachingVoid")
	public void beginCreateTeachingVoid() {
		info("conversation has begun");
		setTeachingVoid(createTeachingVoid());
		info("added a new teaching void '#0' in the conversation context", getTeachingVoid());
		conversationStatus = ConversationStatus.CREATING_NEW_VOID;
	}

	@Begin(join=true, pageflow = "createTeachingVoid")
	public void beginCreateAnotherTeachingVoid() {
		conversationStatus = ConversationStatus.CREATING_NEW_VOID;
		createTeachingVoid();
	}

	public void beginUpdateExistingTeachingVoid(TeachingVoid teachingVoid) {
		info("managing teaching void '#0' of school '#1'.", teachingVoid, getSchool());
		conversationStatus = ConversationStatus.UPDATING_EXISTING_VOID;
		setTeachingVoid(em.merge(teachingVoid));
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#cancel()
	 */
	@End
	public void cancel() {
		info("ending converstation.");

	}

	public TeachingVoid createTeachingVoid() {
		info("creating new teaching void instance and adding it to the conversation context.");
		teachingVoid = new TeachingVoid();
		if (hasSchool())
			teachingVoid.setSchool(getSchool());
		teachingVoid.setRequiredHours(Long.valueOf(0));
		teachingVoid.setTeachingHours(Long.valueOf(0));
		teachingVoid.setTeachingResources(new ArrayList<TeachingResource>());
		return teachingVoid;
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#getTeachingVoid()
	 */
	public TeachingVoid getTeachingVoid() {
		return this.teachingVoid;
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#removeTeachingResource(gr.sch.ira.minoas.model.voids.TeachingResource)
	 */
	public void removeTeachingResource(TeachingResource teachingResource) {
		Collection<TeachingResource> resources = teachingVoid.getTeachingResources();
		if (resources != null) {
			resources.remove(teachingResource);
		}
	}

	@RaiseEvent(EventConstants.EVENT_TEACHING_VOID_ADDED)
	public void saveCreatedTeachingVoid() {
		TeachingVoid teaching_void = getTeachingVoid();
		teaching_void.setSchool(getSchool());
		info("trying to save newly created teaching void '#0'", teaching_void);
		em.persist(teaching_void);
		/*
		 * check if any of the teaching resource(s) has non possitive teaching
		 * hours
		 */
		for (Iterator<TeachingResource> it = teaching_void.getTeachingResources().iterator(); it.hasNext();) {
			TeachingResource resource = it.next();
			if (!(resource.getTeachingHours().longValue() > 0)) {
				it.remove();
			} else
				em.persist(resource);
		}
		em.flush();
		em.merge(teaching_void);
		conversationStatus = ConversationStatus.NEW_VOID_SAVED;
	}

	@RaiseEvent(EventConstants.EVENT_TEACHING_VOID_MODIFIED)
	public void saveExistingTeachingVoid() {
		TeachingVoid teaching_void = getTeachingVoid();
		info("trying to save existing teaching void '#0' in school '#1'", teaching_void, getSchool());
		/*
		 * check if any of the teaching resource(s) has non positive teaching
		 * hours
		 */
		for (Iterator<TeachingResource> it = teaching_void.getTeachingResources().iterator(); it.hasNext();) {
			TeachingResource resource = it.next();
			if (!(resource.getTeachingHours().longValue() > 0)) {
				info(
						"removing teaching resources '#0' from '#1' teaching void, since teaching hours are not positive.",
						resource, teaching_void);
				it.remove();
			} else {
				if (!em.contains(resource)) {
					info("registering new teaching resources '#0' to '#1' teaching void.", resource, teaching_void);
					em.persist(resource);
				}
			}
		}
		teaching_void = em.merge(teaching_void);
		em.flush();
		conversationStatus = ConversationStatus.EXISTING_VOID_UPDATED;
		info("existing teaching void '#0' has been succesfully updated.", teaching_void);
		setTeachingVoid(teaching_void);
	}

	@RaiseEvent(EventConstants.EVENT_TEACHING_VOID_REMOVED)
	public void removeExistingTeachingVoid() {
		info("trying to remove existing teaching void '#0' from school '#1'", getTeachingVoid(), getSchool());
		TeachingVoid teaching_void = em.merge(getTeachingVoid());
		em.remove(teaching_void);
		em.flush();
		info("removed teaching void '#0' from school '#1'", teaching_void, getSchool());
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#setTeachingVoid(gr.sch.ira.minoas.model.voids.TeachingVoid)
	 */
	public void setTeachingVoid(TeachingVoid teachingVoid) {
		this.teachingVoid = teachingVoid;
	}

}
