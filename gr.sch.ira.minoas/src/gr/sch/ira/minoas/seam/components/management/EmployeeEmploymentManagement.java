package gr.sch.ira.minoas.seam.components.management;

import gr.sch.ira.minoas.model.core.AuditType;
import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employee.EmployeeType;
import gr.sch.ira.minoas.model.employement.Employment;
import gr.sch.ira.minoas.model.employement.EmploymentType;
import gr.sch.ira.minoas.seam.components.BaseDatabaseAwareSeamComponent;
import gr.sch.ira.minoas.seam.components.CoreSearching;
import gr.sch.ira.minoas.seam.components.home.EmployeeHome;
import gr.sch.ira.minoas.seam.components.home.EmploymentHome;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.RaiseEvent;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.TransactionPropagationType;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.international.StatusMessage.Severity;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * @version $Id$
 */
@Scope(ScopeType.PAGE)
@Name(value = "employeeEmploymentManagement")
public class EmployeeEmploymentManagement extends BaseDatabaseAwareSeamComponent {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	@In(required = false)
	EmployeeHome employeeHome;

	@In(required = false)
	EmploymentHome employmentHome;

	@DataModel(value = "schoolYearEmployments")
	private Collection<Employment> schoolYearEmployments;

	@DataModel(value = "employments")
	private Collection<Employment> employments;

	@In(required = true, create = true)
	private CoreSearching coreSearching;

	@DataModel(value = "oldEmployments")
	private Collection<Employment> oldEmployments;

	/**
	 * @return the employeeHome
	 */
	public EmployeeHome getEmployeeHome() {
		return employeeHome;
	}

	/**
	 * @param employeeHome the employeeHome to set
	 */
	public void setEmployeeHome(EmployeeHome employeeHome) {
		this.employeeHome = employeeHome;
	}

	/**
	 * @return the employmentHome
	 */
	public EmploymentHome getEmploymentHome() {
		return employmentHome;
	}

	/**
	 * @param employmentHome the employmentHome to set
	 */
	public void setEmploymentHome(EmploymentHome employmentHome) {
		this.employmentHome = employmentHome;
	}

	/**
	 * @return the schoolYearEmployments
	 */
	public Collection<Employment> getSchoolYearEmployments() {
		return schoolYearEmployments;
	}

	/**
	 * @param schoolYearEmployments the schoolYearEmployments to set
	 */
	public void setSchoolYearEmployments(List<Employment> schoolYearEmployments) {
		this.schoolYearEmployments = schoolYearEmployments;
	}

	/**
	 * @return the employments
	 */
	public Collection<Employment> getEmployments() {
		return employments;
	}

	@Factory(value = "schoolYearEmployments")
	public void initializeSchoolYearEmployeeEmployments() {
		if (employeeHome != null & employeeHome.isManaged()) {
			EmploymentType employmentType = null;
			switch (employeeHome.getInstance().getType()) {
			case DEPUTY:
				employmentType = EmploymentType.DEPUTY;
				break;
			case HOURLYPAID:
				employmentType = EmploymentType.HOURLYBASED;
				break;
			case REGULAR:
				employmentType = EmploymentType.REGULAR;
				break;
			}
			this.schoolYearEmployments = getCoreSearching().getEmployeeEmploymentsOfType(employeeHome.getInstance(),
					employmentType, getCoreSearching().getActiveSchoolYear(getEntityManager()));
		}
	}

	@Factory(value = "oldEmployments")
	public void initializeOldEmployments() {
		if (employeeHome != null & employeeHome.isManaged()) {
			EmploymentType employmentType = null;
			switch (employeeHome.getInstance().getType()) {
			case DEPUTY:
				employmentType = EmploymentType.DEPUTY;
				break;
			case HOURLYPAID:
				employmentType = EmploymentType.HOURLYBASED;
				break;
			case REGULAR:
				employmentType = EmploymentType.REGULAR;
				break;
			}
			this.oldEmployments = getCoreSearching().getAllEmployeeEmploymentsOfType(employeeHome.getInstance(),
					employmentType);
		}
	}

