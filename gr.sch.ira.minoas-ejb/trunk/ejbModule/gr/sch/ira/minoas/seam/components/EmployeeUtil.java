/**
 * 
 */
package gr.sch.ira.minoas.seam.components;

import gr.sch.ira.minoas.model.employee.DeputyEmployee;
import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employee.HourlyPaidEmployee;
import gr.sch.ira.minoas.model.employee.RegularEmployee;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Name("employeeUtil")
@Scope(ScopeType.EVENT)
public class EmployeeUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getRegistryID(Employee employee) {
		if (employee instanceof RegularEmployee) {
			return ((RegularEmployee) employee).getRegistryID();
		} else
			return null;
	}

	public boolean isRegular(Employee employee) {
		return employee instanceof RegularEmployee;
	}

	public boolean isDeputy(Employee employee) {
		return employee instanceof DeputyEmployee;
	}

	public boolean isHourlyPaid(Employee employee) {
		return employee instanceof HourlyPaidEmployee;
	}
}
