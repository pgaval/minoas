package gr.sch.ira.minoas.model.employement;

import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employee.RegularEmployee;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "MINOAS_EMPLOYMENT_REGULAR")
@PrimaryKeyJoinColumn(name = "EMPLOYMENT_ID")
@DiscriminatorValue("REGULAR_EMPLOYMENT")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class RegularEmployment extends Employment {

	/**
	 * @see gr.sch.ira.minoas.model.employement.Employment#getEmployee()
	 */
	@Override
	public RegularEmployee getEmployee() {
		Employee e = super.getEmployee();
		if(e instanceof RegularEmployee) 
		return (RegularEmployee)e;
		else throw new RuntimeException("regular employment needs to point to a regular employee");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic
	@Column(name = "m_wh", nullable = false)
	private Integer mandatoryWorkingHours;

	@Basic
	@Column(name = "wh_decr", nullable = true)
	private Integer workingHoursDecrement;

	@Basic
	@Column(name = "wh_decr_reason", nullable = true)
	private String workingHoursDecrementReason;

	/**
	 * @return the mandatoryWorkingHours
	 */
	public Integer getMandatoryWorkingHours() {
		return mandatoryWorkingHours;
	}

	/**
	 * @return the workingHoursDecrement
	 */
	public Integer getWorkingHoursDecrement() {
		return workingHoursDecrement;
	}

	/**
	 * @return the workingHoursDecrementReason
	 */
	public String getWorkingHoursDecrementReason() {
		return workingHoursDecrementReason;
	}

	/**
	 * @param mandatoryWorkingHours the mandatoryWorkingHours to set
	 */
	public void setMandatoryWorkingHours(Integer mandatoryWorkingHours) {
		this.mandatoryWorkingHours = mandatoryWorkingHours;
	}

	/**
	 * @param workingHoursDecrement the workingHoursDecrement to set
	 */
	public void setWorkingHoursDecrement(Integer workingHoursDecrement) {
		this.workingHoursDecrement = workingHoursDecrement;
	}

	/**
	 * @param workingHoursDecrementReason the workingHoursDecrementReason to set
	 */
	public void setWorkingHoursDecrementReason(String workingHoursDecrementReason) {
		this.workingHoursDecrementReason = workingHoursDecrementReason;
	}
	
	public RegularEmployee getRegularEmployee() {
		System.err.println(getEmployee());
		return null;
	}
	
	public Integer getFinalWorkingHours() {
		if(getWorkingHoursDecrement()!=null)
			return getMandatoryWorkingHours()-getWorkingHoursDecrement();
		else return getMandatoryWorkingHours();
	}

}
