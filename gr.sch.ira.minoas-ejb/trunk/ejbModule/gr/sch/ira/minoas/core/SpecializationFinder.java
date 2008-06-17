/**
 * 
 */
package gr.sch.ira.minoas.core;

import gr.sch.ira.minoas.model.core.Specialization;

import java.util.Collection;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.framework.EntityController;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Name("specializationFinder")
public class SpecializationFinder extends EntityController {

	@Transactional
	public Collection<Specialization> suggestSpecialization(
			Object specialization_search_pattern) {
		return getEntityManager()
				.createQuery(
						"SELECT s from Specialization s WHERE lower(s.id) LIKE lower(:search_pattern + '%') OR lower(s.title) LIKE lower(:search_pattern + '%')")
				.setParameter("search_pattern", specialization_search_pattern)
				.getResultList();
	}

}
