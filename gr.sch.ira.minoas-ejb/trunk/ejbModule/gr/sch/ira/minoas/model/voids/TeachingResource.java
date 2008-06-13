/**
 * 
 */
package gr.sch.ira.minoas.model.voids;

import gr.sch.ira.minoas.model.TeacherType;
import gr.sch.ira.minoas.model.core.Teacher;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import static javax.persistence.EnumType.STRING;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
@Entity
@Table(schema="dbo", name = "minoas_teaching_resource")
public class TeachingResource {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="void_id", referencedColumnName = "id")
	private Void fillingVoid;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(name = "id")
	private String id;
	
	@ManyToOne
	@JoinColumn(name="teacher_id", referencedColumnName = "KVD")
	private Teacher teacher;
	
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
	public Void getFillingVoid() {
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
	public void setFillingVoid(Void fillingVoid) {
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
