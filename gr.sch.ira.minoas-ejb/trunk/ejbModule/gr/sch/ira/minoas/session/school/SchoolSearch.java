package gr.sch.ira.minoas.session.school;

import gr.sch.ira.minoas.model.core.School;

import javax.ejb.Local;

@Local
public interface SchoolSearch  extends SchoolAware {

	// seam-gen method
	public void schoolSearch();
	

	

	
	public void remove();

	/**
	 * @return the searchString
	 */
	public String getSearchString();

	/**
	 * @param searchString
	 *            the searchString to set
	 */
	public void setSearchString(String searchString);
	
	
	public String getSearchPattern();
	
	public void selectSchool(School school);
	
	public void begin();
	
	public void end();

}