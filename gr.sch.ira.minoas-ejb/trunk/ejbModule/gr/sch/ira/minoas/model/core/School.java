/**
 * 
 */
package gr.sch.ira.minoas.model.core;

import java.sql.Timestamp;

import gr.sch.ira.minoas.model.BaseModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.jboss.seam.annotations.Name;

/**
 * A class representing a school registered in the system. Each school
 * 
 * @author slavikos
 * 
 */
@Entity
@Table(name = "minoas_school")
@Name("school")
public class School extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", length = 3)
	private String id;

	@Basic
	@Column(name = "ministry_code", length = 7, unique = true)
	private String ministryCode;

	@Basic
	@Column(name = "region")
	private Character regionCode;

	@Basic
	@Column(name = "title", nullable = false, unique = true, length = 40)
	private String title;
	
	
	@Basic
	@Column(name = "points", nullable = true)
	private Byte points;

	@SuppressWarnings("unused")
	@Version
	private Timestamp version;
	
	@OneToOne(optional=true)
	@JoinColumn(name="address_id", nullable=true)
	private Address address;
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
	 * @return the description
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param ministryCode the ministryCode to set
	 */
	public void setMinistryCode(String ministryCode) {
		this.ministryCode = ministryCode;
	}

	/**
	 * @param regionCode the regionCode to set
	 */
	public void setRegionCode(Character regionCode) {
		this.regionCode = regionCode;
	}

	/**
	 * @param description the description to set
	 */
	public void setTitle(String description) {
		this.title = description;
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

	/**
	 * @return the points
	 */
	public Byte getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(Byte points) {
		this.points = points;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
}
