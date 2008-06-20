package gr.sch.ira.minoas.session;

import gr.sch.ira.minoas.core.EventConstants;
import gr.sch.ira.minoas.model.TeacherType;
import gr.sch.ira.minoas.model.voids.TeachingResource;
import gr.sch.ira.minoas.model.voids.TeachingVoid;

import java.util.Collection;
import java.util.LinkedList;

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
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.RaiseEvent;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.core.Events;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

@Stateful
@Name("createTeachingVoid")
@Restrict("#{identity.loggedIn}")
public class CreateTeachingVoidBean extends BaseSchoolAware implements
		CreateTeachingVoid {

	@Logger
	private Log log;

	@In
	FacesMessages facesMessages;

	private TeachingVoid teachingVoid;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@Factory(value = "teachingVoid", scope = ScopeType.CONVERSATION)
	public TeachingVoid createTeachingVoid() {
		teachingVoid = new TeachingVoid();
		teachingVoid.setSchool(getSchool());
		teachingVoid.setRequiredHours(Long.valueOf(0));
		teachingVoid.setTeachingHours(Long.valueOf(0));
		setTeachingVoid(teachingVoid);
		log.info("created teaching void in context");
		return getTeachingVoid();
	}

	@Begin(nested=true, pageflow = "createTeachingVoid")
	public void begin() {
		log.info("conversation has begun");
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
		log
				.info(
						"trying to save new teaching void in school '#0' of specialization '#1' with total required hours equal to '#2'",
						getSchool().getTitle(), getTeachingVoid()
								.getSpecialisation().getId(), getTeachingVoid()
								.getRequiredHours());
		em.persist(getTeachingVoid());
		log
				.info(
						"teaching void in school '#0' of specialization '#1' with total required hours equal to '#2' has been saved succesfully.",
						getSchool().getTitle(), getTeachingVoid()
								.getSpecialisation().getId(), getTeachingVoid()
								.getRequiredHours());

	}

	public void addTeachingResource() {
		TeachingResource resource = new TeachingResource();
		resource.setTeacherType(TeacherType.PERMANENT);
		resource.setTeachingHours(Long.valueOf(0));
		if (getTeachingVoid().getTeachingResources() == null)
			getTeachingVoid().setTeachingResources(
					new LinkedList<TeachingResource>());
		getTeachingVoid().getTeachingResources().add(resource);
	}

	public void removeTeachingResource(TeachingResource teachingResource) {
		Collection<TeachingResource> resources = getTeachingVoid()
				.getTeachingResources();
		if (resources != null) {
			resources.remove(teachingResource);
		}
	}

	public TeachingVoid getTeachingVoid() {
		return teachingVoid;
	}

	public void setTeachingVoid(TeachingVoid teachingVoid) {
		this.teachingVoid = teachingVoid;
	}

}
