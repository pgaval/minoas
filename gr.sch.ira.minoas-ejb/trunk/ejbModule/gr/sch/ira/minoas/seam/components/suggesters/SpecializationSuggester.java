/**
 * 
 */
package gr.sch.ira.minoas.seam.components.suggesters;

import gr.sch.ira.minoas.model.core.Specialization;

import java.util.Collection;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.framework.EntityController;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Name("specializationSuggester")
public class SpecializationSuggester {

	@In
	private EntityManager minoasDatabase;
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Collection<Specialization> suggest(Object specialization_search_pattern) {
		return minoasDatabase
				.createQuery(
						"SELECT s from Specialization s WHERE lower(s.id) LIKE LOWER(:search_pattern + '%') OR LOWER(s.title) LIKE LOWER(:search_pattern + '%')")
				.setParameter("search_pattern", specialization_search_pattern).getResultList();
	}

}
