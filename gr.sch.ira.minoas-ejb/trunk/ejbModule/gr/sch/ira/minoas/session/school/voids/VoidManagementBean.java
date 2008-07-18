package gr.sch.ira.minoas.session.school.voids;

import gr.sch.ira.minoas.core.EventConstants;
import gr.sch.ira.minoas.model.TeacherType;
import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.voids.TeachingResource;
import gr.sch.ira.minoas.model.voids.TeachingRequirement;
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
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.RaiseEvent;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.security.Restrict;

@Stateful
@Restrict("#{identity.loggedIn}")
@Name("voidManagement")
@Local( { VoidManagement.class, IBaseStatefulSeamComponent.class })
public class VoidManagementBean extends BaseSchoolAware implements VoidManagement {

	@DataModel
	private Collection<TeachingRequirement> teachingVoids;

	@Out(required = false, scope = ScopeType.CONVERSATION)
	private ConversationStatus conversationStatus;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@Begin(join = true, pageflow = "manageSchoolVoids")
	public void beginSchoolVoidManagement() {
		info("starting school void(s) management.");
	}

	public Collection<TeachingRequirement> searchTeachingVoids() {
		School school = em.merge(getSchool());
		info("searching for school's '#0' teaching voids.", school);
		em.refresh(school);
		Collection<TeachingRequirement> teachingvoids = school.getVoids();
		info("found totally #0 teaching void(s) registered with school '#1'", teachingvoids.size(), getSchool());
		setTeachingVoids(teachingvoids);
		return getTeachingVoids();
	}

	@In(required = false)
	@Out(required = false)
	private TeachingRequirement teachingVoid;

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.VoidManagement#addTeachingResource()
	 */
	public void addTeachingResource() {
		TeachingResource new_resource = new TeachingResource();
		new_resource.setFillingVoid(getTeachingVoid());
		new_resource.setTeacherType(TeacherType.PERMANENT);
		new_resource.setTeachingHours(Long.valueOf(0L));
		getTeachingVoid().getTeachingResources().add(new_resource);
	}

	@Begin(join = true, pageflow = "createTeachingVoid")
	public void beginCreateTeachingVoid() {
		info("conversation has begun");
		setTeachingVoid(createTeachingVoid());
		info("added a new teaching void '#0' in the conversation context", getTeachingVoid());
		conversationStatus = ConversationStatus.CREATING_NEW_VOID;
	}

	@Begin(join = true, pageflow = "createTeachingVoid")
	public void beginCreateAnotherTeachingVoid() {
		conversationStatus = ConversationStatus.CREATING_NEW_VOID;
		createTeachingVoid();
	}

	public void beginUpdateExistingTeachingVoid(TeachingRequirement teachingVoid) {
		info("managing teaching void '#0' of school '#1'.", teachingVoid, getSchool());
		conversationStatus = ConversationStatus.UPDATING_EXISTING_VOID;
		setTeachingVoid(em.merge(teachingVoid));
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.VoidManagement#cancel()
	 */
	@End
	public void cancel() {
		info("ending converstation.");

	}

	public TeachingRequirement createTeachingVoid() {
		info("creating new teaching void instance and adding it to the conversation context.");
		teachingVoid = new TeachingRequirement();
		if (hasSchool())
			teachingVoid.setSchool(getSchool());
		teachingVoid.setRequiredHours(Long.valueOf(0));
		teachingVoid.setTeachingHours(Long.valueOf(0));
		teachingVoid.setTeachingResources(new ArrayList<TeachingResource>());
		return teachingVoid;
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.VoidManagement#getTeachingVoid()
	 */
	public TeachingRequirement getTeachingVoid() {
		return this.teachingVoid;
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.VoidManagement#removeTeachingResource(gr.sch.ira.minoas.model.voids.TeachingResource)
	 */
	public void removeTeachingResource(TeachingResource teachingResource) {
		Collection<TeachingResource> resources = teachingVoid.getTeachingResources();
		if (resources != null) {
			resources.remove(teachingResource);
		}
	}

	public void saveCreatedTeachingVoid() {
		TeachingRequirement teaching_void = getTeachingVoid();
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
		foo();
		em.flush();
		em.merge(teaching_void);
		conversationStatus = ConversationStatus.NEW_VOID_SAVED;
	}

	public void saveExistingTeachingVoid() {
		TeachingRequirement teaching_void = getTeachingVoid();
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
		foo();
		em.flush();
		conversationStatus = ConversationStatus.EXISTING_VOID_UPDATED;
		info("existing teaching void '#0' has been succesfully updated.", teaching_void);
		setTeachingVoid(teaching_void);
	}

	public void removeExistingTeachingVoid() {
		info("trying to remove existing teaching void '#0' from school '#1'", getTeachingVoid(), getSchool());
		TeachingRequirement teaching_void = em.merge(getTeachingVoid());
		em.remove(teaching_void);
		em.flush();
		info("removed teaching void '#0' from school '#1'", teaching_void, getSchool());
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.VoidManagement#setTeachingVoid(gr.sch.ira.minoas.model.voids.TeachingRequirement)
	 */
	public void setTeachingVoid(TeachingRequirement teachingVoid) {
		this.teachingVoid = teachingVoid;
	}

	public Collection<TeachingRequirement> getTeachingVoids() {
		return teachingVoids;
	}

	public void setTeachingVoids(Collection<TeachingRequirement> teachingVoids) {
		this.teachingVoids = teachingVoids;
	}

	protected void foo() {
		TeachingRequirement teachingVoid = em.merge(this.teachingVoid);
		em.refresh(teachingVoid);
		if (teachingVoid.getTeachingResources() != null) {
			long teaching_hours = 0L;
			for (TeachingResource resource : teachingVoid.getTeachingResources()) {
				teaching_hours += resource.getTeachingHours().longValue();
			}
			teachingVoid.setTeachingHours(Long.valueOf(teaching_hours));
			info("teaching void #0 has totally #1 teaching hours registered.", teachingVoid, teachingVoid
					.getTeachingHours());
			em.persist(teachingVoid);
			info("teaching void #0 has been updated", teachingVoid);
		}
	}

}
