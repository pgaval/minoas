/**
 * 
 */
package gr.sch.ira.minoas.model.voids;

import static javax.persistence.EnumType.STRING;
import gr.sch.ira.minoas.model.TeacherType;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
@Entity
@Table(name = "minoas_teaching_resource")
public class TeachingResource {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="void_id", referencedColumnName = "id")
	private TeachingRequirement fillingVoid;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(name = "id")
	private String id;
	
	
	@Enumerated(STRING)
	@Column(name="type")
	@Basic
	private TeacherType teacherType;
	
	
	@Basic
	@Column(name="hours")
	private Long teachingHours;

	/**
	 * @return the fillingVoid
	 */
	public TeachingRequirement getFillingVoid() {
		return fillingVoid;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the employeeType
	 */
	public TeacherType getTeacherType() {
		return teacherType;
	}

	/**
	 * @return the teachingHours
	 */
	public Long getTeachingHours() {
		return teachingHours;
	}

	/**
	 * @param fillingVoid the fillingVoid to set
	 */
	public void setFillingVoid(TeachingRequirement fillingVoid) {
		this.fillingVoid = fillingVoid;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param employeeType the employeeType to set
	 */
	public void setTeacherType(TeacherType teacherType) {
		this.teacherType = teacherType;
	}

	/**
	 * @param teachingHours the teachingHours to set
	 */
	public void setTeachingHours(Long teachingHours) {
		this.teachingHours = teachingHours;
	}
	
	
}
