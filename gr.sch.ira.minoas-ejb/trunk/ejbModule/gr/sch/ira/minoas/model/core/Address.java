/**
 * 
 */
package gr.sch.ira.minoas.model.core;

import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;

import gr.sch.ira.minoas.model.BaseModel;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Entity
@Name("address")
@Table(name = "minoas_address")
public class Address extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Basic
	@Column(name = "address", length = 128, nullable = false)
	private String address;

	@Basic
	@Column(name = "address_additional", length = 128, nullable = true)
	private String addressAdditional;

	@Basic
	@Column(name = "number", length = 8, nullable = true)
	private String number;

	@Basic
	@Column(name = "post_code", length = 10, nullable = true)
	private String postCode;

	@Basic
	@Column(name = "city", length = 15, nullable = true)
	private String city;

	@Basic
	@Column(name = "longitude", nullable = true)
	private Double longitude;

	@Basic
	@Column(name = "latitude", nullable = true)
	private Double latitude;

	/**
	 * 
	 */
	public Address() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the addressAdditional
	 */
	public String getAddressAdditional() {
		return addressAdditional;
	}

	/**
	 * @param addressAdditional
	 *            the addressAdditional to set
	 */
	public void setAddressAdditional(String addressAdditional) {
		this.addressAdditional = addressAdditional;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode
	 *            the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

}
