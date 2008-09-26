/**
 * 
 */
package gr.sch.ira.minoas.model.employement;

import gr.sch.ira.minoas.model.BaseModel;
import gr.sch.ira.minoas.model.core.PYSDE;
import gr.sch.ira.minoas.model.core.SchoolYear;
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
import org.jboss.seam.annotations.Name;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Entity
@Table(name = "MINOAS_SECONDMENT")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Name("secondment")
public class Secondment extends BaseModel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name = "SCHOOL_YEAR_ID", nullable=false)
	private SchoolYear schoolYear;
	
	@Basic
	@Column(name="MINISTERIAL_ORDER", nullable=true, length=25)
	private String ministerialOrder;

	@Basic
	@Column(name="PYSDE_ORDER", nullable=true, length=25)
	private String pysdeOrder;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PARENT_EMPLOYMENT_ID", nullable=true)
	private Employment affectedEmployment;  
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="REPLACEMENT_EMPLOYMENT_ID", nullable=true)
	private Employment replacementFor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TARGET_UNIT", nullable=false)
	private Unit targetUnit;

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
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@SuppressWarnings("unused")
	@Version
	private Long version;
	
	@ManyToOne
	@JoinColumn(name="TARGET_PYSDE_ID", nullable=true)
	private PYSDE targetPYSDE; 

	@ManyToOne
	@JoinColumn(name="SOURCE_PYSDE_ID", nullable=true)
	private PYSDE sourcePYSDE;
	
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
	 * @return the employeeRequested
	 */
	public Boolean getEmployeeRequested() {
		return employeeRequested;
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

	public String getMinisterialOrder() {
		return ministerialOrder;
	}

	public String getPysdeOrder() {
		return pysdeOrder;
	}

	/**
	 * @return the replacementFor
	 */
	public Employment getReplacementFor() {
		return replacementFor;
	}

	

	public SchoolYear getSchoolYear() {
		return schoolYear;
	}

	public PYSDE getSourcePYSDE() {
		return sourcePYSDE;
	}

	public PYSDE getTargetPYSDE() {
		return targetPYSDE;
	}

	public Unit getTargetUnit() {
		return targetUnit;
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
	 * @param employeeRequested the employeeRequested to set
	 */
	public void setEmployeeRequested(Boolean employeeRequested) {
		this.employeeRequested = employeeRequested;
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

	public void setMinisterialOrder(String ministerialOrder) {
		this.ministerialOrder = ministerialOrder;
	}

	public void setPysdeOrder(String pysdeOrder) {
		this.pysdeOrder = pysdeOrder;
	}

	/**
	 * @param replacementFor the replacementFor to set
	 */
	public void setReplacementFor(Employment replacementFor) {
		this.replacementFor = replacementFor;
	}

	public void setSchoolYear(SchoolYear schoolYear) {
		this.schoolYear = schoolYear;
	}

	public void setSourcePYSDE(PYSDE sourcePYSDE) {
		this.sourcePYSDE = sourcePYSDE;
	}

	public void setTargetPYSDE(PYSDE targetPYSDE) {
		this.targetPYSDE = targetPYSDE;
	}

	public void setTargetUnit(Unit targetUnit) {
		this.targetUnit = targetUnit;
	}

}
