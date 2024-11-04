package haj.com.entity;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import haj.com.entity.lookup.AppraisalChecklist;
import haj.com.framework.IDataEntity;

// TODO: Auto-generated Javadoc
/**
 * Blank.
 *
 * @author Techfinium
 */
@Entity
@Table(name = "trade_appraisals_checklist")
public class TradeAppraisalsChecklist implements IDataEntity
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
	
	/** The WorkPlaceApproval. */
	//@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workplace_approval_id", nullable = true)
	private WorkPlaceApproval workPlaceApproval;
	
	/** The WorkPlaceApproval. */
	//@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appraisal_checklist_id", nullable = true)
	private AppraisalChecklist appraisalChecklist;

	/** The recognition agreement. */
	@ManyToOne(fetch = FetchType.LAZY)
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "answer", nullable = true)
	private YesNoLookup answer;
		
	public TradeAppraisalsChecklist() {
		super();
	}

	public TradeAppraisalsChecklist(WorkPlaceApproval workPlaceApproval, AppraisalChecklist appraisalChecklist) {
		super();
		this.workPlaceApproval = workPlaceApproval;
		this.appraisalChecklist = appraisalChecklist;
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
		TradeAppraisalsChecklist other = (TradeAppraisalsChecklist) obj;
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

	public WorkPlaceApproval getWorkPlaceApproval() {
		return workPlaceApproval;
	}

	public void setWorkPlaceApproval(WorkPlaceApproval workPlaceApproval) {
		this.workPlaceApproval = workPlaceApproval;
	}

	public AppraisalChecklist getAppraisalChecklist() {
		return appraisalChecklist;
	}

	public void setAppraisalChecklist(AppraisalChecklist appraisalChecklist) {
		this.appraisalChecklist = appraisalChecklist;
	}

	public YesNoLookup getAnswer() {
		return answer;
	}

	public void setAnswer(YesNoLookup answer) {
		this.answer = answer;
	}
}