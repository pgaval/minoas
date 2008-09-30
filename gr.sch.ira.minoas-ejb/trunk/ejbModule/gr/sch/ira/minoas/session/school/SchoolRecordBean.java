/**
 * 
 */
package gr.sch.ira.minoas.session.school;

import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.employement.DeputyEmployment;
import gr.sch.ira.minoas.model.employement.RegularEmployment;
import gr.sch.ira.minoas.seam.components.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.seam.components.CoreSearching;
import gr.sch.ira.minoas.seam.components.IBaseStatefulSeamComponent;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Factory;
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
public class SchoolRecordBean extends BaseStatefulSeamComponentImpl implements
		ISchoolRecord {
	
	@In
	private EntityManager minoasDatabase;

	@DataModel(scope=ScopeType.PAGE)
	private List<RegularEmployment> schoolRegularEmployments;
	
	@DataModel(scope=ScopeType.PAGE)
	private List<DeputyEmployment> schoolDeputyEmployments;
	
	
	
	
	@In(value="coreSearching")
	private CoreSearching coreSearching;
	
	
	@In(required=true)
	@Out(required=true, scope=ScopeType.CONVERSATION)
	private School selectedSchool;

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
	
	@Factory(value="schoolRegularEmployments")
	public String searchRegularEmployments() {
		schoolRegularEmployments = coreSearching.getSchoolRegularEmployments(coreSearching.getActiveSchoolYear(), selectedSchool);
		return SUCCESS_OUTCOME;
	}
	
	@Factory(value="schoolDeputyEmployments")
	public String searchDeputyEmployments() {
		schoolDeputyEmployments = coreSearching.getSchoolDeputyEmployments(coreSearching.getActiveSchoolYear(), selectedSchool);
		return SUCCESS_OUTCOME;
	}
	

}
