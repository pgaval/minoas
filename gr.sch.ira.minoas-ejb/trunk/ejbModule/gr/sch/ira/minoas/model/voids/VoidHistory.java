/**
 * 
 */
package gr.sch.ira.minoas.model.voids;

import gr.sch.ira.minoas.model.BaseModel;

import java.util.Date;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 *
 */
public class VoidHistory extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date date;
	private String description;
	private String realm;
}
