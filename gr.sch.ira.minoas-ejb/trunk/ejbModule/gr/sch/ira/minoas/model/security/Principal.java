/**
 * 
 */
package gr.sch.ira.minoas.model.security;

import java.sql.Timestamp;
import java.util.Set;

import gr.sch.ira.minoas.model.BaseModel;
import gr.sch.ira.minoas.model.core.OrganizationalOffice;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Represents an authorized user.
 * 
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * */
@Table(name="minoas_principal")
@Entity
public class Principal extends BaseModel {
	
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(updatable=false, name = "username", length=16)
	@Id
	private String username;
	
	@Basic
	@Column(name="password", nullable=false, length=64)
	private String password;
	
	@Basic
	@Column(name="real_name", length=90, nullable=false)
	private String realName;
	
	@Basic
	@Column(name="email", length=60, nullable=true)
	private String email;
	
	@Basic
	@ManyToOne(optional=false)
	private OrganizationalOffice office;
	
	
	
	@Version
	private Timestamp version;
	
	@ManyToMany
	@JoinTable(name="minoas_principal_rolegroups")
	private Set<RoleGroup> roleGroups;
	
	@ManyToMany
	@JoinTable(name="minoas_principal_roles")
	private Set<Role> roles;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the roleGroups
	 */
	public Set<RoleGroup> getRoleGroups() {
		return roleGroups;
	}

	/**
	 * @param roleGroups the roleGroups to set
	 */
	public void setRoleGroups(Set<RoleGroup> roleGroups) {
		this.roleGroups = roleGroups;
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
	
	
}
