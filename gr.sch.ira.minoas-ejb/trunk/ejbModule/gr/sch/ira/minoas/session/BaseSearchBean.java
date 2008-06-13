/**
 * 
 */
package gr.sch.ira.minoas.session;


/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
public abstract class BaseSearchBean implements ISearchBean {
	
	protected int page = 0;

	/**
	 * @see gr.sch.ira.minoas.session.ISearchBean#getPage()
	 */
	public int getPage() {
		return this.page;
	}
	
	/**
	 * @see gr.sch.ira.minoas.session.ISearchBean#setPage(int)
	 */
	public void setPage(int page) {
		this.page = page;
	}

	

}
