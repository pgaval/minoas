package gr.sch.ira.minoas.seam.components;

import gr.sch.ira.minoas.model.core.OrganizationalOffice;
import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.core.SchoolYear;
import gr.sch.ira.minoas.model.core.Specialization;
import gr.sch.ira.minoas.model.employement.DeputyEmployment;
import gr.sch.ira.minoas.model.employement.RegularEmployment;
import gr.sch.ira.minoas.model.security.Principal;
import gr.sch.ira.minoas.model.security.Role;
import gr.sch.ira.minoas.model.security.RoleGroup;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

@Remote
public interface CoreSearching {
	
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

	public List<SchoolYear> getAvailableSchoolYears();

	public List<SchoolYear> getAvailableSchoolYears(EntityManager entityManager);
	
	public SchoolYear getActiveSchoolYear();
	
	public SchoolYear getActiveSchoolYear(EntityManager entityManager);
	
	public List<RegularEmployment> getSchoolRegularEmployments(EntityManager entityManager, SchoolYear schoolyear, School school);
	
	public List<RegularEmployment> getSchoolRegularEmployments(SchoolYear schoolyear, School school);
	
	
	public List<DeputyEmployment> getSchoolDeputyEmployments(SchoolYear schoolyear, School school);
	
	
	
	
	
	

}
