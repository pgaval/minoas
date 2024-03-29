package gr.sch.ira.minoas.model.employee;

import gr.sch.ira.minoas.model.core.PYSDE;
import gr.sch.ira.minoas.model.core.Specialization;
import gr.sch.ira.minoas.model.employement.Employment;
import gr.sch.ira.minoas.model.employement.Leave;
import gr.sch.ira.minoas.model.employement.Secondment;
import gr.sch.ira.minoas.model.employement.ServiceAllocation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "EMPLOYEE")
public class Employee extends Person {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * An employee can be active or not. 
	 */
	@Basic
	@Column(name = "IS_ACTIVE", nullable = true)
	private Boolean active;

	@Basic
	@Column(name = "BIG_FAMILY", nullable = true)
	private Boolean bigFamily = Boolean.FALSE;

	@Basic
	@Column(name = "COMMENT", nullable = true, length = 256)
	private String comment;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "CURRENT_EMPLOYMENT_ID", nullable = true)
	private Employment currentEmployment;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "PYSDE_ID", nullable = false, updatable = true)
	private PYSDE currentPYSDE;

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Set<Employment> employments;

	/**
	* Each employee have a specialization, which is actually the last employment's 
	* specialization.
	*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LAST_SPECIALIZATION_ID", nullable = true)
	private Specialization lastSpecialization;

	@OneToOne(optional = true, fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "LEAVE_ID", nullable = true)
	private Leave leave;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = { CascadeType.ALL })
	private Collection<Leave> leaves = new ArrayList<Leave>();

	@Basic
	@Column(name = "LEGACY_CODE", nullable = true, updatable = false, length = 10)
	private String legacyCode;

	@OneToOne(optional = true, fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "REGULAR_EMPLOYMEE_INFO_ID", nullable = true)
	private RegularEmployeeInfo regularEmployeeInfo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = { CascadeType.ALL })
	private Collection<Secondment> secondments = new ArrayList<Secondment>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = { CascadeType.ALL })
	private Collection<ServiceAllocation> serviceAllocations = new ArrayList<ServiceAllocation>();

	@Basic
	@Column(name = "SPECIAL_CATEGORY", nullable = true)
	private Boolean specialCategory = Boolean.FALSE;

	@Enumerated(EnumType.STRING)
	@Column(name = "EMPLOYEE_TYPE", nullable = false, updatable = false)
	private EmployeeType type;
	
	@OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.ALL}, mappedBy="employee")
	private EmployeeExclusion exclusion;

	/**
	 * 
	 */
	public Employee() {
		super();
	}

	public Leave addLeave(Leave leave) {
		if (!getLeaves().contains(leave)) {
			getLeaves().add(leave);
			leave.setEmployee(this);
		}
		return leave;
	}

	public Secondment addSecondment(Secondment secondment) {
		if (!getSecondments().contains(secondment)) {
			getSecondments().add(secondment);
			secondment.setEmployee(this);
		}
		return secondment;
	}

	public ServiceAllocation addServiceAllocation(ServiceAllocation serviceAllocation) {
		if (!getServiceAllocations().contains(serviceAllocation)) {
			getServiceAllocations().add(serviceAllocation);
			serviceAllocation.setEmployee(this);
		}
		return serviceAllocation;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @return the bigFamily
	 */
	public Boolean getBigFamily() {
		return bigFamily;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @return the currentEmployment
	 */
	public Employment getCurrentEmployment() {
		return currentEmployment;
	}

	/**
	 * @return the currentPYSDE
	 */
	public PYSDE getCurrentPYSDE() {
		return currentPYSDE;
	}

	/**
	 * @return the employments
	 */
	public Set<Employment> getEmployments() {
		return employments;
	}

	/**
	 * @return the lastSpecialization
	 */
	public Specialization getLastSpecialization() {
		return lastSpecialization;
	}

	/**
	 * @return the leave
	 */
	public Leave getLeave() {
		return leave;
	}

	/**
	 * @return the leaves
	 */
	public Collection<Leave> getLeaves() {
		return leaves;
	}

	/**
	 * @return the legacyCode
	 */
	public String getLegacyCode() {
		return legacyCode;
	}

	/**
	 * @return the regularDetail
	 */
	public RegularEmployeeInfo getRegularDetail() {
		return regularEmployeeInfo;
	}

	/**
	 * @return the secondments
	 */
	public Collection<Secondment> getSecondments() {
		return secondments;
	}

	/**
	 * @return the serviceAllocations
	 */
	public Collection<ServiceAllocation> getServiceAllocations() {
		return serviceAllocations;
	}

	/**
	 * @return the specialCategory
	 */
	public Boolean getSpecialCategory() {
		return specialCategory;
	}

	/**
	 * @return the type
	 */
	public EmployeeType getType() {
		return type;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @param bigFamily the bigFamily to set
	 */
	public void setBigFamily(Boolean bigFamily) {
		this.bigFamily = bigFamily;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @param currentEmployment the currentEmployment to set
	 */
	public void setCurrentEmployment(Employment currentEmployment) {
		this.currentEmployment = currentEmployment;
	}

	/**
	 * @param currentPYSDE the currentPYSDE to set
	 */
	public void setCurrentPYSDE(PYSDE currentPYSDE) {
		this.currentPYSDE = currentPYSDE;
	}

	/**
	 * @param employments the employments to set
	 */
	public void setEmployments(Set<Employment> employments) {
		this.employments = employments;
	}

	/**
	 * @param lastSpecialization the lastSpecialization to set
	 */
	public void setLastSpecialization(Specialization lastSpecialization) {
		this.lastSpecialization = lastSpecialization;
	}

	/**
	 * @param leave the leave to set
	 */
	public void setLeave(Leave leave) {
		this.leave = leave;
	}

	/**
	 * @param leaves the leaves to set
	 */
	public void setLeaves(Collection<Leave> leaves) {
		this.leaves = leaves;
	}

	/**
	 * @param legacyCode the legacyCode to set
	 */
	public void setLegacyCode(String legacyCode) {
		this.legacyCode = legacyCode;
	}

	/**
	 * @param regularDetail the regularDetail to set
	 */
	public void setRegularDetail(RegularEmployeeInfo regularDetail) {
		this.regularEmployeeInfo = regularDetail;
	}

	/**
	 * @param secondments the secondments to set
	 */
	public void setSecondments(Collection<Secondment> secondments) {
		this.secondments = secondments;
	}

	/**
	 * @param serviceAllocations the serviceAllocations to set
	 */
	public void setServiceAllocations(Collection<ServiceAllocation> serviceAllocations) {
		this.serviceAllocations = serviceAllocations;
	}

	/**
	 * @param specialCategory the specialCategory to set
	 */
	public void setSpecialCategory(Boolean specialCategory) {
		this.specialCategory = specialCategory;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(EmployeeType type) {
		this.type = type;
	}

	public String toPrettyString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getLastName());
		sb.append(" ");
		sb.append(getFirstName());
		sb.append(" του ");
		sb.append(getFatherName());
		return sb.toString();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(getLastName());
		sb.append(" ");
		sb.append(getFirstName());
		sb.append(" ");
		sb.append(getFatherName());
		sb.append(" ( ");
		sb.append(getType());
		sb.append(" )");
		sb.append(" ]");
		return sb.toString();
	}

	/**
	 * @return the exclusion
	 */
	public EmployeeExclusion getExclusion() {
		return exclusion;
	}

	/**
	 * @param exclusion the exclusion to set
	 */
	public void setExclusion(EmployeeExclusion exclusion) {
		this.exclusion = exclusion;
	}
	
	

}
