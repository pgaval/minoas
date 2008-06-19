package gr.sch.ira.minoas.session;

import gr.sch.ira.minoas.model.core.School;

import javax.ejb.Local;

@Local
public interface SchoolVoidManagement {  
    
	public void selectSchool(School selectedSchool);
	
	public void remove();
	

}