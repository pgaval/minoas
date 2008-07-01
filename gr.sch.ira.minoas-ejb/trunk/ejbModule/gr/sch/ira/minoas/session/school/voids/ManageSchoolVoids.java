package gr.sch.ira.minoas.session.school.voids;

import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.session.school.SchoolAware;

import javax.ejb.Local;

@Local
public interface ManageSchoolVoids extends SchoolAware {  
    
	public void search(School selectedSchool);
	
	public void remove();
	
	public void begin();
	
	public void end();
	

}