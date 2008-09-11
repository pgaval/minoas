/**
 * 
 */
package gr.sch.ira.minoas.session.employee;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;

import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.seam.components.CoreSearching;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
@Name("employeeSearch")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, IEmployeeSearch.class })
public class EmployeeSearchBean extends BaseStatefulSeamComponentImpl implements
		IEmployeeSearch {
	
	@Out(value = "Employee", required = false, scope = ScopeType.CONVERSATION)
	private Employee activeEmployee;

	@In(value="coreSearching")
	private CoreSearching coreSearching;
	
	@In
	private EntityManager minoasDatabase;

	@DataModel(scope = ScopeType.PAGE, value = "availableEmployees")
	private List<Employee> employees;

	private String searchString;

	@DataModelSelection
	@Out(required = false, scope = ScopeType.CONVERSATION)
	private Employee selectedEmployee; 

}
