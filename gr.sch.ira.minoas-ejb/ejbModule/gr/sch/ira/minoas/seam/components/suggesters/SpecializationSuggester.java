/**
 * 
 */
package gr.sch.ira.minoas.seam.components.suggesters;

import gr.sch.ira.minoas.core.CoreUtils;
import gr.sch.ira.minoas.model.core.Specialization;

import java.util.Collection;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Name("specializationSuggester")
public class SpecializationSuggester extends BaseSuggester{

	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Collection<Specialization> suggest(Object specialization_search_pattern) {
		return getMinoasDatabase()
				.createQuery(
						"SELECT s from Specialization s WHERE lower(s.id) LIKE LOWER(:search_pattern) OR LOWER(s.title) LIKE LOWER(:search_pattern)")
				.setParameter("search_pattern", CoreUtils.getSearchPattern(String.valueOf(specialization_search_pattern))).getResultList();
	}

}
