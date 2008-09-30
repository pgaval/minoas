/**
 * 
 */
package gr.sch.ira.minoas.model.employement;

import gr.sch.ira.minoas.model.BaseModel;
import gr.sch.ira.minoas.model.core.Unit;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author slavikos
 *
 */
@Entity
@Table(name = "MINOAS_SECONDMENT_TYPE")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class SecondmentType extends BaseModel {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@SuppressWarnings("unused")
	@Column(name="VERSION")
	@Version
	private Long version;
	
	@Basic
	@Column(name="TITLE", length=64, nullable=false, unique=true)
	private String title;
	
	@OneToMany
	@JoinTable(name="MINOAS_SECONDMENT_TYPE_UNITS")
	private Collection<Unit> suitableUnits;
	
	/**
	 * 
	 */
	public SecondmentType() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Collection<Unit> getSuitableUnits() {
		return suitableUnits;
	}

	public void setSuitableUnits(Collection<Unit> suitableUnits) {
		this.suitableUnits = suitableUnits;
	}

}
