package haj.com.entity.lookup;

import static javax.persistence.GenerationType.IDENTITY;

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

import haj.com.annotations.CSVAnnotation;
import haj.com.annotations.CSVLookupAnnotation;
import haj.com.annotations.LegacyReportingAnnotation;
import haj.com.dao.lookup.ResolveByCodeLookupDAO;
import haj.com.entity.Company;
import haj.com.entity.OfoCodes;
import haj.com.framework.AbstractLookup;
import haj.com.service.lookup.HomeAffairsService;

// TODO: Auto-generated Javadoc
/**
 * Excel name: TVET.xlsx Tab Name: TVET
 *
 * @author Techfinium
 */
@Entity
@Table(name = "legacy_tvet")
//site info
@LegacyReportingAnnotation(name = "Total Apprenticeship Linked to Site", query = "select count(o) from LegacyTvet o where o.legacyOrganisationSites = true", key = "count")
@LegacyReportingAnnotation(name = "Total Apprenticeship not Linked to Site", query = "select count(o) from LegacyTvet o where o.legacyOrganisationSites = false", key = "count")
@LegacyReportingAnnotation(query = "select o from LegacyTvet o where o.legacyOrganisationSites = false", key = "Apprenticeship not Linked Site", returnType = LegacyTvet.class, singleResult = false)
//id info
@LegacyReportingAnnotation(name = "Total Valid ID Numbers", query = "select count(o) from LegacyTvet o where o.validRsaIdNumber = true", key = "count")
@LegacyReportingAnnotation(name = "Total Invalid ID Numbers", query = "select count(o) from LegacyTvet o where o.validRsaIdNumber = false", key = "count")
@LegacyReportingAnnotation(name = "Total ID Numbers on Home Affairs", query = "select count(o) from LegacyTvet o where o.appearsOnHomeAffairsData = true", key = "count")
@LegacyReportingAnnotation(name = "Total ID Numbers not on Home Affairs", query = "select count(o) from LegacyTvet o where o.appearsOnHomeAffairsData = false", key = "count")
@LegacyReportingAnnotation(name = "Total ID Numbers on Person File", query = "select count(o) from LegacyTvet o where o.idNo in (select distinct x.idNo from LegacyPerson x)", key = "count")
@LegacyReportingAnnotation(name = "Total ID Numbers not on Person File", query = "select count(o) from LegacyTvet o where o.idNo not in (select distinct x.idNo from LegacyPerson x)", key = "count")
@LegacyReportingAnnotation(query = "select o from LegacyTvet o where o.validRsaIdNumber = false", key = "Invalid ID Numbers", returnType = LegacyTvet.class, singleResult = false)
@LegacyReportingAnnotation(query = "select o from LegacyTvet o where o.appearsOnHomeAffairsData = false", key = "ID Numbers not on Home Affairs", returnType = LegacyTvet.class, singleResult = false)
@LegacyReportingAnnotation(query = "select o from LegacyTvet o where o.idNo not in (select distinct x.idNo from LegacyPerson x)", key = "ID Numbers not on Person File", returnType = LegacyTvet.class, singleResult = false)
//qualification info
@LegacyReportingAnnotation(name = "Total Linked Qualification", query = "select count(o) from LegacyTvet o where o.qualification is not null", key = "count")
@LegacyReportingAnnotation(name = "Total Non Linked Qualification", query = "select count(o) from LegacyTvet o where o.qualification is null", key = "count")
@LegacyReportingAnnotation(query = "select o from LegacyTvet o where o.qualification is null", key = "Non Linked Qualifications", returnType = LegacyTvet.class, singleResult = false)
//ofoCodes info
@LegacyReportingAnnotation(name = "Total Linked Ofo Codes", query = "select count(o) from LegacyTvet o where o.ofoCodes is not null", key = "count")
@LegacyReportingAnnotation(name = "Total Non Linked Ofo Codes", query = "select count(o) from LegacyTvet o where o.ofoCodes is null", key = "count")
@LegacyReportingAnnotation(query = "select o from LegacyTvet o where o.ofoCodes is null", key = "Non Linked Ofo Codes", returnType = LegacyTvet.class, singleResult = false)
//Application
@LegacyReportingAnnotation(name = "Total Application Status on Home Affairs", query = "select count(o) from LegacyTvet o where o.status = 'Application' and  o.appearsOnHomeAffairsData = true", key = "count")
@LegacyReportingAnnotation(name = "Total Application Status not on Home Affairs", query = "select count(o) from LegacyTvet o where o.status = 'Application' and  o.appearsOnHomeAffairsData = false", key = "count")
@LegacyReportingAnnotation(key = "Application Status not on Home Affairs", query = "select o from LegacyTvet o where o.status = 'Application' and  o.appearsOnHomeAffairsData = false", returnType = LegacyTvet.class, singleResult = false)
//Registered
@LegacyReportingAnnotation(name = "Total Registered Status on Home Affairs", query = "select count(o) from LegacyTvet o where o.status = 'Registered' and  o.appearsOnHomeAffairsData = true", key = "count")
@LegacyReportingAnnotation(name = "Total Registered Status not on Home Affairs", query = "select count(o) from LegacyTvet o where o.status = 'Registered' and  o.appearsOnHomeAffairsData = false", key = "count")
@LegacyReportingAnnotation(key = "Registered Status not on Home Affairs", query = "select o from LegacyTvet o where o.status = 'Registered' and  o.appearsOnHomeAffairsData = false", returnType = LegacyTvet.class, singleResult = false)
//Application
@LegacyReportingAnnotation(name = "Total Application Status on Person File", query = "select count(o) from LegacyTvet o where o.status = 'Application' and o.idNo in (select distinct x.idNo from LegacyPerson x)", key = "count")
@LegacyReportingAnnotation(name = "Total Application Status not on Person File", query = "select count(o) from LegacyTvet o where o.status = 'Application' and o.idNo not in (select distinct x.idNo from LegacyPerson x)", key = "count")
@LegacyReportingAnnotation(key = "Application Status not on Person File", query = "select o from LegacyTvet o where o.status = 'Application' and o.idNo not in (select distinct x.idNo from LegacyPerson x)", returnType = LegacyTvet.class, singleResult = false)
//Registered
@LegacyReportingAnnotation(name = "Total Registered Status on Person File", query = "select count(o) from LegacyTvet o where o.status = 'Registered' and o.idNo in (select distinct x.idNo from LegacyPerson x)", key = "count")
@LegacyReportingAnnotation(name = "Total Registered Status not on Person File", query = "select count(o) from LegacyTvet o where o.status = 'Registered' and o.idNo not in (select distinct x.idNo from LegacyPerson x)", key = "count")
@LegacyReportingAnnotation(key = "Registered Status not on Person File", query = "select o from LegacyTvet o where o.status = 'Registered' and o.idNo not in (select distinct x.idNo from LegacyPerson x)", returnType = LegacyTvet.class, singleResult = false)
//Application
@LegacyReportingAnnotation(name = "Total Application Status on Person File and Home Affairs", query = "select count(o) from LegacyTvet o where o.status = 'Application' and o.idNo in (select distinct x.idNo from LegacyPerson x) and  o.appearsOnHomeAffairsData = true", key = "count")
@LegacyReportingAnnotation(name = "Total Application Status not on Person File and Home Affairs", query = "select count(o) from LegacyTvet o where o.status = 'Application' and o.idNo not in (select distinct x.idNo from LegacyPerson x) and  o.appearsOnHomeAffairsData = false", key = "count")
@LegacyReportingAnnotation(key = "Application Status not on Person File and Home Affairs", query = "select o from LegacyTvet o where o.status = 'Application' and o.idNo not in (select distinct x.idNo from LegacyPerson x) and  o.appearsOnHomeAffairsData = false", returnType = LegacyTvet.class, singleResult = false)
//Registered
@LegacyReportingAnnotation(name = "Total Registered Status on Person File and Home Affairs", query = "select count(o) from LegacyTvet o where o.status = 'Registered' and o.idNo in (select distinct x.idNo from LegacyPerson x) and  o.appearsOnHomeAffairsData = true", key = "count")
@LegacyReportingAnnotation(name = "Total Registered Status not on Person File and Home Affairs", query = "select count(o) from LegacyTvet o where o.status = 'Registered' and o.idNo not in (select distinct x.idNo from LegacyPerson x) and  o.appearsOnHomeAffairsData = false", key = "count")
@LegacyReportingAnnotation(key = "Registered Status not on Person File and Home Affairs", query = "select o from LegacyTvet o where o.status = 'Registered' and o.idNo not in (select distinct x.idNo from LegacyPerson x) and  o.appearsOnHomeAffairsData = false", returnType = LegacyTvet.class, singleResult = false)
public class LegacyTvet extends AbstractLookup {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Unique Id of Accreditation. */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	/*
	 * PN: 1 Id
	 */
	@CSVAnnotation(name = "Id2", className = String.class)
	@Column(name = "id_two")
	private String idTwo;

