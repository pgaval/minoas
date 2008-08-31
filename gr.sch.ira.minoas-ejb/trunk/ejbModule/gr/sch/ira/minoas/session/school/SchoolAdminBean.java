/**
 * 
 */
package gr.sch.ira.minoas.session.school;

import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.seam.components.CoreSearching;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Name("schoolAdmin")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, ISchoolAdmin.class })
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SchoolAdminBean extends BaseStatefulSeamComponentImpl implements
		ISchoolAdmin {

	@In(value = "school", create = true)
	@Out(value = "school", required = false, scope = ScopeType.CONVERSATION)
	private School activeSchool;

	@In(value="coreSearching")
	private CoreSearching coreSearching;
	
	@In
	private EntityManager minoasDatabase;

	@DataModel(scope = ScopeType.PAGE, value = "availableSchools")
	private List<School> schools;

	private String searchString;

	@DataModelSelection
	@Out(required = false, scope = ScopeType.CONVERSATION)
	private School selectedSchool;
	
	/**
	 * @see gr.sch.ira.minoas.session.school.ISchoolAdmin#cancelSchool()
	 */
	public String cancelSchool() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl#create()
	 */
	@Create
	@Override
	public void create() {
		// TODO Auto-generated method stub
		super.create();
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.ISchoolAdmin#deleteSchool()
	 */
	public String deleteSchool() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl#destroy()
	 */
	@Remove
	@Destroy
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
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
		return searchString;
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.ISchoolAdmin#newSchool()
	 */
	public String newSchool() {
		// TODO Auto-generated method stub
		return null;
	}

	

	/**
	 * @see gr.sch.ira.minoas.session.school.ISchoolAdmin#saveSchool()
	 */
	public String saveSchool() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.ISchoolAdmin#search()
	 */
	@Factory(value="availableSchools")
	public String search() {
		schools =  coreSearching.searchShools(getSearchString());
		return SUCCESS_OUTCOME;
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.ISchoolAdmin#selectSchool()
	 */
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
