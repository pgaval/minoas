package gr.sch.ira.minoas.core.session;

import gr.sch.ira.minoas.model.core.OrganizationalOffice;
import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.core.SchoolYear;
import gr.sch.ira.minoas.model.core.Specialization;
import gr.sch.ira.minoas.model.security.Principal;
import gr.sch.ira.minoas.model.security.Role;
import gr.sch.ira.minoas.model.security.RoleGroup;
import gr.sch.ira.minoas.model.voids.TeachingRequirement;
import gr.sch.ira.minoas.session.BaseStatelessSeamComponentImpl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("coreSearching")
@Scope(ScopeType.EVENT)
@Stateless
@Local(CoreSearching.class)
public class CoreSearchingBean extends BaseStatelessSeamComponentImpl implements
		CoreSearching {

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#getAvailableSchoolYears()
	 */
	public List<SchoolYear> getAvailableSchoolYears() {
		return getAvailableSchoolYears(null);
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#getAvailableSchoolYears(javax.persistence.EntityManager)
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
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchPrincipals(javax.persistence.EntityManager,
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
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchPrincipals(java.lang.String)
	 */
	public List<Principal> searchPrincipals(String search_string) {
		return searchPrincipals(null, search_string);
	}

	@PersistenceContext
	private EntityManager em;

	protected EntityManager getEntityManager(EntityManager entityManager) {
		return entityManager != null ? entityManager : getEntityManager();
	}

	protected EntityManager getEntityManager() {
		return this.em;
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchVoids(gr.sch.ira.minoas.model.core.School,
	 *      gr.sch.ira.minoas.model.core.Specialization, int)
	 */
	@SuppressWarnings("unchecked")
	public List<TeachingRequirement> searchVoids(School school,
			Specialization specialization, int minHours) {
		return em
				.createQuery(
						"SELECT v from TeachingVoid v WHERE v.school = :school ORDER BY (v.specialisation.id)")
				.setParameter("school", school).getResultList();
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchVoids(gr.sch.ira.minoas.model.core.School)
	 */
	public List<TeachingRequirement> searchVoids(School school) {
		throw new RuntimeException("not supported operation.");
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchShools(java.lang.String,
	 *      java.lang.String)
	 */
	public List<School> searchShools(String school_search_pattern,
			String regionCode) {
		throw new RuntimeException("not implemented yet");
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchShools(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<School> searchShools(String school_search_pattern) {
		return em
				.createQuery(
						"SELECT s from School s WHERE lower(s.title) LIKE :search_pattern AND s.ministryCode != '0000000'")
				.setParameter("search_pattern", school_search_pattern)
				.getResultList();
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#getSpecialization(java.lang.String)
	 */
	public Specialization getSpecialization(String id) {
		return em.find(Specialization.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Role> searchRoles(String role_search_pattern) {
		String pattern = getSearchPattern(role_search_pattern);
		info("searching for roles with #0 search pattern", pattern);
		List return_value = em.createQuery(
				"SELECT r from Role r WHERE lower(r.id) LIKE :search_pattern")
				.setParameter("search_pattern", pattern).getResultList();
		info("found totally #0 role(s).", return_value.size());
		return return_value;
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#findRole(java.lang.String)
	 */
	public Role findRole(String roleID) {
		return em.find(Role.class, roleID);
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#findRoleGroup(java.lang.String)
	 */
	public RoleGroup findRoleGroup(String roleGroupID) {
		return em.find(RoleGroup.class, roleGroupID);
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchRoleGroups(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<RoleGroup> searchRoleGroups(String roleGroup_search_pattern) {
		String pattern = getSearchPattern(roleGroup_search_pattern);
		info("searching for role groups with #0 search pattern", pattern);
		List return_value = em
				.createQuery(
						"SELECT r from RoleGroup r WHERE lower(r.id) LIKE :search_pattern")
				.setParameter("search_pattern", pattern).getResultList();
		info("found totally #0 role group(s).", return_value.size());
		return return_value;
	}

	protected String getSearchPattern(String searchString) {
		return searchString == null ? "%" : '%' + searchString.toLowerCase()
				.replace('*', '%') + '%';
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#getAvailableRoleGroups()
	 */
	@SuppressWarnings("unchecked")
	public List<RoleGroup> getAvailableRoleGroups() {
		debug("fetching all available role groups");
		List return_value = em.createQuery("SELECT r from RoleGroup r")
				.getResultList();
		debug("found totally #0 role group(s).", return_value.size());
		return return_value;
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#getAvailableRoles()
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getAvailableRoles() {
		debug("fetching all available groups");
		List return_value = em.createQuery("SELECT r from Role r")
				.getResultList();
		debug("found totally #0 role(s).", return_value.size());
		return return_value;

	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#getAvailableOrganizationalOffices()
	 */
	public List<OrganizationalOffice> getAvailableOrganizationalOffices() {
		debug("fetching all available organizational offices");
		List return_value = em.createQuery(
				"SELECT r from OrganizationalOffice r").getResultList();
		debug("found totally #0 organizational office(s).", return_value.size());
		return return_value;
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchOrganizationalOffices(javax.persistence.EntityManager,
	 *      java.lang.String)
	 */
	public List<OrganizationalOffice> searchOrganizationalOffices(
			EntityManager entityManager, String search_string) {
		throw new RuntimeException("not implemented yet");
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchOrganizationalOffices(java.lang.String)
	 */
	public List<OrganizationalOffice> searchOrganizationalOffices(
			String search_string) {
		throw new RuntimeException("not implemented yet");
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#getActiveSchoolYear()
	 */
	public SchoolYear getActiveSchoolYear() {
		return getActiveSchoolYear(null);
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#getActiveSchoolYear(javax.persistence.EntityManager)
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
