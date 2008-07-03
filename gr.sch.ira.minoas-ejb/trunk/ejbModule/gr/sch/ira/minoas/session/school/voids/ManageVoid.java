package gr.sch.ira.minoas.session.school.voids;

import gr.sch.ira.minoas.model.voids.TeachingResource;
import gr.sch.ira.minoas.model.voids.TeachingVoid;
import gr.sch.ira.minoas.session.school.SchoolAware;

import javax.ejb.Local;

@Local
public interface ManageVoid extends SchoolAware {
	public void setTeachingVoid(TeachingVoid teachingVoid);

	public TeachingVoid getTeachingVoid();

	public void remove();

	public void begin();

	public void end();
	
	public void cancel();
	
	  public void addTeachingResource();
		public void removeTeachingResource(TeachingResource teachingResource);
}
