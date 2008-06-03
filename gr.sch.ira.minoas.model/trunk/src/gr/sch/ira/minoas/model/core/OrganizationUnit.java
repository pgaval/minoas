/**
 * 
 */
package gr.sch.ira.minoas.model.core;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

/**
 * 
 * @author 
 *
 */
@Entity
@Table(schema="dbo", name = "YPHRESIA")
public class OrganizationUnit {
	@Id
	@Column(name="KY")
	private Long id;
	@Column(name="TITLOS")
	private String title;
	@Column(name="PROIST")
	private String director;
	@Column(name="ANAPL")
	private String secondDirector;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
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
	/**
	 * @return the director
	 */
	public String getDirector() {
		return director;
	}
	/**
	 * @param director the director to set
	 */
	public void setDirector(String director) {
		this.director = director;
	}
	/**
	 * @return the secondDirector
	 */
	public String getSecondDirector() {
		return secondDirector;
	}
	/**
	 * @param secondDirector the secondDirector to set
	 */
	public void setSecondDirector(String secondDirector) {
		this.secondDirector = secondDirector;
	}
}
