package gr.sch.ira.minoas.session;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import gr.sch.ira.minoas.model.core.School;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * @version $Id$
 */
public abstract class BaseSchoolAware implements SchoolAware {

	@In(scope=ScopeType.SESSION,required=false) @Out(scope=ScopeType.SESSION,required=false)
	private School school;
	/**
	 * @see gr.sch.ira.minoas.session.SchoolAware#getSchool()
	 */
	public School getSchool() {
		return this.school;
	}

	/**
	 * @see gr.sch.ira.minoas.session.SchoolAware#hasSchool()
	 */
	public boolean hasSchool() {
		return getSchool()!=null;
	}

	/**
	 * @see gr.sch.ira.minoas.session.SchoolAware#setSchool(gr.sch.ira.minoas.model.core.School)
	 */
	public void setSchool(School school) {
		this.school =school;
	}

}
