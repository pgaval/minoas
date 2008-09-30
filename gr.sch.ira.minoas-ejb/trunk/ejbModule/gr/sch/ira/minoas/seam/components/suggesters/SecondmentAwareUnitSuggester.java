/**
 * 
 */
package gr.sch.ira.minoas.seam.components.suggesters;

import gr.sch.ira.minoas.model.core.Unit;
import gr.sch.ira.minoas.seam.components.BaseDatabaseAwareSeamComponent;

import java.util.Collection;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Transactional;

/**
 * @author slavikos
 *
 */
@Name("secondmentAwareUnitSuggester")
@Scope(ScopeType.CONVERSATION)
public class SecondmentAwareUnitSuggester extends BaseDatabaseAwareSeamComponent {
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Collection<Unit> suggest(Object secondmemt_search_pattern) {
		return getMinoasDatabase().createQuery("SELECT u FROM Unit u WHERE LOWER(u.title) LIKE LOWER(:lala+'%')").setParameter("lala", secondmemt_search_pattern).getResultList();
	}

}
