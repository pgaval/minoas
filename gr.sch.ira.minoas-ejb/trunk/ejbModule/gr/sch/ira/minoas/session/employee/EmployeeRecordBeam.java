/**
 * 
 */
package gr.sch.ira.minoas.session.employee;

import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.security.Restrict;

import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.seam.components.CoreSearching;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;
import gr.sch.ira.minoas.session.school.ISchoolRecord;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Name("employeeRecord")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, IEmployeeRecord.class })
public class EmployeeRecordBeam extends BaseStatefulSeamComponentImpl implements
		IEmployeeRecord {
	
	@In(value="coreSearching")
	private CoreSearching coreSearching;
	
	@In
	private EntityManager minoasDatabase;
	
	@In(required=true)
	private Employee selectedEmployee;

}
