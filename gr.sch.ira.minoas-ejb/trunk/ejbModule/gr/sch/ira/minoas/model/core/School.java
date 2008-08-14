/**
 * 
 */
package gr.sch.ira.minoas.model.core;

import gr.sch.ira.minoas.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A class representing a school registered in the system. Each school
 * 
 * @author slavikos
 * 
 */
@Entity
@Table(name = "minoas_school")
public class School extends BaseModel {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", length=3)
	private String id;

	@Column(name = "ministry_code", length=7, unique=true)
	private String ministryCode;
	
	@Column(name = "region")
	private Character regionCode;
	
	
	@Column(name = "title", nullable=false, unique=true, length=40)
	private String title;

	
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
	 * @param description
	 *            the description to set
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
}
