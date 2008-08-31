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
@Table(name = "MINOAS_EMPLOYMENT_HPAID")
@PrimaryKeyJoinColumn(name = "EMPLOYMENT_ID")
@DiscriminatorValue("HPAID_EMPLOYMENT")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class HourlyPaidEmployment extends Employment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Basic
	@Column(name = "working_hours", nullable = false)
	private Integer workingHours;

	/**
	 * 
	 */
	public HourlyPaidEmployment() {
		super();
	}

	/**
	 * @return the workingHours
	 */
	public Integer getWorkingHours() {
		return workingHours;
	}

	/**
	 * @param workingHours the workingHours to set
	 */
	public void setWorkingHours(Integer workingHours) {
		this.workingHours = workingHours;
	}

}
