/**
 * 
 */
package gr.sch.ira.minoas.model.employement;

import gr.sch.ira.minoas.model.BaseModel;
import gr.sch.ira.minoas.model.core.Unit;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Entity
@Table(name = "MINOAS_SECONDMENT")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Secondment extends BaseModel {

	
	/**
	 * @return the replacementFor
	 */
	public Employment getReplacementFor() {
		return replacementFor;
	}

	/**
	 * @param replacementFor the replacementFor to set
	 */
	public void setReplacementFor(Employment replacementFor) {
		this.replacementFor = replacementFor;
	}

	/**
	 * @return the employeeRequested
	 */
	public Boolean getEmployeeRequested() {
		return employeeRequested;
	}

	/**
	 * @param employeeRequested the employeeRequested to set
	 */
	public void setEmployeeRequested(Boolean employeeRequested) {
		this.employeeRequested = employeeRequested;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PARENT_EMPLOYMENT_ID")
	private Employment affectedEmployment;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="REPLACEMENT_EMPLOYMENT_ID")
	private Employment replacementFor; 

	@Basic
	@Column(name = "DUE_TO", nullable=true)
	private Date dueTo;

	@Basic
	@Column(name="EMPLOYEE_REQUESTED", nullable=true)
	private Boolean employeeRequested;

	@Basic
	@Column(name = "ESTABLISHED", nullable = true)
	private Date established;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "UNIT_ID")
	private Unit secondmentUnit;

	@SuppressWarnings("unused")
	@Version
	private Long version;

	/**
	 * @return the affectedEmployment
	 */
	public Employment getAffectedEmployment() {
		return affectedEmployment;
	}

	/**
	 * @return the dueTo
	 */
	public Date getDueTo() {
		return dueTo;
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
	 * @return the secondmentUnit
	 */
	public Unit getSecondmentUnit() {
		return secondmentUnit;
	}

	/**
	 * @param affectedEmployment the affectedEmployment to set
	 */
	public void setAffectedEmployment(Employment affectedEmployment) {
		this.affectedEmployment = affectedEmployment;
	}

	/**
	 * @param dueTo the dueTo to set
	 */
	public void setDueTo(Date dueTo) {
		this.dueTo = dueTo;
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
	 * @param secondmentUnit the secondmentUnit to set
	 */
	public void setSecondmentUnit(Unit secondmentUnit) {
		this.secondmentUnit = secondmentUnit;
	}

}
