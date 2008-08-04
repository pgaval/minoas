package gr.sch.ira.minoas.session.security;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.security.Role;

import gr.sch.ira.minoas.session.BaseStatelessSeamComponentImpl;

/**
 * @author <a href="mailto:fsla@forthnet.gr">Filippos Slavik</a>
 * @version $Id$
 */
@Stateless
@Name("createRole")
@Local(ICreateRole.class)
public class CreateRoleAction extends BaseStatelessSeamComponentImpl implements ICreateRole {
	
	@PersistenceContext
	private EntityManager em;
	
	@In
	private Role role;
	/**
	 * @see gr.sch.ira.minoas.session.security.ICreateRole#createRole()
	 */
	public void createRole() {
		info("created role #0", role);
	}

}
