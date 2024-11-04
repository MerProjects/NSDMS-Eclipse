package haj.com.entity.lookup;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import haj.com.framework.AbstractLookup;

// TODO: Auto-generated Javadoc
/**
 * ProviderType Provider_Type_Id.
 *
 * @author Techfinium
 */
@Entity
@Table(name = "provider_type")
public class ProviderType extends AbstractLookup {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Unique Id of ProviderType. */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	/** Description of ProviderType. */
	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "appear_on_wsp", columnDefinition = "BIT default false")
	private Boolean appearOnWsp;
	
	@Column(name = "appear_on_provider_reg", columnDefinition = "BIT default false")
	private Boolean appearOnProviderReg;


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
		ProviderType other = (ProviderType) obj;
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
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAppearOnWsp() {
		return appearOnWsp;
	}

	public void setAppearOnWsp(Boolean appearOnWsp) {
		this.appearOnWsp = appearOnWsp;
	}

	public Boolean getAppearOnProviderReg() {
		return appearOnProviderReg;
	}

	public void setAppearOnProviderReg(Boolean appearOnProviderReg) {
		this.appearOnProviderReg = appearOnProviderReg;
	}

}