	/*
	 * PN: 2 IDNo
	 */
	@CSVAnnotation(name = "IDNo", className = String.class, lookupField = "appearsOnHomeAffairsData")
	@Column(name = "id_no")
	private String idNo;

	@CSVAnnotation(name = "IDNo", className = String.class, lookupField = "validRsaIdNumber")
	@Transient
	private String idNoTransient;

	/*
	 * PN: 3 Firstname
	 */
	@CSVAnnotation(name = "Firstname", className = String.class)
	@Column(name = "first_name")
	private String firstName;

	/*
	 * PN: 4 MiddleNames
	 */
	@CSVAnnotation(name = "MiddleNames", className = String.class)
	@Column(name = "middle_names")
	private String middleNames;

	/*
	 * PN: 5 Surname
	 */
	@CSVAnnotation(name = "Surname", className = String.class)
	@Column(name = "sur_name")
	private String surname;

	/*
	 * PN: 6 StartDate
	 */
	@CSVAnnotation(name = "StartDate", className = String.class)
	@Column(name = "start_date")
	private String startDate;

	/*
	 * PN: 7 EndDate
	 */
	@CSVAnnotation(name = "EndDate", className = String.class)
	@Column(name = "end_date")
	private String endDate;

	/*
	 * PN: 8 ApplicationDate
	 */
	@CSVAnnotation(name = "ApplicationDate", className = String.class)
	@Column(name = "application_date")
	private String applicationDate;

