/**
 * 
 */
package gr.sch.ira.minoas.model.security;

import gr.sch.ira.minoas.model.BaseModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jboss.seam.annotations.AutoCreate;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Entity
@Table(name = "ROLE")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Role extends BaseModel {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	@Basic
	@Column(name="DESCR", nullable=true, length=250)
	private String description;

	@Id @GeneratedValue
	@Column(name = "ID", length = 32, updatable = false)
	private Long id;

	@Basic
	@Column(name = "NAME", nullable = false, length = 32, unique=true )
	private String name;
	
	/**
	 * 
	 */
	public Role() {
		super();
	}

	/**
	 * @param name
	 * @param description
	 */
	public Role(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	
	

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the title
	 */
	public String getName() {
		return name;
	}

	

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param title the title to set
	 */
	public void setName(String title) {
		this.name = title;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("<Role:");
		sb.append(getName());
		sb.append(">");
		return sb.toString();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Role))
			return false;
		Role other = (Role) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}