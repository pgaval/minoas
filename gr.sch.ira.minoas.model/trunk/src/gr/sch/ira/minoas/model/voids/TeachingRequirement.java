/**
 * 
 */
package gr.sch.ira.minoas.model.voids;

import java.util.Date;

import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.core.Specialization;

/**
 * A class representing a void (expressed in teaching hours) in a concrete
 * school. 
 *  
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
public class TeachingRequirement {
	/**
	 * Teaching hours required to fill the void/
	 */
	private Long hours;
	/**
	 * The specialisation of the void.
	 */
	private Specialization specialisation;
	/**
	 * The school where the void exists.
	 */
	private School school;
	/**
	 * A date representing when this very void has been 
	 * inserted to the system.
	 */
	private Date insertedOn;
	/**
	 * @return the teachingHours
	 */
	public Long getHours() {
		return hours;
	}
	/**
	 * @param teachingHours the teachingHours to set
	 */
	public void setHours(Long teachingHours) {
		this.hours = teachingHours;
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
	 * @return the insertedOn
	 */
	public Date getInsertedOn() {
		return insertedOn;
	}
	/**
	 * @param insertedOn the insertedOn to set
	 */
	public void setInsertedOn(Date insertedOn) {
		this.insertedOn = insertedOn;
	}
}
