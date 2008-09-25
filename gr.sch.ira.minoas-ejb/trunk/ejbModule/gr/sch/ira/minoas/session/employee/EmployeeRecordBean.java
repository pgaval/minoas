/**
 * 
 */
package gr.sch.ira.minoas.session.employee;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.jboss.seam.annotations.Conversational;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;

import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employement.RegularEmployment;
import gr.sch.ira.minoas.seam.components.CoreSearching;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;

/**
 * @author slavikos
 *
 */
@Name("employeeRecord")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, IEmployeeRecord.class })
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Conversational
public class EmployeeRecordBean extends BaseStatefulSeamComponentImpl implements
		IEmployeeRecord {
	
	@DataModel("regularEmployments")
	private Collection<RegularEmployment> regularEmployments;
	
	@DataModelSelection("regularEmployments")
	private RegularEmployment selectedRegularEmployment;
	
	@In
	private CoreSearching coreSearching;
	
	@In(required=true)
	private Employee activeEmployee;

	public String search() {
		info("fetching various info for '#0' employee", getActiveEmployee());
		this.regularEmployments = coreSearching.getEmployeeRegularsEmployments(getActiveEmployee());
		return SUCCESS_OUTCOME;
	}

	public Employee getActiveEmployee() {
		return activeEmployee;
	}

	public void setActiveEmployee(Employee activeEmployee) {
		this.activeEmployee = activeEmployee;
	}
	
	

}
