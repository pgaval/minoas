package gr.sch.ira.minoas.core.session;

import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.core.Specialization;
import gr.sch.ira.minoas.model.voids.TeachingVoid;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
@Name("coreSearching")
@Scope(ScopeType.STATELESS)
@Stateless
public 
class CoreSearchingBean implements CoreSearching {
	@PersistenceContext
	private EntityManager em;

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchVoids(gr.sch.ira.minoas.model.core.School,
	 *      gr.sch.ira.minoas.model.core.Specialization, int)
	 */
	public Collection<TeachingVoid> searchVoids(School school,
			Specialization specialization, int minHours) {
		return em
				.createQuery(
						"SELECT v from TeachingVoid v WHERE v.school = :school ORDER BY (v.specialisation.id)")
				.setParameter("school", school).getResultList();
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchVoids(gr.sch.ira.minoas.model.core.School)
	 */
	public Collection<TeachingVoid> searchVoids(School school) {
		return school.getVoids();
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchShools(java.lang.String,
	 *      java.lang.String)
	 */
	public Collection<School> searchShools(String school_search_pattern,
			String regionCode) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchShools(java.lang.String)
	 */
	public Collection<School> searchShools(String school_search_pattern) {
		return em
				.createQuery(
						"SELECT s from School s WHERE lower(s.title) LIKE :search_pattern AND s.ministryCode != '0000000'")
				.setParameter("search_pattern", school_search_pattern).getResultList();
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#getSpecialization(java.lang.String)
	 */
	public Specialization getSpecialization(String id) {
		return em.find(Specialization.class, id);
	}

	

}
