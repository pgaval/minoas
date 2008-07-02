package gr.sch.ira.minoas.session.school.voids;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.security.Restrict;

import gr.sch.ira.minoas.model.voids.TeachingVoid;
import gr.sch.ira.minoas.session.school.BaseSchoolAware;

@Stateful
@Restrict("#{identity.loggedIn}")
@Name("manageVoid")
public class ManageVoidBean extends BaseSchoolAware implements ManageVoid {
	
	@Out
	private TeachingVoid teachingVoid;
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#getTeachingVoid()
	 */
	public TeachingVoid getTeachingVoid() {
		return this.teachingVoid;
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#setTeachingVoid(gr.sch.ira.minoas.model.voids.TeachingVoid)
	 */
	public void setTeachingVoid(TeachingVoid teachingVoid) {
		this.teachingVoid = em.merge(teachingVoid);
		this.teachingVoid.getTeachingResources();
		info(this.teachingVoid.getTeachingResources());
		info("trying to managed '#0' teaching void.", teachingVoid);
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#begin()
	 */
	public void begin() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#end()
	 */
	@End
	public void end() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#remove()
	 */
	@Remove
	@Destroy
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
