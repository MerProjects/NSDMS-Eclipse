package haj.com.entity;
// Generated May 30, 2014 10:27:06 AM by Hibernate Tools 3.6.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import haj.com.entity.lookup.STATSSAAreaCode;
import haj.com.entity.lookup.Town;
import haj.com.framework.IDataEntity;


@Entity
@Table(name = "wsp_company_address_history")
public class WspCompanyAddressHistory implements IDataEntity, Cloneable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;
	
	/** The Id of Address Entity where referenced */
	private Long orginalAddressId;
	
	/** target key for task. */
	private Long targetKey;

	/** target class for task. */
	private String targetClass;
	
	private WspCompanyMainHistory wspCompanyMainHistory;

	
	/** First line of address 
	 *  First line of address is required
	 *  Field maximum length = 50
	 *  May not start with space
	 *  First line of address can't be more than 50 characters
	 *  Uppercase value in field may only contain characters ABCDEFGHIJKLMNOPQRTSUVWXYZ -1234567890#&()/\:._`',
	 *  Uppercase value in field may not contain characters %UNKNOWN% or %AS ABOVE% or %SOOS BO% or %DELETE% or
	 *  N/A or NA or U or NONE or GEEN or 0 or TEST or %ONTBREEK% or NIL or - or 
	 *  The field may not contain a value that contains only numbers
	 *  Uppercase value in field should not contain characters %ZZZ% or %XXX% or %ADDRES%
	 */
//	@NotEmpty(message="Enter first line of address 1")
//	@Size(min=1,max=50,message="First line of address can't be more than 50 characters")
	private String addressLine1;
	
	/** Second line of address. 
	 *  Second line of address is required
	 *  Field maximum length = 50
	 *  May not start with space
	 *  Second line of address can't be more than 50 characters
	 *  Uppercase value in field may only contain characters ABCDEFGHIJKLMNOPQRTSUVWXYZ -1234567890#&()/\:._`',
	 *  Uppercase value in field may not contain characters %UNKNOWN% or %AS ABOVE% or %SOOS BO% or
	 *  %DELETE% or N/A or NA or U or NONE or GEEN or 0 or TEST or %ONTBREEK% or NIL or - or 
	 *  The field may not contain a value that contains only numbers
	 *  Uppercase value in field should not contain characters %ZZZ% or %XXX% or %ADDRES%
	 */
//	@NotEmpty(message="Enter Second line of address")
//	@Size(min=1,max=50,message="Second line of addresss can't be more than 50 characters")
	private String addressLine2;
	
	/** Third line of address.
	 *  Third line of address is not  required
	 *  Field maximum length = 50
	 *  May not start with space
	 *  Third line of address can't be more than 50 characters
	 *  Uppercase value in field may only contain characters ABCDEFGHIJKLMNOPQRTSUVWXYZ -1234567890#&()/\:._`',
	 *  Uppercase value in field may not contain characters %UNKNOWN% or %AS ABOVE% or %SOOS BO% or
	 *  %DELETE% or N/A or NA or U or NONE or GEEN or 0 or TEST or %ONTBREEK% or NIL or - or 
	 *  The field may not contain a value that contains only numbers
	 *  Uppercase value in field should not contain characters %ZZZ% or %XXX% or %ADDRES%
	 */
	@Size(max=50,message="Third line of address can't be more than 50 characters")
	private String addressLine3;
	
	/** Fourth line of address.
	 *  Fourth line of address is required
	 *  Field maximum length = 50
	 *  May not start with space
	 *  Fourth line of address can't be more than 50 characters
	 *  Uppercase value in field may only contain characters ABCDEFGHIJKLMNOPQRTSUVWXYZ -1234567890#&()/\:._`',
	 *  Uppercase value in field may not contain characters %UNKNOWN% or %AS ABOVE% or %SOOS BO% or
	 *  %DELETE% or N/A or NA or U or NONE or GEEN or 0 or TEST or %ONTBREEK% or NIL or - or 
	 *  The field may not contain a value that contains only numbers
	 *  Uppercase value in field should not contain characters %ZZZ% or %XXX% or %ADDRES%
	 */
//	@NotEmpty(message="Enter Fourth line of address")
//	@Size(min=1,max=50,message="Fourth line of address can't be more than 50 characters")
	private String addressLine4;
	
	/** Address post code. 
	 * This filed is required
	 * Field maximum length = 4
	 * May not start with space
	 * Uppercase value in field may only contain characters 1234567890
	 * Length of field must be exactly 4 characters
	 */