	@Factory(value = "employments")
	public void initializeEmployeeEmployments() {
		this.employments = getCoreSearching().getEmployeeEmploymentsOfType(employeeHome.getInstance(),
				EmploymentType.HOURLYBASED);
	}

	/**
	 * @param employments the employments to set
	 */
	public void setEmployments(Collection<Employment> employments) {
		this.employments = employments;
	}

	@Transactional(TransactionPropagationType.REQUIRED)
	@RaiseEvent("employeeEmploymentDeleted")
	public String deleteEmployment() {
		Employment employment = employmentHome != null ? employmentHome.getInstance() : null;
		Employee employee = employeeHome != null ? employeeHome.getInstance() : null;
		if (employmentHome != null && employmentHome.isManaged()) {
			employment.setActive(Boolean.FALSE);
			employment.setDeleted(Boolean.TRUE);
			employment.setDeletedBy(getPrincipal());
			employment.setDeletedOn(new Date());
			Employment currentEmployeeEmployment = employee.getCurrentEmployment();
			if(currentEmployeeEmployment!=null && currentEmployeeEmployment.getId().equals(employment.getId())) {
				employee.setCurrentEmployment(null);
			}
			String result = employmentHome.update();
			
				initializeEmployeeEmployments();
				initializeSchoolYearEmployeeEmployments();
				initializeOldEmployments();
				logAudit(AuditType.UPDATE, "Ακύρωση της σχέσης "+employment+" του εκπαιδευτικού "+employee+".");	
			info("canceled employment #0 for employee #1", employment, employee);
			employmentHome.clearInstance();
			return result;
		} else {
			error("trying to delete a NULL or transient employment #0 for employee #1", employment, employee);
			return null;
		}

	}
	
	@Transactional(TransactionPropagationType.REQUIRED)
	@RaiseEvent("employeeEmploymentDisabled")
	public String terminatelEmployment() {
		Employment employment = employmentHome != null ? employmentHome.getInstance() : null;
		Employee employee = employeeHome != null ? employeeHome.getInstance() : null;
		if (employmentHome != null && employmentHome.isManaged()) {
			employment.setActive(Boolean.FALSE);
			Employment currentEmployeeEmployment = employee.getCurrentEmployment();
			if(currentEmployeeEmployment!=null && currentEmployeeEmployment.getId().equals(employment.getId())) {
				employee.setCurrentEmployment(null);
			}
			String result = employmentHome.update();
			
				initializeEmployeeEmployments();
				initializeSchoolYearEmployeeEmployments();
				initializeOldEmployments();
			
			info("canceled employment #0 for employee #1", employment, employee);
			logAudit(AuditType.UPDATE, "Τερματισμός της σχέσης "+employment+" του εκπαιδευτικού "+employee+".");
			employmentHome.clearInstance();
			return result;
		} else {
			error("trying to delete a NULL or transient employment #0 for employee #1", employment, employee);
			return null;
		}

	}

	@Transactional(TransactionPropagationType.REQUIRED)
	@RaiseEvent("employeeEmploymentUpdated")
	public String updateEmployment() {
		if (getEmploymentHome().isManaged() && getEmployeeHome().isManaged()) {
			return employmentHome.update();
		} else {
			getFacesMessages().add(Severity.ERROR, "employee home or employment home is not managed", (Object[]) null);
			return null;
		}
	}

	@Transactional(TransactionPropagationType.REQUIRED)
	@RaiseEvent("employeeEmploymentCreated")
	public String createEmployment() {
		if (getEmployeeHome().isManaged()) {
			if (!getEmploymentHome().isManaged()) {
				String result = employmentHome.persist();
				employeeHome.getInstance().setCurrentEmployment(employmentHome.getInstance());
				employeeHome.update();
				initializeEmployeeEmployments();
				initializeSchoolYearEmployeeEmployments();
				initializeOldEmployments();
				return result;
			} else {
				getFacesMessages().add(Severity.ERROR, "employment home is managed", (Object[]) null);
				return null;
			}
		} else {
			getFacesMessages().add(Severity.ERROR, "employee home or is not managed", (Object[]) null);
			return null;
		}
	}

