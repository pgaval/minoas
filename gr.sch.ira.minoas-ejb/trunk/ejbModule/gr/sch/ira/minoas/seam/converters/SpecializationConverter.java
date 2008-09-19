/**
 * 
 */
package gr.sch.ira.minoas.seam.converters;

import gr.sch.ira.minoas.model.core.Specialization;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.annotations.faces.Converter;
import org.jboss.seam.framework.EntityController;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Converter
@Name("specializationConverter")
@Transactional
public class SpecializationConverter extends EntityController implements javax.faces.convert.Converter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4196332658706303684L;

	/**
	 * 
	 */
	public SpecializationConverter() {
		super();
	}

	/**
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
	 * javax.faces.component.UIComponent, java.lang.String)
	 */
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			return getEntityManager().find(Specialization.class, value);
		}
		else
			return null;
	}

	/**
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
	 * javax.faces.component.UIComponent, java.lang.Object)
	 */
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			if (value instanceof Specialization) {
				return ((Specialization) value).getId();
			}
			else {
				return value.toString();
			}
		}
		else
			return null;
	}

}