/**
 * 
 */
package gr.sch.ira.minoas.session.security;

import gr.sch.ira.minoas.model.security.Principal;
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
@Name("principalSearch")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, IPrincipalSearch.class })
public class PrincipalSearchBean extends BaseStatefulSeamComponentImpl implements IPrincipalSearch {
	
	@DataModel
	private Collection<Principal> principals;
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	private String searchString;
	
	/**
	 * @see gr.sch.ira.minoas.session.security.IPrincipalSearch#getSearchPattern()
	 */
	public String getSearchPattern() {
		return getSearchString() == null ? "%" : '%' + getSearchString().toLowerCase().replace('*', '%') + '%';
	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IPrincipalSearch#search()
	 */
	@SuppressWarnings("unchecked")
	public void search() {
		this.principals = em.createQuery(
				"SELECT p from Principal p WHERE lower(p.username) LIKE :search_pattern")
		.setParameter("search_pattern", getSearchPattern()).getResultList();
	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IPrincipalSearch#getSearchString()
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IPrincipalSearch#setSearchString(java.lang.String)
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
}
