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
import org.jboss.seam.annotations.End;
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
public class SchoolVoidManagementBean extends BaseSearchBean implements
		SchoolVoidManagement {

	@Logger
	private Log log;

	@EJB
	private CoreSearching coreSearching;

	@In
	FacesMessages facesMessages;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@In(required = false)
	@Out
	private School school;

	@DataModel
	private Collection<TeachingVoid> voids;

	public void schoolVoidSearch(School school) {
		// implement your business logic here
		log
				.info("schoolVoidSearch.schoolVoidSearch(school) action called with school :"
						+ school);
		facesMessages.add("schoolVoidSearch");
		setSchool(school);
		performQuery();
	}

	@Remove
	@Destroy
	public void remove() {

	}

	/**
	 * @see gr.sch.ira.minoas.voids.session.ISearchBean#performQuery()
	 */
	public void performQuery() {
		Collection<TeachingVoid> result = coreSearching.searchVoids(
				getSchool(), null, 0);
		this.voids = result;
	}

	/**
	 * @return the school
	 */
	public School getSchool() {
		return school;
	}

	/**
	 * @param school
	 *            the school to set
	 */
	public void setSchool(School school) {
		this.school = school;
	}

	@Begin(join = true)
	public void selectSchool(School selectedSchool) {
		setSchool(em.merge(selectedSchool));
		Collection<TeachingVoid> result = coreSearching.searchVoids(
				getSchool(), null, 0);
		this.voids = result;
	}

}
