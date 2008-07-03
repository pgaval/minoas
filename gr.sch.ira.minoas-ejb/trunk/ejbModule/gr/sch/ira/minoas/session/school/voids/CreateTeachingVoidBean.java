package gr.sch.ira.minoas.session.school.voids;

import gr.sch.ira.minoas.core.EventConstants;
import gr.sch.ira.minoas.model.TeacherType;
import gr.sch.ira.minoas.model.voids.TeachingResource;
import gr.sch.ira.minoas.model.voids.TeachingVoid;
import gr.sch.ira.minoas.session.school.BaseSchoolAware;

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
import org.jboss.seam.annotations.RaiseEvent;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;

@Stateful
@Name("createTeachingVoid")
@Restrict("#{identity.loggedIn}")
public class CreateTeachingVoidBean extends BaseSchoolAware implements
		CreateTeachingVoid {

	@In
	FacesMessages facesMessages;

	private TeachingVoid teachingVoid;

	private Collection<TeachingResource> teachingResources;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@Factory(value = "teachingVoid", scope = ScopeType.CONVERSATION)
	public TeachingVoid createTeachingVoid() {
		teachingVoid = new TeachingVoid();
		teachingVoid.setSchool(getSchool());
		teachingVoid.setRequiredHours(Long.valueOf(0));
		teachingVoid.setTeachingHours(Long.valueOf(0));
		setTeachingVoid(teachingVoid);
		info("created teaching void in context");
		return getTeachingVoid();
	}

	@Factory(value = "teachingResources", scope = ScopeType.CONVERSATION)
	public Collection<TeachingResource> createTeachingResources() {
		info("created teaching resources in context");
		setTeachingResources(new ArrayList<TeachingResource>());
		return getTeachingResources();
	}

	@Begin(nested = true, pageflow = "createTeachingVoid")
	public void begin() {
		info("conversation has begun");
		// TODO Auto-generated method stub

	}

	@End
	public void cancel() {
		// TODO Auto-generated method stub

	}

	@Remove
	@Destroy
	public void destroy() {
		// TODO Auto-generated method stub

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

	public void addTeachingResource() {
		TeachingResource resource = new TeachingResource();
		resource.setFillingVoid(getTeachingVoid());
		resource.setTeacherType(TeacherType.PERMANENT);
		resource.setTeachingHours(Long.valueOf(0));
		resource.setFillingVoid(getTeachingVoid());
		getTeachingResources().add(resource);
	}

	public void removeTeachingResource(TeachingResource teachingResource) {
		if (teachingResources != null) {
			teachingResources.remove(teachingResource);
		}
	}

	public TeachingVoid getTeachingVoid() {
		return teachingVoid;
	}

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
	 * @param teachingResources
	 *            the teachingResources to set
	 */
	public void setTeachingResources(
			Collection<TeachingResource> teachingResources) {
		this.teachingResources = teachingResources;
	}

}
