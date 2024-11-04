package haj.com.entity.lookup;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import haj.com.entity.OfoCodes;
import haj.com.framework.AbstractLookup;

// TODO: Auto-generated Javadoc
/**
 * DGYear.
 *
 * @author Techfinium
 */
@Entity
@Table(name = "appraisal_checklist")
public class AppraisalChecklist extends AbstractLookup {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Unique Id of AbetBand. */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;


	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appraisals_id")
	private Appraisals appraisals;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appraisal_category_id")
	private AppraisalCategories appraisalCategory;
	
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appraisal_category_code_id")
	private AppraisalCategoryCode appraisalCategoryCode;
	
	/**
	 * Instantiates a new abet band.
	 */
	public AppraisalChecklist() {
		super();
		// TODO Auto-generated constructor stub
	}

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
	
	public AppraisalChecklist(Appraisals appraisals, AppraisalCategories appraisalCategory) {
		super();
		this.appraisals = appraisals;
		this.appraisalCategory = appraisalCategory;
	}
	
	public AppraisalChecklist(Appraisals appraisals, AppraisalCategories appraisalCategory, AppraisalCategoryCode appraisalCategoryCode) {
		super();
		this.appraisals = appraisals;
		this.appraisalCategory = appraisalCategory;
		this.appraisalCategoryCode = appraisalCategoryCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		AppraisalChecklist other = (AppraisalChecklist) obj;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
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

	public Appraisals getAppraisals() {
		return appraisals;
	}

	public void setAppraisals(Appraisals appraisals) {
		this.appraisals = appraisals;
	}

	public AppraisalCategories getAppraisalCategory() {
		return appraisalCategory;
	}

	public void setAppraisalCategory(AppraisalCategories appraisalCategory) {
		this.appraisalCategory = appraisalCategory;
	}

	public AppraisalCategoryCode getAppraisalCategoryCode() {
		return appraisalCategoryCode;
	}

	public void setAppraisalCategoryCode(AppraisalCategoryCode appraisalCategoryCode) {
		this.appraisalCategoryCode = appraisalCategoryCode;
	}

}
