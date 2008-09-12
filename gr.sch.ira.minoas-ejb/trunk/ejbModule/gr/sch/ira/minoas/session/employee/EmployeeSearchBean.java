/**
 * 
 */
package gr.sch.ira.minoas.session.employee;

import gr.sch.ira.minoas.model.core.SchoolYear;
import gr.sch.ira.minoas.model.core.Specialization;
import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.seam.components.CoreSearching;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;

import java.util.ArrayList;
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
@Name("employeeSearch")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, IEmployeeSearch.class })
public class EmployeeSearchBean extends BaseStatefulSeamComponentImpl implements
		IEmployeeSearch {
	
	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeSearch#getSchoolYearFilter()
	 */
	public SchoolYear getSchoolYearFilter() {
		return this.schoolYearFilter;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeSearch#setSchoolYearFilter(gr.sch.ira.minoas.model.core.SchoolYear)
	 */
	public void setSchoolYearFilter(SchoolYear school_year) {
		this.schoolYearFilter = school_year;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeSearch#getEmploymentFilter()
	 */
	public Boolean getEmploymentFilter() {
		return employmentFilter;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeSearch#getSpecializationFilter()
	 */
	public Specialization getSpecializationFilter() {
		return this.specializationFilter;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeSearch#setEmploymentFilter(java.lang.Boolean)
	 */
	public void setEmploymentFilter(Boolean employment_filter) {
		this.employmentFilter = employment_filter;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeSearch#setSpecializationFilter(gr.sch.ira.minoas.model.core.Specialization)
	 */
	public void setSpecializationFilter(
			Specialization specialization_filter) {
		this.specializationFilter = specializationFilter;
	}

	@Out(value = "Employee", required = false, scope = ScopeType.CONVERSATION)
	private Employee activeEmployee;
	
	private Boolean employmentFilter;
	
	private Specialization specializationFilter;  

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeSearch#search()
	 */
	@Factory(value="employeesSearchResult")
	public String search() {
		employees = new ArrayList<Employee>();
		coreSearching.getAvailableRoleGroups();
		return SUCCESS_OUTCOME;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeSearch#select()
	 */
	public String select() {
		// TODO Auto-generated method stub
		return null;
	}

	@In(value="coreSearching")
	private CoreSearching coreSearching;
	
	@In
	private EntityManager minoasDatabase;
	
	@In(required=false)
	private SchoolYear defaultSchoolYear;
	
	private SchoolYear schoolYearFilter;

	@DataModel(scope = ScopeType.PAGE, value = "employeesSearchResult")
	private List<Employee> employees;

	private String searchString;

	@DataModelSelection
	@Out(required = false, scope = ScopeType.CONVERSATION)
	private Employee selectedEmployee;

	/**
	 * @see gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl#create()
	 */
	@Override
	public void create() {
		super.create();
		setEmploymentFilter(Boolean.TRUE);
	}

	/**
	 * @see gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	} 

}
