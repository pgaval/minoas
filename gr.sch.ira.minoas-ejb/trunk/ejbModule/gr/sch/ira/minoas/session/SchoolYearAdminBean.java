/**
 * 
 */
package gr.sch.ira.minoas.session;

import gr.sch.ira.minoas.core.session.CoreSearching;
import gr.sch.ira.minoas.model.core.SchoolYear;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Name("schoolYearAdmin")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, ISchoolYearAdmin.class })
public class SchoolYearAdminBean extends BaseStatefulSeamComponentImpl implements ISchoolYearAdmin {

	@In(value = "schoolYear", create = true)
	@Out(value = "schoolYear", required = false, scope = ScopeType.CONVERSATION)
	private SchoolYear activeSchoolYear;

	@EJB
	private CoreSearching coreSearching;

	@PersistenceContext
	private EntityManager em;

	@DataModel(scope = ScopeType.PAGE, value = "availableSchoolYears")
	private List<SchoolYear> schoolYears;

	@DataModelSelection
	@Out(required = false, scope = ScopeType.CONVERSATION)
	private SchoolYear selectedSchoolYear;

	/**
	 * @see gr.sch.ira.minoas.session.ISchoolYearAdmin#cancelSchoolYear()
	 */
	public String cancelSchoolYear() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see gr.sch.ira.minoas.session.ISchoolYearAdmin#deleteSchoolYear()
	 */
	public String deleteSchoolYear() {
		return SUCCESS_OUTCOME;
	}

	/**
	 * @see gr.sch.ira.minoas.session.ISchoolYearAdmin#editSchoolYear()
	 */
	public String editSchoolYear() {
		return null;
	}

	/**
	 * @return the activeSchoolYear
	 */
	public SchoolYear getActiveSchoolYear() {
		return activeSchoolYear;
	}

	/**
	 * @see gr.sch.ira.minoas.session.ISchoolYearAdmin#newSchoolYear()
	 */
	@Begin(join=true, pageflow = "new-school-year")
	public String newSchoolYear() {
		info("started new school year conversation.");
		SchoolYear new_school_year = new SchoolYear();
		Calendar now = Calendar.getInstance();
		/* prepare the from date */
		now.set(Calendar.MONTH, Calendar.SEPTEMBER);
		now.set(Calendar.DAY_OF_MONTH, 1);
		new_school_year.setStartDate(new Date(now.getTimeInMillis()));
		/* prepare the title */
		StringBuffer sb = new StringBuffer();
		sb.append("Σχολική Χρονία ");
		sb.append(now.get(Calendar.YEAR));
		sb.append(" - ");
		sb.append((now.get(Calendar.YEAR) + 1));
		new_school_year.setTitle(sb.toString());
		/* prepare the to date */
		now.set(Calendar.MONTH, Calendar.JUNE);
		now.set(Calendar.DAY_OF_MONTH, 30);
		now.add(Calendar.YEAR, 1);
		new_school_year.setEndDate(new Date(now.getTimeInMillis()));

		new_school_year.setId(new Long(System.currentTimeMillis()));

		setActiveSchoolYear(new_school_year);
		return BEGIN_OUTCOME;
	}

	/**
	 * @see gr.sch.ira.minoas.session.ISchoolYearAdmin#saveSchoolYear()
	 */
	public String saveSchoolYear() {
		return saveSchoolYear(getActiveSchoolYear());
	}

	public String saveSchoolYear(SchoolYear schoolYear) {
		try {
			info("trying to save/update school year #0", schoolYear);
			if (schoolYear.getId() != null && em.find(SchoolYear.class, new Long(schoolYear.getId())) == null) {
				em.persist(schoolYear);
			}
			else {
				em.merge(schoolYear);
			}
			em.flush();
			info("school year #0 has been successufully saved/updated.", schoolYear);
			return SUCCESS_OUTCOME;
		}
		catch (Exception ex) {
			error("failed to save/update school year #0 due to an exception", ex, schoolYear);
			return FAILURE_OUTCOME;
		}
	}

	/**
	 * @see gr.sch.ira.minoas.session.ISchoolYearAdmin#search()
	 */
	@Factory(value = "availableSchoolYears")
	public String search() {
		this.schoolYears = coreSearching.getAvailableSchoolYears();
		setActiveSchoolYear(null);
		return SUCCESS_OUTCOME;
	}

	/**
	 * @param activeSchoolYear the activeSchoolYear to set
	 */
	public void setActiveSchoolYear(SchoolYear activeSchoolYear) {
		this.activeSchoolYear = activeSchoolYear;
	}

}
