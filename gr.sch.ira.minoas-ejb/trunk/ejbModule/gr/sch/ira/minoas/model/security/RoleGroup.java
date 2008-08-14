/**
 * 
 */
package gr.sch.ira.minoas.model.security;

import gr.sch.ira.minoas.model.BaseModel;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
@Entity
@Table(name = "minoas_role_group")
@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL)
public class RoleGroup extends BaseModel {
	
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Comment for <code>id</code>
	 */
	@Id
	@Column(name = "id", length = 32,updatable=false)
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
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "minoas_rolegroup_role")
	@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL)
	private List<Role> roles;
	
	@SuppressWarnings("unused")
	@Version
	private Timestamp version;

	/**
	 * 
	 */
	public RoleGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

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
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("<RoleGroup:");
		sb.append(getId());
		sb.append(">");
		return sb.toString();
	}

}
