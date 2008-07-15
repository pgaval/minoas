package gr.sch.ira.minoas.session.school;

import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.session.BaseSeamComponent;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * @version $Id$
 */
public abstract class BaseSchoolAware extends BaseStatefulSeamComponentImpl implements
		SchoolAware {

	@In(required = false)
	@Out(required = false)
	private School school;

	/**
	 * @see gr.sch.ira.minoas.session.school.SchoolAware#getSchool()
	 */
	public School getSchool() {
		return this.school;
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.SchoolAware#hasSchool()
	 */
	public boolean hasSchool() {
		return getSchool() != null;
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.SchoolAware#setSchool(gr.sch.ira.minoas.model.core.School)
	 */
	public void setSchool(School school) {
		debug("school '#0' has been selected.", getSchool());
		this.school = school;
	}

}