//	@NotEmpty(message="Enter postal code")
//	@Pattern(regexp="[\\d]{4}", message="Postal code must be 4 characters long and may only contain digits(1234567890)")
	private String postcode;
	
	/** The latitude. */
	private Double latitude;
	
	/** The longitude. */
	private Double longitude;
	
	/** The Degrees for latitude. */
	private Double latitudeDegrees;
	
	/** The Minutes latitude. */
	private Double latitudeMinutes;
	
	/** The Seconds latitude. */
	private Double latitudeSeconds;
	
	/** The Degrees for longitude. */
	private Double longitudeDegrees;
	
	/** The Minutes longitude. */
	private Double longitudeMinutes;
	
	/** The Seconds longitude. */
	private Double longitudeSeconds;
	
	/** The primary addr. */
	private Boolean primaryAddr;

	/** The create date. */
	private Date createDate;
	
	private Date orginalCreateDate;

	/** The sites. */
	private Sites sites;
	
	/** The company. */
	private Company company;
	
	/** The hosting company. */
	private HostingCompany hostingCompany;
	
	private Town town;

	/** The municipality. */
	private Municipality municipality;
	
	/** The user. */
	private Users user;
	
	/** The same address. */
	private Boolean sameAddress;
	

	private STATSSAAreaCode statsSaAreaCode;

	/**
	 * Instantiates a new address.
	 */
	public WspCompanyAddressHistory() {
	}

	/**
	 * Instantiates a new address.
	 *
	 * @param company the company
	 */
	public WspCompanyAddressHistory(Company company) {
		super();
		this.company = company;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the address line 1.
	 *
	 * @return the address line 1
	 */
	@Column(name = "address_line_1", length = 100)
	public String getAddressLine1() {
		return this.addressLine1;
	}

	/**
	 * Sets the address line 1.
	 *
	 * @param addressLine1 the new address line 1
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * Gets the address line 2.
	 *
	 * @return the address line 2
	 */
	@Column(name = "address_line_2", length = 100)
	public String getAddressLine2() {
		return this.addressLine2;
	}

	/**
	 * Sets the address line 2.
	 *
	 * @param addressLine2 the new address line 2
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * Gets the address line 3.
	 *
	 * @return the address line 3
	 */
	@Column(name = "address_line_3", length = 100)
	public String getAddressLine3() {
		return this.addressLine3;
	}

	/**
	 * Sets the address line 3.
	 *
	 * @param addressLine3 the new address line 3
	 */
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	/**
	 * Gets the address line 4.
	 *
	 * @return the address line 4
	 */
	@Column(name = "address_line_4", length = 100)
	public String getAddressLine4() {
		return this.addressLine4;
	}

	/**
	 * Sets the address line 4.
	 *
	 * @param addressLine4 the new address line 4
	 */
	public void setAddressLine4(String addressLine4) {
		this.addressLine4 = addressLine4;
	}

	/**
	 * Gets the postcode.
	 *
	 * @return the postcode
	 */
	@Column(name = "postcode", length = 10)
	public String getPostcode() {
		return this.postcode;
	}

	/**
	 * Sets the postcode.
	 *
	 * @param postcode the new postcode
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	@Column(name = "latitude", precision = 22, scale = 0)
	public Double getLatitude() {
		return this.latitude;
	}

	/**
	 * Sets the latitude.
	 *
	 * @param latitude the new latitude
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Gets the longitude.
	 *
	 * @return the longitude
	 */
	@Column(name = "longitude", precision = 22, scale = 0)
	public Double getLongitude() {
		return this.longitude;
	}

	/**
	 * Sets the longitude.
	 *
	 * @param longitude the new longitude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Gets the primary addr.
	 *
	 * @return the primary addr
	 */
	@Column(name = "primary_addr")
	public Boolean getPrimaryAddr() {
		return this.primaryAddr;
	}

	/**
	 * Sets the primary addr.
	 *
	 * @param primaryAddr the new primary addr
	 */
	public void setPrimaryAddr(Boolean primaryAddr) {
		this.primaryAddr = primaryAddr;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() {
		Object clone = null;
		try {
			clone = super.clone();
		} catch (CloneNotSupportedException e) {
			// should never happen
		}
		return clone;

	}

	/**
	 * Gets the creates the date.
	 *
	 * @return the creates the date
	 */
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = true, length = 19)
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the creates the date.
	 *
	 * @param createDate the new creates the date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = true)
	public Users getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(Users user) {
		this.user = user;
	}

	/**
	 * The company.
	 *
	 * @return the company
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = true)
	public Company getCompany() {
		return company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company the new company
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * Gets the hosting company.
	 *
	 * @return the hosting company
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hosting_company_id", nullable = true)
	public HostingCompany getHostingCompany() {
		return hostingCompany;
	}

	/**
	 * Sets the hosting company.
	 *
	 * @param hostingCompany the new hosting company
	 */
	public void setHostingCompany(HostingCompany hostingCompany) {
		this.hostingCompany = hostingCompany;
	}

	/**
	 * Gets the same address.
	 *
	 * @return the same address
	 */
	@Column(name = "same_address")
	public Boolean getSameAddress() {
		return sameAddress;
	}

	/**
	 * Sets the same address.
	 *
	 * @param sameAddress the new same address
	 */
	public void setSameAddress(Boolean sameAddress) {
		this.sameAddress = sameAddress;
	}

	/**
	 * Gets the municipality.
	 *
	 * @return the municipality
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "municipality_id", nullable = true)
	public Municipality getMunicipality() {
		return municipality;
	}

	/**
	 * Sets the municipality.
	 *
	 * @param municipality the new municipality
	 */
	public void setMunicipality(Municipality municipality) {
		this.municipality = municipality;
	}

	/**
	 * Gets the sites.
	 *
	 * @return the sites
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sites_id", nullable = true)
	public Sites getSites() {
		return sites;
	}

	/**
	 * Sets the sites.
	 *
	 * @param sites the new sites
	 */
	public void setSites(Sites sites) {
		this.sites = sites;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "town_id", nullable = true)
	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	
	@Column(name = "latitude_degrees", nullable=true)
	public Double getLatitudeDegrees() {
		return latitudeDegrees;
	}

	public void setLatitudeDegrees(Double latitudeDegrees) {
		this.latitudeDegrees = latitudeDegrees;
	}

	@Column(name = "latitude_minutes", nullable=true)
	public Double getLatitudeMinutes() {
		return latitudeMinutes;
	}

	public void setLatitudeMinutes(Double latitudeMinutes) {
		this.latitudeMinutes = latitudeMinutes;
	}

	@Column(name = "latitude_seconds", nullable=true)
	public Double getLatitudeSeconds() {
		return latitudeSeconds;
	}

	public void setLatitudeSeconds(Double latitudeSeconds) {
		this.latitudeSeconds = latitudeSeconds;
	}

	@Column(name = "longitude_degrees", nullable=true)
	public Double getLongitudeDegrees() {
		return longitudeDegrees;
	}

	public void setLongitudeDegrees(Double longitudeDegrees) {
		this.longitudeDegrees = longitudeDegrees;
	}

	@Column(name = "longitude_minutes", nullable=true)
	public Double getLongitudeMinutes() {
		return longitudeMinutes;
	}

	public void setLongitudeMinutes(Double longitudeMinutes) {
		this.longitudeMinutes = longitudeMinutes;
	}

	@Column(name = "longitude_seconds", nullable=true)
	public Double getLongitudeSeconds() {
		return longitudeSeconds;
	}

	public void setLongitudeSeconds(Double longitudeSeconds) {
		this.longitudeSeconds = longitudeSeconds;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stats_saarea_code_id", nullable = true)
	@Fetch(FetchMode.JOIN)
	public STATSSAAreaCode getStatsSaAreaCode() {
		return statsSaAreaCode;
	}

	public void setStatsSaAreaCode(STATSSAAreaCode statsSaAreaCode) {
		this.statsSaAreaCode = statsSaAreaCode;
	}

	@Column(name = "orginal_address_id", nullable=true)
	public Long getOrginalAddressId() {
		return orginalAddressId;
	}

	public void setOrginalAddressId(Long orginalAddressId) {
		this.orginalAddressId = orginalAddressId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "orginal_create_date", length = 19)
	public Date getOrginalCreateDate() {
		return orginalCreateDate;
	}

	public void setOrginalCreateDate(Date orginalCreateDate) {
		this.orginalCreateDate = orginalCreateDate;
	}

	@Column(name = "target_key", nullable = true)
	public Long getTargetKey() {
		return targetKey;
	}

	public void setTargetKey(Long targetKey) {
		this.targetKey = targetKey;
	}

	@Column(name = "target_class", nullable = true)
	public String getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wsp_company_main_history_id", nullable = true)
	public WspCompanyMainHistory getWspCompanyMainHistory() {
		return wspCompanyMainHistory;
	}

	public void setWspCompanyMainHistory(WspCompanyMainHistory wspCompanyMainHistory) {
		this.wspCompanyMainHistory = wspCompanyMainHistory;
	}
	
}
