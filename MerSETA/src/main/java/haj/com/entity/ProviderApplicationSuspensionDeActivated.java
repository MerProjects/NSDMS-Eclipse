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

import haj.com.entity.enums.ApprovalEnum;
import haj.com.entity.enums.ProviderSusActionsEnum;
import haj.com.framework.IDataEntity;

@Entity
@Table(name = "provider_application_suspension_de_activated")
public class ProviderApplicationSuspensionDeActivated implements IDataEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

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
	@JoinColumn(name = "training_provider_application_id", nullable = true)
	private TrainingProviderApplication trainingProviderApplication;
	
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = true)
	private Company company;
	
	@Column(name = "accreditation_number_at_time")
	private String accreditationNumberAtTime;
	
	@Column(name = "reason", columnDefinition = "LONGTEXT")
	private String reason;
	
	@Enumerated
	@Column(name = "previous_provider_status")
	private ApprovalEnum previousProviderStatus; 
	
	@Enumerated
	@Column(name = "status_assigned")
	private ApprovalEnum statusAssigned;
	
	@Enumerated
	@Column(name = "provider_sus_actions")
	private ProviderSusActionsEnum providerSusActions;
	
	
	@Column(name = "removed_suspension", columnDefinition = "BIT default false")
	private Boolean removedSuspension;
	
	@Transient
	private List<Doc> doc;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProviderApplicationSuspensionDeActivated other = (ProviderApplicationSuspensionDeActivated) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* Getters and setters */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Users getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Users createUser) {
		this.createUser = createUser;
	}

	public TrainingProviderApplication getTrainingProviderApplication() {
		return trainingProviderApplication;
	}

	public void setTrainingProviderApplication(TrainingProviderApplication trainingProviderApplication) {
		this.trainingProviderApplication = trainingProviderApplication;
	}

	public ApprovalEnum getPreviousProviderStatus() {
		return previousProviderStatus;
	}

	public void setPreviousProviderStatus(ApprovalEnum previousProviderStatus) {
		this.previousProviderStatus = previousProviderStatus;
	}

	public ApprovalEnum getStatusAssigned() {
		return statusAssigned;
	}

	public void setStatusAssigned(ApprovalEnum statusAssigned) {
		this.statusAssigned = statusAssigned;
	}

	public List<Doc> getDoc() {
		return doc;
	}

	public void setDoc(List<Doc> doc) {
		this.doc = doc;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Boolean getRemovedSuspension() {
		return removedSuspension;
	}

	public void setRemovedSuspension(Boolean removedSuspension) {
		this.removedSuspension = removedSuspension;
	}

	public String getAccreditationNumberAtTime() {
		return accreditationNumberAtTime;
	}

	public void setAccreditationNumberAtTime(String accreditationNumberAtTime) {
		this.accreditationNumberAtTime = accreditationNumberAtTime;
	}

	public ProviderSusActionsEnum getProviderSusActions() {
		return providerSusActions;
	}

	public void setProviderSusActions(ProviderSusActionsEnum providerSusActions) {
		this.providerSusActions = providerSusActions;
	}

}