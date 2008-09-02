package gr.sch.ira.minoas.model.employement;

import gr.sch.ira.minoas.model.BaseModel;
import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.core.SchoolYear;
import gr.sch.ira.minoas.model.employee.Employee;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * This class represents an employment. Every employment is binded to a concrete
 * {@link Employee} in the system and always references a {@link SchoolYear}.
 * <br />
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * @version $Id$
 */
@Entity
@Table(name = "MINOAS_EMPLOYMENT")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("EMPLOYMENT")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public abstract class Employment extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch=FetchType.EAGER)
	@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
	@JoinColumn(name = "EMPLOYEE_ID", nullable = false)
	private Employee employee;

	@Basic
	@Column(name = "ESTABLISHED", nullable = true)
	private Date established;

	@Id
	@Column(name = "EMPLOYMENT_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	@JoinColumn(name = "SCHOOL_ID", nullable = false)
	private School school;

	@OneToOne
	@JoinColumn(name = "SCHOOL_YEAR_ID")
	private SchoolYear schoolYear;

	@Basic
	@Column(name = "TERMINATED", nullable = true)
	private Date terminated;

	@SuppressWarnings("unused")
	@Version
	private Long version;

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
	 * @param terminated the terminated to set
	 */
	public void setTerminated(Date terminated) {
		this.terminated = terminated;
	}

}
