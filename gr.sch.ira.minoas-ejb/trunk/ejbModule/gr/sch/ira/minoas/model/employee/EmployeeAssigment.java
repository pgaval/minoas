/**
 * 
 */
package gr.sch.ira.minoas.model.employee;

import gr.sch.ira.minoas.model.BaseModel;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Entity
@Table(name = "minoas_allocation")
public class EmployeeAssigment extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@SuppressWarnings("unused")
	@Version
	private Timestamp version;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

}
