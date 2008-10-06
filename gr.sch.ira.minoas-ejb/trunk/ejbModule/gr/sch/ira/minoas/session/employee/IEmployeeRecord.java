/**
 * 
 */
package gr.sch.ira.minoas.session.employee;

/**
 * @author slavikos
 *
 */
public interface IEmployeeRecord {
	public String search();
	
	public String searchEmployeeRegularEmployments();
	
	public String searchEmployeeDeputyEmployments();
	
	public String searchEmployeeSecondments();
}
