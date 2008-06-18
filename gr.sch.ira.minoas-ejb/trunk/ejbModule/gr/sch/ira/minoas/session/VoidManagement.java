package gr.sch.ira.minoas.session;

import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.voids.TeachingResource;
import gr.sch.ira.minoas.model.voids.TeachingVoid;

import javax.ejb.Local;

@Local
public interface VoidManagement {  
	
	//seam-gen methods
	public String begin();
	public String cancel();
	public String end();
	public void destroy();
	public void selectSchool(School selectedSchool);
	public void addTeachingResource();
	public void removeTeachingResource(TeachingResource teachingResource);
	public void setTeachingVoid(TeachingVoid teachingVoid);
	public TeachingVoid getTeachingVoid();
	public TeachingVoid createTeachingVoid();

	
   //add additional interface methods here	
}