/**
 * 
 */
package gr.sch.ira.minoas.seam.components;

import gr.sch.ira.minoas.model.core.SchoolYear;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
@Name("userDefaults")
public class UserDefaults {

	@In
	private EntityManager minoasDatabase;
	
	@In
	private CoreSearching coreSearching;
	
	
	@Out(scope=ScopeType.SESSION)
	private SchoolYear defaultSchoolYear;
	
	@Observer("org.jboss.seam.security.loginSuccessful")
	public void loginSuccesful() {
		this.defaultSchoolYear = coreSearching.getActiveSchoolYear();
	}
}
