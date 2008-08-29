/**
 * 
 */
package gr.sch.ira.minoas.model.core;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import gr.sch.ira.minoas.model.BaseModel;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "type")
@Table(name="minoas_unit")
public abstract class Unit extends BaseModel {

	@OneToOne(optional=true)
	@JoinColumn(name="address_id", nullable=true)
	private Address address;
	
	@Id
	@Column(name = "id", length = 3)
	private String id;
	
	@Basic
	@Column(name = "title", nullable = false, unique = true, length = 80)
	private String title;
	
	@SuppressWarnings("unused")
	@Version
	private Long version;
	
	/**
	 * 
	 */
	public Unit() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
