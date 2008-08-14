package gr.sch.ira.minoas.model.employee;

import gr.sch.ira.minoas.model.BaseModel;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="minoas_employee")
@Inheritance(strategy=InheritanceType.JOINED)
public class Employee extends BaseModel {
	
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	@Basic
	@Column(name="birth_date")
	private Date dateOfBirth;
	
	@Basic
	@Column(name="father_name", nullable=false, length=15)
	private String fatherName;
	
	@Basic
	@Column(name="first_name", nullable=false, length=15)
	private String firstName;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(length=32, name = "id")
	private String id;
	
	@Basic
	@Column(name="id_number", unique=true, length=10)
	private String idNumber;
	
	
	@Basic
	@Column(name="last_name", nullable=false, length=35)
	private String lastName;
	
	@Basic
	@Column(name="mother_name", nullable=true, length=15)
	private String motherName;
	
	@Basic
	@Column(name="vat_number", unique=true, nullable=false, length=9)
	private String vATNumber;
	
	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}
	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}
	/**
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the motherName
	 */
	public String getMotherName() {
		return motherName;
	}
	/**
	 * @param motherName the motherName to set
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the vATNumber
	 */
	public String getVATNumber() {
		return vATNumber;
	}
	/**
	 * @param number the vATNumber to set
	 */
	public void setVATNumber(String number) {
		vATNumber = number;
	}
}
