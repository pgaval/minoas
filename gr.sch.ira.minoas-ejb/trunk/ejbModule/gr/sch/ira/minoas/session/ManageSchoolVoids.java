package gr.sch.ira.minoas.session;

import gr.sch.ira.minoas.model.core.School;

import javax.ejb.Local;

@Local
public interface ManageSchoolVoids extends SchoolAware {  
    
	public void search(School selectedSchool);
	
	public void remove();
	
	public void begin();
	
	public void end();
	

}