package gr.sch.ira.minoas.session.school.voids;

import gr.sch.ira.minoas.core.session.CoreSearching;
import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.voids.TeachingResource;
import gr.sch.ira.minoas.model.voids.TeachingVoid;
import gr.sch.ira.minoas.session.school.BaseSchoolAware;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;

@Stateful
@Restrict("#{identity.loggedIn}")
@Name("manageSchoolVoids")
public class ManageSchoolVoidsBean extends BaseSchoolAware implements
		ManageSchoolVoids {
	@EJB
	private CoreSearching coreSearching;

	@In
	FacesMessages facesMessages;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@DataModel
	private Collection<TeachingVoid> teachingVoids;

	@Remove
	@Destroy
	public void remove() {

	}


	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageSchoolVoids#beginSchoolVoidManagement()
	 */
	@Begin(nested = true, pageflow = "manageSchoolVoids")
	public void beginSchoolVoidManagement() {
		info("starting school void management.");
	}
	
	public Collection<TeachingVoid> searchTeachingVoids() {
		info("searching for school's '#0' teaching voids.", getSchool());
		School school = em.merge(getSchool());
		Collection<TeachingVoid> teachingvoids = school.getVoids();
		info("found totally #0 teaching void(s) registered with school '#1'", teachingvoids.size(), getSchool());
		setTeachingVoids(teachingvoids);
		return getTeachingVoids();
	}


	public void removeTeachingVoid(TeachingVoid teachingVoid) {
	}


	public Collection<TeachingVoid> getTeachingVoids() {
		return teachingVoids;
	}


	public void setTeachingVoids(Collection<TeachingVoid> teachingVoids) {
		this.teachingVoids = teachingVoids;
	}

}
