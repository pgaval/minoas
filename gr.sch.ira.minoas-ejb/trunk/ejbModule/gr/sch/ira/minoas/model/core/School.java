/**
 * 
 */
package gr.sch.ira.minoas.model.core;

import gr.sch.ira.minoas.model.BaseModel;
import gr.sch.ira.minoas.model.voids.TeachingVoid;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * A class representing a school registered in the system. Each school
 * 
 * @author slavikos
 * 
 */
@Entity
@Table(name = "SXOLEIA")
public class School extends BaseModel {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "KVDSX")
	private String id;

	@Column(name = "KOD_SXOLEIOY")
	private String ministryCode;
	@Column(name = "PERIOXH")
	private Character regionCode;
	@OneToMany(mappedBy = "school")
	private Collection<Teacher> teachers;
	@Column(name = "SXOLEIO")
	private String title;

	@OneToMany(mappedBy = "school", fetch=FetchType.LAZY)
	private Collection<TeachingVoid> voids;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the ministryCode
	 */
	public String getMinistryCode() {
		return ministryCode;
	}

	/**
	 * @return the regionCode
	 */
	public Character getRegionCode() {
		return regionCode;
	}

	/**
	 * @return the teachers
	 */
	public Collection<Teacher> getTeachers() {
		return teachers;
	}

	/**
	 * @return the description
	 */
	public String getTitle() {
		return title;
	}

	// /**
	// * @return the unit
	// */
	// public OrganizationUnit getUnit() {
	// return unit;
	// }
	// /**
	// * @param unit the unit to set
	// */
	// public void setUnit(OrganizationUnit unit) {
	// this.unit = unit;
	// }
	/**
	 * @return the voids
	 */
	public Collection<TeachingVoid> getVoids() {
		return voids;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param ministryCode
	 *            the ministryCode to set
	 */
	public void setMinistryCode(String ministryCode) {
		this.ministryCode = ministryCode;
	}

	/**
	 * @param regionCode
	 *            the regionCode to set
	 */
	public void setRegionCode(Character regionCode) {
		this.regionCode = regionCode;
	}

	/**
	 * @param teachers
	 *            the teachers to set
	 */
	public void setTeachers(Collection<Teacher> teachers) {
		this.teachers = teachers;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setTitle(String description) {
		this.title = description;
	}

	/**
	 * @param voids
	 *            the voids to set
	 */
	public void setVoids(Collection<TeachingVoid> voids) {
		this.voids = voids;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		sb.append(getMinistryCode());
		sb.append(" -  ");
		sb.append(getTitle());
		sb.append(" (");
		sb.append(getRegionCode());
		sb.append(")] ");
		return sb.toString();
	}
}
