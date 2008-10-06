/**
 * 
 */
package gr.sch.ira.minoas.model.employement;

import gr.sch.ira.minoas.model.BaseModel;
import gr.sch.ira.minoas.model.core.PYSDE;
import gr.sch.ira.minoas.model.core.SchoolYear;
import gr.sch.ira.minoas.model.core.Unit;
import gr.sch.ira.minoas.model.employee.Employee;

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
import javax.persistence.OneToOne;
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
	 * @return the supersededBy
	 */
	public Secondment getSupersededBy() {
		return supersededBy;
	}

	/**
	 * @param supersededBy the supersededBy to set
	 */
	public void setSupersededBy(Secondment supersededBy) {
		this.supersededBy = supersededBy;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name = "SCHOOL_YEAR_ID", nullable=false)
	private SchoolYear schoolYear;
	
	/**
	 * A secondment may or may not be active.
	 */
	@Basic
	@Column(name="IS_ACTIVE", nullable=true)
	private Boolean active;
	
	/**
	 * A secondment may be superseded by another secondment
	 */
	@OneToOne
	@JoinColumn(name="SUPERSEDED_BY_ID", nullable=true)
	private Secondment supersededBy;
	
	@Basic
	@Column(name="MINISTERIAL_ORDER", nullable=true, length=25)
	private String ministerialOrder;

	@Basic
	@Column(name="PYSDE_ORDER", nullable=true, length=25)
	private String pysdeOrder;
	
	@Basic
	@Column(name="HEADMASTER_ORDER", nullable=true, length=25)
	private String headMasterOrder;

	
	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="SECONDMENT_TYPE_ID", nullable=false)
	private SecondmentType secondmentType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PARENT_EMPLOYMENT_ID", nullable=true)
	private Employment affectedEmployment;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="REPLACEMENT_EMPLOYMENT_ID", nullable=true)
	private Employment replacementFor;

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="EMPLOYEE_ID", nullable=false)
	private Employee employee;  
	
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
	
	public Employee getEmployee() {
		return employee;
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
	
	public String getHeadMasterOrder() {
		return headMasterOrder;
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

	public SecondmentType getSecondmentType() {
		return secondmentType;
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

	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	public void setHeadMasterOrder(String headMasterOrder) {
		this.headMasterOrder = headMasterOrder;
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

	public void setSecondmentType(SecondmentType secondmentType) {
		this.secondmentType = secondmentType;
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
