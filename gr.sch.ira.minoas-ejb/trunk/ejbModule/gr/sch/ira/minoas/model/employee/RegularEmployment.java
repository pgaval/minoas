package gr.sch.ira.minoas.model.employee;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import gr.sch.ira.minoas.model.BaseModel;
import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.core.SchoolYear;

@Entity
@Table(name="minoas_regular_employments")
public class RegularEmployment extends BaseModel {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(length=32, name = "id")
	private String id;
	
	private SchoolYear schoolYear;
	private Employee employee;
	private School school;
	
	@Basic
	private String decisionNumber;
	
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
	public String getId() {
		return id;
	}
	
}
