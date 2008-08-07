/**
 * 
 */
package gr.sch.ira.minoas.session.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

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

import gr.sch.ira.minoas.model.security.Role;
import gr.sch.ira.minoas.model.security.RoleGroup;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;
import gr.sch.ira.minoas.session.IBaseStatefulSeamComponent;

/**
 * @author slavikos
 * 
 */
@Name("roleGroupManagement")
@Stateful
@Restrict("#{identity.loggedIn}")
@Local( { IBaseStatefulSeamComponent.class, IRoleGroupManagement.class })
@Scope(ScopeType.CONVERSATION)
public class RoleGroupManagementBean extends BaseStatefulSeamComponentImpl
		implements IRoleGroupManagement {

	@In(required = false)
	@Out(required = false)
	private List<Role> newRoleGroupRolesList;

	@In(required = false)
	@Out(required = false)
	private List<Role> availableRolesList;

	/**
	 * @see gr.sch.ira.minoas.session.security.IRoleGroupManagement#constructNewRoleGroup()
	 */
	@Factory("newRoleGroup")
	@SuppressWarnings("unchecked")
	public void constructNewRoleGroup() {
		info("constructing new instance of empty role group as requested.");
		this.newRoleGroup = new RoleGroup("", "");
		this.newRoleGroupRolesList = new ArrayList<Role>();

		this.availableRolesList = em.createQuery("SELECT r from Role r ")
				.getResultList();
	}

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@DataModel
	private List<RoleGroup> roleGroups;

	@DataModelSelection
	private RoleGroup roleGroup;

	private String searchString;

	@In(required = false)
	@Out(required = false)
	private RoleGroup newRoleGroup;

	/**
	 * @see gr.sch.ira.minoas.session.security.IRoleGroupManagement#search()
	 */
	@Factory("roleGroups")
	@SuppressWarnings("unchecked")
	@Begin(join = true)
	public void search() {
		String searchPattern = getSearchPattern();
		info("searching for role groups with #0 search pattern", searchPattern);
		this.roleGroups = em
				.createQuery(
						"SELECT r from RoleGroup r WHERE lower(r.id) LIKE :search_pattern")
				.setParameter("search_pattern", searchPattern).getResultList();
		info("found totally #0 role group(s).", this.roleGroups.size());
	}

	public String getSearchPattern() {
		return getSearchString() == null ? "%" : '%' + getSearchString()
				.toLowerCase().replace('*', '%') + '%';
	}

	public String getSearchString() {
		return searchString;
	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IRoleGroupManagement#setSearchString(java.lang.String)
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IRoleGroupManagement#updateRoleGroup()
	 */
	public void updateRoleGroup() {

	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IRoleGroupManagement#removeRoleGroup()
	 */
	public void removeRoleGroup() {
		info("about to remove role group #0.", roleGroup);
		RoleGroup role_to_remove = em.merge(this.roleGroup);
		em.remove(role_to_remove);
		info("role group #0 has been removed.", this.roleGroup);
		search();
	}

	/**
	 * @see gr.sch.ira.minoas.session.security.IRoleGroupManagement#saveRoleGroup()
	 */
	public void saveRoleGroup() {
		info("about to save new role group #0", this.newRoleGroup);
		RoleGroup existing_role_group = em.find(RoleGroup.class, newRoleGroup
				.getId());
		if (existing_role_group == null) {
			em.persist(this.newRoleGroup);
			this.newRoleGroup.setRoles(new LinkedHashSet<Role>());

			for (Role role : newRoleGroupRolesList) {
				newRoleGroup.getRoles().add(role);
				em.persist(role);

			}
			info(
					"role group #0, successfully saved, with totally #1 roles registered.",
					this.newRoleGroup, this.newRoleGroupRolesList.size());
			// em.flush();
			// constructNewRole();
			constructNewRoleGroup();
			search();
		} else {
			warn(
					"ignoring save request of role #0, since that role already exists",
					this.newRoleGroup);
			facesMessages.add("role fdsf", newRoleGroup.getId());
		}

	}

	public void selectRoleGroup() {

	}

}