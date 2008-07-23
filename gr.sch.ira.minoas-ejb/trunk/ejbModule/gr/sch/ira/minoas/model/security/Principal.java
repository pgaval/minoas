/**
 * 
 */
package gr.sch.ira.minoas.model.security;

import java.sql.Timestamp;

import gr.sch.ira.minoas.model.BaseModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	
	@Column(name="email", length=60, nullable=true)
	private String email;
	
	@Version
	private Timestamp version;

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
	
	
}
