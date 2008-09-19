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
import gr.sch.ira.minoas.session.BaseStatelessSeamComponentImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("coreSearching")
@Scope(ScopeType.EVENT)
@AutoCreate
public class CoreSearchingBean extends BaseStatelessSeamComponentImpl implements
		CoreSearching {

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#getSchoolDeputyEmployments(gr.sch.ira.minoas.model.core.SchoolYear, gr.sch.ira.minoas.model.core.School)
	 */
	@SuppressWarnings("unchecked")
	public List<DeputyEmployment> getSchoolDeputyEmployments(SchoolYear schoolyear, School school) {
		info("fetching all deputy employments in school unit #0 during school year #1", school, schoolyear);
		List<DeputyEmployment>  return_value  = getEntityManager().createQuery("SELECT e FROM DeputyEmployment e WHERE e.school=:school AND e.schoolYear=:schoolyear ORDER BY e.specialization.id, e.employee.lastName").setParameter("school", school).setParameter("schoolyear", schoolyear).getResultList();
		info("found totally #0 deputy employment(s) in school unit #1 and school year #2.", return_value.size(), school, schoolyear);
		return return_value;
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#getSchoolRegularEmployments(javax.persistence.EntityManager, gr.sch.ira.minoas.model.core.SchoolYear, gr.sch.ira.minoas.model.core.School)
	 */
	@SuppressWarnings("unchecked")
	public List<RegularEmployment> getSchoolRegularEmployments(EntityManager entityManager, SchoolYear schoolyear,
			School school) {
		info("fetching all regular employments in school unit #0 during school year #1", school, schoolyear);
		EntityManager em = getEntityManager(entityManager);
		List<RegularEmployment>  return_value  = em.createQuery("SELECT e FROM RegularEmployment e WHERE e.school=:school AND e.schoolYear=:schoolyear ORDER BY e.specialization.id, e.employee.lastName").setParameter("school", school).setParameter("schoolyear", schoolyear).getResultList();
		info("found totally #0 regular employment(s) in school unit #1 and school year #2.", return_value.size(), school, schoolyear);
		return return_value;
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#getSchoolRegularEmployments(gr.sch.ira.minoas.model.core.SchoolYear, gr.sch.ira.minoas.model.core.School)
	 */
	public List<RegularEmployment> getSchoolRegularEmployments(SchoolYear schoolyear, School school) {
		return getSchoolRegularEmployments(null, schoolyear, school);
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#getAvailableSchoolYears()
	 */
	public List<SchoolYear> getAvailableSchoolYears() {
		return getAvailableSchoolYears(null);
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#getAvailableSchoolYears(javax.persistence.EntityManager)
	 */
	@SuppressWarnings("unchecked")
	public List<SchoolYear> getAvailableSchoolYears(EntityManager entityManager) {
		debug("fetching all available school years");
		EntityManager em = getEntityManager(entityManager);
		List<SchoolYear> return_value = em.createQuery(
				"SELECT r from SchoolYear r").getResultList();
		debug("found totally #0 school years(s).", return_value.size());
		return return_value;
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#searchPrincipals(javax.persistence.EntityManager,
	 *      java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Principal> searchPrincipals(EntityManager entityManager,
			String search_string) {
		EntityManager em = getEntityManager(entityManager);
		String pattern = getSearchPattern(search_string);
		return em
				.createQuery(
						"SELECT p FROM Principal p WHERE lower(p.username) LIKE :search_pattern")
				.setParameter("search_pattern", pattern).getResultList();
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#searchPrincipals(java.lang.String)
	 */
	public List<Principal> searchPrincipals(String search_string) {
		return searchPrincipals(null, search_string);
	}

	@In
	private EntityManager minoasDatabase;

	protected EntityManager getEntityManager(EntityManager entityManager) {
		return entityManager != null ? entityManager : getEntityManager();
	}

	protected EntityManager getEntityManager() {
		return this.minoasDatabase;
	}

	
	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#searchShools(java.lang.String,
	 *      java.lang.String)
	 */
	public List<School> searchShools(String school_search_pattern,
			String regionCode) {
		throw new RuntimeException("not implemented yet");
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#searchShools(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<School> searchShools(String school_search_pattern) {
		String pattern = getSearchPattern(school_search_pattern);
		info("searching for schools with #0 search pattern.", pattern);
		List return_value =  getEntityManager()
				.createQuery(
						"SELECT s from School s WHERE lower(s.title) LIKE :search_pattern AND s.ministryCode != '0000000'")
				.setParameter("search_pattern", pattern)
				.getResultList();
		info("found totally #0 school(s).", return_value.size());
		return return_value;
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#getSpecialization(java.lang.String)
	 */
	public Specialization getSpecialization(String id) {
		return getEntityManager().find(Specialization.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Role> searchRoles(String role_search_pattern) {
		String pattern = getSearchPattern(role_search_pattern);
		info("searching for roles with #0 search pattern", pattern);
		List return_value =getEntityManager().createQuery(
				"SELECT r from Role r WHERE lower(r.id) LIKE :search_pattern")
				.setParameter("search_pattern", pattern).getResultList();
		info("found totally #0 role(s).", return_value.size());
		return return_value;
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#findRole(java.lang.String)
	 */
	public Role findRole(String roleID) {
		return getEntityManager().find(Role.class, roleID);
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#findRoleGroup(java.lang.String)
	 */
	public RoleGroup findRoleGroup(String roleGroupID) {
		return getEntityManager().find(RoleGroup.class, roleGroupID);
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#searchRoleGroups(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<RoleGroup> searchRoleGroups(String roleGroup_search_pattern) {
		String pattern = getSearchPattern(roleGroup_search_pattern);
		info("searching for role groups with #0 search pattern", pattern);
		List return_value = getEntityManager()
				.createQuery(
						"SELECT r from RoleGroup r WHERE lower(r.id) LIKE :search_pattern")
				.setParameter("search_pattern", pattern).getResultList();
		info("found totally #0 role group(s).", return_value.size());
		return return_value;
	}

	public static final String getSearchPattern(String searchString) {
		return searchString == null ? "%" : '%' + searchString.toLowerCase()
				.replace('*', '%') + '%';
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#getAvailableRoleGroups()
	 */
	@SuppressWarnings("unchecked")
	public List<RoleGroup> getAvailableRoleGroups() {
		debug("fetching all available role groups");
		List return_value = getEntityManager().createQuery("SELECT r from RoleGroup r")
				.getResultList();
		debug("found totally #0 role group(s).", return_value.size());
		return return_value;
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#getAvailableRoles()
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getAvailableRoles() {
		debug("fetching all available groups");
		List return_value = getEntityManager().createQuery("SELECT r from Role r")
				.getResultList();
		debug("found totally #0 role(s).", return_value.size());
		return return_value;

	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#getAvailableOrganizationalOffices()
	 */
	@SuppressWarnings("unchecked")
	public List<OrganizationalOffice> getAvailableOrganizationalOffices() {
		debug("fetching all available organizational offices");
		List return_value = getEntityManager().createQuery(
				"SELECT r from OrganizationalOffice r").getResultList();
		debug("found totally #0 organizational office(s).", return_value.size());
		return return_value;
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#searchOrganizationalOffices(javax.persistence.EntityManager,
	 *      java.lang.String)
	 */
	public List<OrganizationalOffice> searchOrganizationalOffices(
			EntityManager entityManager, String search_string) {
		throw new RuntimeException("not implemented yet");
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#searchOrganizationalOffices(java.lang.String)
	 */
	public List<OrganizationalOffice> searchOrganizationalOffices(
			String search_string) {
		throw new RuntimeException("not implemented yet");
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#getActiveSchoolYear()
	 */
	public SchoolYear getActiveSchoolYear() {
		return getActiveSchoolYear(null);
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.CoreSearching#getActiveSchoolYear(javax.persistence.EntityManager)
	 */
	public SchoolYear getActiveSchoolYear(EntityManager entityManager) {
		try {
			debug("trying to find active school year");
			EntityManager em = getEntityManager(entityManager);
			return (SchoolYear) em
					.createQuery(
							"SELECT s from SchoolYear s WHERE s.currentSchoolYear IS TRUE")
					.getSingleResult();
		} catch (NoResultException nre) {
			warn("no active school year found");
			return null;
		}
	}

}