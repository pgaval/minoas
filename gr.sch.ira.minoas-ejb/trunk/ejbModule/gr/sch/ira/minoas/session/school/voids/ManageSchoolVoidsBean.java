package gr.sch.ira.minoas.session.school.voids;

import gr.sch.ira.minoas.core.session.CoreSearching;
import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.voids.TeachingVoid;
import gr.sch.ira.minoas.session.school.BaseSchoolAware;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
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
	private Collection<TeachingVoid> voids;

	@Remove
	@Destroy
	public void remove() {

	}

	public void search(School selectedSchool) {
		setSchool(em.merge(selectedSchool));
		info("searching for school's '#0' voids.", getSchool());
		Collection<TeachingVoid> result = coreSearching.searchVoids(
				getSchool(), null, 0);
		info("found totally '#0' voids for school '#1'", result.size(),
				getSchool());
		this.voids = result;
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageSchoolVoids#beginSchoolVoidManagement()
	 */
	@Begin(nested = true, pageflow = "manageSchoolVoids")
	public void beginSchoolVoidManagement(School selectedSchool) {
		
		
	}

}
