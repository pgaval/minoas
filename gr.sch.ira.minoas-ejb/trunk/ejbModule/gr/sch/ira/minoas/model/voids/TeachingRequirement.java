/**
 * 
 */
package gr.sch.ira.minoas.model.voids;

import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;
import gr.sch.ira.minoas.model.BaseModel;
import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.core.Specialization;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * A class representing teaching hour(s) requirement in a concrete school during
 * a defined school year.
 * 
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Entity
@Table(name = "minoas_void")
public class TeachingRequirement extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Version
	private Timestamp version;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Teaching hours required to fill the void/
	 */
	@Basic
	@Column(name = "hours")
	private Long requiredHours;

	/**
	 * The specialisation of the void.
	 */
	@ManyToOne(optional = true, fetch = EAGER)
	@JoinColumn(name = "specialisation_id")
	private Specialization specialisation;

	/**
	 * The school where the void exists.
	 */
	@ManyToOne(optional = false, fetch = EAGER)
	@JoinColumn(name = "school_id")
	private School school;

	@Basic
	@Column(name = "teaching_hours", nullable = true)
	private Long teachingHours;

	@OneToMany(cascade = REMOVE, fetch = FetchType.EAGER, mappedBy = "fillingVoid")
	private Collection<TeachingResource> teachingResources;

	/**
	 * @return the teachingHours
	 */
	public Long getRequiredHours() {
		return requiredHours;
	}

	/**
	 * @param teachingHours the teachingHours to set
	 */
	public void setRequiredHours(Long teachingHours) {
		this.requiredHours = teachingHours;
	}

	/**
	 * @return the specialisation
	 */
	public Specialization getSpecialisation() {
		return specialisation;
	}

	/**
	 * @param specialisation the specialisation to set
	 */
	public void setSpecialisation(Specialization specialisation) {
		this.specialisation = specialisation;
	}

	/**
	 * @return the school
	 */
	public School getSchool() {
		return school;
	}

	/**
	 * @param school the school to set
	 */
	public void setSchool(School school) {
		this.school = school;
	}

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
	 * @return the teachingHours
	 */
	public Long getTeachingHours() {
		return teachingHours;
	}

	/**
	 * @param teachingHours the teachingHours to set
	 */
	public void setTeachingHours(Long teachingHours) {
		this.teachingHours = teachingHours;
	}

	/**
	 * @return the teachingResources
	 */
	public Collection<TeachingResource> getTeachingResources() {
		return teachingResources;
	}

	/**
	 * @param teachingResources the teachingResources to set
	 */
	public void setTeachingResources(Collection<TeachingResource> teachingResources) {
		this.teachingResources = teachingResources;
	}

	public String toString() {
		return "(" + getSpecialisation() + "," + getRequiredHours() + ")";
	}

	public Long getTeachingHoursOverPlus() {
		return Long.valueOf(getTeachingHours() - getRequiredHours());
	}

	public Long getTeachingHoursInsufficiency() {
		return Long.valueOf(getRequiredHours() - getTeachingHours());
	}

}
