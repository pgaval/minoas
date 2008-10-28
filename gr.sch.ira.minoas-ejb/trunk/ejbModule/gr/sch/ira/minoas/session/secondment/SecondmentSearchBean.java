package gr.sch.ira.minoas.session.secondment;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateful;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;

import gr.sch.ira.minoas.model.INamedQueryConstants;
import gr.sch.ira.minoas.model.core.SchoolYear;
import gr.sch.ira.minoas.model.employement.Secondment;
import gr.sch.ira.minoas.seam.components.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.seam.components.IBaseStatefulSeamComponent;


@Name("secondmentSearch")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, ISecondmentSearch.class })
public class SecondmentSearchBean extends BaseStatefulSeamComponentImpl implements ISecondmentSearch {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@DataModel(scope = ScopeType.PAGE, value = "secondments")
	private List<Secondment> secondments;
	
	@DataModelSelection("secondments")
	private Secondment selectedSecondment;
	
	@Out(scope=ScopeType.CONVERSATION, value="activeSecondment", required=false)
	private Secondment activeSecondment;

	/**
	 * @see gr.sch.ira.minoas.session.secondment.ISecondmentSearch#searchActiveSecondments()
	 */
	@SuppressWarnings("unchecked")
	public String searchActiveSecondments() {
		SchoolYear schoolYear = getActiveSchoolYear();
		info("searching active secondments during '#0' school year.", schoolYear);
		List<Secondment> result = getMinoasDatabase().createNamedQuery(INamedQueryConstants.NAMED_QUERY_SECONDMENT_FIND_ALL_ACTIVE).setParameter(INamedQueryConstants.QUERY_PARAMETER_SCHOOL_YEAR,schoolYear).getResultList();
		info("found totally '#0' active secondments during '#1' school year.", result.size(), schoolYear);
		setSecondments(result);
		return SUCCESS_OUTCOME;
	}

	/**
	 * @see gr.sch.ira.minoas.session.secondment.ISecondmentSearch#searchSchoolIncomingSecondments()
	 */
	public String searchSchoolIncomingSecondments() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see gr.sch.ira.minoas.session.secondment.ISecondmentSearch#searchSchoolOutgoingSecondments()
	 */
	public String searchSchoolOutgoingSecondments() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see gr.sch.ira.minoas.session.secondment.ISecondmentSearch#searchWithinPYSDESecondments()
	 */
	public String searchWithinPYSDESecondments() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the secondments
	 */
	public List<Secondment> getSecondments() {
		return secondments;
	}

	/**
	 * @param secondments the secondments to set
	 */
	public void setSecondments(List<Secondment> secondments) {
		this.secondments = secondments;
	}

	/**
	 * @return the selectedSecondment
	 */
	public Secondment getSelectedSecondment() {
		return selectedSecondment;
	}

	/**
	 * @param selectedSecondment the selectedSecondment to set
	 */
	public void setSelectedSecondment(Secondment selectedSecondment) {
		this.selectedSecondment = selectedSecondment;
	}

	/**
	 * @return the activeSecondment
	 */
	public Secondment getActiveSecondment() {
		return activeSecondment;
	}

	/**
	 * @param activeSecondment the activeSecondment to set
	 */
	public void setActiveSecondment(Secondment activeSecondment) {
		this.activeSecondment = activeSecondment;
	}

}
