/**
 * 
 */
package gr.sch.ira.minoas.session.employee;

import gr.sch.ira.minoas.model.core.SchoolYear;
import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employement.Employment;
import gr.sch.ira.minoas.model.employement.Secondment;
import gr.sch.ira.minoas.model.employement.SecondmentType;
import gr.sch.ira.minoas.seam.components.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.seam.components.CoreSearching;
import gr.sch.ira.minoas.seam.components.IBaseStatefulSeamComponent;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Local;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.FlushModeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.TransactionPropagationType;
import org.jboss.seam.annotations.Transactional;
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
public class EmployeeManagementBean extends BaseStatefulSeamComponentImpl
		implements IEmployeeManagement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private Employee activeEmployee;


	@In(value = "coreSearching")
	private CoreSearching coreSearching;

	@Out(required = false)
	private Secondment employeeActiveSecondment;

	@Out(required = false)
	private Secondment newSecondment;

	@Out(required = false)
	private SecondmentType selectedSecondmentType;

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeManagement#beginEmployeeAdminConversation()
	 */
	@Begin(flushMode = FlushModeType.MANUAL, nested = true, pageflow = "employee-management")
	public String beginEmployeeManagementConversation() {
		info("employee '#0' management conversation begun.",
				getActiveEmployee());
		return BEGIN_OUTCOME;
	}

	@Begin(flushMode = FlushModeType.MANUAL, nested = true, pageflow = "new-secondment")
	public void beginEmployeeNewSecondment() {
		info("a new secondment, during school year #0,  conversation has begun.",getActiveSchoolYear());
	}

	public boolean hasActiveEmployee() {
		return activeEmployee != null;
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.BaseStatefulSeamComponentImpl#create()
	 */
	@Override
	@Create
	public void create() {
		super.create();
	}

	public String prepareNewSecondmet() {
		/*
		 * check if the employee has already an active secondment
		 */
		setEmployeeActiveSecondment(coreSearching
				.getEmployeeActiveSecondment(getActiveEmployee()));
		if (getEmployeeActiveSecondment() != null) {
			warn("employee '#0' has already an active secondment '#1'.",
					getActiveEmployee(), getEmployeeActiveSecondment());
		}
		/* prepare the new secondment object */

		/* fill the obvious values */
		newSecondment = new Secondment();
		newSecondment.setEmployeeRequested(false);
		newSecondment.setActive(Boolean.TRUE);
		newSecondment.setSupersededBy(null);
		newSecondment.setSchoolYear(getActiveSchoolYear());
		newSecondment.setEmployeeRequested(Boolean.TRUE);
		newSecondment.setEmployee(getActiveEmployee());
		newSecondment.setEstablished(new Date((Calendar
				.getInstance(greekLocale)).getTimeInMillis()));
		Calendar dueTo = Calendar.getInstance(greekLocale);
		dueTo.set(Calendar.MONTH, Calendar.JUNE);
		dueTo.set(Calendar.DAY_OF_MONTH, 30);
		newSecondment.setDueTo(new Date(dueTo.getTimeInMillis()));

		/* find the employee's current employment */
		Collection<Employment> activeEmployments = coreSearching
				.getEmployeeActiveEmployments(getActiveEmployee());
		if (activeEmployments.size() != 1) {
			/*
			 * for a secondment to be valid, the employee in question needs to
			 * have exactly one active employment.
			 */
			warn(
					"new secondment for employee '#0' with ID '#1' is not possible, since the employee in question needs to have exactly one active employment and not #2 employment(s)",
					getActiveEmployee(), getActiveEmployee().getId(),
					activeEmployments.size());

			getFacesMessages()
					.addFromResourceBundle(
							FacesMessage.SEVERITY_ERROR,
							"EmployeeManagementBean.ONE_EMPLOYMENT_NEEDED_FOR_SECONDMENT",
							getActiveEmployee());
			return FAILURE_OUTCOME;
		}
		Employment affected_employment = activeEmployments.iterator().next();
		
		newSecondment.setAffectedEmployment(affected_employment);
		newSecondment
				.setSourcePYSDE(getMinoasDatabase().merge(affected_employment.getSchool().getPysde()));
		newSecondment.setSourceUnit(getMinoasDatabase().merge(affected_employment.getSchool()));

		/*
		 * adjust working hours by retrieving the mandatory working hours from
		 * the affected employment. When copying do not copy any decrement
		 * since, this can be done later on.
		 */
		newSecondment.setFinalWorkingHours(affected_employment
				.getMandatoryWorkingHours());

		return SUCCESS_OUTCOME;
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
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeManagement#endEmployeeManagementConversation()
	 */
	@End(beforeRedirect = true)
	public String endEmployeeManagementConversation() {
		info("employee '#0' management conversation ended.",
				getActiveEmployee());
		return END_OUTCOME;
	}

	@End
	public String endEmployeeNewSecondment() {
		info(
				"new secondment '#2'conversation ended for employee '#0' during school year '#1'.",
				getActiveEmployee(), getActiveSchoolYear(), newSecondment);
		newSecondment=null;
		return END_OUTCOME;
	}

	/**
	 * @return the activeEmployee
	 */
	public Employee getActiveEmployee() {
		return activeEmployee;
	}

	

	/**
	 * @return the employeeActiveSecondment
	 */
	public Secondment getEmployeeActiveSecondment() {
		return employeeActiveSecondment;
	}

	/**
	 * @see gr.sch.ira.minoas.session.employee.IEmployeeManagement#saveSecondment()
	 */
	public String saveSecondment() {
		/* check */
		info("trying to save secondment #0", this.newSecondment);
		if (getEmployeeActiveSecondment() != null) {
			Secondment activeSecondment = getEmployeeActiveSecondment();
			activeSecondment.setActive(Boolean.FALSE);
			activeSecondment.setSupersededBy(newSecondment);
			getMinoasDatabase().merge(activeSecondment);
		}
		newSecondment.setInsertedOn(new Date(System.currentTimeMillis()));
		if (newSecondment.getTargetUnit() != null) {
			newSecondment.setTargetPYSDE(newSecondment.getTargetUnit()
					.getPysde());
		}
		setEmployeeActiveSecondment(newSecondment);
		getMinoasDatabase().persist(newSecondment);
		Employment employment = getMinoasDatabase().merge(
				newSecondment.getAffectedEmployment());
		employment.setSecondment(newSecondment);
		getMinoasDatabase().flush();
		return SUCCESS_OUTCOME;
	}

	public String secondmentTypeSelected() {
		info("lalalalala #0", newSecondment.getSecondmentType());
		selectedSecondmentType = newSecondment.getSecondmentType();
		return SUCCESS_OUTCOME;
	}

	/**
	 * @param activeEmployee
	 *            the activeEmployee to set
	 */
	public void setActiveEmployee(Employee activeEmployee) {
		this.activeEmployee = activeEmployee;
	}

	

	/**
	 * @param employeeActiveSecondment
	 *            the employeeActiveSecondment to set
	 */
	public void setEmployeeActiveSecondment(Secondment employeeActiveSecondment) {
		this.employeeActiveSecondment = employeeActiveSecondment;
	}

}
