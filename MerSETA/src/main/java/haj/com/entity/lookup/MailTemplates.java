package haj.com.entity.lookup;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import haj.com.entity.enums.MailEnum;
import haj.com.framework.IDataEntity;

// TODO: Auto-generated Javadoc
/**
 * MailTemplates.
 *
 * @author Techfinium
 */
@Entity
@Table(name = "mail_templates")
public class MailTemplates implements IDataEntity {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Unique Id of bank. */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	/** The mail code. */
	@Enumerated
	@NaturalId
	@Column(name = "mail_code")
	private MailEnum mailCode;
	
	/** Description of bank. */
	@Column(name = "subject_text", length=500)
	private String subjectText;
	
	/** Description of bank. */
	@Column(name = "template_text", columnDefinition = "LONGTEXT")
	private String templateText;

	/**
	 * Instantiates a new bank.
	 */
	public MailTemplates() {
		super();
		// TODO Auto-generated constructor stub
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
		MailTemplates other = (MailTemplates) obj;
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
	 * @param id            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the template text.
	 *
	 * @return the template text
	 */
	public String getTemplateText() {
		return templateText;
	}

	/**
	 * Sets the template text.
	 *
	 * @param templateText the new template text
	 */
	public void setTemplateText(String templateText) {
		this.templateText = templateText;
	}

	/**
	 * Gets the mail code.
	 *
	 * @return the mail code
	 */
	public MailEnum getMailCode() {
		return mailCode;
	}

	/**
	 * Sets the mail code.
	 *
	 * @param mailCode the new mail code
	 */
	public void setMailCode(MailEnum mailCode) {
		this.mailCode = mailCode;
	}

	public String getSubjectText() {
		return subjectText;
	}

	public void setSubjectText(String subjectText) {
		this.subjectText = subjectText;
	}

	

}
