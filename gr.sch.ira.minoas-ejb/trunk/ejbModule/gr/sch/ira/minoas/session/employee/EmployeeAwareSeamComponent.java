/**
 * 
 */
package gr.sch.ira.minoas.session.employee;

import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.seam.components.BaseStatefulSeamComponentImpl;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;

/**
 * @author slavikos
 *
 */
public class EmployeeAwareSeamComponent extends BaseStatefulSeamComponentImpl implements IEmployeeAware {
	
	@In(value="activeEmployee", required=false)
	@Out(value = "activeEmployee", required = false, scope = ScopeType.CONVERSATION)
	private Employee activeEmployee;

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeAware#setActiveEmployee(gr.sch.ira.minoas.model.employee.Employee)
	 */
	public void setActiveEmployee(Employee activeEmployee) {
		this.activeEmployee = activeEmployee;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeAware#getActiveEmployee()
	 */
	public Employee getActiveEmployee() {
		return activeEmployee;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeAware#hasActiveEmployee()
	 */
	public boolean hasActiveEmployee() {
		return getActiveEmployee()!=null;
	}
	
}
