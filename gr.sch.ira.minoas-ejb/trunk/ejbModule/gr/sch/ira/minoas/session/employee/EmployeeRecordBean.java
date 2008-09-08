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
@Name("employeeRecord")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, IEmployeeRecord.class })
public class EmployeeRecordBean extends BaseStatefulSeamComponentImpl implements
		IEmployeeRecord {
	
	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeRecord#selectEmployee(gr.sch.ira.minoas.model.employee.Employee)
	 */
	public String selectEmployee(Employee employee) {
		System.err.println(employee);
		return null;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeRecord#selectEmployment(gr.sch.ira.minoas.model.employement.Employment)
	 */
	public String selectEmployment(Employment employment) {
		System.err.println(employment);
		return null;
	}

	@In(value="coreSearching")
	private CoreSearching coreSearching;
	
	@In
	private EntityManager minoasDatabase;
	
	@In(required=false)
	@Out
	private Employee selectedEmployee;

	
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
