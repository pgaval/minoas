/**
 * 
 */
package gr.sch.ira.minoas.model.security;

import gr.sch.ira.minoas.model.BaseModel;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
@Entity
@Table(name = "minoas_role_group")
public class RoleGroup extends BaseModel {
	
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Comment for <code>id</code>
	 */
	@Id
	@Column(name = "id", length = 20)
	private String id;

	/**
	 * Comment for <code>title</code>
	 */
	@Basic
	@Column(name = "title", nullable = true)
	private String title;

	/**
	 * Comment for <code>roles</code>
	 */
	@ManyToMany
	@JoinTable(name = "minoas_rolegroup_role")
	private Set<Role> roles;

	/**
	 * @param id
	 * @param title
	 */
	public RoleGroup(String id, String title) {
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
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

}
