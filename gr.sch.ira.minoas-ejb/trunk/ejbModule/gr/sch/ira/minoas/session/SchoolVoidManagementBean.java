package gr.sch.ira.minoas.session;

import gr.sch.ira.minoas.core.session.CoreSearching;
import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.voids.TeachingVoid;

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
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

@Stateful
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
@Name("schoolVoidManagement")
public class SchoolVoidManagementBean extends BaseSchoolAware implements SchoolVoidManagement {

	@Begin(pageflow = "manageSchoolVoids")
	public void begin() {
		log.info("conversation has begun");
		// TODO Auto-generated method stub

	}
	
	public void end() {
	}

	@Logger
	private Log log;

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
		Collection<TeachingVoid> result = coreSearching.searchVoids(
				getSchool(), null, 0);
		this.voids = result;
	}

}
