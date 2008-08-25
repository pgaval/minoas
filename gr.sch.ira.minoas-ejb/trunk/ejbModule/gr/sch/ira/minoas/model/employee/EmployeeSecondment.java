/**
 * 
 */
package gr.sch.ira.minoas.model.employee;

import gr.sch.ira.minoas.model.BaseModel;
import gr.sch.ira.minoas.model.core.SchoolYear;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Entity
@Table(name = "minoas_secondment")
public class EmployeeSecondment extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	@Basic
	@Column(name = "employee_requested")
	private boolean employeeRequested;

	@Basic
	@Column(name = "start_date")
	private Date startDate;

	@Basic
	@Column(name = "end_date")
	private Date endDate;

	@SuppressWarnings("unused")
	@Version
	private Timestamp version;

	@Basic
	private Date recovationDate;

	@Basic
	private SchoolYear schoolYear;

	@Basic
	private String decisionNumber;

	@Basic
	private String recovationDecisionNumber;

	/**
	 * @return the employeeRequested
	 */
	public boolean isEmployeeRequested() {
		return employeeRequested;
	}

	/**
	 * @param employeeRequested the employeeRequested to set
	 */
	public void setEmployeeRequested(boolean employeeRequested) {
		this.employeeRequested = employeeRequested;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the recovationDate
	 */
	public Date getRecovationDate() {
		return recovationDate;
	}

	/**
	 * @param recovationDate the recovationDate to set
	 */
	public void setRecovationDate(Date recovationDate) {
		this.recovationDate = recovationDate;
	}

	/**
	 * @return the schoolYear
	 */
	public SchoolYear getSchoolYear() {
		return schoolYear;
	}

	/**
	 * @param schoolYear the schoolYear to set
	 */
	public void setSchoolYear(SchoolYear schoolYear) {
		this.schoolYear = schoolYear;
	}

	/**
	 * @return the decisionNumber
	 */
	public String getDecisionNumber() {
		return decisionNumber;
	}

	/**
	 * @param decisionNumber the decisionNumber to set
	 */
	public void setDecisionNumber(String decisionNumber) {
		this.decisionNumber = decisionNumber;
	}

	/**
	 * @return the recovationDecisionNumber
	 */
	public String getRecovationDecisionNumber() {
		return recovationDecisionNumber;
	}

	/**
	 * @param recovationDecisionNumber the recovationDecisionNumber to set
	 */
	public void setRecovationDecisionNumber(String recovationDecisionNumber) {
		this.recovationDecisionNumber = recovationDecisionNumber;
	}

}
