package haj.com.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import haj.com.entity.enums.ApprovalEnum;
import haj.com.entity.enums.SdpCompanyActionListEnum;
import haj.com.entity.lookup.SdpType;
import haj.com.framework.IDataEntity;
import haj.com.validators.exports.SETMISFieldValidation;

// TODO: Auto-generated Javadoc
/**
 * SdpCompanyActions.
 *
 * @author Techfinium
 */
@Entity
@Table(name = "sdp_company_actions")
@AuditTable(value = "sdp_company_actions_hist")
@Audited
public class SdpCompanyActions implements IDataEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Unique Id of SdpCompanyActions. */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	/** Create Date of Object. */
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 19)
	private Date createDate;
	
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "create_user_id", nullable = true)
	private Users createUser;
	
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = true)
	@SETMISFieldValidation(process = true, fieldName = "company", fieldValue = "NOT_NULL")
	private Company company;
	
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sdp_user_id", nullable = true)
	@SETMISFieldValidation(process = true, fieldName = "sdp", fieldValue = "NOT_NULL")
	private Users sdpUser;
	
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "training_site_id", nullable = true)
	private TrainingSite trainingSite;
	
	@Enumerated
	@Column(name = "approval_status")
	private ApprovalEnum approvalStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "approval_date", length = 19)
	private Date approvalDate;
	
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approval_user_id", nullable = true)
	private Users approvalUser;
	
	@Enumerated
	@Column(name = "sdp_company_action")
	private SdpCompanyActionListEnum sdpCompanyAction;
	
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "current_designation_id", nullable = true)
	private SdpType currentDesignation;
	
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "new_designation_id", nullable = true)
	private SdpType newDesignation;
	
	@Column(name = "sdp_company_flat_id", length = 19)
	private Long sdpCompanyFlatId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "training_provider_application_id", nullable = true)
	private TrainingProviderApplication TrainingProviderApplication;
	
	@Column(name = "reason", columnDefinition = "LONGTEXT")
	private String reason;
	
	@Transient
	private List<Doc> doc;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		SdpCompanyActions other = (SdpCompanyActions) obj;
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
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the creates the date.
	 *
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the creates the date.
	 *
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Users getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Users createUser) {
		this.createUser = createUser;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Users getSdpUser() {
		return sdpUser;
	}

	public void setSdpUser(Users sdpUser) {
		this.sdpUser = sdpUser;
	}

	public TrainingSite getTrainingSite() {
		return trainingSite;
	}

	public void setTrainingSite(TrainingSite trainingSite) {
		this.trainingSite = trainingSite;
	}

	public Long getSdpCompanyFlatId() {
		return sdpCompanyFlatId;
	}

	public void setSdpCompanyFlatId(Long sdpCompanyFlatId) {
		this.sdpCompanyFlatId = sdpCompanyFlatId;
	}

	public ApprovalEnum getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(ApprovalEnum approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Users getApprovalUser() {
		return approvalUser;
	}

	public void setApprovalUser(Users approvalUser) {
		this.approvalUser = approvalUser;
	}

	public SdpCompanyActionListEnum getSdpCompanyAction() {
		return sdpCompanyAction;
	}

	public void setSdpCompanyAction(SdpCompanyActionListEnum sdpCompanyAction) {
		this.sdpCompanyAction = sdpCompanyAction;
	}

	public SdpType getCurrentDesignation() {
		return currentDesignation;
	}

	public void setCurrentDesignation(SdpType currentDesignation) {
		this.currentDesignation = currentDesignation;
	}

	public SdpType getNewDesignation() {
		return newDesignation;
	}

	public void setNewDesignation(SdpType newDesignation) {
		this.newDesignation = newDesignation;
	}

	public List<Doc> getDoc() {
		return doc;
	}

	public void setDoc(List<Doc> doc) {
		this.doc = doc;
	}

	public TrainingProviderApplication getTrainingProviderApplication() {
		return TrainingProviderApplication;
	}

	public void setTrainingProviderApplication(TrainingProviderApplication trainingProviderApplication) {
		TrainingProviderApplication = trainingProviderApplication;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