	/*
	 * PN: 9 RegistrationDate
	 */
	@CSVAnnotation(name = "RegistrationDate", className = String.class)
	@Column(name = "registration_date")
	private String registrationDate;

	/*
	 * PN: 10 CompletionDate
	 */
	@CSVAnnotation(name = "CompletionDate", className = String.class)
	@Column(name = "completion_date")
	private String completionDate;

	/*
	 * PN: 11 Status
	 */
	@CSVAnnotation(name = "Status", className = String.class)
	@Column(name = "status")
	private String status;

	/*
	 * PN: 12 EmployerSDL
	 */
	@CSVAnnotation(name = "EmployerSDL", className = String.class, lookupField = "legacyOrganisationSites")
	@Column(name = "employer_sdl")
	private String employerSDL;

	@Column(name = "legacy_organisation_sites")
	@CSVLookupAnnotation(className = ResolveByCodeLookupDAO.class, method = "findLegacyOrganisationSites", paramClass = String.class)
	private Boolean legacyOrganisationSites;

	/*
	 * PN: 13 OrganisationNameLegal
	 */
	@CSVAnnotation(name = "OrganisationNameLegal", className = String.class)
	@Column(name = "organisation_name_legal")
	private String organisationNameLegal;

	/*
	 * PN: 14 OrganisationNameTrade
	 */
	@CSVAnnotation(name = "OrganisationNameTrade", className = String.class)
	@Column(name = "organisation_name_trade")
	private String organisationNameTrade;

	/*
	 * PN: 15 ProgrammeType
	 */
	@CSVAnnotation(name = "ProgrammeType", className = String.class)
	@Column(name = "programme_type")
	private String programmeType;

	/*
	 * PN: 16 LearningArea
	 */
	@CSVAnnotation(name = "LearningArea", className = String.class)
	@Column(name = "learning_area")
	private String learningArea;

	/*
	 * PN: 17 LearningAreaOther
	 */
	@CSVAnnotation(name = "LearningAreaOther", className = String.class)
	@Column(name = "learning_area_other")
	private String learningAreaOther;

	/*
	 * PN: 18 Provider
	 */
	@CSVAnnotation(name = "Provider", className = String.class)
	@Column(name = "provider")
	private String provider;

	/*
	 * PN: 19 OtherProvider
	 */
	@CSVAnnotation(name = "OtherProvider", className = String.class)
	@Column(name = "other_provider")
	private String otherProvider;

