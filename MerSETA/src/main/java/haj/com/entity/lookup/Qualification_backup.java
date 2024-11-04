package haj.com.entity.lookup;

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

import haj.com.framework.AbstractLookup;


// TODO: Auto-generated Javadoc
/**
 * The Class Qualification_backup.
 */
@Entity
@Table(name = "qualification")
public class Qualification_backup  extends AbstractLookup
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	/** The description. */
	@Column(name="description", length=500)
	private String description;
	
    /** The nqf level. */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="nqf_level_id")
	private NqfLevels nqfLevel;

    /** The create date. */
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 19)
    private Date createDate;
    
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
		Qualification_backup other = (Qualification_backup) obj;
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
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the nqf level.
	 *
	 * @return the nqf level
	 */
	public NqfLevels getNqfLevel() {
		return nqfLevel;
	}

	/**
	 * Sets the nqf level.
	 *
	 * @param nqfLevel the new nqf level
	 */
	public void setNqfLevel(NqfLevels nqfLevel) {
		this.nqfLevel = nqfLevel;
	}

	/* (non-Javadoc)
	 * @see haj.com.framework.AbstractLookup#getCreateDate()
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/* (non-Javadoc)
	 * @see haj.com.framework.AbstractLookup#setCreateDate(java.util.Date)
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}