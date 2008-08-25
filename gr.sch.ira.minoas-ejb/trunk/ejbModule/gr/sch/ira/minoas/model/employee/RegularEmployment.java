package gr.sch.ira.minoas.model.employee;

import gr.sch.ira.minoas.model.BaseModel;
import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.core.SchoolYear;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "minoas_regular_employments")
public class RegularEmployment extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private SchoolYear schoolYear;

	private Employee employee;

	private School school;

	@Basic
	private String decisionNumber;

	@SuppressWarnings("unused")
	@Version
	private Timestamp version;

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
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the school
	 */
	public School getSchool() {
		return school;
	}

	/**
	 * @param school the school to set
	 */
	public void setSchool(School school) {
		this.school = school;
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
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

}
