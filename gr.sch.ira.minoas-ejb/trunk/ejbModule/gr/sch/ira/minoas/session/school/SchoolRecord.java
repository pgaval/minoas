/**
 * 
 */
package gr.sch.ira.minoas.session.school;

import java.util.List;

import gr.sch.ira.minoas.core.session.CoreSearching;
import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employee.Employment;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

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
public class SchoolRecord extends BaseStatefulSeamComponentImpl implements
		ISchoolRecord {
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;

	@DataModel(scope=ScopeType.PAGE)
	private List<Employment> schoolRegularEmployments;
	
	@DataModel(scope=ScopeType.PAGE)
	private List<Employee> schoolSecondmentEmployments;
	
	@EJB
	private CoreSearching coreSearching;
	
	
	@In(required=true)
	@Out(required=true)
	private School selectedSchool;

	/**
	 * @see gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl#create()
	 */
	@Create
	@Override
	public void create() {
		// TODO Auto-generated method stub
		super.create();
	}
	
	/**
	 * @see gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl#destroy()
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
		return SUCCESS_OUTCOME;
	}
	

}
