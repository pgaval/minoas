/**
 * 
 */
package gr.sch.ira.minoas.seam.components.suggesters;

import gr.sch.ira.minoas.core.CoreUtils;
import gr.sch.ira.minoas.model.core.Unit;
import gr.sch.ira.minoas.model.security.Principal;
import gr.sch.ira.minoas.seam.components.BaseDatabaseAwareSeamComponent;

import java.util.Collection;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;

/**
 * @author slavikos
 *
 */
@Name("principalSuggester")
public class PrincipalSuggester extends BaseDatabaseAwareSeamComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Transactional
	public Collection<Principal> suggest(Object unit_search_pattern) {
		return getEntityManager()
				.createQuery(
						"SELECT p FROM Principal p WHERE LOWER(p.realName) LIKE LOWER(:search_pattern) OR LOWER(p.username) LIKE LOWER(:search_pattern)")
				.setParameter("search_pattern", CoreUtils.getSearchPattern(String.valueOf(unit_search_pattern)))
				.getResultList();
	}

}
