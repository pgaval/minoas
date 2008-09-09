/**
 * 
 */
package gr.sch.ira.minoas.session.employee;

import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employement.Employment;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
public interface IEmployeeAdmin {
	
	public String selectEmployee(Employee employee);

	public String selectEmployment(Employment employment);
	
}
