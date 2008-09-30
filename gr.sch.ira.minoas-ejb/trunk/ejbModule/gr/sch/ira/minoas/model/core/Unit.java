/**
 * 
 */
package gr.sch.ira.minoas.model.core;

import gr.sch.ira.minoas.model.BaseModel;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "UNIT")
@Table(name = "MINOAS_UNIT")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Unit extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADDRESS_ID", nullable = true)
	@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
	private Address address;
	
	@Id
	@Column(name = "UNIT_ID", length = 3)
	private String id;

	@Basic
	@Column(name = "TITLE", nullable = false, unique = true, length = 80)
	private String title;

	@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name="MINOAS_UNIT_TELEPHONES")
	private List<Telephone> telephones;
	
	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name="CATEGORY_ID", nullable=true)
	private UnitCategory category;
	
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

	

	/**
	 * @return the telephones
	 */
	public List<Telephone> getTelephones() {
		return telephones;
	}

	/**
	 * @param telephones the telephones to set
	 */
	public void setTelephones(List<Telephone> telephones) {
		this.telephones = telephones;
	}

	public UnitCategory getCategory() {
		return category;
	}

	public void setCategory(UnitCategory category) {
		this.category = category;
	}

}
