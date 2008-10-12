/**
 * 
 */
package gr.sch.ira.minoas.session.school;

import gr.sch.ira.minoas.model.core.SchoolYear;
import gr.sch.ira.minoas.model.core.Unit;
import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employement.Employment;
import gr.sch.ira.minoas.model.employement.EmploymentType;
import gr.sch.ira.minoas.model.employement.Secondment;
import gr.sch.ira.minoas.seam.components.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.seam.components.CoreSearching;
import gr.sch.ira.minoas.seam.components.IBaseStatefulSeamComponent;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.FlushModeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.security.Restrict;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Name("schoolRecord")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, ISchoolRecord.class })
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SchoolRecordBean extends BaseStatefulSeamComponentImpl implements ISchoolRecord {

	
	/**
	 * @return the schoolDeputyEmployments
	 */
	public Collection<Employment> getSchoolDeputyEmployments() {
		return schoolDeputyEmployments;
	}

	/**
	 * @param schoolDeputyEmployments the schoolDeputyEmployments to set
	 */
	public void setSchoolDeputyEmployments(Collection<Employment> schoolDeputyEmployments) {
		this.schoolDeputyEmployments = schoolDeputyEmployments;
	}

	/**
	 * @return the schoolRegularEmployments
	 */
	public Collection<Employment> getSchoolRegularEmployments() {
		return schoolRegularEmployments;
	}

	/**
	 * @param schoolRegularEmployments the schoolRegularEmployments to set
	 */
	public void setSchoolRegularEmployments(Collection<Employment> schoolRegularEmployments) {
		this.schoolRegularEmployments = schoolRegularEmployments;
	}

	/**
	 * @return the schoolSecondments
	 */
	public Collection<Secondment> getSchoolSecondments() {
		return schoolSecondments;
	}

	/**
	 * @param schoolSecondments the schoolSecondments to set
	 */
	public void setSchoolSecondments(Collection<Secondment> schoolSecondments) {
		this.schoolSecondments = schoolSecondments;
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.ISchoolRecord#searchSchoolSecondments()
	 */
	@Factory(value="schoolSecondments")
	public void searchSchoolSecondments() {
		setSchoolSecondments(coreSearching.getUnitSecondments(getActiveSchoolYear(), getActiveSchool()));
	}

	@In(value = "activeSchool", required = false)
	@Out(value = "activeSchool", required = false, scope = ScopeType.CONVERSATION)
	private Unit activeSchool;

	@In(value = "coreSearching")
	private CoreSearching coreSearching;

	@DataModel(scope = ScopeType.PAGE)
	private Collection<Employment> schoolDeputyEmployments;

	@DataModel(scope = ScopeType.PAGE)
	private Collection<Employment> schoolRegularEmployments;

	@DataModel(scope = ScopeType.PAGE)
	private Collection<Secondment> schoolSecondments;

	/**
	 * @see gr.sch.ira.minoas.session.school.ISchoolRecord#begin()
	 */
	@Begin(flushMode = FlushModeType.AUTO, pageflow = "school-record", nested = true)
	public void begin() {
		info("school record conversation begun, with '#0' active school unit.", getActiveSchool());
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.BaseStatefulSeamComponentImpl#create()
	 */
	@Create
	@Override
	public void create() {
		super.create();
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.BaseStatefulSeamComponentImpl#destroy()
	 */
	@Remove
	@Destroy
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@End
	public void end() {
		info("school record '#0' conversation has ended.", getActiveSchool());
	}

	/**
	 * @return the activeSchool
	 */
	public Unit getActiveSchool() {
		return activeSchool;
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.ISchoolRecord#hasActiveSchool()
	 */
	public boolean hasActiveSchool() {
		return activeSchool != null;
	}

	@Factory(value = "schoolDeputyEmployments")
	public void searchDeputyEmployments() {
		setSchoolDeputyEmployments(coreSearching.getSchoolEmploymentsOfType(coreSearching.getActiveSchoolYear(),
				getActiveSchool(), EmploymentType.DEPUTY));
	}

	@Factory(value = "schoolRegularEmployments")
	public void searchRegularEmployments() {
		setSchoolRegularEmployments(coreSearching.getSchoolEmploymentsOfType(coreSearching.getActiveSchoolYear(),
				getActiveSchool(), EmploymentType.REGULAR));
	}

	/**
	 * @param activeSchool the activeSchool to set
	 */
	public void setActiveSchool(Unit activeSchool) {
		this.activeSchool = activeSchool;
	}

}