	@Transactional(TransactionPropagationType.REQUIRED)
	public String prepareForNewHourlyBasedEmployment() {
		if (getEmployeeHome().isManaged()) {
			EmploymentHome employmentHome = getEmploymentHome();
			employmentHome.clearInstance();
			Employment e = employmentHome.getInstance();
			e.setEmployee(getEmployeeHome().getInstance());
			e.setActive(Boolean.TRUE);
			e.setSchoolYear(getCoreSearching().getActiveSchoolYear(getEntityManager()));
			e.setType(EmploymentType.HOURLYBASED);
			e.setEstablished(new Date());
			e.setFinalWorkingHours(11);
			e.setMandatoryWorkingHours(11);
			e.setSpecialization(getEmployeeHome().getInstance().getLastSpecialization());
			return "prepared";
		} else {
			getFacesMessages().add(Severity.ERROR, "employee home or is not managed", (Object[]) null);
			return null;
		}
	}

	@Transactional(TransactionPropagationType.REQUIRED)
	public String prepareForNewDeputyEmployment() {
		if (getEmployeeHome().isManaged()) {
			EmploymentHome employmentHome = getEmploymentHome();
			employmentHome.clearInstance();
			Employment e = employmentHome.getInstance();
			e.setEmployee(getEmployeeHome().getInstance());
			e.setActive(Boolean.TRUE);
			e.setSchoolYear(getCoreSearching().getActiveSchoolYear(getEntityManager()));
			e.setType(EmploymentType.DEPUTY);
			e.setEstablished(new Date());
			e.setFinalWorkingHours(21);
			e.setMandatoryWorkingHours(21);
			
			/* be helpfull and calculate the end date */
			e.setTerminated(e.getSchoolYear().getTeachingSchoolYearStop());
			e.setSpecialization(getEmployeeHome().getInstance().getLastSpecialization());
			return "prepared";
		} else {
			getFacesMessages().add(Severity.ERROR, "employee home or is not managed", (Object[]) null);
			return null;
		}
	}
	
	@Transactional(TransactionPropagationType.REQUIRED)
	public String prepareForNewRegularEmployment() {
		if (getEmployeeHome().isManaged()) {
			if(getEmployeeHome().getInstance().getCurrentEmployment()==null) {
				EmploymentHome employmentHome = getEmploymentHome();
				employmentHome.clearInstance();
				Employment e = employmentHome.getInstance();
				e.setEmployee(getEmployeeHome().getInstance());
				e.setActive(Boolean.TRUE);
				e.setSchoolYear(getCoreSearching().getActiveSchoolYear(getEntityManager()));
				e.setType(EmploymentType.REGULAR);
				e.setEstablished(new Date());
				e.setMandatoryWorkingHours(21);
				/* be helpfull and calculate the end date */
				e.setTerminated(e.getSchoolYear().getTeachingSchoolYearStop());
				e.setSpecialization(getEmployeeHome().getInstance().getLastSpecialization());
				return "prepared";
			} else {
				getFacesMessages().add(Severity.ERROR, "Ο Εκπαιδευτικός έχει ενεργή σχέση εργασίας", (Object[]) null);
				return null;
			}
			
		} else {
			getFacesMessages().add(Severity.ERROR, "employee home or is not managed", (Object[]) null);
			return null;
		}
	}

	/**
	 * @return the coreSearching
	 */
	public CoreSearching getCoreSearching() {
		return coreSearching;
	}

	/**
	 * @param coreSearching the coreSearching to set
	 */
	public void setCoreSearching(CoreSearching coreSearching) {
		this.coreSearching = coreSearching;
	}

	/**
	 * @return the oldEmployments
	 */
	public Collection<Employment> getOldEmployments() {
		return oldEmployments;
	}

	/**
	 * @param oldEmployments the oldEmployments to set
	 */
	public void setOldEmployments(Collection<Employment> oldEmployments) {
		this.oldEmployments = oldEmployments;
	}
}
