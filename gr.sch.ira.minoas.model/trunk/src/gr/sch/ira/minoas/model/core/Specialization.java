/**
 * 
 */
package gr.sch.ira.minoas.model.core;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

/**
 * 
 * @author slavikos
 *
 */
@Entity
@Table(schema="dbo", name = "EIDIK")
public class Specialization {
	
	@Id
	@Column(name="EID")
	private String id;
	@Column(name="PERIGRAFH")
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

}
