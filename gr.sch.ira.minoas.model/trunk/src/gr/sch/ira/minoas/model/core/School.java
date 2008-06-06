/**
 * 
 */
package gr.sch.ira.minoas.model.core;

import gr.sch.ira.minoas.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.JoinColumn;

/**
 * A class representing a school registered in the system. Each school 
 * @author slavikos
 *
 */
@Entity
@Table(schema="dbo", name = "SXOLEIA")
public class School extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="KVDSX")
	private Long id;
	@Column(name="SXOLEIO")
	private String description;
	@Column(name="PERIOXH")
	private Character regionCode;
	@Column(name="KOD_SXOLEIOY")
	private Long ministryCode;
	
	@OneToOne(fetch=LAZY, targetEntity = gr.sch.ira.minoas.model.core.OrganizationUnit.class)
	@JoinColumn(name="KY", referencedColumnName = "KY")
	private OrganizationUnit unit;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the ministryCode
	 */
	public Long getMinistryCode() {
		return ministryCode;
	}
	/**
	 * @param ministryCode the ministryCode to set
	 */
	public void setMinistryCode(Long ministryCode) {
		this.ministryCode = ministryCode;
	}
	/**
	 * @return the regionCode
	 */
	public Character getRegionCode() {
		return regionCode;
	}
	/**
	 * @param regionCode the regionCode to set
	 */
	public void setRegionCode(Character regionCode) {
		this.regionCode = regionCode;
	}
	/**
	 * @return the unit
	 */
	public OrganizationUnit getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(OrganizationUnit unit) {
		this.unit = unit;
	}
}
