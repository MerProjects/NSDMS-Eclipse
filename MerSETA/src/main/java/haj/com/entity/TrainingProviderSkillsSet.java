package haj.com.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import haj.com.entity.lookup.SkillsProgram;
import haj.com.framework.IDataEntity;
import haj.com.entity.lookup.SkillsSet;

// TODO: Auto-generated Javadoc
/**
 * Blank.
 *
 * @author Techfinium
 */
@Entity
@Table(name = "training_provider_skills_set")
@AuditTable(value = "training_provider_skills_set_hist")
@Audited
public class TrainingProviderSkillsSet implements IDataEntity
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Unique Id of Blank. */
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
	@JoinColumn(name = "training_provider_application_id", nullable = true)
	private TrainingProviderApplication trainingProviderApplication;
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "skills_set_id", nullable = true)
	private SkillsSet SkillsSet;
	
	 /* Soft Deleted Defaulted to false If true should not be displayed and / or
	 * referenced.
	 */
	@Column(name = "soft_delete")
	private Boolean softDelete;
	
	@Column(name = "accept")
	private Boolean accept;
	
	/**  target key for task. */
	@Column(name = "target_key", nullable = true)
	private Long targetKey;

	/**  target class for task. */
	@Column(name = "target_class", nullable = true)
	private String targetClass;

	
	/** Note. */
	@Column(name="note", columnDefinition="LONGTEXT")
    private String note;
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "training_provider_doc_parent_id", nullable = true)
	@Fetch(FetchMode.JOIN)
	private TrainingProviderDocParent trainingProviderDocParent;
	
	@Column(name = "manually_added", columnDefinition = "BIT default false")
	private Boolean manuallyAdded;
	
	@Transient
	private Boolean cannotRemove;
	
	@Transient
	private List<Doc> docList;
	

	public TrainingProviderSkillsSet() {
		super();
		// TODO Auto-generated constructor stub
	}

    
	public TrainingProviderSkillsSet(SkillsSet ss,
			TrainingProviderApplication trainingProviderApplication) {
		this.SkillsSet=ss;
		this.trainingProviderApplication=trainingProviderApplication;
	}

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
		TrainingProviderSkillsSet other = (TrainingProviderSkillsSet) obj;
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
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Gets the note.
	 *
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Sets the note.
	 *
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}


	public TrainingProviderApplication getTrainingProviderApplication() {
		return trainingProviderApplication;
	}


	public void setTrainingProviderApplication(TrainingProviderApplication trainingProviderApplication) {
		this.trainingProviderApplication = trainingProviderApplication;
	}


	public SkillsSet getSkillsSet() {
		return SkillsSet;
	}


	public void setSkillsSet(SkillsSet skillsSet) {
		SkillsSet = skillsSet;
	}


	public Boolean getSoftDelete() {
		return softDelete;
	}


	public void setSoftDelete(Boolean softDelete) {
		this.softDelete = softDelete;
	}


	public Boolean getAccept() {
		return accept;
	}


	public void setAccept(Boolean accept) {
		this.accept = accept;
	}


	public Long getTargetKey() {
		return targetKey;
	}


	public void setTargetKey(Long targetKey) {
		this.targetKey = targetKey;
	}


	public String getTargetClass() {
		return targetClass;
	}


	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}


	public TrainingProviderDocParent getTrainingProviderDocParent() {
		return trainingProviderDocParent;
	}


	public void setTrainingProviderDocParent(TrainingProviderDocParent trainingProviderDocParent) {
		this.trainingProviderDocParent = trainingProviderDocParent;
	}


	public List<Doc> getDocList() {
		return docList;
	}


	public void setDocList(List<Doc> docList) {
		this.docList = docList;
	}


	public Boolean getManuallyAdded() {
		return manuallyAdded;
	}


	public void setManuallyAdded(Boolean manuallyAdded) {
		this.manuallyAdded = manuallyAdded;
	}


	public Boolean getCannotRemove() {
		return cannotRemove;
	}


	public void setCannotRemove(Boolean cannotRemove) {
		this.cannotRemove = cannotRemove;
	}



}
