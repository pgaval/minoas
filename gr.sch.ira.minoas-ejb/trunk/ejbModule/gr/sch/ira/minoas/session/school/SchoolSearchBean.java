package gr.sch.ira.minoas.session.school;

import gr.sch.ira.minoas.core.session.CoreSearching;
import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.session.BaseSeamComponent;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Local;
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

@Name("schoolSearch")
@Scope(ScopeType.CONVERSATION)
@Restrict("#{identity.loggedIn}")
@Stateful
@Local( { IBaseStatefulSeamComponent.class, SchoolSearch.class })
public class SchoolSearchBean extends BaseStatefulSeamComponentImpl implements SchoolSearch {

	@In
	FacesMessages facesMessages;

	@In(required = false)
	@Out(required = false, scope = ScopeType.CONVERSATION)
	private School school = null;

	@DataModel
	private Collection<School> schools;

	private @EJB
	CoreSearching coreSearching;

	private String searchString;

	public void schoolSearch() {
		this.schools = coreSearching.searchShools(getSearchPattern());
		info("searching for schools with search pattern \"#0\" returned #1 row(s).", getSearchPattern(), this.schools
				.size());

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
		return getSearchString() == null ? "%" : '%' + getSearchString().toLowerCase().replace('*', '%') + '%';
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.SchoolSearch#selectSchool(gr.sch.ira.minoas.model.core.School)
	 */
	public void selectSchool(School school) {
		this.school = school;
		info("school \"#0\" selected and is now part of the conversation.", this.school);
	}

}
