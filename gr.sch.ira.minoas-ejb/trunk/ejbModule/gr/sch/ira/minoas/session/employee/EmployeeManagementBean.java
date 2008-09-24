/**
 * 
 */
package gr.sch.ira.minoas.session.employee;

import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employement.Employment;
import gr.sch.ira.minoas.seam.components.CoreSearching;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;

import javax.ejb.Local;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.security.Restrict;



/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Name("employeeManagement")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, IEmployeeManagement.class })
public class EmployeeManagementBean extends BaseStatefulSeamComponentImpl implements
		IEmployeeManagement {
	
	@In(required=false)
	@Out(required=false)
	private Employee activeEmployee;

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeManagement#beginEmployeeAdminConversation()
	 */
	@Begin(nested=true, pageflow="employee-management")
	public String beginEmployeeManagementConversation() {
		info("employee management conversation begun.");
		return BEGIN_OUTCOME;
	}
	
	@In(value="coreSearching")
	private CoreSearching coreSearching;
	
	@In
	private EntityManager minoasDatabase;
	
	/**
	 * @see gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl#create()
	 */
	@Override
	@Create
	public void create() {
		super.create();
	}

	/**
	 * @see gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl#destroy()
	 */
	@Override
	@Destroy
	@Remove
	public void destroy() {
		super.destroy();
	}
	

	/**
	 * @return the activeEmployee
	 */
	public Employee getActiveEmployee() {
		return activeEmployee;
	}

	/**
	 * @param activeEmployee the activeEmployee to set
	 */
	public void setActiveEmployee(Employee activeEmployee) {
		this.activeEmployee = activeEmployee;
	}

	
}
