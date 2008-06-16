package gr.sch.ira.minoas.session;

import gr.sch.ira.minoas.model.core.School;

import javax.ejb.Local;

@Local
public interface VoidManagement {  
	
	//seam-gen methods
	public String begin();
	public String cancel();
	public String end();
	public void destroy();
	public void selectSchool(School selectedSchool);
	public void createVoid();
	
   //add additional interface methods here	
}