/**
 * 
 */
package gr.sch.ira.minoas.session.security;

import gr.sch.ira.minoas.model.security.Principal;
import gr.sch.ira.minoas.model.security.Role;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.security.Restrict;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Name("roleSearch")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, IRoleSearch.class })
public class RoleSearchBean extends BaseStatefulSeamComponentImpl implements
		IRoleSearch {

	@DataModel
	private Collection<Role> roles;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	private String searchString;

	/**
	 * @see gr.sch.ira.minoas.session.security.IRoleSearch#getSearchPattern()
	 */
	public String getSearchPattern() {
		return getSearchString() == null ? "%" : '%' + getSearchString()
				.toLowerCase().replace('*', '%') + '%';
	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IRoleSearch#search()
	 */
	@SuppressWarnings("unchecked")
	public void search() {
		this.roles = em
				.createQuery(
						"SELECT r from Role r WHERE lower(r.id) LIKE :search_pattern")
				.setParameter("search_pattern", getSearchPattern())
				.getResultList();
	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IRoleSearch#getSearchString()
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IRoleSearch#setSearchString(java.lang.String)
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
}
