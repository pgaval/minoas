package gr.sch.ira.minoas.seam.components.reports;

import gr.sch.ira.minoas.model.core.SpecializationGroup;
import gr.sch.ira.minoas.model.core.Unit;
import gr.sch.ira.minoas.model.employement.Secondment;
import gr.sch.ira.minoas.model.employement.SecondmentType;
import gr.sch.ira.minoas.model.transfers.OutstandingImprovement;
import gr.sch.ira.minoas.seam.components.criteria.DateSearchType;
import gr.sch.ira.minoas.seam.components.criteria.OutstandingImprovementCriteria;
import gr.sch.ira.minoas.seam.components.criteria.SecondmentCriteria;
import gr.sch.ira.minoas.seam.components.reports.resource.OutstandingImprovementItem;
import gr.sch.ira.minoas.seam.components.reports.resource.SecondmentItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.persistence.Query;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * @version $Id$
 */
@Name("outstandingImprovementsReport")
@Scope(ScopeType.CONVERSATION)
public class OutstandingImprovementsReport extends BaseReport {

	@DataModel(value = "reportData")
	private Collection<OutstandingImprovementItem> reportData = null;

	@In(required = true, create=true)
	private OutstandingImprovementCriteria outstandingImprovementCriteria;

	/**
	 * 
	 */
	public OutstandingImprovementsReport() {
	}

	public void generateReport() throws Exception {

		Character improvementRegion = getOutstandingImprovementCriteria().getImprovementRegion();
		SpecializationGroup specializationGroup = getOutstandingImprovementCriteria().getSpecializationGroup();

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT i FROM OutstandingImprovement i WHERE i.isProcessed IS FALSE ");
		if (improvementRegion != null) {
			sb.append(" AND i.improvementRegionCode=:improvementRegion ");
		}
		if (specializationGroup != null) {
			sb
					.append(" AND EXISTS (SELECT g FROM SpecializationGroup g WHERE g=:specializationGroup AND i.employee.lastSpecialization MEMBER OF g.specializations) ");
		}
		sb.append(" ORDER BY i.employee.lastName");

		Query q = getEntityManager().createQuery(sb.toString());
		if (improvementRegion != null) {
			q.setParameter("improvementRegion", improvementRegion);
		}
		if (specializationGroup != null) {
			q.setParameter("specializationGroup", specializationGroup);
		}
		Collection<OutstandingImprovement> improvements = q.getResultList();
		info("found totally #0 outstanding improvement(s) matching criteria", improvements.size());
		reportData = new ArrayList<OutstandingImprovementItem>(improvements.size());
		for (OutstandingImprovement improvement : improvements) {
			reportData.add(new OutstandingImprovementItem(improvement));
		}
	}

	/**
	 * @return the outstandingImprovementCriteria
	 */
	public OutstandingImprovementCriteria getOutstandingImprovementCriteria() {
		return outstandingImprovementCriteria;
	}

	/**
	 * @param outstandingImprovementCriteria the outstandingImprovementCriteria to set
	 */
	public void setOutstandingImprovementCriteria(OutstandingImprovementCriteria outstandingImprovementCriteria) {
		this.outstandingImprovementCriteria = outstandingImprovementCriteria;
	}

	/**
	 * @return the reportData
	 */
	public Collection<OutstandingImprovementItem> getReportData() {
		return reportData;
	}

	/**
	 * @param reportData the reportData to set
	 */
	public void setReportData(Collection<OutstandingImprovementItem> reportData) {
		this.reportData = reportData;
	}

}
