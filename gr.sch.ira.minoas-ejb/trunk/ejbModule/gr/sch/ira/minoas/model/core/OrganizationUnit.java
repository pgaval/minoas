/**
 * 
 */
package gr.sch.ira.minoas.model.core;

import gr.sch.ira.minoas.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author 
 *
 */
@Entity
@Table(name = "minoas_organization_unit")
public class OrganizationUnit extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", length=1)
	private String id;
	
	@Column(name="title")
	private String title;
	
	
	//private Collection<School> schools;
	
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
	
	
//	/**
//	 * @return the schools
//	 */
//	public Collection<School> getSchools() {
//		return schools;
//	}
//	/**
//	 * @param schools the schools to set
//	 */
//	public void setSchools(Collection<School> schools) {
//		this.schools = schools;
//	}
}
