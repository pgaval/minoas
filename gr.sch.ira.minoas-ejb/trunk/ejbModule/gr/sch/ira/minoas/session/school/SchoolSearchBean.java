package gr.sch.ira.minoas.session.school;

import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.seam.components.CoreSearching;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.FlushModeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.faces.FacesMessages;

@Name("schoolSearch")
@Scope(ScopeType.CONVERSATION)
@Restrict("#{identity.loggedIn}")
@Stateful
@Local( { IBaseStatefulSeamComponent.class, ISchoolSearch.class })
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SchoolSearchBean extends BaseStatefulSeamComponentImpl implements ISchoolSearch {

	@In(value = "school", required=false)
	@Out(value = "school", required = false, scope = ScopeType.CONVERSATION)
	private School activeSchool;

	
	
	@In(value="coreSearching")
	CoreSearching coreSearching;
	
	@In
	private EntityManager minoasDatabase;

	
	@DataModel(scope = ScopeType.PAGE, value = "availableSchools")
	private List<School> schools;

	private String searchString;

	@DataModelSelection
	@Out(required = false, scope = ScopeType.CONVERSATION)
	private School selectedSchool;

	/**
	 * @see gr.sch.ira.minoas.session.school.ISchoolSearch#beginSchoolSearchConversation()
	 */
	@Begin(join=true, pageflow="school-search", flushMode=FlushModeType.AUTO)
	public String beginSchoolSearchConversation() {
		info("school search conversation begun.");
		return BEGIN_OUTCOME;
	}


	/**
	 * @return the activeSchool
	 */
	public School getActiveSchool() {
		return activeSchool;
	}

	/**
	 * @return the searchString
	 */
	public String getSearchString() {
		return this.searchString;
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.ISchoolSearch#search()
	 */
	@Factory(value="availableSchools")
	public String search() {
		schools =  coreSearching.searchShools(getSearchString());
		return SUCCESS_OUTCOME;
	}

	public String selectSchool() {
		if(selectedSchool!=null) {
			info("school #0 selected successfully.",selectedSchool);
			setActiveSchool(selectedSchool);
		}
		return SCHOOL_SELECTED_OUTCOME;
	
	}

	/**
	 * @param activeSchool the activeSchool to set
	 */
	public void setActiveSchool(School activeSchool) {
		this.activeSchool = activeSchool;
	}


	/**
	 * @param searchString the searchString to set
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

}
