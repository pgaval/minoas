package gr.sch.ira.minoas.model.employee;

import gr.sch.ira.minoas.model.core.School;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "minoas_regular_employment")
public class RegularEmployment extends Employment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic
	@Column(name="school_id", nullable=false)
	private School school;

	

	

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

	

	

}
