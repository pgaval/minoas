package gr.sch.ira.minoas.session.school.voids;

import java.util.ArrayList;
import java.util.Collection;

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

import gr.sch.ira.minoas.model.TeacherType;
import gr.sch.ira.minoas.model.voids.TeachingResource;
import gr.sch.ira.minoas.model.voids.TeachingVoid;
import gr.sch.ira.minoas.session.school.BaseSchoolAware;

@Stateful
@Restrict("#{identity.loggedIn}")
@Name("manageVoid")
public class ManageVoidBean extends BaseSchoolAware implements ManageVoid {

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#cancel()
	 */
	@End
	public void cancel() {
		// TODO Auto-generated method stub
		
	}

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@Out
	private TeachingVoid teachingVoid;

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#addTeachingResource()
	 */
	public void addTeachingResource() {
		Collection<TeachingResource> resources = teachingVoid
				.getTeachingResources();
		if (resources == null) {
			resources = new ArrayList<TeachingResource>();
			teachingVoid.setTeachingResources(resources);
		}
		TeachingResource new_resource = new TeachingResource();
		new_resource.setFillingVoid(this.teachingVoid);
		new_resource.setTeacherType(TeacherType.PERMANENT);
		new_resource.setTeachingHours(Long.valueOf(0L));
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
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#getTeachingVoid()
	 */
	public TeachingVoid getTeachingVoid() {
		return this.teachingVoid;
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#remove()
	 */
	@Remove
	@Destroy
	public void remove() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#removeTeachingResource(gr.sch.ira.minoas.model.voids.TeachingResource)
	 */
	public void removeTeachingResource(TeachingResource teachingResource) {
		Collection<TeachingResource> resources = teachingVoid
				.getTeachingResources();
		if (resources != null) {
			resources.remove(teachingResource);
		}
	}

	/**
	 * @see gr.sch.ira.minoas.session.school.voids.ManageVoid#setTeachingVoid(gr.sch.ira.minoas.model.voids.TeachingVoid)
	 */
	public void setTeachingVoid(TeachingVoid teachingVoid) {
		this.teachingVoid = em.merge(teachingVoid);
		info("trying to managed '#0' teaching void.", teachingVoid);
	}

}
