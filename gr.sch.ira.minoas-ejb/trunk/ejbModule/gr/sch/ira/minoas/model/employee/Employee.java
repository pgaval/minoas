package gr.sch.ira.minoas.model.employee;

import gr.sch.ira.minoas.model.BaseModel;
import gr.sch.ira.minoas.model.core.Address;
import gr.sch.ira.minoas.model.core.Specialization;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "MINOAS_EMPLOYEE")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("EMPLOYEE")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Employee extends BaseModel {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "ADDRESS_ID", nullable = true)
	@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
	private Address address;

	@Basic
	@Column(name = "BIRTH_DAY", nullable=true)
	private Date dateOfBirth;

	@Basic
	@Column(name = "FATHER_NAME", nullable = true, length = 15)
	private String fatherName;

	@Basic
	@Column(name = "FIRST_NAME", nullable = false, length = 15)
	private String firstName;

	@Id
	@Column(name = "EMPLOYEE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Basic
	@Column(name="LEGACY_CODE", nullable=false, updatable=false, unique=true, length=6)
	private String legacyCode;

	@Basic
	@Column(name = "ID_NUMBER", unique = false, nullable=true, length = 10)
	private String idNumber;

	@Basic
	@Column(name = "LAST_NAME", nullable = false, length = 35)
	private String lastName;

	@Basic
	@Column(name = "MOTHER_NAME", nullable = true, length = 15)
	private String motherName;

	@ManyToOne(optional = false)
	@JoinColumn(name = "SPECIALIZATION_ID", nullable = false, updatable = false)
	private Specialization specialization;

	@Basic
	@Column(name = "VAT_NUMBER", unique = false, nullable = true, length = 10)
	private String vATNumber;

	@SuppressWarnings("unused")
	@Version
	private Long version;

	/**
	 * 
	 */
	public Employee() {
		super();
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the motherName
	 */
	public String getMotherName() {
		return motherName;
	}

	/**
	 * @return the specialization
	 */
	public Specialization getSpecialization() {
		return specialization;
	}

	/**
	 * @return the vATNumber
	 */
	public String getVATNumber() {
		return vATNumber;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param motherName the motherName to set
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	/**
	 * @param specialization the specialization to set
	 */
	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	/**
	 * @param number the vATNumber to set
	 */
	public void setVATNumber(String number) {
		vATNumber = number;
	}

	/**
	 * @return the legacyCode
	 */
	public String getLegacyCode() {
		return legacyCode;
	}

	/**
	 * @param legacyCode the legacyCode to set
	 */
	public void setLegacyCode(String legacyCode) {
		this.legacyCode = legacyCode;
	}
}
