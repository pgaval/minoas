package gr.sch.ira.minoas.seam.components.reports;

import gr.sch.ira.minoas.model.core.SchoolYear;
import gr.sch.ira.minoas.model.employement.Disposal;
import gr.sch.ira.minoas.model.employement.Employment;
import gr.sch.ira.minoas.model.employement.EmploymentType;
import gr.sch.ira.minoas.model.employement.Leave;
import gr.sch.ira.minoas.model.employement.Secondment;
import gr.sch.ira.minoas.model.employement.ServiceAllocation;
import gr.sch.ira.minoas.seam.components.home.SchoolHome;
import gr.sch.ira.minoas.seam.components.reports.resource.SchoolUniversalEmploymentItem;
import gr.sch.ira.minoas.seam.components.reports.resource.SchoolUniversalEmployments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
@Name(value = "schoolEmploymentsReport")
@Scope(ScopeType.CONVERSATION)
public class SchoolEmploymentsReport extends BaseReport {

	private DateFormat dateFormat;

	@DataModel(value = "schoolEmployments")
	private SchoolUniversalEmployments schoolEmployments;

	@In
	private SchoolHome schoolHome;

	/**
	 * 
	 */
	public SchoolEmploymentsReport() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}

	private String constructComment(Disposal disposal) {
		StringBuffer sb = new StringBuffer();
		sb.append("Διάθεση απο τις ");
		sb.append(this.dateFormat.format(disposal.getEstablished()));
		sb.append(" εως και ");
		sb.append(this.dateFormat.format(disposal.getDueTo()));
		sb.append(" στο/σε ");
		sb.append(disposal.getDisposalUnit().getTitle());
		sb.append(" για ");
		sb.append(disposal.getHours());
		sb.append(" ωρες και ");
		sb.append(disposal.getDays());
		sb.append(" ημέρες. ");
		return sb.toString();

	}

	private String constructComment(Leave leave) {
		StringBuffer sb = new StringBuffer();
		sb.append("Άδεια τύπου ");
		sb.append(getLocalizedMessage(leave.getLeaveType().getKey()));
		sb.append(" απο τις ");
		sb.append(this.dateFormat.format(leave.getEstablished()));
		sb.append(" εως και ");
		sb.append(this.dateFormat.format(leave.getDueTo()));
		sb.append(". ");
		return sb.toString();
	}

	private String constructComment(Secondment secondment) {
		StringBuffer sb = new StringBuffer();
		sb.append("Αποσπάση απο τις ");
		sb.append(this.dateFormat.format(secondment.getEstablished()));
		sb.append(" στο/σε ");
		sb.append(secondment.getTargetUnit().getTitle());
		sb.append(". ");
		return sb.toString();
	}

	private String constructComment(ServiceAllocation serviceAllocation) {
		StringBuffer sb = new StringBuffer();
		sb.append("Θητεία τύπου ");
		sb.append(getLocalizedMessage(serviceAllocation.getServiceType().getKey()));
		sb.append(" απο τις ");
		sb.append(this.dateFormat.format(serviceAllocation.getEstablished()));
		sb.append(" εως και ");
		sb.append(this.dateFormat.format(serviceAllocation.getDueTo()));
		sb.append(" στο/σε ");
		sb.append(serviceAllocation.getServiceUnit().getTitle());
		sb.append(". ");
		return sb.toString();
	}

	private String constructIncomingComment(Secondment secondment) {
		StringBuffer sb = new StringBuffer();
		sb.append("Αποσπασμένος/νη στην μονάδα απο τις ");
		sb.append(this.dateFormat.format(secondment.getEstablished()));
		sb.append(" απο το/την ");
		sb.append(secondment.getSourceUnit().getTitle());
		sb.append(". ");
		return sb.toString();
	}

	private String constructIncomingComment(ServiceAllocation serviceAllocation) {
		StringBuffer sb = new StringBuffer();
		sb.append("Υπηρετή στην μονάδα με θητεία τύπου ");
		sb.append(getLocalizedMessage(serviceAllocation.getServiceType().getKey()));
		sb.append(" απο τις ");
		sb.append(this.dateFormat.format(serviceAllocation.getEstablished()));
		sb.append(" εως και ");
		sb.append(this.dateFormat.format(serviceAllocation.getDueTo()));
		sb.append(" απο το/την ");
		sb.append(serviceAllocation.getSourceUnit().getTitle());
		sb.append(". ");
		return sb.toString();
	}

	private String constructIncommingComment(Disposal disposal) {
		StringBuffer sb = new StringBuffer();
		sb.append("Υπηρετή στην μονάδα λόγω διάθεσης απο τις ");
		sb.append(this.dateFormat.format(disposal.getEstablished()));
		sb.append(" εως και ");
		sb.append(this.dateFormat.format(disposal.getDueTo()));
		sb.append(" απο την μονάδα ");
		sb.append(disposal.getAffectedEmployment().getSchool().getTitle());
		sb.append(" για ");
		sb.append(disposal.getHours());
		sb.append(" ώρες και ");
		sb.append(disposal.getDays());
		sb.append(" ημέρες. ");
		return sb.toString();

	}

	public void generateReport() {

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * CURRENT PROBLEM THERE ARE SECONDMENT WITHOUT EMPLOYMENT (FROM OTHER PYSDE) AND DISPOSALS (NO AFFECTED EMPLOYMENT CAUSE THE 
		 * DISPOSAL OVERRIDES A SECONDMENT).
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

		long started = System.currentTimeMillis(), finished;
		info("generating report school employment analysis");
		Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);

		SchoolYear activeSchoolYear = getCoreSearching().getActiveSchoolYear(getEntityManager());

		SchoolUniversalEmployments reportData = new SchoolUniversalEmployments(getCoreSearching()
				.getSchoolTeachingRequirement(getEntityManager(), schoolHome.getInstance(), activeSchoolYear),
				getCoreSearching().getSpecializationGroups(activeSchoolYear, getEntityManager()));

		/* ************************************************************************************ */
		/* REGULAR EMPLOYMENTS 																	*/
		/* ************************************************************************************ */

		Collection<Employment> schoolRegularEmployments = getCoreSearching().getSchoolEmploymentsOfType(
				getEntityManager(), activeSchoolYear, schoolHome.getInstance(), EmploymentType.REGULAR);

		for (Employment employment : schoolRegularEmployments) {
			SchoolUniversalEmploymentItem item = new SchoolUniversalEmploymentItem(employment);

			/* check if the employment is associated with an secondment */
			Secondment s = getCoreSearching().getEmployeeActiveSecondment(getEntityManager(), employment.getEmployee(),
					today);
			if (s != null) {
				/* the employment is overriden by a secondment */
				item.setEmployeeFinalWorkingHours(0);
				item.setEmploymentComment(constructComment(s));
			}

			/* check if the employment is associated with a disposal */
			Collection<Disposal> disposals = getCoreSearching().getEmployeeActiveDisposals(getEntityManager(),
					employment.getEmployee(), today);
			if (disposals != null && disposals.size() > 0) {
				StringBuffer sb = new StringBuffer();
				int total_hours = 0;
				for (Disposal d : disposals) {
					sb.append(constructComment(d));
					total_hours += d.getHours();
				}
				item.setEmployeeFinalWorkingHours(item.getEmployeeFinalWorkingHours() - total_hours);
				item.setEmploymentComment(sb.toString());
			}

			/* check if the employment is associated with a service allocation */
			ServiceAllocation serviceAllocation = getCoreSearching().getEmployeeActiveServiceAllocation(
					getEntityManager(), employment.getEmployee(), today);
			if (serviceAllocation != null) {
				/* the employment is overriden by a service allocation */
				item.setEmployeeFinalWorkingHours(serviceAllocation.getWorkingHoursOnRegularPosition());
				item.setEmploymentComment(constructComment(serviceAllocation));
			}

			/* check if the employment is associated with a leave */
			Leave leave = getCoreSearching()
					.getEmployeeActiveLeave(getEntityManager(), employment.getEmployee(), today);
			if (leave != null) {
				/* the employment is overriden by a service allocation */
				item.setEmployeeFinalWorkingHours(0);
				item.setEmploymentComment(constructComment(leave));
			}

			reportData.add(item);
		}

		/* ************************************************************************************ */
		/* DEPUTY EMPLOYMENTS 																	*/
		/* ************************************************************************************ */
		Collection<Employment> schoolDeputyEmployments = getCoreSearching().getSchoolEmploymentsOfType(
				getEntityManager(), activeSchoolYear, schoolHome.getInstance(), EmploymentType.DEPUTY);

		for (Employment employment : schoolDeputyEmployments) {
			SchoolUniversalEmploymentItem item = new SchoolUniversalEmploymentItem(employment);

			/* check if the employment is associated with a disposal */
			Collection<Disposal> disposals = getCoreSearching().getEmployeeActiveDisposals(getEntityManager(),
					employment.getEmployee(), today);
			if (disposals != null && disposals.size() > 0) {
				StringBuffer sb = new StringBuffer();
				int total_hours = 0;
				for (Disposal d : disposals) {
					sb.append(constructComment(d));
					total_hours += d.getHours();
				}
				item.setEmployeeFinalWorkingHours(item.getEmployeeFinalWorkingHours() - total_hours);
				item.setEmploymentComment(sb.toString());
			}

			/* check if the employment is associated with a service allocation */
			ServiceAllocation serviceAllocation = getCoreSearching().getEmployeeActiveServiceAllocation(
					getEntityManager(), employment.getEmployee(), today);
			if (serviceAllocation != null) {
				/* the employment is overriden by a service allocation */
				item.setEmployeeFinalWorkingHours(serviceAllocation.getWorkingHoursOnRegularPosition());
				item.setEmploymentComment(constructComment(serviceAllocation));
			}

			/* check if the employment is associated with a leave */
			Leave leave = getCoreSearching()
					.getEmployeeActiveLeave(getEntityManager(), employment.getEmployee(), today);
			if (leave != null) {
				/* the employment is overriden by a service allocation */
				item.setEmployeeFinalWorkingHours(0);
				item.setEmploymentComment(constructComment(leave));
			}

			reportData.add(item);
		}

		/* ************************************************************************************ */
		/* HOURLY BASED EMPLOYMENTS 																	*/
		/* ************************************************************************************ */
		Collection<Employment> schoolHourlyBasedEmployments = getCoreSearching().getSchoolEmploymentsOfType(
				getEntityManager(), activeSchoolYear, schoolHome.getInstance(), EmploymentType.HOURLYBASED);

		for (Employment employment : schoolHourlyBasedEmployments) {
			SchoolUniversalEmploymentItem item = new SchoolUniversalEmploymentItem(employment);
			item.setEmployeeMandatoryHours(11);
			/* check if the employment is associated with a leave */
			Leave leave = getCoreSearching()
					.getEmployeeActiveLeave(getEntityManager(), employment.getEmployee(), today);
			if (leave != null) {
				/* the employment is overriden by a service allocation */
				item.setEmployeeFinalWorkingHours(0);
				item.setEmploymentComment(constructComment(leave));
			}

			reportData.add(item);
		}

		/* now handle incomming employees */

		/* secondments */
		Collection<Secondment> incomingSecondents = getCoreSearching().getSchoolSecondments(getEntityManager(),
				schoolHome.getInstance(), activeSchoolYear, today);
		for (Secondment secondment : incomingSecondents) {
			System.err.println(secondment.getEmployee().toString() + " " + secondment.getAffectedEmployment());
			try {
				SchoolUniversalEmploymentItem item = secondment.getAffectedEmployment() != null ? new SchoolUniversalEmploymentItem(
						secondment)
						: new SchoolUniversalEmploymentItem(secondment.getEmployee());
				item.setEmploymentComment(constructIncomingComment(secondment));

				if (secondment.getAffectedEmployment() == null) {
					/* this is a special case of secondement when it is not
					 * associated with an employment
					 * 
					 * This shit happens with secondments from other PYSDE
					 */

					if (secondment.getFinalWorkingHours() != null)
						item.setEmployeeFinalWorkingHours(secondment.getFinalWorkingHours());
					if (secondment.getMandatoryWorkingHours() != null)
						item.setEmployeeMandatoryHours(secondment.getMandatoryWorkingHours());
					//item.setEmployeeEmploymentEstablishedDate(employment.getEstablished());
					//item.setEmployeeEmploymentTerminatedDate(employment.getTerminated());

				}

				/* check if the employment is associated with a disposal */
				Collection<Disposal> disposals = getCoreSearching().getEmployeeActiveDisposals(getEntityManager(),
						secondment.getEmployee(), today);
				if (disposals != null && disposals.size() > 0) {
					StringBuffer sb = new StringBuffer();
					int total_hours = 0;
					for (Disposal d : disposals) {
						sb.append(constructComment(d));
						total_hours += d.getHours();
					}
					item.setEmployeeFinalWorkingHours(item.getEmployeeFinalWorkingHours() - total_hours);
					item.setEmploymentComment(item.getEmploymentComment().concat(sb.toString()));
				}

				/* check if the employment is associated with a leave */
				Leave leave = getCoreSearching().getEmployeeActiveLeave(getEntityManager(), secondment.getEmployee(),
						today);
				if (leave != null) {
					/* the employment is overriden by a service allocation */
					item.setEmployeeFinalWorkingHours(0);
					item.setEmploymentComment(item.getEmploymentComment().concat(constructComment(leave)));
				}

				reportData.add(item);
			} catch (Exception ex) {
				System.err.println(ex);
				error("error with secondment #1", incomingSecondents);
				continue;
			}
		}

		Collection<Disposal> incomingDisposal = getCoreSearching().getSchoolDisposals(getEntityManager(),
				schoolHome.getInstance(), activeSchoolYear, today, null);
		for (Disposal disposal : incomingDisposal) {
			try {
				SchoolUniversalEmploymentItem item = new SchoolUniversalEmploymentItem(disposal);
				item.setEmploymentComment(constructIncommingComment(disposal));
				item.setEmployeeFinalWorkingHours(disposal.getHours());

				/* check if the employment is associated with a leave */
				Leave leave = getCoreSearching().getEmployeeActiveLeave(getEntityManager(), disposal.getEmployee(),
						today);
				if (leave != null) {
					/* the employment is overriden by a service allocation */
					item.setEmployeeFinalWorkingHours(0);
					item.setEmploymentComment(item.getEmploymentComment().concat(constructComment(leave)));
				}

				reportData.add(item);
			} catch (Exception ex) {
				continue;
			}
		}

		Collection<ServiceAllocation> incomingServiceAllocation = getCoreSearching()
				.getSchoolIncomingServiceAllocations(getEntityManager(), schoolHome.getInstance(), today);
		for (ServiceAllocation serviceAllocation : incomingServiceAllocation) {

			/* check if the incoming service allocation has regular position in the school. 
			 * If so, then it has been already handled.
			 */
			if (serviceAllocation.getServiceUnit().getId().equals(serviceAllocation.getSourceUnit().getId())) {
				continue;
			}
			SchoolUniversalEmploymentItem item = new SchoolUniversalEmploymentItem(serviceAllocation);
			item.setEmploymentComment(constructIncomingComment(serviceAllocation));
			item.setEmployeeFinalWorkingHours(serviceAllocation.getWorkingHoursOnServicingPosition());

			/* check if the employment is associated with a leave */
			Leave leave = getCoreSearching().getEmployeeActiveLeave(getEntityManager(),
					serviceAllocation.getEmployee(), today);
			if (leave != null) {
				/* the employment is overriden by a service allocation */
				item.setEmployeeFinalWorkingHours(0);
				item.setEmploymentComment(item.getEmploymentComment().concat(constructComment(leave)));
			}

			reportData.add(item);

		}

		setSchoolEmployments(reportData);
		finished = System.currentTimeMillis();
		info("report has been generated in #0 [ms]", (finished - started));

	}

	/**
	 * @return the schoolEmployments
	 */
	public SchoolUniversalEmployments getSchoolEmployments() {
		return schoolEmployments;
	}

	/**
	 * @param schoolEmployments the schoolEmployments to set
	 */
	public void setSchoolEmployments(SchoolUniversalEmployments schoolEmployments) {
		this.schoolEmployments = schoolEmployments;
	}

}
