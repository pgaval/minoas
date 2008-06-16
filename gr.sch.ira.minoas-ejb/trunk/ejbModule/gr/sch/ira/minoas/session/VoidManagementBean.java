package gr.sch.ira.minoas.session;

import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.voids.TeachingVoid;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Out;
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
	
	@In(required=true) @Out
	private School school;
	
	@In(required=false)
	@Out(required=false)
	private TeachingVoid teachingVoid;
	
	@In
	private FacesMessages facesMessages;

	public String begin() {
		// TODO Auto-generated method stub
		return null;
	}

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
	public String end() {
		// TODO Auto-generated method stub
		return null;
	}

	public void createVoid() {
		teachingVoid = new TeachingVoid();
		teachingVoid.setSchool(school);
	}

}
