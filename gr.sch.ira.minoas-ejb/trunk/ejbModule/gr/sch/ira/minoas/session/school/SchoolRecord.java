/**
 * 
 */
package gr.sch.ira.minoas.session.school;

import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;

import javax.ejb.Local;
import javax.ejb.Stateful;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.security.Restrict;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Name("schoolRecord")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, ISchoolRecord.class })
public class SchoolRecord extends BaseStatefulSeamComponentImpl implements
		ISchoolRecord {

	@In() 
	private School selectedSchool;
	/**
	 * @see gr.sch.ira.minoas.session.school.ISchoolRecord#selectSchool(gr.sch.ira.minoas.model.core.School)
	 */
	public String selectSchool(School school) {
		info("lalalal #0", school);
//		
//		if (school != null) {
//			this.school = school;
//
//			return SUCCESS_OUTCOME;
//		} else
//			return FAILURE_OUTCOME;
		return null;
	}

}
