package gr.sch.ira.minoas.seam.components;

import gr.sch.ira.minoas.core.CoreUtils;
import gr.sch.ira.minoas.model.core.OrganizationalOffice;
import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.core.SchoolYear;
import gr.sch.ira.minoas.model.core.Specialization;
import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employement.Employment;
import gr.sch.ira.minoas.model.employement.EmploymentType;
import gr.sch.ira.minoas.model.security.Principal;
import gr.sch.ira.minoas.model.security.Role;
import gr.sch.ira.minoas.model.security.RoleGroup;
import gr.sch.ira.minoas.seam.components.converters.EmployeeTypeConverter;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("coreSearching")
@Scope(ScopeType.EVENT)
@AutoCreate
public class CoreSearching extends BaseDatabaseAwareSeamComponent {

	@SuppressWarnings("unchecked")
	public List<Employment> getSchoolEmployments(SchoolYear schoolyear, School school) {
		info("fetching all employments in school unit #0 during school year #1", school, schoolyear);
		List<Employment> return_value = getEntityManager()
				.createQuery(
						"SELECT e FROM Employment e WHERE e.school=:school AND e.schoolYear=:schoolyear ORDER BY e.specialization.id, e.employee.lastName")
				.setParameter("school", school).setParameter("schoolyear", schoolyear).getResultList();
		info("found totally #0 deputy employment(s) in school unit #1 and school year #2.", return_value.size(),
				school, schoolyear);
		return return_value;
	}

	@SuppressWarnings("unchecked")
	public List<Employment> getSchoolEmploymentsOfType(SchoolYear schoolyear, School school, EmploymentType type) {
		info("fetching all employments of type #3 in school unit #0 during school year #1", school, schoolyear, type);
		List<Employment> return_value = getEntityManager()
				.createQuery(
						"SELECT e FROM Employment e WHERE e.school=:school AND e.schoolYear=:schoolyear AND e.type=:type ORDER BY e.specialization.id, e.employee.lastName")
				.setParameter("school", school).setParameter("schoolyear", schoolyear).setParameter("type", type)
				.getResultList();
		info("found totally #0 deputy employment(s) in school unit #1 and school year #2.", return_value.size(),
				school, schoolyear);
		return return_value;
	}

	public List<SchoolYear> getAvailableSchoolYears() {
		return getAvailableSchoolYears(null);
	}

	@SuppressWarnings("unchecked")
	public List<SchoolYear> getAvailableSchoolYears(EntityManager entityManager) {
		debug("fetching all available school years");
		EntityManager em = getEntityManager(entityManager);
		List<SchoolYear> return_value = em.createQuery("SELECT r from SchoolYear r").getResultList();
		debug("found totally #0 school years(s).", return_value.size());
		return return_value;
	}

	@SuppressWarnings("unchecked")
	public List<Principal> searchPrincipals(EntityManager entityManager, String search_string) {
		EntityManager em = getEntityManager(entityManager);
		String pattern = CoreUtils.getSearchPattern(search_string);
		return em.createQuery("SELECT p FROM Principal p WHERE lower(p.username) LIKE :search_pattern").setParameter(
				"search_pattern", pattern).getResultList();
	}

	public List<Principal> searchPrincipals(String search_string) {
		return searchPrincipals(null, search_string);
	}

	protected EntityManager getEntityManager(EntityManager entityManager) {
		return entityManager != null ? entityManager : getEntityManager();
	}

	protected EntityManager getEntityManager() {
		return this.minoasDatabase;
	}

	public List<School> searchShools(String school_search_pattern, String regionCode) {
		throw new RuntimeException("not implemented yet");
	}

	@SuppressWarnings("unchecked")
	public List<School> searchShools(String school_search_pattern) {
		String pattern = CoreUtils.getSearchPattern(school_search_pattern);
		info("searching for schools with #0 search pattern.", pattern);
		List return_value = getEntityManager().createQuery(
				"SELECT s from School s WHERE lower(s.title) LIKE :search_pattern AND s.ministryCode != '0000000'")
				.setParameter("search_pattern", pattern).getResultList();
		info("found totally #0 school(s).", return_value.size());
		return return_value;
	}

