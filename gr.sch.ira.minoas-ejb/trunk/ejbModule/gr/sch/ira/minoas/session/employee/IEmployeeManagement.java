/**
 * 
 */
package gr.sch.ira.minoas.session.employee;

import gr.sch.ira.minoas.model.employement.SecondmentType;


/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
public interface IEmployeeManagement {
	
	public String prepareNewSecondmet();
	
	public String secondmentTypeSelected();
	
	public String saveSecondment();
	
	public boolean hasActiveEmployee();
	
}
