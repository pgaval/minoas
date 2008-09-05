package gr.sch.ira.minoas.model.employement;

import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employee.RegularEmployee;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic
	@Column(name = "MANDATORY_WORK_HRS", nullable = false)
	private Integer mandatoryWorkingHours;

	@Basic
	@Column(name = "WORK_HRS_DECR", nullable = true)
	private Integer workingHoursDecrement;

	@Basic
	@Column(name = "WORK_HRS_DECR_REASON", nullable = true)
	private String workingHoursDecrementReason;
	
	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name="SECONDMENT_ID", nullable=true)
	private Secondment secondment;

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

	public Integer getFinalWorkingHours() {
		if(getWorkingHoursDecrement()!=null)
			return getMandatoryWorkingHours()-getWorkingHoursDecrement();
		else return getMandatoryWorkingHours();
	}

	/**
	 * @return the mandatoryWorkingHours
	 */
	public Integer getMandatoryWorkingHours() {
		return mandatoryWorkingHours;
	}

	public RegularEmployee getRegularEmployee() {
		System.err.println(getEmployee());
		return null;
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

	/**
	 * @return the secondment
	 */
	public Secondment getSecondment() {
		return secondment;
	}

	/**
	 * @param secondment the secondment to set
	 */
	public void setSecondment(Secondment secondment) {
		this.secondment = secondment;
	}
	
	public boolean hasSecondment() {
		return getSecondment()!=null;
	}

}
