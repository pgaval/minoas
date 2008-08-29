/**
 * 
 */
package gr.sch.ira.minoas.model.core;

import java.sql.Timestamp;

import gr.sch.ira.minoas.model.BaseModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
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
@DiscriminatorValue("school")
public class School extends Unit {

	private static final long serialVersionUID = 1L;

	

	@Basic
	@Column(name = "ministry_code", length = 7, unique = true)
	private String ministryCode;

	@Basic
	@Column(name = "region")
	private Character regionCode;

	
	
	
	@Basic
	@Column(name = "points", nullable = true)
	private Byte points;

	
	
	
	
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


}
