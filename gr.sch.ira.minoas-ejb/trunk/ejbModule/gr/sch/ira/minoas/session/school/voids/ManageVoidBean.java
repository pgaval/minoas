package gr.sch.ira.minoas.session.school.voids;

import gr.sch.ira.minoas.core.EventConstants;
import gr.sch.ira.minoas.model.TeacherType;
import gr.sch.ira.minoas.model.voids.TeachingResource;
import gr.sch.ira.minoas.model.voids.TeachingVoid;
import gr.sch.ira.minoas.session.school.BaseSchoolAware;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.RaiseEvent;
import org.jboss.seam.annotations.security.Restrict;

@Stateful
@Restrict("#{identity.loggedIn}")
@Name("manageVoid")
public class ManageVoidBean extends BaseSchoolAware implements ManageVoid {

	
	
	@Out(required=false, scope=ScopeType.CONVERSATION)
	private ConversationStatus conversationStatus;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

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

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#begin()
	 */
	public void begin() {
		// TODO Auto-generated method stub

	}

	@Begin(nested = true, pageflow = "createTeachingVoid")
	public void beginCreateTeachingVoid() {
		info("conversation has begun");
		conversationStatus = ConversationStatus.CREATING_NEW_VOID;
	}
	
	public void beginCreateAnotherTeachingVoid() {
		conversationStatus = ConversationStatus.CREATING_NEW_VOID;
		createTeachingVoid();
	}
	
	public void beginUpdateExistingTeachingVoid(TeachingVoid teachingVoid) {
		conversationStatus = ConversationStatus.UPDATING_EXISTING_VOID;
		setTeachingVoid(em.merge(teachingVoid));
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#cancel()
	 */
	@End
	public void cancel() {
		// TODO Auto-generated method stub

	}

	@Factory(value = "teachingVoid", scope = ScopeType.CONVERSATION)
	public TeachingVoid createTeachingVoid() {
		info("creating new teaching void instance and adding it to the conversation context.");
		teachingVoid = new TeachingVoid();
		teachingVoid.setSchool(getSchool());
		teachingVoid.setRequiredHours(Long.valueOf(0));
		teachingVoid.setTeachingHours(Long.valueOf(0));
		teachingVoid.setTeachingResources(new ArrayList<TeachingResource>());
		setTeachingVoid(teachingVoid);
		return teachingVoid;
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#getTeachingVoid()
	 */
	public TeachingVoid getTeachingVoid() {
		return this.teachingVoid;
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#remove()
	 */
	@Remove
	@Destroy
	public void remove() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#removeTeachingResource(gr.sch.ira.minoas.model.voids.TeachingResource)
	 */
	public void removeTeachingResource(TeachingResource teachingResource) {
		Collection<TeachingResource> resources = teachingVoid
				.getTeachingResources();
		if (resources != null) {
			resources.remove(teachingResource);
		}
	}

	@RaiseEvent(EventConstants.EVENT_TEACHING_VOID_ADDED)
	public void saveCreatedTeachingVoid() {
		info("trying to save newly created teaching void '#0'",
				getTeachingVoid());
		em.persist(getTeachingVoid());
		/*
		 * check if any of the teaching resource(s) has non possitive teaching
		 * hours
		 */
		for (Iterator<TeachingResource> it = getTeachingVoid()
				.getTeachingResources().iterator(); it.hasNext();) {
			TeachingResource resource = it.next();
			if (!(resource.getTeachingHours().longValue() > 0)) {
				it.remove();
			} else
				em.persist(resource);
		}
		em.flush();
		em.merge(getTeachingVoid());
		conversationStatus = ConversationStatus.NEW_VOID_SAVED;

	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#setTeachingVoid(gr.sch.ira.minoas.model.voids.TeachingVoid)
	 */
	public void setTeachingVoid(TeachingVoid teachingVoid) {
		this.teachingVoid = teachingVoid;
	}

	

}
