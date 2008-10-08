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
@Name("regularEmploymentSearching")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, IRegularEmploymentSearch.class })
public class RegularEmploymentSearchBean extends
		BaseStatefulSeamComponentImpl implements IRegularEmploymentSearch {

	/**
	 * @see gr.sch.ira.minoas.session.employment.IRegularEmploymentSearch#selectEmployment()
	 */
	public String selectEmployment() {
		if(selectedRegularEmployment!=null) {
			info("selected '#0' regular employment.", selectedRegularEmployment);
			this.activeEmployment = selectedRegularEmployment;
			this.activeEmployee = selectedRegularEmployment.getEmployee();
			return EMPLOYMENT_SELECTED_OUTCOME;
		} else return FAILURE_OUTCOME;
		
	}


	@In
	private EntityManager minoasDatabase;
	
	@In(required=false)
	private Unit selectedSchool;

	
	@In(required=false)
	private SchoolYear defaultSchoolYear;
	
	@DataModel(value="regularEmploymentsSearchResult", scope=ScopeType.PAGE)
	private List<Employment> regularEmployments;
	
	@DataModelSelection 
	private Employment selectedRegularEmployment;
	
	@Out(required=false, value="activeEmployment")
	private Employment activeEmployment;
	
	@Out(required=false, value="activeEmployee")
	private Employee activeEmployee;
	
	
	@In(value="coreSearching")
	private CoreSearching coreSearching;
	

	/**
	 * @see gr.sch.ira.minoas.session.employment.IRegularEmploymentSearch#search()
	 */
	@Factory(value="regularEmploymentsSearchResult")
	public String search() {
		info("searching for regular employments in school '#0' during school year '#1'.", getSchool(), getSchoolYear());
		this.regularEmployments = coreSearching.getSchoolEmploymentsOfType(getSchoolYear(), getSchool(), EmploymentType.REGULAR);
		return SUCCESS_OUTCOME;
	}
	
	


	/**
	 * @see gr.sch.ira.minoas.session.employment.IRegularEmploymentSearch#getSchool()
	 */
	public Unit getSchool() {
		return this.selectedSchool;
	}


	/**
	 * @see gr.sch.ira.minoas.session.employment.IRegularEmploymentSearch#getSchoolYear()
	 */
	public SchoolYear getSchoolYear() {
		// TODO Auto-generated method stub
		return this.defaultSchoolYear;
	}


	/**
	 * @see gr.sch.ira.minoas.session.employment.IRegularEmploymentSearch#setSchool(gr.sch.ira.minoas.model.core.School)
	 */
	public void setSchool(Unit school) {
		this.selectedSchool = school;
	}


	/**
	 * @see gr.sch.ira.minoas.session.employment.IRegularEmploymentSearch#setSchoolYear(gr.sch.ira.minoas.model.core.SchoolYear)
	 */
	public void setSchoolYear(SchoolYear schoolYear) {
		this.defaultSchoolYear = schoolYear;
	}
	
	

}
