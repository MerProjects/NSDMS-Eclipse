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

import haj.com.framework.IDataEntity;

// TODO: Auto-generated Javadoc
/**
 *
 * @author Techfinium
 */
@Entity
@Table(name = "chat_message")
public class ChatMessage implements IDataEntity
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

	/** The hosting company departments. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hosting_company_department_id")
	@Fetch(FetchMode.JOIN)
	private HostingCompanyDepartments hostingCompanyDepartments;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = true)
	@Fetch(FetchMode.JOIN)
	private Users user;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_chat_id", nullable = true)
	@Fetch(FetchMode.JOIN)
	private ChatMessage parentChat;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attend_to_by_staff_id", nullable = true)
	private Users attendToByStaff;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "attend_to_date_by_staff", length = 19)
	private Date attendToDateByStaff;



	/** Note. */
	@Column(name="msg", columnDefinition="LONGTEXT")
    private String msg;




	public ChatMessage(HostingCompanyDepartments hostingCompanyDepartments, Users user) {
		super();
		this.hostingCompanyDepartments = hostingCompanyDepartments;
		this.user = user;
	}

	public ChatMessage() {
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
		ChatMessage other = (ChatMessage) obj;
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

	public HostingCompanyDepartments getHostingCompanyDepartments() {
		return hostingCompanyDepartments;
	}

	public void setHostingCompanyDepartments(HostingCompanyDepartments hostingCompanyDepartments) {
		this.hostingCompanyDepartments = hostingCompanyDepartments;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}



	public ChatMessage getParentChat() {
		return parentChat;
	}

	public void setParentChat(ChatMessage parentChat) {
		this.parentChat = parentChat;
	}

	public Date getAttendToDateByStaff() {
		return attendToDateByStaff;
	}

	public void setAttendToDateByStaff(Date attendToDateByStaff) {
		this.attendToDateByStaff = attendToDateByStaff;
	}

	public Users getAttendToByStaff() {
		return attendToByStaff;
	}

	public void setAttendToByStaff(Users attendToByStaff) {
		this.attendToByStaff = attendToByStaff;
	}


}