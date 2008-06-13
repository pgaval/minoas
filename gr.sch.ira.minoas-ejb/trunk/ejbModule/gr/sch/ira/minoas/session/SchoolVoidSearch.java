package gr.sch.ira.minoas.session;

import gr.sch.ira.minoas.model.core.School;

import javax.ejb.Local;

@Local
public interface SchoolVoidSearch extends ISearchBean {  
    
	//seam-gen method
	public void schoolVoidSearch(School school);  
	
	public void remove();
	
    //add additional interface methods here
}