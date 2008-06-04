/**
 * 
 */
package gr.sch.ira.minoas.model.lala;

import java.util.Date;

import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.core.Specialization;

/**
 * A 
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
public class RequiredHours {
	private Long requiredHours;
	private Specialization specialization;
	private School school;
	private Date insertedOn;
	private String createdBy;
}
