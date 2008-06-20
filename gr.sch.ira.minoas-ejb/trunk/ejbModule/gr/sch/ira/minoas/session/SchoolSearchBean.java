package gr.sch.ira.minoas.session;

import gr.sch.ira.minoas.core.session.CoreSearching;
import gr.sch.ira.minoas.model.core.School;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
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
@Name("schoolSearch")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class SchoolSearchBean extends BaseSchoolAware implements SchoolSearch {

	@Logger
	private Log log;

	@In
	FacesMessages facesMessages;

	@PersistenceContext
	private EntityManager em;
	
	@Begin(pageflow="selectSchool")
	public void begin() {
	}

	@End
	public void end() {
	}

	@DataModel
	private Collection<School> schools;

	private @EJB CoreSearching coreSearching;
	
	private String searchString;

	public void schoolSearch() {
		this.schools = coreSearching.searchShools(getSearchPattern());
		log.info("searching for schools with search pattern \"#0\" returned #1 row(s)." , getSearchPattern(), this.schools.size());
		
	}

	@Remove
	@Destroy
	public void remove() {
		schools = null;
	}

	/**
	 * @return the searchString
	 */
	public String getSearchString() {
		return this.searchString;
	}

	/**
	 * @param searchString
	 *            the searchString to set
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	@Factory(value = "pattern", scope = ScopeType.EVENT)
	public String getSearchPattern() {
		return getSearchString() == null ? "%" : '%' + getSearchString()
				.toLowerCase().replace('*', '%') + '%';
	}

	@End
	public void selectSchool(School school) {
		log.info("selected school \"#0\".", school.getTitle());
		setSchool(school);
	}

}
