/**
 * 
 */
package gr.sch.ira.minoas.session.employment;

import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.core.SchoolYear;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
public interface IRegularEmploymentSearching {
	public String search();
	public void setSchool(School school);
	public School getSchool();
	public void setSchoolYear(SchoolYear schoolYear);
	public SchoolYear getSchoolYear();

}
