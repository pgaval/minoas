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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.jboss.seam.annotations.Name;

/**
 * Represents an authorized user.
 * 
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * */
@Table(name="minoas_principal")
@Name("principal")
@Entity
public class Principal extends BaseModel {
	
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Basic
	@Column(name="email", length=60, nullable=true)
	private String email;
	
	@Basic
	@ManyToOne(optional=true)
	@JoinColumn(name="office_id", nullable=true)
	private OrganizationalOffice office;
	
	@Basic
	@Column(name="password", nullable=false, length=64)
	private String password;
	
	@Basic
	@Column(name="real_name", length=90, nullable=false)
	private String realName;
	
	@ManyToMany
	@JoinTable(name="minoas_principal_rolegroups")
	private Set<RoleGroup> roleGroups;
	
	@Column(updatable=false, name = "username", length=16)
	@Id
	private String username;

	@SuppressWarnings("unused")
	@Version
	private Timestamp version;

	/**
	 * 
	 */
	public Principal() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	

	/**
	 * @return the office
	 */
	public OrganizationalOffice getOffice() {
		return office;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @return the roleGroups
	 */
	public Set<RoleGroup> getRoleGroups() {
		return roleGroups;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param office the office to set
	 */
	public void setOffice(OrganizationalOffice office) {
		this.office = office;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @param roleGroups the roleGroups to set
	 */
	public void setRoleGroups(Set<RoleGroup> roleGroups) {
		this.roleGroups = roleGroups;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	
	
}
