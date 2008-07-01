package gr.sch.ira.minoas.session.school.voids;

import gr.sch.ira.minoas.model.voids.TeachingResource;
import gr.sch.ira.minoas.model.voids.TeachingVoid;
import gr.sch.ira.minoas.session.school.SchoolAware;

import javax.ejb.Local;

@Local
public interface CreateTeachingVoid extends SchoolAware {  
    
	public void begin();
	public void cancel();
	public void end();
    public void destroy();
    public void addTeachingResource();
	public void removeTeachingResource(TeachingResource teachingResource);
	public TeachingVoid createTeachingVoid();
   
}