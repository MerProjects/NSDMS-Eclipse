package haj.com.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import haj.com.framework.IDataEntity;

// TODO: Auto-generated Javadoc
/**
 * NLRDFile22.
 *
 * @author Techfinium
 */
@Entity
@Table(name = "nlrd_file_22")
public class NLRDFile22 implements IDataEntity {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Unique Id of NLRDFile22. */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

/*	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="company_id", nullable=true)
	private Company company;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
	private Set<OrderItems> orderItemses = new HashSet<OrderItems>(0);
	*/
	
	@Column(name = "qualification_code")
	private String qualificationCode;
	
	@Column(name = "qualification_name")
	private String qualificationName;
	
	@Column(name = "qualification_type_id")
	private String qualificationTypeId;
	
	@Column(name = "nqf_level_id")
	private String nQFLevelId;
	
	@Column(name = "subdomain_id")
	private String subdomainId;
	
	@Column(name = "abet_band_id")
	private String abetBandId;
	
	@Column(name = "qual_registration_start_date")
	private Date qualRegistrationStartDate;
	
	@Column(name = "qual_registration_end_date")
	private Date qualRegistrationEndDate;
	
	@Column(name = "provider_code")
	private String providerCode;
	
	@Column(name = "provider_etqa_id")
	private String providerEtqaId;
	
	@Column(name = "date_stamp")
	private Date dateStamp;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NLRDFile22 other = (NLRDFile22) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String getQualificationCode() {
		return qualificationCode;
	}

	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}

	public String getQualificationName() {
		return qualificationName;
	}

	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}

	public String getQualificationTypeId() {
		return qualificationTypeId;
	}

	public void setQualificationTypeId(String qualificationTypeId) {
		this.qualificationTypeId = qualificationTypeId;
	}

	public String getnQFLevelId() {
		return nQFLevelId;
	}

	public void setnQFLevelId(String nQFLevelId) {
		this.nQFLevelId = nQFLevelId;
	}

	public String getSubdomainId() {
		return subdomainId;
	}

	public void setSubdomainId(String subdomainId) {
		this.subdomainId = subdomainId;
	}

	public String getAbetBandId() {
		return abetBandId;
	}

	public void setAbetBandId(String abetBandId) {
		this.abetBandId = abetBandId;
	}

	public Date getQualRegistrationStartDate() {
		return qualRegistrationStartDate;
	}

	public void setQualRegistrationStartDate(Date qualRegistrationStartDate) {
		this.qualRegistrationStartDate = qualRegistrationStartDate;
	}

	public Date getQualRegistrationEndDate() {
		return qualRegistrationEndDate;
	}

	public void setQualRegistrationEndDate(Date qualRegistrationEndDate) {
		this.qualRegistrationEndDate = qualRegistrationEndDate;
	}

	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public String getProviderEtqaId() {
		return providerEtqaId;
	}

	public void setProviderEtqaId(String providerEtqaId) {
		this.providerEtqaId = providerEtqaId;
	}

	public Date getDateStamp() {
		return dateStamp;
	}

	public void setDateStamp(Date dateStamp) {
		this.dateStamp = dateStamp;
	}
}