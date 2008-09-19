/**
 * 
 */
package gr.sch.ira.minoas.session;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
public abstract class BaseSeamComponent {

	public static final String SUCCESS_OUTCOME = "success";

	public static final String FAILURE_OUTCOME = "failure";

	public static final String BEGIN_OUTCOME = "begin";
	
	public static final String SCHOOL_SELECTED_OUTCOME ="school-selected";
	
	public static final String EMPLOYEE_SELECTED_OUTCOME ="employee-selected";
	
	public static final String EMPLOYMENT_SELECTED_OUTCOME ="employment-selected";

	@Logger
	private Log logger;

	@In
	protected FacesMessages facesMessages;

	/**
	 * @param arg0
	 * @param arg1
	 * @see org.jboss.seam.log.Log#debug(java.lang.Object, java.lang.Object[])
	 */
	public void debug(Object arg0, Object... arg1) {
		logger.debug(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see org.jboss.seam.log.Log#debug(java.lang.Object, java.lang.Throwable,
	 * java.lang.Object[])
	 */
	public void debug(Object arg0, Throwable arg1, Object... arg2) {
		logger.debug(arg0, arg1, arg2);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @see org.jboss.seam.log.Log#error(java.lang.Object, java.lang.Object[])
	 */
	public void error(Object arg0, Object... arg1) {
		logger.error(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see org.jboss.seam.log.Log#error(java.lang.Object, java.lang.Throwable,
	 * java.lang.Object[])
	 */
	public void error(Object arg0, Throwable arg1, Object... arg2) {
		logger.error(arg0, arg1, arg2);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @see org.jboss.seam.log.Log#fatal(java.lang.Object, java.lang.Object[])
	 */
	public void fatal(Object arg0, Object... arg1) {
		logger.fatal(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see org.jboss.seam.log.Log#fatal(java.lang.Object, java.lang.Throwable,
	 * java.lang.Object[])
	 */
	public void fatal(Object arg0, Throwable arg1, Object... arg2) {
		logger.fatal(arg0, arg1, arg2);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @see org.jboss.seam.log.Log#info(java.lang.Object, java.lang.Object[])
	 */
	public void info(Object arg0, Object... arg1) {
		logger.info(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see org.jboss.seam.log.Log#info(java.lang.Object, java.lang.Throwable,
	 * java.lang.Object[])
	 */
	public void info(Object arg0, Throwable arg1, Object... arg2) {
		logger.info(arg0, arg1, arg2);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @see org.jboss.seam.log.Log#warn(java.lang.Object, java.lang.Object[])
	 */
	public void warn(Object arg0, Object... arg1) {
		logger.warn(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @see org.jboss.seam.log.Log#warn(java.lang.Object, java.lang.Throwable,
	 * java.lang.Object[])
	 */
	public void warn(Object arg0, Throwable arg1, Object... arg2) {
		logger.warn(arg0, arg1, arg2);
	}

}
