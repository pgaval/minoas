

package gr.sch.ira.minoas.model.employement;

import gr.sch.ira.minoas.model.BaseModel;
import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.core.SchoolYear;
import gr.sch.ira.minoas.model.employee.Employee;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * This class represents an employment.  Every employment is binded to a concrete
 * {@link Employee} in the system and always references a {@link SchoolYear}.
 * <br />
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * @version $Id$
 */
@Entity
@Table(name = "minoas_employment")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employment extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	

	@ManyToOne
	@JoinColumn(name="employee_id", nullable=false)
	private Employee employee;

	@Basic
	@Column(name="established", nullable=true)
	private Date established;
	@Basic
	@Column(name="hours", nullable=false, updatable=false)
	private Integer hours;

	@Basic
	@Column(name="hours_deducted", nullable=true, updatable=false)
	private Integer hoursDeducted;

	@Basic
	@Column(name="hours_deducted_reason", nullable=true)
	private String hoursDeductionReason;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Basic
	@Column(name="school_id", nullable=false)
	private School school;
	
	@OneToOne
	@JoinColumn(name="schoolyear_id")
	private SchoolYear schoolYear;

	@Basic
	@OneToOne
	@JoinColumn(name="old_employment_id", nullable=true, updatable=false)
	private Employment supersededEmployment;
	
	@Basic
	@Column(name="terminated", nullable=true)
	private Date terminated;
	
	@Version
	@SuppressWarnings("unused")
	private Timestamp version;
	
	/**
	 * 
	 */
	public Employment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}
	
	/**
	 * @return the established
	 */
	public Date getEstablished() {
		return established;
	}
	
	
	/**
	 * @return the hours
	 */
	public Integer getHours() {
		return hours;
	}

	/**
	 * @return the hoursDeducted
	 */
	public Integer getHoursDeducted() {
		return hoursDeducted;
	}

	/**
	 * @return the hoursDeductionReason
	 */
	public String getHoursDeductionReason() {
		return hoursDeductionReason;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the school
	 */
	public School getSchool() {
		return school;
	}

	/**
	 * @return the schoolYear
	 */
	public SchoolYear getSchoolYear() {
		return schoolYear;
	}

	/**
	 * @return the supersededEmployment
	 */
	public Employment getSupersededEmployment() {
		return supersededEmployment;
	}

	/**
	 * @return the terminated
	 */
	public Date getTerminated() {
		return terminated;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @param established the established to set
	 */
	public void setEstablished(Date established) {
		this.established = established;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(Integer hours) {
		this.hours = hours;
	}

	/**
	 * @param hoursDeducted the hoursDeducted to set
	 */
	public void setHoursDeducted(Integer hoursDeducted) {
		this.hoursDeducted = hoursDeducted;
	}

	/**
	 * @param hoursDeductionReason the hoursDeductionReason to set
	 */
	public void setHoursDeductionReason(String hoursDeductionReason) {
		this.hoursDeductionReason = hoursDeductionReason;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param school the school to set
	 */
	public void setSchool(School school) {
		this.school = school;
	}

	/**
	 * @param schoolYear the schoolYear to set
	 */
	public void setSchoolYear(SchoolYear schoolYear) {
		this.schoolYear = schoolYear;
	}

	/**
	 * @param supersededEmployment the supersededEmployment to set
	 */
	public void setSupersededEmployment(Employment supersededEmployment) {
		this.supersededEmployment = supersededEmployment;
	}

	/**
	 * @param terminated the terminated to set
	 */
	public void setTerminated(Date terminated) {
		this.terminated = terminated;
	}
	
	
	
	

}