	/*
	 * PN: 20 ProviderType
	 */
	@CSVAnnotation(name = "ProviderType", className = String.class)
	@Column(name = "provider_type")
	private String providerType;

	/*
	 * PN: 21 OFOCode
	 */
	@CSVAnnotation(name = "OFOCode", className = String.class, lookupField = "ofoCodes")
	@Column(name = "ofo_code")
	private String ofoCode;

	/*
	 * PN: 22 SkillPriority
	 */
	@CSVAnnotation(name = "SkillPriority", className = String.class)
	@Column(name = "skill_priority")
	private String skillPriority;

	/*
	 * PN: 23 NQFLevel
	 */
	@CSVAnnotation(name = "NQFLevel", className = String.class)
	@Column(name = "nqf_level")
	private String nqfLevel;

	/*
	 * PN: 24 FundingStatus
	 */
	@CSVAnnotation(name = "FundingStatus", className = String.class)
	@Column(name = "funding_status")
	private String fundingStatus;

	/*
	 * PN: 25 PartnershipSDL
	 */
	@CSVAnnotation(name = "PartnershipSDL", className = String.class)
	@Column(name = "partnership_sdl")
	private String partnershipSDL;

	/*
	 * PN: 26 partnetLegalName
	 */
	@CSVAnnotation(name = "partnetLegalName", className = String.class)
	@Column(name = "partnet_legal_name")
	private String partnetLegalName;

	/*
	 * PN: 27 DGTag
	 */
	@CSVAnnotation(name = "DGTag", className = String.class)
	@Column(name = "dg_tag")
	private String dgTag;

	/*
	 * PN: 28 Amount
	 */
	@CSVAnnotation(name = "Amount", className = String.class)
	@Column(name = "amount")
	private String amount;

	/*
	 * PN: 29 SSPNeedsID
	 */
	@CSVAnnotation(name = "SSPNeedsID", className = String.class)
	@Column(name = "ssp_needs_id")
	private String sspNeedsid;

	/*
	 * PN: 30 TVETQualification
	 */
	@CSVAnnotation(name = "TVETQualification", className = String.class)
	@Column(name = "tvet_qualification")
	private String tvetQualification;

	/*
	 * PN: 31 DateCreated
	 */
	@CSVAnnotation(name = "DateCreated", className = String.class)
	@Column(name = "date_created")
	private String dateCreated;

	/*
	 * PN: 32 DateUpdated
	 */
	@CSVAnnotation(name = "DateUpdated", className = String.class)
	@Column(name = "date_updated")
	private String dateUpdated;

