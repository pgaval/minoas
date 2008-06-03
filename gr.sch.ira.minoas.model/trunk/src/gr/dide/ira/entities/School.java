/**
 * 
 */
package gr.dide.ira.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.JoinColumn;

/**
 * @author slavikos
 *
 */
@Entity
@Table(schema="dbo", name = "SXOLEIA")
public class School {
	@Id
	@Column(name="KVDSX")
	private Long id;
	@Column(name="SXOLEIO")
	private String description;
	@Column(name="PERIOXH")
	private Character regionCode;
	@Column(name="KOD_SXOLEIOY")
	private Long ministryCode;
	
	@OneToOne(fetch=LAZY, targetEntity = gr.dide.ira.entities.Unit.class)
	@JoinColumn(name="KY", referencedColumnName = "KY")
	private Unit unit;
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
	public Unit getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
}
