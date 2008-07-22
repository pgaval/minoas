/**
 * 
 */
package gr.sch.ira.minoas.model.core;

import gr.sch.ira.minoas.model.BaseModel;
import gr.sch.ira.minoas.model.voids.TeachingRequirement;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

/**
 * Represent a school year.
 * 
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * */
@Entity
@Table(name = "minoas_school_year")
public class SchoolYear extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch=FetchType.LAZY)
	private Collection<TeachingRequirement> teachingRequirements;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(name = "id", length=32)
	private String id;
	
	@Basic
	@Column(name="start_date", nullable=false)
	private Date startDate;
	
	@Basic
	@Column(name="end_date", nullable=false)
	private Date endDate;
	
	
	@Version
	private Timestamp version;
	
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

}
