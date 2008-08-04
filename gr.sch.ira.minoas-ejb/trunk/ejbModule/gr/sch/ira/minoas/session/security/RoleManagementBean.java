/**
 * 
 */
package gr.sch.ira.minoas.session.security;

import gr.sch.ira.minoas.core.EventConstants;
import gr.sch.ira.minoas.model.security.Role;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;

import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.RaiseEvent;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Name("roleManagement")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, IRoleManagement.class })
@Scope(ScopeType.CONVERSATION)
public class RoleManagementBean extends BaseStatefulSeamComponentImpl implements
		IRoleManagement {

	@In(required=false)
	@Out(required=false)
	private Role role;
	
	/**
	 * @see gr.sch.ira.minoas.session.security.IRoleManagement#selectRole(gr.sch.ira.minoas.model.security.Role)
	 */
	@Begin(nested=true)
	public void selectRole(Role role) {
		this.role = em.merge(role);
		info("selected role #0 for management.", this.role);
	}

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;

	/**
	 * @see gr.sch.ira.minoas.session.security.IRoleManagement#removeRole(gr.sch.ira.minoas.model.security.Role)
	 */
	@End
	@RaiseEvent(EventConstants.EVENT_ROLE_REMOVED)
	public void removeRole() {
		info("trying to remove role #0 from system.", role);
		em.remove(this.role);
		info("removed succesfully role #0 from system.", role);
	}
	
	@End
	public void saveRole() {
		info("about to save role #0", this.role);
	}
	
	
}