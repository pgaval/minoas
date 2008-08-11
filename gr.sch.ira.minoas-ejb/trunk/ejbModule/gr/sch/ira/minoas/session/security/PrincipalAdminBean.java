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

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;

/**
 * @author slavikos
 *
 */
@Name("principalAdmin")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, IPrincipalAdmin.class })
@Scope(ScopeType.CONVERSATION)
public class PrincipalAdminBean extends BaseStatefulSeamComponentImpl implements IPrincipalAdmin {

	

	/**
	 * @see gr.sch.ira.minoas.session.security.IPrincipalAdmin#cancelPrincipal()
	 */
	public String cancelPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IPrincipalAdmin#savePrincipal()
	 */
	public String savePrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IPrincipalAdmin#newPrincipal()
	 */
	public String newPrincipal() {
		info("requested the creation of a new principal.");
		setActivePrincipal(null);
		return "start";
	}

	/**
	 * @return the activePrincipal
	 */
	public Principal getActivePrincipal() {
		return activePrincipal;
	}

	/**
	 * @param activePrincipal the activePrincipal to set
	 */
	public void setActivePrincipal(Principal activePrincipal) {
		this.activePrincipal = activePrincipal;
	}

	@EJB
	private CoreSearching coreSearching;
	
	@In(value="principal", create=true)
	@Out(value="principal", required=false)
	private Principal activePrincipal;
	/**
	 * @see gr.sch.ira.minoas.session.security.IPrincipalAdmin#editPrincipal()
	 */
	public String editPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IPrincipalAdmin#search()
	 */
	public String search() {
		principals = coreSearching.searchPrincipals(getSearchString());
		selectedPrinicipal = null;
		return null;
	}

	private String searchString;
	
	
	@DataModel
	private List<Principal> principals;
	
	@DataModelSelection
	private Principal selectedPrinicipal;

	/**
	 * @return the searchString
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @param searchString the searchString to set
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	/**
	 * @return the principals
	 */
	public List<Principal> getPrincipals() {
		return principals;
	}

	/**
	 * @param principals the principals to set
	 */
	public void setPrincipals(List<Principal> principals) {
		this.principals = principals;
	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IPrincipalAdmin#selectPrincipal()
	 */
	public String selectPrincipal() {
		setActivePrincipal(this.selectedPrinicipal);
		info("principal #0 selected for management", getActivePrincipal());
		return null;
	}

	
	
	
}
