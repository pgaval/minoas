/**
 * 
 */
package gr.sch.ira.minoas.converters;

import gr.sch.ira.minoas.model.core.Specialization;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.faces.Converter;
import org.jboss.seam.framework.EntityController;


/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
@Converter
@Name("specializationConverter")
public class SpecializationConverter extends EntityController implements javax.faces.convert.Converter {

	
	/**
	 * 
	 */
	public SpecializationConverter() {
		super();
	}

	/**
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return getEntityManager().find(Specialization.class, arg2);
	}

	/**
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		
		if(arg2 instanceof Specialization) {
			return ((Specialization)arg2).getId();
		} else {
			return "<UNKNOWN SPECIALIZATION>";
		}
	}

	

}