	public Specialization getSpecialization(String id) {
		return getEntityManager().find(Specialization.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Role> searchRoles(String role_search_pattern) {
		String pattern = CoreUtils.getSearchPattern(role_search_pattern);
		info("searching for roles with #0 search pattern", pattern);
		List return_value = getEntityManager().createQuery(
				"SELECT r from Role r WHERE lower(r.id) LIKE :search_pattern").setParameter("search_pattern", pattern)
				.getResultList();
		info("found totally #0 role(s).", return_value.size());
		return return_value;
	}

	public Role findRole(String roleID) {
		return getEntityManager().find(Role.class, roleID);
	}

	public RoleGroup findRoleGroup(String roleGroupID) {
		return getEntityManager().find(RoleGroup.class, roleGroupID);
	}

	@SuppressWarnings("unchecked")
	public List<RoleGroup> searchRoleGroups(String roleGroup_search_pattern) {
		String pattern = CoreUtils.getSearchPattern(roleGroup_search_pattern);
		info("searching for role groups with #0 search pattern", pattern);
		List return_value = getEntityManager().createQuery(
				"SELECT r from RoleGroup r WHERE lower(r.id) LIKE :search_pattern").setParameter("search_pattern",
				pattern).getResultList();
		info("found totally #0 role group(s).", return_value.size());
		return return_value;
	}

	@SuppressWarnings("unchecked")
	public List<RoleGroup> getAvailableRoleGroups() {
		debug("fetching all available role groups");
		List return_value = getEntityManager().createQuery("SELECT r from RoleGroup r").getResultList();
		debug("found totally #0 role group(s).", return_value.size());
		return return_value;
	}

	@SuppressWarnings("unchecked")
	public List<Role> getAvailableRoles() {
		debug("fetching all available groups");
		List return_value = getEntityManager().createQuery("SELECT r from Role r").getResultList();
		debug("found totally #0 role(s).", return_value.size());
		return return_value;

	}

	@SuppressWarnings("unchecked")
	public List<OrganizationalOffice> getAvailableOrganizationalOffices() {
		debug("fetching all available organizational offices");
		List return_value = getEntityManager().createQuery("SELECT r from OrganizationalOffice r").getResultList();
		debug("found totally #0 organizational office(s).", return_value.size());
		return return_value;
	}

	public List<OrganizationalOffice> searchOrganizationalOffices(EntityManager entityManager, String search_string) {
		throw new RuntimeException("not implemented yet");
	}

	public List<OrganizationalOffice> searchOrganizationalOffices(String search_string) {
		throw new RuntimeException("not implemented yet");
	}

	public SchoolYear getActiveSchoolYear() {
		return getActiveSchoolYear(null);
	}

	public SchoolYear getActiveSchoolYear(EntityManager entityManager) {
		try {
			debug("trying to find active school year");
			EntityManager em = getEntityManager(entityManager);
			return (SchoolYear) em.createQuery("SELECT s from SchoolYear s WHERE s.currentSchoolYear IS TRUE")
					.getSingleResult();
		}
		catch (NoResultException nre) {
			warn("no active school year found");
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<Employment> getEmployeeEmployments(Employee employee) {
		List<Employment> result;
		debug("trying to featch all  employments for employee '#0'", employee);
		result = minoasDatabase.createQuery("SELECT e from Employment e WHERE e.employee=:employee").setParameter(
				"employee", employee).getResultList();
		info("found totally '#0' employments for regular employee '#1'.", result.size(), employee);
		return result;

	}

	@SuppressWarnings("unchecked")
	public Collection<Employment> getEmployeeEmploymentsOfType(Employee employee, EmploymentType type) {
		List<Employment> result;
		debug("trying to featch all  employments for employee '#0'", employee);
		result = minoasDatabase.createQuery("SELECT e from Employment e WHERE e.employee=:employee AND e.type=:type")
				.setParameter("employee", employee).setParameter("type", type).getResultList();
		info("found totally '#0' employments for regular employee '#1'.", result.size(), employee);
		return result;

	}
}
