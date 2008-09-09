/**
 * 
 */
package gr.sch.ira.minoas.session.employee;

import javax.ejb.Local;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.security.Restrict;

import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employement.Employment;
import gr.sch.ira.minoas.seam.components.CoreSearching;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;
import gr.sch.ira.minoas.session.school.ISchoolRecord;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Name("employeeAdmin")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, IEmployeeAdmin.class })
public class EmployeeAdminBean extends BaseStatefulSeamComponentImpl implements
		IEmployeeAdmin {
	
	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeAdmin#selectEmployee(gr.sch.ira.minoas.model.employee.Employee)
	 */
	public String selectEmployee(Employee employee) {
		System.err.println(employee);
		this.selectedEmployee = employee;
		return SUCCESS_OUTCOME;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeAdmin#selectEmployment(gr.sch.ira.minoas.model.employement.Employment)
	 */
	public String selectEmployment(Employment employment) {
		this.selectedEmployment = minoasDatabase.merge(employment);
		this.selectedEmployee = employment.getEmployee();
		return SUCCESS_OUTCOME;
	}

	@In(value="coreSearching")
	private CoreSearching coreSearching;
	
	@In
	private EntityManager minoasDatabase;
	
	@In(required=false)
	@Out(required=false)
	private Employee selectedEmployee;

	@In(required=false)
	@Out(required=false)
	private Employment selectedEmployment;
	/**
	 * @see gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl#create()
	 */
	@Override
	@Create
	public void create() {
		// TODO Auto-generated method stub
		super.create();
	}

	/**
	 * @see gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl#destroy()
	 */
	@Override
	@Destroy
	@Remove
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
