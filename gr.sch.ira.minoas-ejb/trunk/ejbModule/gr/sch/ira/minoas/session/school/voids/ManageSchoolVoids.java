package gr.sch.ira.minoas.session.school.voids;

import java.util.Collection;

import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.voids.TeachingVoid;
import gr.sch.ira.minoas.session.school.SchoolAware;

import javax.ejb.Local;

@Local
public interface ManageSchoolVoids extends SchoolAware {  
    
	
	public void beginSchoolVoidManagement();
	
	public void removeTeachingVoid(TeachingVoid teachingVoid);
	
	public Collection<TeachingVoid> searchTeachingVoids();
	
	public void remove();
	
	
	

}