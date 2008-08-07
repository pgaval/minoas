package gr.sch.ira.minoas.core.session;

import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.core.Specialization;
import gr.sch.ira.minoas.model.security.Role;
import gr.sch.ira.minoas.model.security.RoleGroup;
import gr.sch.ira.minoas.model.voids.TeachingRequirement;
import gr.sch.ira.minoas.session.BaseStatelessSeamComponentImpl;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("coreSearching")
@Scope(ScopeType.STATELESS)
@Stateless
@Local(CoreSearching.class)
public class CoreSearchingBean extends BaseStatelessSeamComponentImpl implements CoreSearching {

	@PersistenceContext
	private EntityManager em;

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchVoids(gr.sch.ira.minoas.model.core.School,
	 * gr.sch.ira.minoas.model.core.Specialization, int)
	 */
	@SuppressWarnings("unchecked")
	public Collection<TeachingRequirement> searchVoids(School school, Specialization specialization, int minHours) {
		return em.createQuery("SELECT v from TeachingVoid v WHERE v.school = :school ORDER BY (v.specialisation.id)")
				.setParameter("school", school).getResultList();
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchVoids(gr.sch.ira.minoas.model.core.School)
	 */
	public Collection<TeachingRequirement> searchVoids(School school) {
		throw new RuntimeException("not supported operation.");
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchShools(java.lang.String,
	 * java.lang.String)
	 */
	public Collection<School> searchShools(String school_search_pattern, String regionCode) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchShools(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Collection<School> searchShools(String school_search_pattern) {
		return em.createQuery(
				"SELECT s from School s WHERE lower(s.title) LIKE :search_pattern AND s.ministryCode != '0000000'")
				.setParameter("search_pattern", school_search_pattern).getResultList();
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#getSpecialization(java.lang.String)
	 */
	public Specialization getSpecialization(String id) {
		return em.find(Specialization.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Role> searchRoles(String role_search_pattern) {
		String pattern = getSearchPattern(role_search_pattern); 
		info("searching for roles with #0 search pattern", pattern);
		Collection return_value = em.createQuery("SELECT r from Role r WHERE lower(r.id) LIKE :search_pattern")
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
	public Collection<RoleGroup> searchRoleGroups(String roleGroup_search_pattern) {
		String pattern = getSearchPattern(roleGroup_search_pattern); 
		info("searching for role groups with #0 search pattern", pattern);
		Collection return_value = em.createQuery("SELECT r from RoleGroup r WHERE lower(r.id) LIKE :search_pattern")
				.setParameter("search_pattern", pattern).getResultList();
		info("found totally #0 role group(s).", return_value.size());
		return return_value;
	}
	
	protected String getSearchPattern(String searchString) {
		return searchString == null ? "%" : '%' + searchString
				.toLowerCase().replace('*', '%') + '%';
	}
	

}
