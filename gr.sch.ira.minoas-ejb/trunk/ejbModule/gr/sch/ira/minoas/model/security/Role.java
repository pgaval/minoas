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
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(name = "id", length=32)
	private String id;
	
	@Basic
	@Column(name="title", nullable=true)
	private String title;

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