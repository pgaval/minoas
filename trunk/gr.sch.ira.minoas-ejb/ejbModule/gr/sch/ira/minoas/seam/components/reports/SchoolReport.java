package gr.sch.ira.minoas.seam.components.reports;

import gr.sch.ira.minoas.model.core.SchoolYear;
import gr.sch.ira.minoas.model.employement.EmploymentType;
import gr.sch.ira.minoas.model.employement.ServiceAllocationType;
import gr.sch.ira.minoas.seam.components.home.SchoolHome;
import gr.sch.ira.minoas.seam.components.reports.resource.DisposalReportItem;
import gr.sch.ira.minoas.seam.components.reports.resource.EmployeeReportItem;
import gr.sch.ira.minoas.seam.components.reports.resource.EmploymentReportItem;
import gr.sch.ira.minoas.seam.components.reports.resource.LeaveReportItem;
import gr.sch.ira.minoas.seam.components.reports.resource.SecondmentItem;
import gr.sch.ira.minoas.seam.components.reports.resource.ServiceAllocationItem;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;

/**
 * @author <a href="mailto:fsla@forthnet.gr">Filippos Slavik</a>
 * @version $Id$
 */
@Name(value = "schoolReport")
@Scope(ScopeType.CONVERSATION)
public class SchoolReport extends BaseReport {

	@DataModel(value = "incomingDisposals")
	private Collection<DisposalReportItem> incomingDisposals;

	@DataModel(value = "incomingSecondments")
	private Collection<SecondmentItem> incomingSecondments;

	@DataModel(value = "incomingServiceAllocations")
	private Collection<ServiceAllocationItem> incomingServiceAllocations;

	@DataModel(value = "schoolOutgoingDisposals")
	private Collection<DisposalReportItem> outcomingDisposals;

	@DataModel(value = "schoolOutgoingSecondments")
	private Collection<SecondmentItem> outcomingSecondments;

	@DataModel(value = "schoolOutgoingServiceAllocations")
	private Collection<ServiceAllocationItem> outcomingServiceAllocations;

	@DataModel(value = "schoolChiefs")
	private Collection<ServiceAllocationItem> schoolChiefs;

	@DataModel(value = "schoolDeputyEmployees")
	private Collection<EmployeeReportItem> schoolDeputyEmployees;

	@DataModel(value = "schoolDeputyEmployments")
	private Collection<EmploymentReportItem> schoolDeputyEmployments;

	@In
	private SchoolHome schoolHome;

	@DataModel(value = "schoolHourlyBasedEmployments")
	private Collection<EmploymentReportItem> schoolHourlyBasedEmployments;

	@DataModel(value = "schoolLeaves")
	private Collection<LeaveReportItem> schoolLeaves;

	@DataModel(value = "schoolRegularEmployments")
	private Collection<EmploymentReportItem> schoolRegularEmployments;

	@DataModel(value = "schoolRegularsEmployees")
	private Collection<EmployeeReportItem> schoolRegularsEmployees;

	/**
	 * 
	 */
	public SchoolReport() {
	}

	public void generateReport() {

		long started = System.currentTimeMillis(), finished;
		info("generating report ");
		Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);

		SchoolYear activeSchoolYear = getCoreSearching().getActiveSchoolYear(getEntityManager());
		schoolChiefs = convertServiceAllocationCollection(getCoreSearching().getSchoolIncomingServiceAllocationsOfType(
				getEntityManager(), schoolHome.getInstance(), today,
				Arrays.asList(ServiceAllocationType.SCHOOL_HEADMASTER, ServiceAllocationType.SCHOOL_SUBHEADMASTER)));

		schoolRegularsEmployees = convertEmployeeCollection(getCoreSearching()
				.getSchoolActiveEmployeesOfEmploymentType(getEntityManager(), schoolHome.getInstance(),
						getCoreSearching().getActiveSchoolYear(getEntityManager()), today, EmploymentType.REGULAR));

