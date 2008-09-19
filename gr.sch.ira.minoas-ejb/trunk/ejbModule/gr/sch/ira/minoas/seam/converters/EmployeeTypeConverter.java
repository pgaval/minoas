/**
 * 
 */
package gr.sch.ira.minoas.seam.converters;

import gr.sch.ira.minoas.model.employee.DeputyEmployee;
import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employee.HourlyPaidEmployee;
import gr.sch.ira.minoas.model.employee.RegularEmployee;
import gr.sch.ira.minoas.model.employement.RegularEmployment;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.jboss.seam.annotations.Name;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@org.jboss.seam.annotations.faces.Converter
@Name("employeeTypeConverter")
public class EmployeeTypeConverter implements Converter {

	public static final String REGULAR_EMPLOYEE_TYPE_STR = "Μόνιμος";

	public static final String DEPUTY_EMPLOYEE_TYPE_STR = "Αναπληρωτής";

	public static final String HOURLY_PAID_EMPLOYEE_TYPE_STR = "Ωρομίσθιος";

	/**
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.String)
	 */
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return null;
	}

	/**
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.Object)
	 */
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && value instanceof Employee) {
			if (value instanceof RegularEmployee)
				return REGULAR_EMPLOYEE_TYPE_STR;
			else if (value instanceof DeputyEmployee)
				return DEPUTY_EMPLOYEE_TYPE_STR;
			else if (value instanceof HourlyPaidEmployee)
				return HOURLY_PAID_EMPLOYEE_TYPE_STR;
			else
				return null;
		} else
			return null;
	}

}