/*
 * Copyright (c) 2008 FORTHnet, S.A. All Rights Reserved. This software is
 * provided "AS IS," without a warranty of any kind. ALL EXPRESS OR IMPLIED
 * CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE
 * HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THE
 * SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE
 * FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL,
 * CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS
 * OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE
 * SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear facility.
 * Licensee represents and warrants that it will not use or redistribute the
 * Software for such purposes.
 */

package gr.sch.ira.minoas.model.employee;

import gr.sch.ira.minoas.model.core.Specialization;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author <a href="mailto:fsla@forthnet.gr">Filippos Slavik</a>
 * @version $Id$
 */
@Entity
@Table(name = "minoas_regular_employee")
public class RegularEmployee extends Employee {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Greek Official Gazette of appointment (÷≈  ƒÈÔÒÈÛÏÔ˝)
	 * 
	 */
	@Basic
	@Column(name = "appointment_gof", length = 4)
	private String appointmentGOF;

	/**
	 * Greek Official Gazette of Date appointment («ÏÂÒÔÏÁÌﬂ· ÷≈  ƒÈÔÒÈÛÏÔ˝)
	 * 
	 */
	@Basic
	@Column(name = "appointment_gof_date")
	private Date appointmentGOFDate;

	@Basic
	@Column(name = "registry_id", length = 7)
	private String registryID;

	@ManyToOne(optional = false)
	@JoinColumn(name = "specialization_id", nullable = false, updatable = false)
	private Specialization specialization;
	
	/**
	 * @return the appointmentGOF
	 */
	public String getAppointmentGOF() {
		return appointmentGOF;
	}

	/**
	 * @return the appointmentGOFDate
	 */
	public Date getAppointmentGOFDate() {
		return appointmentGOFDate;
	}

	/**
	 * @return the registryID
	 */
	public String getRegistryID() {
		return registryID;
	}

	/**
	 * @return the specialization
	 */
	public Specialization getSpecialization() {
		return specialization;
	}

	/**
	 * @param appointmentGOF the appointmentGOF to set
	 */
	public void setAppointmentGOF(String appointmentGOF) {
		this.appointmentGOF = appointmentGOF;
	}

	/**
	 * @param appointmentGOFDate the appointmentGOFDate to set
	 */
	public void setAppointmentGOFDate(Date appointmentGOFDate) {
		this.appointmentGOFDate = appointmentGOFDate;
	}

	/**
	 * @param registryID the registryID to set
	 */
	public void setRegistryID(String registryID) {
		this.registryID = registryID;
	}

	/**
	 * @param specialization the specialization to set
	 */
	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

}
