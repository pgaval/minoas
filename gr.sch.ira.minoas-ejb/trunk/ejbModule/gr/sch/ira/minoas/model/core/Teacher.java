/**
 * 
 */
package gr.sch.ira.minoas.model.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Basic;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
@Entity
@Table(name = "basiko")
public class Teacher {
	@Column(name="AFM", nullable=true)
	private String AFM;
	
	@Column(name="AM", nullable=true)
	private String AM;
	@Column(name="PATERAS")
	private String fatherName;
	
	@Id
	@Column(name="KVD", updatable=false)
	private String id;
	@Column(name="ON_MHTROS", nullable=true)
	private String motherName;
	@Basic
	@Column(name="ONOMA")
	private String name;
	@ManyToOne
	@JoinColumn(name="OKVD", referencedColumnName = "KVDSX", nullable=true)
	private School school;
	@Column(name="EPIUETO")
	private String surname;
	/**
	 * @return the aM
	 */
	public String getAM() {
		return AM;
	}
	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the motherName
	 */
	public String getMotherName() {
		return motherName;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the school
	 */
	public School getSchool() {
		return school;
	}
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param am the aM to set
	 */
	public void setAM(String am) {
		AM = am;
	}
	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param motherName the motherName to set
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param school the school to set
	 */
	public void setSchool(School school) {
		this.school = school;
	}
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

}
