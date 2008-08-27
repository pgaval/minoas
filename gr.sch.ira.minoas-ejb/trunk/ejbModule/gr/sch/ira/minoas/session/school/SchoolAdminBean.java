/**
 * 
 */
package gr.sch.ira.minoas.session.school;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;

import gr.sch.ira.minoas.core.session.CoreSearching;
import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.core.SchoolYear;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;
import gr.sch.ira.minoas.session.ISchoolYearAdmin;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Name("schoolAdmin")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, ISchoolAdmin.class })
public class SchoolAdminBean extends BaseStatefulSeamComponentImpl implements
		ISchoolAdmin {

	private String searchString;
	
	@In(value = "school", create = true)
	@Out(value = "school", required = false, scope = ScopeType.CONVERSATION)
	private School activeSchool;

	@EJB
	private CoreSearching coreSearching;

	@PersistenceContext
	private EntityManager em;

	@DataModel(scope = ScopeType.PAGE, value = "availableSchools")
	private List<School> schools;

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
	 * @see gr.sch.ira.minoas.session.school.ISchoolAdmin#deleteSchool()
	 */
	public String deleteSchool() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.ISchoolAdmin#editSchool()
	 */
	public String editSchool() {
		// TODO Auto-generated method stub
		return null;
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
	public String search() {
		schools =  coreSearching.searchShools(getSearchString());
		return null;
	}

	/**
	 * @return the searchString
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @param searchString the searchString to set
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

}