		schoolDeputyEmployees = convertEmployeeCollection(getCoreSearching().getSchoolActiveEmployeesOfEmploymentType(
				getEntityManager(), schoolHome.getInstance(),
				getCoreSearching().getActiveSchoolYear(getEntityManager()), today, EmploymentType.DEPUTY));

		incomingSecondments = convertSecondmentCollection(getCoreSearching().getSchoolSecondments(getEntityManager(),
				schoolHome.getInstance(), activeSchoolYear, today));

		incomingServiceAllocations = convertServiceAllocationCollection(getCoreSearching()
				.getSchoolIncomingServiceAllocations(getEntityManager(), schoolHome.getInstance(), today));

		incomingDisposals = convertDisposalCollection(getCoreSearching().getSchoolDisposals(getEntityManager(),
				schoolHome.getInstance(), activeSchoolYear, today, null));

		outcomingSecondments = convertSecondmentCollection(getCoreSearching().getSchoolOutgoingSecondments(
				getEntityManager(), schoolHome.getInstance(), activeSchoolYear, today));

		schoolLeaves = convertLeaveCollection(getCoreSearching().getSchoolLeaves(getEntityManager(),
				schoolHome.getInstance(), activeSchoolYear, today, null));

		schoolRegularEmployments = convertEmploymentCollection(getCoreSearching().getSchoolEmploymentsOfType(
				getEntityManager(), activeSchoolYear, schoolHome.getInstance(), EmploymentType.REGULAR));

		schoolDeputyEmployments = convertEmploymentCollection(getCoreSearching().getSchoolEmploymentsOfType(
				getEntityManager(), activeSchoolYear, schoolHome.getInstance(), EmploymentType.DEPUTY));

		schoolHourlyBasedEmployments = convertEmploymentCollection(getCoreSearching().getSchoolEmploymentsOfType(
				getEntityManager(), activeSchoolYear, schoolHome.getInstance(), EmploymentType.HOURLYBASED));

		outcomingServiceAllocations = convertServiceAllocationCollection(getCoreSearching()
				.getSchoolReallyOutgoingServiceAllocations(getEntityManager(), schoolHome.getInstance(), today, null));

