package gr.sch.ira.minoas.session;

import gr.sch.ira.minoas.model.core.School;

import javax.ejb.Local;

@Local
public interface SchoolVoidManagement extends ISearchBean {  
    
	//seam-gen method
	public void schoolVoidSearch(School school);  
	
	public void selectSchool(School selectedSchool);
	
	public void remove();
	
	public void createTeachingVoid();
    //add additional interface methods here
}