package gr.sch.ira.minoas.core.session;

import gr.sch.ira.minoas.model.core.OrganizationalOffice;
import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.core.Specialization;
import gr.sch.ira.minoas.model.security.Principal;
import gr.sch.ira.minoas.model.security.Role;
import gr.sch.ira.minoas.model.security.RoleGroup;
import gr.sch.ira.minoas.model.voids.TeachingRequirement;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

@Remote
public interface CoreSearching {
	public List<TeachingRequirement> searchVoids(School school);

	public List<TeachingRequirement> searchVoids(School school,
			Specialization specialization, int minHours);
	
	/**
	 * Searches for schools with a given pattern.
	 * @param school_search_pattern
	 * @return
	 */
	public List<School> searchShools(String school_search_pattern);
	
	public List<School> searchShools(String school_search_pattern, String regionCode);
	
	public Specialization getSpecialization(String id);
	
	public List<Role> searchRoles(String role_search_pattern);
	
	public Role findRole(String roleID);
	
	public RoleGroup findRoleGroup(String roleGroupID);
	
	public List<RoleGroup> searchRoleGroups(String roleGroup_search_pattern);
	
	public List<Role> getAvailableRoles();
	
	public List<RoleGroup> getAvailableRoleGroups();
	
	public List<Principal> searchPrincipals(String search_string);
	
	public List<Principal> searchPrincipals(EntityManager entityManager, String search_string);
	
	public List<OrganizationalOffice> searchOrganizationalOffices(String search_string);
	
	public List<OrganizationalOffice> searchOrganizationalOffices(EntityManager entityManager, String search_string);
	
	public List<OrganizationalOffice> getAvailableOrganizationalOffices();

}
