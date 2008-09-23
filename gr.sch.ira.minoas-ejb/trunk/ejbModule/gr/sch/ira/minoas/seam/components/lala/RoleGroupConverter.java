/**
 * 
 */
package gr.sch.ira.minoas.seam.components.lala;

import gr.sch.ira.minoas.model.security.RoleGroup;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.annotations.faces.Converter;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Converter
@Name("roleGroupConverter")
@Transactional
public class RoleGroupConverter implements javax.faces.convert.Converter {

	
	@In
	private EntityManager minoasDatabase;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2019700284340330696L;

	/**
	 * 
	 */
	public RoleGroupConverter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
	 * javax.faces.component.UIComponent, java.lang.String)
	 */
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			return minoasDatabase.find(RoleGroup.class, value);
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
			if (value instanceof RoleGroup) {
				return ((RoleGroup) value).getId();
			}
			else {
				return value.toString();
			}
		}
		else
			return null;
	}

}
