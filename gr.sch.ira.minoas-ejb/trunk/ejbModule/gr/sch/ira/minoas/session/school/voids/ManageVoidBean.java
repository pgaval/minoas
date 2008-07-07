package gr.sch.ira.minoas.session.school.voids;

import java.util.ArrayList;
import java.util.Collection;

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
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.RaiseEvent;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;

import gr.sch.ira.minoas.core.EventConstants;
import gr.sch.ira.minoas.model.TeacherType;
import gr.sch.ira.minoas.model.voids.TeachingResource;
import gr.sch.ira.minoas.model.voids.TeachingVoid;
import gr.sch.ira.minoas.session.school.BaseSchoolAware;

@Stateful
@Restrict("#{identity.loggedIn}")
@Name("manageVoid")
public class ManageVoidBean extends BaseSchoolAware implements ManageVoid {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	private Collection<TeachingResource> teachingResources;

	
	private TeachingVoid teachingVoid;

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#addTeachingResource()
	 */
	public void addTeachingResource() {
		Collection<TeachingResource> resources = teachingVoid
				.getTeachingResources();
		if (resources == null) {
			resources = new ArrayList<TeachingResource>();
			teachingVoid.setTeachingResources(resources);
		}
		TeachingResource new_resource = new TeachingResource();
		new_resource.setFillingVoid(this.teachingVoid);
		new_resource.setTeacherType(TeacherType.PERMANENT);
		new_resource.setTeachingHours(Long.valueOf(0L));
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
		setTeachingVoid(teachingVoid);
		return teachingVoid;
	}
	
	@Factory(value = "teachingResources", scope = ScopeType.CONVERSATION)
	public Collection<TeachingResource> createTeachingResources() {
		info("created teaching resources in context");
		setTeachingResources(new ArrayList<TeachingResource>());
		return getTeachingResources();
	}

	
	@End
	@RaiseEvent(EventConstants.EVENT_TEACHING_VOID_ADDED)
	public void end() {
		info(
				"trying to save new teaching void in school '#0' of specialization '#1' with total required hours equal to '#2'",
				getSchool().getTitle(), getTeachingVoid().getSpecialisation()
						.getId(), getTeachingVoid().getRequiredHours());
		em.persist(getTeachingVoid());

		if (teachingResources != null && teachingResources.size() > 0) {
			for (TeachingResource resource : teachingResources) {
				em.persist(resource);
			}
		}
		em.flush();
		info(
				"teaching void in school '#0' of specialization '#1' with total required hours equal to '#2' has been saved succesfully.",
				getSchool().getTitle(), getTeachingVoid().getSpecialisation()
						.getId(), getTeachingVoid().getRequiredHours());

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

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#setTeachingVoid(gr.sch.ira.minoas.model.voids.TeachingVoid)
	 */
	public void setTeachingVoid(TeachingVoid teachingVoid) {
		this.teachingVoid = teachingVoid;
	}

	/**
	 * @return the teachingResources
	 */
	public Collection<TeachingResource> getTeachingResources() {
		return teachingResources;
	}

	/**
	 * @param teachingResources the teachingResources to set
	 */
	public void setTeachingResources(Collection<TeachingResource> teachingResources) {
		this.teachingResources = teachingResources;
	}

}
