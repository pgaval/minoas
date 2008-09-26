/**
 * 
 */
package gr.sch.ira.minoas.model.core;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jboss.seam.annotations.Name;

import gr.sch.ira.minoas.model.BaseModel;

/**
 * @author slavikos
 *
 */
@Entity
@Name("pysde")
@Table(name = "MINOAS_PYSDE")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class PYSDE extends BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Basic
	@Column(name = "TITLE", length = 64, nullable = false, updatable = true, unique = true)
	private String title;
	/**
	 * 
	 */
	public PYSDE() {
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

}
