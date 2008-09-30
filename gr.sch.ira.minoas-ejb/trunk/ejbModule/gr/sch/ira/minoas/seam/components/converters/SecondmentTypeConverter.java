/**
 * 
 */
package gr.sch.ira.minoas.seam.components.converters;

import gr.sch.ira.minoas.model.employement.SecondmentType;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.annotations.faces.Converter;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Converter
@Name("secondmentTypeConverter")
@Transactional
public class SecondmentTypeConverter extends DatabaseAwareBaseConverter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4196332658706303684L;

	/**
	 * 
	 */
	public SecondmentTypeConverter() {
		super();
	}

	/**
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.String)
	 */
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null) {
			Long secondmentTypeID = null;
			try {
				secondmentTypeID = Long.valueOf(value.toString());
			} catch (Exception ex) {
				warn(
						"failed to convert value '#0' to a valid secondment type, due to an exception '#1'",
						value, ex.getMessage());
				return null;
			}
			return getMinoasDatabase().find(SecondmentType.class,
					secondmentTypeID);
		} else
			return null;
	}

	/**
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.Object)
	 */
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			if (value instanceof SecondmentType) {
				return ((SecondmentType) value).getTitle();
			} else {
				return value.toString();
			}
		} else
			return null;
	}

}
