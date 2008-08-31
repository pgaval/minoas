/**
 * 
 */
package gr.sch.ira.minoas.model.core;

import gr.sch.ira.minoas.model.BaseModel;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jboss.seam.annotations.Name;

/**
 * Represent a school year.
 * 
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * */
@Name("schoolYear")
@Entity
@Table(name = "MINOAS_SCHOOL_YEAR")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class SchoolYear extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Basic
	@Column(name = "CURRENT", nullable = false, updatable = true)
	private boolean currentSchoolYear;

	@Basic
	@Column(name = "END_DATE", nullable = false)
	private Date endDate;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Basic
	@Column(name = "START_DATE", nullable = false)
	private Date startDate;

	@Basic
	@Column(name = "TITLE", length = 64, nullable = false, updatable = true, unique = true)
	private String title;

	/**
	 * 
	 */
	public SchoolYear() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param endDate
	 * @param startDate
	 * @param title
	 */
	public SchoolYear(Date endDate, Date startDate, String title) {
		super();
		this.endDate = endDate;
		this.startDate = startDate;
		this.title = title;
	}

	/**
	 * @param title
	 */
	public SchoolYear(String title) {
		super();
		this.title = title;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the currentSchoolYear
	 */
	public boolean isCurrentSchoolYear() {
		return currentSchoolYear;
	}

	/**
	 * @param currentSchoolYear the currentSchoolYear to set
	 */
	public void setCurrentSchoolYear(boolean currentSchoolYear) {
		this.currentSchoolYear = currentSchoolYear;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(getId());
		sb.append("/");
		sb.append(getTitle());
		sb.append("]");
		return sb.toString();
	}

}
