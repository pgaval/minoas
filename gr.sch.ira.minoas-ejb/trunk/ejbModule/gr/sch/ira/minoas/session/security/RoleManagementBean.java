/**
 * 
 */
package gr.sch.ira.minoas.session.security;

import gr.sch.ira.minoas.model.security.Role;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;

import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Factory;
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
@Name("roleManagement")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, IRoleManagement.class })
@Scope(ScopeType.CONVERSATION)
public class RoleManagementBean extends BaseStatefulSeamComponentImpl implements
		IRoleManagement {

	
	private String searchString;
	
	@DataModel
	private Collection<Role> roles;
	
	
	/**
	 * @see gr.sch.ira.minoas.session.security.IRoleManagement#selectRole()
	 */
	public void selectRole() {
		info("role #0 selected for management", this.role);
	}

	@DataModelSelection
	private Role role;

	@In(required = false)
	@Out(required = false)
	private Role newRole;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	/**
	 * @see gr.sch.ira.minoas.session.security.IRoleManagement#removeRole(gr.sch.ira.minoas.model.security.Role)
	 */
	public void removeRole() {
		info("trying to remove role #0 from system.", role);
		em.remove(this.role);
		info("removed succesfully role #0 from system.", role);
		search();
	}
	
	public void saveRole() {
		info("about to save new role #0", this.newRole);
		Role existing_role = em.find(Role.class, newRole.getId());
		if (existing_role == null) {
			em.persist(this.newRole);
			info("role #0, successfully saved.", this.newRole);
			em.flush();
			constructNewRole();
			search();
		} else {
			warn(
					"ignoring save request of role #0, since that role already exists",
					this.newRole);
			facesMessages.add("role fdsf", newRole.getId());
			
		}
	}

	
	@Factory("roles")
	@SuppressWarnings("unchecked")
	@Begin(join=true)
	public void search() {
		String searchPattern = getSearchPattern();
		info("searching for roles with #0 search pattern", searchPattern);
		this.roles = em
				.createQuery(
						"SELECT r from Role r WHERE lower(r.id) LIKE :search_pattern")
				.setParameter("search_pattern", searchPattern)
				.getResultList();
	}
	
	@Factory("newRole")
	public void constructNewRole() {
		info("constructing new instance of role");
		this.newRole = new Role("", "");
	}
	
	public String getSearchPattern() {
		return getSearchString() == null ? "%" : '%' + getSearchString()
				.toLowerCase().replace('*', '%') + '%';
	}

	
	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
}
