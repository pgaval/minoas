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
 * 
 * @author slavikos
 * 
 */
@Entity
@Table(name = "minoas_specialization")
public class Specialization extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", length = 6)
	private String id;

	@Column(name = "title", nullable = false, length = 70)
	private String title;

	/**
	 * 
	 */
	public Specialization() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String toString() {
		return getId();
	}

}
