/**
 * 
 */
package gr.sch.ira.minoas.session.employment;

import gr.sch.ira.minoas.model.core.SchoolYear;
import gr.sch.ira.minoas.model.core.Unit;
import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employement.Employment;
import gr.sch.ira.minoas.model.employement.EmploymentType;
import gr.sch.ira.minoas.seam.components.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.seam.components.CoreSearching;
import gr.sch.ira.minoas.seam.components.IBaseStatefulSeamComponent;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
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
@Name("deputyEmploymentSearching")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, IDeputyEmploymentSearch.class })
public class DeputyEmploymentSearchBean extends
		BaseStatefulSeamComponentImpl implements IDeputyEmploymentSearch {

	@In
	private EntityManager minoasDatabase;
	
	@In(required=false)
	private Unit selectedSchool;

	
	@In(required=false)
	private SchoolYear defaultSchoolYear;
	
	@DataModel(value="deputyEmploymentsSearchResult", scope=ScopeType.PAGE)
	private List<Employment> deputyEmployments;
	
	@DataModelSelection 
	private Employment selectedDeputyEmployment;
	
	@Out(required=false, value="activeEmployment")
	private Employment activeEmployment;
	
	@Out(required=false, value="activeEmployee")
	private Employee activeEmployee;
	
	
	@In(value="coreSearching")
	private CoreSearching coreSearching;
	

	/**
	 * @see gr.sch.ira.minoas.session.employment.IRegularEmploymentSearch#search()
	 */
	@Factory(value="deputyEmploymentsSearchResult")
	public String search() {
		info("searching for deputy employments in school '#0' during school year '#1'.", getSchool(), getSchoolYear());
		this.deputyEmployments = coreSearching.getSchoolEmploymentsOfType(getSchoolYear(), getSchool(), EmploymentType.DEPUTY);
		return SUCCESS_OUTCOME;
	}
	
	


	/**
	 * @see gr.sch.ira.minoas.session.employment.IDeputyEmploymentSearch#getSchool()
	 */
	public Unit getSchool() {
		return this.selectedSchool;
	}


	/**
	 * @see gr.sch.ira.minoas.session.employment.IDeputyEmploymentSearch#getSchoolYear()
	 */
	public SchoolYear getSchoolYear() {
		return this.defaultSchoolYear;
	}


	/**
	 * @see gr.sch.ira.minoas.session.employment.IDeputyEmploymentSearch#setSchool(gr.sch.ira.minoas.model.core.School)
	 */
	public void setSchool(Unit school) {
		this.selectedSchool = school;
	}


	/**
	 * @see gr.sch.ira.minoas.session.employment.IDeputyEmploymentSearch#setSchoolYear(gr.sch.ira.minoas.model.core.SchoolYear)
	 */
	public void setSchoolYear(SchoolYear schoolYear) {
		this.defaultSchoolYear = schoolYear;
	}




	public String selectEmployment() {
		if(selectedDeputyEmployment!=null) {
			info("selected '#0' deputy employment.", selectedDeputyEmployment);
			this.activeEmployment = selectedDeputyEmployment;
			this.activeEmployee = selectedDeputyEmployment.getEmployee();
			return EMPLOYMENT_SELECTED_OUTCOME;
		} else return FAILURE_OUTCOME;
		
	
	}
	
	

}
