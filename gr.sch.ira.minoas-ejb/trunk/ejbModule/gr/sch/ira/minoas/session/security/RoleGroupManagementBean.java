/**
 * 
 */
package gr.sch.ira.minoas.session.security;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;

import gr.sch.ira.minoas.model.security.RoleGroup;
import gr.sch.ira.minoas.session.BaseStatefulSeamComponentImpl;

/**
 * @author slavikos
 *
 */
public class RoleGroupManagementBean extends BaseStatefulSeamComponentImpl {
	
	
	@PersistenceContext
	private EntityManager em;
	
	@DataModel
	private Collection<RoleGroup> roleGroups;
	
	@DataModelSelection
	private RoleGroup roleGroup;
	
	private String searchString;
	
	@Factory("roleGroups")
	@SuppressWarnings("unchecked")
	@Begin(join=true)
	public void search() {
		String searchPattern = getSearchPattern();
		info("searching for roles with #0 search pattern", searchPattern);
		this.roleGroups = em
				.createQuery(
						"SELECT r from RoleGroup r WHERE lower(r.id) LIKE :search_pattern")
				.setParameter("search_pattern", searchPattern)
				.getResultList();
	}
	
	public String getSearchPattern() {
		return getSearchString() == null ? "%" : '%' + getSearchString()
				.toLowerCase().replace('*', '%') + '%';
	}

	
	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

}
