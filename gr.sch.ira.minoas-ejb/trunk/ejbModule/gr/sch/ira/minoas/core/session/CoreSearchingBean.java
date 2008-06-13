package gr.sch.ira.minoas.core.session;

import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.core.Specialization;
import gr.sch.ira.minoas.model.voids.Void;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

public @Stateless
class CoreSearchingBean implements CoreSearching {
	@PersistenceContext
	private EntityManager em;

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchVoids(gr.sch.ira.minoas.model.core.School,
	 *      gr.sch.ira.minoas.model.core.Specialization, int)
	 */
	public Collection<Void> searchVoids(School school,
			Specialization specialization, int minHours) {
		return em
				.createQuery(
						"SELECT v from Void v WHERE v.school = :school ORDER BY (v.specialisation.id)")
				.setParameter("school", school).getResultList();
	}

	/**
	 * @see gr.sch.ira.minoas.core.session.CoreSearching#searchVoids(gr.sch.ira.minoas.model.core.School)
	 */
	public Collection<Void> searchVoids(School school) {
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
						"SELECT s from School s WHERE lower(s.title) LIKE :search_pattern")
				.setParameter("search_pattern", school_search_pattern).getResultList();
	}

}
