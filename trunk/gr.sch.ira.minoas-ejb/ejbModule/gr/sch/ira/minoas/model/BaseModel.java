/**
 * 
 */
package gr.sch.ira.minoas.model;

import gr.sch.ira.minoas.model.security.Principal;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@MappedSuperclass
public abstract class BaseModel implements Serializable {
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="INSERTED_BY_ID", nullable=true)
	private Principal insertedBy;
	
	@Basic(fetch=FetchType.LAZY)
	@Temporal(TemporalType.DATE)
	@Column(name="INSERTED_ON", length=128, nullable=true, updatable=false)
	private Date insertedOn;
	
	@SuppressWarnings("unused")
	@Column(name = "VERSION")
	@Version
	private Long version;


	/**
	 * @return the insertedBy
	 */
	protected Principal getInsertedBy() {
		return insertedBy;
	}

	/**
	 * @param insertedBy the insertedBy to set
	 */
	protected void setInsertedBy(Principal insertedBy) {
		this.insertedBy = insertedBy;
	}

	/**
	 * @return the insertedOn
	 */
	protected Date getInsertedOn() {
		return insertedOn;
	}

	/**
	 * @param insertedOn the insertedOn to set
	 */
	protected void setInsertedOn(Date insertedOn) {
		this.insertedOn = insertedOn;
	}

}