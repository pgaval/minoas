/**
 * 
 */
package gr.sch.ira.minoas.session.security;

import gr.sch.ira.minoas.core.session.CoreSearching;
import gr.sch.ira.minoas.model.security.Principal;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Name("principalAdmin")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, IPrincipalAdmin.class })
@Scope(ScopeType.CONVERSATION)
public class PrincipalAdminBean extends BaseStatefulSeamComponentImpl implements IPrincipalAdmin {

	@In(value = "principal", create = true)
	@Out(value = "principal", required = false)
	private Principal activePrincipal;

	@EJB
	private CoreSearching coreSearching;

	@DataModel
	private List<Principal> principals;

	private String searchString;

	@DataModelSelection
	private Principal selectedPrinicipal;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	/**
	 * @see gr.sch.ira.minoas.session.security.IPrincipalAdmin#cancelPrincipal()
	 */
	public String cancelPrincipal() {
		info("canceling creation of new principal on user's request.");
		setActivePrincipal(null);
		return "cancel";
	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IPrincipalAdmin#editPrincipal()
	 */
	@Begin(nested = true)
	public String editPrincipal() {
		if (selectedPrinicipal != null)
			setActivePrincipal(selectedPrinicipal);
		info("selected #0 principal for editing.", getActivePrincipal());
		return "success";
	}

	/**
	 * @return the activePrincipal
	 */
	public Principal getActivePrincipal() {
		return activePrincipal;
	}

	/**
	 * @return the principals
	 */
	public List<Principal> getPrincipals() {
		return principals;
	}

	/**
	 * @return the searchString
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IPrincipalAdmin#newPrincipal()
	 */
	@Begin(pageflow = "new-principal")
	public String newPrincipal() {
		setActivePrincipal(null);
		return "start";
	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IPrincipalAdmin#savePrincipal()
	 */
	public String savePrincipal() {
		info("saving new principal '#0' on user's request.", getActivePrincipal());
		savePrincipal(getActivePrincipal());
		return "success";
	}

	public void savePrincipal(Principal principal) {
		if (em.find(Principal.class, new String(principal.getUsername())) != null) {
			em.merge(principal);
			info("principal #0 has been updated.", principal);
		}
		else {
			em.persist(principal);
			info("principal #0 has been saved.", principal);
		}
	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IPrincipalAdmin#search()
	 */
	public String search() {
		principals = coreSearching.searchPrincipals(getSearchString());
		selectedPrinicipal = null;
		return null;
	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IPrincipalAdmin#selectPrincipal()
	 */
	public String selectPrincipal() {
		setActivePrincipal(this.selectedPrinicipal);
		info("principal #0 selected for management", getActivePrincipal());
		return null;
	}

	/**
	 * @param activePrincipal the activePrincipal to set
	 */
	public void setActivePrincipal(Principal activePrincipal) {
		this.activePrincipal = activePrincipal;
	}

	/**
	 * @param principals the principals to set
	 */
	public void setPrincipals(List<Principal> principals) {
		this.principals = principals;
	}

	/**
	 * @param searchString the searchString to set
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

}
