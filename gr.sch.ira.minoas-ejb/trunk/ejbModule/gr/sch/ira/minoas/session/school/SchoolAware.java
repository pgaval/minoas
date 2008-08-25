package gr.sch.ira.minoas.session.school;

import gr.sch.ira.minoas.model.core.School;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * @version $Id$
 */
public interface SchoolAware {
	public void setSchool(School school);

	public School getSchool();

	public boolean hasSchool();
}
