package gr.sch.ira.minoas.session.school.voids;

import java.util.Collection;

import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.voids.TeachingVoid;
import gr.sch.ira.minoas.session.school.SchoolAware;

import javax.ejb.Local;

public interface ManageSchoolVoids {  
    
	
	public void beginSchoolVoidManagement();
	
	public Collection<TeachingVoid> searchTeachingVoids();
	
	public void cancel();
	
	
	

}