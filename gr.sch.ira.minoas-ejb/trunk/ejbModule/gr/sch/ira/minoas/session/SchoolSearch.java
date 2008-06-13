package gr.sch.ira.minoas.session;

import javax.ejb.Local;

@Local
public interface SchoolSearch extends ISearchBean {

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

}