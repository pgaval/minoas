/**
 * 
 */
package gr.sch.ira.minoas.session.employment;

import gr.sch.ira.minoas.model.core.SchoolYear;
import gr.sch.ira.minoas.model.core.Unit;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
public interface IRegularEmploymentSearch {
	public String search();
	public void setSchool(Unit school);
	public Unit getSchool();
	public void setSchoolYear(SchoolYear schoolYear);
	public SchoolYear getSchoolYear();
	public String selectEmployment();

}
