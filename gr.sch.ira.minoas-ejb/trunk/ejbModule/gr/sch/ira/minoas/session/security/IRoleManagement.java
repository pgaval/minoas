/**
 * 
 */
package gr.sch.ira.minoas.session.security;

import gr.sch.ira.minoas.model.security.Role;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
public interface IRoleManagement {
	
	public void constructNewRole();
	public void removeRole();
	public void saveRole();
	public void search();
	public void selectRole();
	

}
