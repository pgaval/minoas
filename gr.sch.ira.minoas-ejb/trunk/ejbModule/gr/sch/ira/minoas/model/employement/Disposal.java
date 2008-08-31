/**
 * 
 */
package gr.sch.ira.minoas.model.employement;

import gr.sch.ira.minoas.model.core.Unit;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 * @author slavikos
 * 
 */
public class Disposal {

	@OneToOne
	private Employment affectedEmployment;

	@Basic
	@Column(name = "established", nullable = true)
	private Date established;

	@Basic
	@Column(name = "hours", nullable = false)
	private Integer hours;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany
	@JoinColumn(name = "unit_id")
	private Unit secondmentUnit;

	@SuppressWarnings("unused")
	@Version
	private Long version;

	/**
	 * 
	 */
	public Disposal() {
		// TODO Auto-generated constructor stub
	}

}
