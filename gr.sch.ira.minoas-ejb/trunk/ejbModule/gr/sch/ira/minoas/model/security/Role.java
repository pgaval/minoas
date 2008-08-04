/**
 * 
 */
package gr.sch.ira.minoas.model.security;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import gr.sch.ira.minoas.model.BaseModel;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
@Entity
@Table(name="minoas_role")
public class Role extends BaseModel {

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "["+getId()+"]";
	}

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", length=32)
	private String id;
	
	@Basic
	@Column(name="title", nullable=true, length=250)
	private String title;

	/**
	 * 
	 */
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param title
	 */
	public Role(String id, String title) {
		super();
		this.id = id;
		this.title = title;
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

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
}
