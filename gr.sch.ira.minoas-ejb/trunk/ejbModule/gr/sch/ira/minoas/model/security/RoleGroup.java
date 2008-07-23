/**
 * 
 */
package gr.sch.ira.minoas.model.security;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import gr.sch.ira.minoas.model.BaseModel;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
@Entity
@Table(name="minoas_role_group")
public class RoleGroup extends BaseModel {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(name = "id", length=32)
	private String id;
	
	@Basic
	@Column(name="title", nullable=true)
	private String title;
	

}
