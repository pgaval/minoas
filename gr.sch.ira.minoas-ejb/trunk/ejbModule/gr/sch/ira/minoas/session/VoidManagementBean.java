package gr.sch.ira.minoas.session;

import gr.sch.ira.minoas.core.EventConstants;
import gr.sch.ira.minoas.model.TeacherType;
import gr.sch.ira.minoas.model.core.School;
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
import org.jboss.seam.annotations.RaiseEvent;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

@Stateful
@Name("voidManagement")
public class VoidManagementBean implements VoidManagement {

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@Begin
	public void selectSchool(School selectedSchool) {
		school = em.merge(selectedSchool);
		
	}

	@Logger
	private Log log;
	
	@In(required=false)
	private School school;
	
	private TeachingVoid teachingVoid;
	
	
	
	@In
	private FacesMessages facesMessages;

	@Begin(pageflow="createTeachingVoid")
	public String begin() {
		// TODO Auto-generated method stub
		return null;
	}

	@End
	public String cancel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Remove
	@Destroy
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@End
	@RaiseEvent(EventConstants.EVENT_TEACHING_VOID_MODIFIED)
	public String end() {
		log.info("trying to store void of specialization #0 for school #1 with totally #2 teaching hours requirment.", getTeachingVoid().getSpecialisation(), getTeachingVoid().getSchool(), getTeachingVoid().getRequiredHours());
		TeachingVoid t_void = getTeachingVoid();
		long t_hours = t_void.getTeachingHours().longValue();
		for (TeachingResource r : t_void.getTeachingResources()) {
			t_hours = r.getTeachingHours().longValue();
		}
		t_void.setTeachingHours(Long.valueOf(t_hours));
		em.persist(t_void); 
		return "success";
	}
	
	

	/**
	 * @see gr.sch.ira.minoas.session.VoidManagement#addTeachingResource()
	 */
	public void addTeachingResource() {
		TeachingResource resource = new TeachingResource();
		resource.setTeacherType(TeacherType.PERMANENT);
		resource.setTeachingHours(Long.valueOf(0));
		if(getTeachingVoid().getTeachingResources()==null)
			getTeachingVoid().setTeachingResources(new LinkedList<TeachingResource>());
		getTeachingVoid().getTeachingResources().add(resource);
	}

	/**
	 * @see gr.sch.ira.minoas.session.VoidManagement#removeTeachingResource(gr.sch.ira.minoas.model.voids.TeachingResource)
	 */
	public void removeTeachingResource(TeachingResource teachingResource) {
		Collection<TeachingResource> resources = getTeachingVoid().getTeachingResources();
		if(resources!=null) {
			resources.remove(teachingResource);
		}
		
	}

	/**
	 * @see gr.sch.ira.minoas.session.VoidManagement#setTeachingVoid(gr.sch.ira.minoas.model.voids.TeachingVoid)
	 */
	public void setTeachingVoid(TeachingVoid teachingVoid) {
		this.teachingVoid = teachingVoid;
	}

	/**
	 * @see gr.sch.ira.minoas.session.VoidManagement#getTeachingVoid()
	 */
	public TeachingVoid getTeachingVoid() {
		return this.teachingVoid;
	}
	
	@Factory(value="teachingVoid",  scope=ScopeType.CONVERSATION)
	public TeachingVoid createTeachingVoid() {
		teachingVoid = new TeachingVoid();
		teachingVoid.setSchool(school);
		teachingVoid.setRequiredHours(Long.valueOf(0));
		teachingVoid.setTeachingHours(Long.valueOf(0));
		setTeachingVoid(teachingVoid);
		log.info("created teaching void in context");
		return getTeachingVoid();
	}
	
}