		finished = System.currentTimeMillis();
		info("report has been generated in #0 [ms]", (finished - started));

	}

	/**
	 * @return the incomingDisposals
	 */
	public Collection<DisposalReportItem> getIncomingDisposals() {
		return incomingDisposals;
	}

	/**
	 * @return the incomingSecondments
	 */
	public Collection<SecondmentItem> getIncomingSecondments() {
		return incomingSecondments;
	}

	/**
	 * @return the incomingServiceAllocations
	 */
	public Collection<ServiceAllocationItem> getIncomingServiceAllocations() {
		return incomingServiceAllocations;
	}

	/**
	 * @return the outcomingDisposals
	 */
	public Collection<DisposalReportItem> getOutcomingDisposals() {
		return outcomingDisposals;
	}

	/**
	 * @return the outcomingSecondments
	 */
	public Collection<SecondmentItem> getOutcomingSecondments() {
		return outcomingSecondments;
	}

	/**
	 * @return the outcomingServiceAllocations
	 */
	public Collection<ServiceAllocationItem> getOutcomingServiceAllocations() {
		return outcomingServiceAllocations;
	}

	/**
	 * @return the schoolChiefs
	 */
	public Collection<ServiceAllocationItem> getSchoolChiefs() {
		return schoolChiefs;
	}

	/**
	 * @return the schoolDeputysEmployees
	 */
	public Collection<EmployeeReportItem> getSchoolDeputyEmployees() {
		return schoolDeputyEmployees;
	}

	/**
	 * @return the schoolDeputyEmployments
	 */
	public Collection<EmploymentReportItem> getSchoolDeputyEmployments() {
		return schoolDeputyEmployments;
	}

	/**
	 * @return the schoolHourlyBasedEmployments
	 */
	public Collection<EmploymentReportItem> getSchoolHourlyBasedEmployments() {
		return schoolHourlyBasedEmployments;
	}

	/**
	 * @return the schoolLeaves
	 */
	public Collection<LeaveReportItem> getSchoolLeaves() {
		return schoolLeaves;
	}

	/**
	 * @return the schoolRegularEmployments
	 */
	public Collection<EmploymentReportItem> getSchoolRegularEmployments() {
		return schoolRegularEmployments;
	}

	/**
	 * @return the schoolRegularsEmployees
	 */
	public Collection<EmployeeReportItem> getSchoolRegularsEmployees() {
		return schoolRegularsEmployees;
	}

	/**
	 * @param incomingDisposals the incomingDisposals to set
	 */
	public void setIncomingDisposals(Collection<DisposalReportItem> incomingDisposals) {
		this.incomingDisposals = incomingDisposals;
	}

	/**
	 * @param incomingSecondments the incomingSecondments to set
	 */
	public void setIncomingSecondments(Collection<SecondmentItem> incomingSecondments) {
		this.incomingSecondments = incomingSecondments;
	}

	/**
	 * @param incomingServiceAllocations the incomingServiceAllocations to set
	 */
	public void setIncomingServiceAllocations(Collection<ServiceAllocationItem> incomingServiceAllocations) {
		this.incomingServiceAllocations = incomingServiceAllocations;
	}

	/**
	 * @param outcomingDisposals the outcomingDisposals to set
	 */
	public void setOutcomingDisposals(Collection<DisposalReportItem> outcomingDisposals) {
		this.outcomingDisposals = outcomingDisposals;
	}

	/**
	 * @param outcomingSecondments the outcomingSecondments to set
	 */
	public void setOutcomingSecondments(Collection<SecondmentItem> outcomingSecondments) {
		this.outcomingSecondments = outcomingSecondments;
	}

	/**
	 * @param outcomingServiceAllocations the outcomingServiceAllocations to set
	 */
	public void setOutcomingServiceAllocations(Collection<ServiceAllocationItem> outcomingServiceAllocations) {
		this.outcomingServiceAllocations = outcomingServiceAllocations;
	}

	/**
	 * @param schoolChiefs the schoolChiefs to set
	 */
	public void setSchoolChiefs(Collection<ServiceAllocationItem> schoolChiefs) {
		this.schoolChiefs = schoolChiefs;
	}

	/**
	 * @param schoolDeputysEmployees the schoolDeputysEmployees to set
	 */
	public void setSchoolDeputyEmployees(Collection<EmployeeReportItem> schoolDeputysEmployees) {
		this.schoolDeputyEmployees = schoolDeputysEmployees;
	}

	/**
	 * @param schoolDeputyEmployments the schoolDeputyEmployments to set
	 */
	public void setSchoolDeputyEmployments(Collection<EmploymentReportItem> schoolDeputyEmployments) {
		this.schoolDeputyEmployments = schoolDeputyEmployments;
	}

	/**
	 * @param schoolHourlyBasedEmployments the schoolHourlyBasedEmployments to set
	 */
	public void setSchoolHourlyBasedEmployments(Collection<EmploymentReportItem> schoolHourlyBasedEmployments) {
		this.schoolHourlyBasedEmployments = schoolHourlyBasedEmployments;
	}

	/**
	 * @param schoolLeaves the schoolLeaves to set
	 */
	public void setSchoolLeaves(Collection<LeaveReportItem> schoolLeaves) {
		this.schoolLeaves = schoolLeaves;
	}

	/**
	 * @param schoolRegularEmployments the schoolRegularEmployments to set
	 */
	public void setSchoolRegularEmployments(Collection<EmploymentReportItem> schoolRegularEmployments) {
		this.schoolRegularEmployments = schoolRegularEmployments;
	}

	/**
	 * @param schoolRegularsEmployees the schoolRegularsEmployees to set
	 */
	public void setSchoolRegularsEmployees(Collection<EmployeeReportItem> schoolRegularsEmployees) {
		this.schoolRegularsEmployees = schoolRegularsEmployees;
	}

}
