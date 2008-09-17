/**
 * 
 */
package gr.sch.ira.minoas.session.employee;

import gr.sch.ira.minoas.model.core.SchoolYear;
import gr.sch.ira.minoas.model.core.Specialization;
import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.seam.components.CoreSearching;
import gr.sch.ira.minoas.seam.components.CoreSearchingBean;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;

import java.util.List;

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
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Name("employeeSearch")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, IEmployeeSearch.class })
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class EmployeeSearchBean extends BaseStatefulSeamComponentImpl implements
		IEmployeeSearch {

	@Out(value = "Employee", required = false, scope = ScopeType.CONVERSATION)
	private Employee activeEmployee;

	@In(value = "coreSearching")
	private CoreSearching coreSearching;

	@In(required = false)
	private SchoolYear defaultSchoolYear;

	private String employeeFatherNameFilter;

	private String employeeFirstNameFilter;

	private String employeeLastNameFilter;

	private String employeeRegistryIDFilter;

	@DataModel(scope = ScopeType.PAGE, value = "employeesSearchResult")
	private List<Employee> employees;

	private String employeeVATNumberFilter;

	private Boolean employeeEmploymentFilter;

	@In
	private EntityManager minoasDatabase;

	private SchoolYear schoolYearFilter;

	private String searchString;

	@DataModelSelection
	@Out(required = false, scope = ScopeType.CONVERSATION)
	private Employee selectedEmployee;

	private Specialization specializationFilter;

	/**
	 * @see gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl#create()
	 */
	@Override
	public void create() {
		super.create();
		setEmployeeEmploymentFilter(Boolean.TRUE);
	}

	/**
	 * @see gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	/**
	 * @return the employeeFatherNameFilter
	 */
	public String getEmployeeFatherNameFilter() {
		return employeeFatherNameFilter;
	}

	/**
	 * @return the employeeFirstNameFilter
	 */
	public String getEmployeeFirstNameFilter() {
		return employeeFirstNameFilter;
	}

	/**
	 * @return the employeeLastNameFilter
	 */
	public String getEmployeeLastNameFilter() {
		return employeeLastNameFilter;
	}

	/**
	 * @return the employeeRegistryIDFilter
	 */
	public String getEmployeeRegistryIDFilter() {
		return employeeRegistryIDFilter;
	}

	/**
	 * @return the employeeVATNumberFilter
	 */
	public String getEmployeeVATNumberFilter() {
		return employeeVATNumberFilter;
	}

	public Boolean getEmployeeEmploymentFilter() {
		return this.employeeEmploymentFilter;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeSearch#getSchoolYearFilter()
	 */
	public SchoolYear getSchoolYearFilter() {
		return this.schoolYearFilter;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeSearch#getSpecializationFilter()
	 */
	public Specialization getSpecializationFilter() {
		return this.specializationFilter;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeSearch#search()
	 */
	public String search() {
		info(
				"searching for employees with matching '#0' last name, '#1' first name and '#2' father name with employment filter set to '#3'.",
				getEmployeeLastNameFilter(), getEmployeeFirstNameFilter(),
				getEmployeeFatherNameFilter(), getEmployeeEmploymentFilter());
		if (getEmployeeEmploymentFilter()) {
			employees = minoasDatabase
					.createQuery(
							"SELECT e FROM Employee e WHERE e.lastName LIKE UPPER(:lastName) AND e.firstName LIKE UPPER(:firstName) AND e.fatherName LIKE UPPER(:fatherName) AND e.currentEmployment IS NOT NULL  ORDER BY e.lastName ASC, e.firstName ASC")
					.setParameter(
							"lastName",
							CoreSearchingBean
									.getSearchPattern(getEmployeeLastNameFilter()))
					.setParameter(
							"firstName",
							CoreSearchingBean
									.getSearchPattern(getEmployeeFirstNameFilter()))
					.setParameter(
							"fatherName",
							CoreSearchingBean
									.getSearchPattern(getEmployeeFatherNameFilter()))
					.getResultList();
		} else {
			employees = minoasDatabase
					.createQuery(
							"SELECT e FROM Employee e WHERE e.lastName LIKE UPPER(:lastName) AND e.firstName LIKE UPPER(:firstName) AND e.fatherName LIKE UPPER(:fatherName) ORDER BY e.lastName ASC, e.firstName ASC")
					.setParameter(
							"lastName",
							CoreSearchingBean
									.getSearchPattern(getEmployeeLastNameFilter()))
					.setParameter(
							"firstName",
							CoreSearchingBean
									.getSearchPattern(getEmployeeFirstNameFilter()))
					.setParameter(
							"fatherName",
							CoreSearchingBean
									.getSearchPattern(getEmployeeFatherNameFilter()))
					.getResultList();
		}
		info("found #0 employee(s)", employees.size());
		return SUCCESS_OUTCOME;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeSearch#searchByRegistryID()
	 */
	public String searchByRegistryID() {
		info("searching for employee with registry ID '#0'.",
				getEmployeeRegistryIDFilter());
		employees = minoasDatabase.createQuery(
				"SELECT e FROM Employee e WHERE e.registryID=:registry_id")
				.setParameter("registry_id", getEmployeeRegistryIDFilter())
				.getResultList();
		return SUCCESS_OUTCOME;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeSearch#searchByVATNumber()
	 */
	public String searchByVATNumber() {
		info("searching for employee with VAT Number '#0'.",
				getEmployeeVATNumberFilter());
		employees = minoasDatabase.createQuery(
				"SELECT e FROM Employee e WHERE e.vatNumber=:vat_number")
				.setParameter("vat_number", getEmployeeVATNumberFilter())
				.getResultList();
		return SUCCESS_OUTCOME;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeSearch#select()
	 */
	public String select() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param employeeFatherNameFilter
	 *            the employeeFatherNameFilter to set
	 */
	public void setEmployeeFatherNameFilter(String employeeFatherNameFilter) {
		this.employeeFatherNameFilter = employeeFatherNameFilter;
	}

	/**
	 * @param employeeFirstNameFilter
	 *            the employeeFirstNameFilter to set
	 */
	public void setEmployeeFirstNameFilter(String employeeFirstNameFilter) {
		this.employeeFirstNameFilter = employeeFirstNameFilter;
	}

	/**
	 * @param employeeLastNameFilter
	 *            the employeeLastNameFilter to set
	 */
	public void setEmployeeLastNameFilter(String employeeLastNameFilter) {
		this.employeeLastNameFilter = employeeLastNameFilter;
	}

	/**
	 * @param employeeRegistryIDFilter
	 *            the employeeRegistryIDFilter to set
	 */
	public void setEmployeeRegistryIDFilter(String employeeRegistryIDFilter) {
		this.employeeRegistryIDFilter = employeeRegistryIDFilter;
	}

	/**
	 * @param employeeVATNumberFilter
	 *            the employeeVATNumberFilter to set
	 */
	public void setEmployeeVATNumberFilter(String employeeVATNumberFilter) {
		this.employeeVATNumberFilter = employeeVATNumberFilter;
	}

	public void setEmployeeEmploymentFilter(Boolean employment_filter) {
		this.employeeEmploymentFilter = employment_filter;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeSearch#setSchoolYearFilter(gr.sch.ira.minoas.model.core.SchoolYear)
	 */
	public void setSchoolYearFilter(SchoolYear school_year) {
		this.schoolYearFilter = school_year;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeSearch#setSpecializationFilter(gr.sch.ira.minoas.model.core.Specialization)
	 */
	public void setSpecializationFilter(Specialization specialization_filter) {
		this.specializationFilter = specializationFilter;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeSearch#beginEmployeeSearchConversation()
	 */
	@Begin(join = true, pageflow = "employee-search", flushMode = FlushModeType.AUTO)
	public String beginEmployeeSearchConversation() {
		info("being employee search conversation.");
		return BEGIN_OUTCOME;
	}
}