	/*
	 * PN: 33 SAQA ID
	 */
	@CSVAnnotation(name = "SAQA_ID", className = String.class, lookupField = "qualification")
	@Column(name = "saqa_id")
	private String saqaID;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "qualification_id")
	@CSVLookupAnnotation(className = ResolveByCodeLookupDAO.class, method = "findQualification", paramClass = String.class)
	private Qualification qualification;

	@Column(name = "appears_on_home_affairs_data")
	@CSVLookupAnnotation(className = HomeAffairsService.class, method = "findByDhaIdNumberMore", paramClass = String.class)
	private Boolean appearsOnHomeAffairsData;

	@Column(name = "valid_rsa_id_number")
	@CSVLookupAnnotation(className = HomeAffairsService.class, method = "checkRsaId", paramClass = String.class)
	private Boolean validRsaIdNumber;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ofo_codes_id")
	@CSVLookupAnnotation(className = ResolveByCodeLookupDAO.class, method = "findOfoCodeAndSpecialisation", paramClass = String.class)
	private OfoCodes ofoCodes;
	
	@Column(name = "created_company_learner")
	private Boolean createdCompanyLearner;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employer_id", nullable = true)
	private Company employer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = true)
	private Company company;

	/**
	 * Instantiates a new Accreditation.
	 */
	public LegacyTvet() {
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
		LegacyTvet other = (LegacyTvet) obj;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		return true;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleNames() {
		return middleNames;
	}

	public void setMiddleNames(String middleNames) {
		this.middleNames = middleNames;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmployerSDL() {
		return employerSDL;
	}

	public void setEmployerSDL(String employerSDL) {
		this.employerSDL = employerSDL;
	}

	public String getOrganisationNameLegal() {
		return organisationNameLegal;
	}

	public void setOrganisationNameLegal(String organisationNameLegal) {
		this.organisationNameLegal = organisationNameLegal;
	}

	public String getOrganisationNameTrade() {
		return organisationNameTrade;
	}

	public void setOrganisationNameTrade(String organisationNameTrade) {
		this.organisationNameTrade = organisationNameTrade;
	}

	public String getProgrammeType() {
		return programmeType;
	}

	public void setProgrammeType(String programmeType) {
		this.programmeType = programmeType;
	}

	public String getLearningArea() {
		return learningArea;
	}

	public void setLearningArea(String learningArea) {
		this.learningArea = learningArea;
	}

	public String getLearningAreaOther() {
		return learningAreaOther;
	}

	public void setLearningAreaOther(String learningAreaOther) {
		this.learningAreaOther = learningAreaOther;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getOtherProvider() {
		return otherProvider;
	}

	public void setOtherProvider(String otherProvider) {
		this.otherProvider = otherProvider;
	}

	public String getProviderType() {
		return providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	public String getOfoCode() {
		return ofoCode;
	}

	public void setOfoCode(String ofoCode) {
		this.ofoCode = ofoCode;
	}

	public String getSkillPriority() {
		return skillPriority;
	}

	public void setSkillPriority(String skillPriority) {
		this.skillPriority = skillPriority;
	}

	public String getNqfLevel() {
		return nqfLevel;
	}

	public void setNqfLevel(String nqfLevel) {
		this.nqfLevel = nqfLevel;
	}

	public String getFundingStatus() {
		return fundingStatus;
	}

	public void setFundingStatus(String fundingStatus) {
		this.fundingStatus = fundingStatus;
	}

	public String getPartnershipSDL() {
		return partnershipSDL;
	}

	public void setPartnershipSDL(String partnershipSDL) {
		this.partnershipSDL = partnershipSDL;
	}

	public String getPartnetLegalName() {
		return partnetLegalName;
	}

	public void setPartnetLegalName(String partnetLegalName) {
		this.partnetLegalName = partnetLegalName;
	}

	public String getDgTag() {
		return dgTag;
	}

	public void setDgTag(String dgTag) {
		this.dgTag = dgTag;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSspNeedsid() {
		return sspNeedsid;
	}

	public void setSspNeedsid(String sspNeedsid) {
		this.sspNeedsid = sspNeedsid;
	}

	public String getTvetQualification() {
		return tvetQualification;
	}

	public void setTvetQualification(String tvetQualification) {
		this.tvetQualification = tvetQualification;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdTwo() {
		return idTwo;
	}

	public void setIdTwo(String idTwo) {
		this.idTwo = idTwo;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getSaqaID() {
		return saqaID;
	}

	public void setSaqaID(String saqaID) {
		this.saqaID = saqaID;
	}

	public String getIdNoTransient() {
		return idNoTransient;
	}

	public void setIdNoTransient(String idNoTransient) {
		this.idNoTransient = idNoTransient;
	}

	public Boolean getLegacyOrganisationSites() {
		return legacyOrganisationSites;
	}

	public void setLegacyOrganisationSites(Boolean legacyOrganisationSites) {
		this.legacyOrganisationSites = legacyOrganisationSites;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public Boolean getAppearsOnHomeAffairsData() {
		return appearsOnHomeAffairsData;
	}

	public void setAppearsOnHomeAffairsData(Boolean appearsOnHomeAffairsData) {
		this.appearsOnHomeAffairsData = appearsOnHomeAffairsData;
	}

	public Boolean getValidRsaIdNumber() {
		return validRsaIdNumber;
	}

	public void setValidRsaIdNumber(Boolean validRsaIdNumber) {
		this.validRsaIdNumber = validRsaIdNumber;
	}

	public OfoCodes getOfoCodes() {
		return ofoCodes;
	}

	public void setOfoCodes(OfoCodes ofoCodes) {
		this.ofoCodes = ofoCodes;
	}

	public Boolean getCreatedCompanyLearner() {
		return createdCompanyLearner;
	}

	public void setCreatedCompanyLearner(Boolean createdCompanyLearner) {
		this.createdCompanyLearner = createdCompanyLearner;
	}

	public Company getEmployer() {
		return employer;
	}

	public Company getCompany() {
		return company;
	}

	public void setEmployer(Company employer) {
		this.employer = employer;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
