/**
 * 
 */
package gr.sch.ira.minoas.model.employement;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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
