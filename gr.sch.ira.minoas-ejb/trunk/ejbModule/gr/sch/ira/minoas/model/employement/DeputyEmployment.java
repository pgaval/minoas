/**
 * 
 */
package gr.sch.ira.minoas.model.employement;

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

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Entity
@Table(name = "MINOAS_EMPLOYMENT_DEPUTY")
@PrimaryKeyJoinColumn(name = "EMPLOYMENT_ID")
@DiscriminatorValue("DEPUTY_EMPLOYMENT")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class DeputyEmployment extends Employment {

	public Integer getFinalWorkingHours() {
		if(getWorkingHoursDecrement()!=null)
			return getMandatoryWorkingHours()-getWorkingHoursDecrement();
		else return getMandatoryWorkingHours();
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
	 * 
	 */
	public DeputyEmployment() {
		super();
	}

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

}
