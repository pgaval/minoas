/**
 * 
 */
package gr.sch.ira.minoas.session.employee;

import gr.sch.ira.minoas.model.core.SchoolYear;
import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employement.Secondment;
import gr.sch.ira.minoas.model.employement.SecondmentType;
import gr.sch.ira.minoas.seam.components.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.seam.components.CoreSearching;
import gr.sch.ira.minoas.seam.components.IBaseStatefulSeamComponent;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.Local;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Unwrap;
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
	
	@Out(required=false)
	private Secondment newSecondment;
	
	@Out(required=false)
	private SecondmentType selectedSecondmentType;
	
	
	
	private SchoolYear activeSchoolYear;

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeManagement#beginEmployeeAdminConversation()
	 */
	@Begin(nested=true, pageflow="employee-management")
	public String beginEmployeeManagementConversation() {
		info("employee '#0' management conversation begun.", getActiveEmployee());
		return BEGIN_OUTCOME;
	}
	
	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeManagement#endEmployeeManagementConversation()
	 */
	@End(beforeRedirect=true)
	public String endEmployeeManagementConversation() {
		info("employee '#0' management conversation ended.", getActiveEmployee());
		return END_OUTCOME;
	}
	
	@End(beforeRedirect=true)
	public String endEmployeeNewSecondment() {
		info("new secondment '#2'conversation ended for employee '#0' during school year '#1'.", getActiveEmployee(), getActiveSchoolYear(), newSecondment);
		return END_OUTCOME;
	}

	@In(value="coreSearching")
	private CoreSearching coreSearching;
	

	
	/**
	 * @see gr.sch.ira.minoas.seam.components.BaseStatefulSeamComponentImpl#create()
	 */
	@Override
	@Create
	public void create() {
		super.create();
		activeSchoolYear = coreSearching.getActiveSchoolYear();
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.BaseStatefulSeamComponentImpl#destroy()
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

	
	public String secondmentTypeSelected() {
		selectedSecondmentType = newSecondment.getSecondmentType();
		debug("lalalalala #0", newSecondment.getSecondmentType());
		return SUCCESS_OUTCOME;
	}
	
	@Begin(join=true)
	public String beginEmployeeNewSecondment() {
		info("new secondment conversation begun for employee '#0' during school year '#1'.", getActiveEmployee(), getActiveSchoolYear());
		/* prepare the new secondment object */
		
		newSecondment = new Secondment();
		newSecondment.setEmployeeRequested(false);
		newSecondment.setSchoolYear(getActiveSchoolYear());
		newSecondment.setEmployeeRequested(Boolean.TRUE);
		newSecondment.setEmployee(getActiveEmployee());
		
		newSecondment.setEstablished(new Date((Calendar.getInstance(greekLocale)).getTimeInMillis()));
		
		Calendar dueTo = Calendar.getInstance(greekLocale);
		dueTo.set(Calendar.MONTH, Calendar.JUNE);
		dueTo.set(Calendar.DAY_OF_MONTH, 30);
		newSecondment.setDueTo(new Date(dueTo.getTimeInMillis()));
		return BEGIN_OUTCOME;
	}

	public SchoolYear getActiveSchoolYear() {
		return activeSchoolYear;
	}

	public void setActiveSchoolYear(SchoolYear activeSchoolYear) {
		this.activeSchoolYear = activeSchoolYear;
	}

	
}
