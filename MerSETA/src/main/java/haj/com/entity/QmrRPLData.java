package haj.com.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import haj.com.entity.enums.EmploymentStatusEnum;
import haj.com.entity.enums.QmrEnteredCompletedEnum;
import haj.com.entity.enums.QmrEquityEnum;
import haj.com.entity.enums.QmrGenderEnum;
import haj.com.framework.IDataEntity;

/**
 * QmrRPLData.
 *
 * @author Techfinium
 */
@Entity
@Table(name = "qmr_rpl_data")
@AuditTable(value = "qmr_rpl_data_hist")
@Audited
public class QmrRPLData implements IDataEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Unique Id of QmrRPLData. */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	/** Create Date of Object. */
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 19)
	private Date createDate;
	
	// link to the quarter
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "qmr_fin_years_id", nullable = true)
	private QmrFinYears qmrFinYears;
	
	// Entered /  Completed indicator
	@Enumerated
	@Column(name = "qmr_entered_completed", length = 19)
	private QmrEnteredCompletedEnum qmrEnteredCompleted;
	
	// Gender Indicator
	@Enumerated
	@Column(name = "qmr_gender", length = 19)
	private QmrGenderEnum qmrGender;
	
	// Equity Indicator
	@Enumerated
	@Column(name = "qmr_equity", length = 19)
	private QmrEquityEnum qmrEquity;
	
	// Employed / unemployed Indicator
	@Enumerated
	@Column(name = "employed_un_employed", length = 19)
	private EmploymentStatusEnum employedUnEmployed;
	
	// Age Indicator
	@Column(name = "age_indicator", length = 10)
	private Integer ageIndicator;
	
	// Citizen indicator
	@Column(name = "rsa_citizen")
	private Boolean rsaCitizen;
	
	// Disabled indicator
	@Column(name = "disabilityIdentified")
	private Boolean disabilityIdentified;
	
	// Company Learner Flat Link
	@Column(name = "company_learner_flat_link")
	private Long companyLearnerFlatLink;
	
	/* QMR Data Start */
	@Column(name = "reporting_period")
	private String reportingPeriod;
	
	@Column(name = "names_of_the_learner")
	private String namesOfTheLearner;
	
	@Column(name = "surname_of_the_learner")
	private String surnameOfTheLearner;
	
	@Column(name = "id_number_of_the_learner")
	private String idNumberOfTheLearner;
	
	@Column(name = "type_of_learning_programme")
	private String typeOfLearningProgramme;
	
	/* entered learner ship dates */
	@Column(name = "date_the_learner_entered_the_learning_programme")
	private Date dateTheLearnerEnteredTheLearningProgramme;
	
	@Column(name = "actual_start_date_of_the_learning_programme")
	private Date actualStartDateOfTheLearningProgramme;
	
	/* completed learner ship dates */
	@Column(name = "date_the_learner_completed_the_learning_programme")
	private Date dateTheLearnerCompletedTheLearningProgramme;
	
	@Column(name = "date_recived_certificate_learning_programme")
	private Date dateRecivedCertificateLearningProgramme;
	
	@Column(name = "ofo_code")
	private String ofoCode;
	
	@Column(name = "nqf_level")
	private String nqfLevel;
	
	@Column(name = "qualification_as_per_ofo_code_desctiption_of_the_qualification")
	private String qualificationAsPerOfoCodeDesctiptionOfTheQualification;
	
	@Column(name = "name_of_the_employer")
	private String nameOfTheEmployer;
	
	@Column(name = "employer_registartion_sdl_number")
	private String employerRegistartionSdlNumber;
	
	@Column(name = "employer_contact_details")
	private String employerContactDetails;
	
	@Column(name = "name_of_the_traing_provider")
	private String nameOfTheTraingProvider;
	
	@Column(name = "training_provider_accrediciation_number")
	private String trainingProviderAccrediciationNumber;
	
	@Column(name = "training_provider_contact_details")
	private String trainingProviderContactDetails;
	
	@Column(name = "is_training_provider_private_public")
	private String isTrainingProviderPrivatePublic;
	
	@Column(name = "learner_province")
	private String learnerProvince;
	
	@Column(name = "learner_local_district_munciplaity")
	private String learnerLocalDistrictMunciplaity;
	
	@Column(name = "specify_learner_residential_area")
	private String specifyLearnerResidentialArea;
	
	@Column(name = "is_the_learner_residential_area_urban_rural")
	private String isTheLearnerResidentialAreaUrbanRural;
	
	@Column(name = "is_the_programme_seta_industry_funded")
	private String isTheProgrammeSetaIndustryFunded;
	
	@Column(name = "amount_spent_per_learner")
	private Double amountSpentPerLearner;
	
	@Column(name = "race")
	private String race;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "age_of_learner")
	private Integer ageOfLearner;
	
	@Column(name = "disability")
	private String disability;
	
	@Column(name = "youth")
	private String youth;
	
	@Column(name = "non_rsa_citizen")
	private String nonRsaCitizen;
	/* QMR Data End */
	
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
		QmrRPLData other = (QmrRPLData) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*
	 * Index:
	 * CREATE INDEX IDX_DR_qmr_rpl_data ON merseta.qmr_rpl_data (qmr_fin_years_id, qmr_entered_completed);
	 */
	
	/* Getters and setters */
	
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

	public QmrFinYears getQmrFinYears() {
		return qmrFinYears;
	}

	public void setQmrFinYears(QmrFinYears qmrFinYears) {
		this.qmrFinYears = qmrFinYears;
	}

	public QmrGenderEnum getQmrGender() {
		return qmrGender;
	}

	public void setQmrGender(QmrGenderEnum qmrGender) {
		this.qmrGender = qmrGender;
	}

	public QmrEquityEnum getQmrEquity() {
		return qmrEquity;
	}

	public void setQmrEquity(QmrEquityEnum qmrEquity) {
		this.qmrEquity = qmrEquity;
	}

	public Integer getAgeIndicator() {
		return ageIndicator;
	}

	public void setAgeIndicator(Integer ageIndicator) {
		this.ageIndicator = ageIndicator;
	}

	public Boolean getRsaCitizen() {
		return rsaCitizen;
	}

	public void setRsaCitizen(Boolean rsaCitizen) {
		this.rsaCitizen = rsaCitizen;
	}

	public Boolean getDisabilityIdentified() {
		return disabilityIdentified;
	}

	public void setDisabilityIdentified(Boolean disabilityIdentified) {
		this.disabilityIdentified = disabilityIdentified;
	}

	public Long getCompanyLearnerFlatLink() {
		return companyLearnerFlatLink;
	}

	public void setCompanyLearnerFlatLink(Long companyLearnerFlatLink) {
		this.companyLearnerFlatLink = companyLearnerFlatLink;
	}

	public String getReportingPeriod() {
		return reportingPeriod;
	}

	public void setReportingPeriod(String reportingPeriod) {
		this.reportingPeriod = reportingPeriod;
	}

	public String getNamesOfTheLearner() {
		return namesOfTheLearner;
	}

	public void setNamesOfTheLearner(String namesOfTheLearner) {
		this.namesOfTheLearner = namesOfTheLearner;
	}

	public String getSurnameOfTheLearner() {
		return surnameOfTheLearner;
	}

	public void setSurnameOfTheLearner(String surnameOfTheLearner) {
		this.surnameOfTheLearner = surnameOfTheLearner;
	}

	public String getIdNumberOfTheLearner() {
		return idNumberOfTheLearner;
	}

	public void setIdNumberOfTheLearner(String idNumberOfTheLearner) {
		this.idNumberOfTheLearner = idNumberOfTheLearner;
	}

	public String getTypeOfLearningProgramme() {
		return typeOfLearningProgramme;
	}

	public void setTypeOfLearningProgramme(String typeOfLearningProgramme) {
		this.typeOfLearningProgramme = typeOfLearningProgramme;
	}

	public String getOfoCode() {
		return ofoCode;
	}

	public void setOfoCode(String ofoCode) {
		this.ofoCode = ofoCode;
	}

	public String getNqfLevel() {
		return nqfLevel;
	}

	public void setNqfLevel(String nqfLevel) {
		this.nqfLevel = nqfLevel;
	}

	public String getQualificationAsPerOfoCodeDesctiptionOfTheQualification() {
		return qualificationAsPerOfoCodeDesctiptionOfTheQualification;
	}

	public void setQualificationAsPerOfoCodeDesctiptionOfTheQualification(
			String qualificationAsPerOfoCodeDesctiptionOfTheQualification) {
		this.qualificationAsPerOfoCodeDesctiptionOfTheQualification = qualificationAsPerOfoCodeDesctiptionOfTheQualification;
	}

	public String getNameOfTheEmployer() {
		return nameOfTheEmployer;
	}

	public void setNameOfTheEmployer(String nameOfTheEmployer) {
		this.nameOfTheEmployer = nameOfTheEmployer;
	}

	public String getEmployerRegistartionSdlNumber() {
		return employerRegistartionSdlNumber;
	}

	public void setEmployerRegistartionSdlNumber(String employerRegistartionSdlNumber) {
		this.employerRegistartionSdlNumber = employerRegistartionSdlNumber;
	}

	public String getEmployerContactDetails() {
		return employerContactDetails;
	}

	public void setEmployerContactDetails(String employerContactDetails) {
		this.employerContactDetails = employerContactDetails;
	}

	public String getNameOfTheTraingProvider() {
		return nameOfTheTraingProvider;
	}

	public void setNameOfTheTraingProvider(String nameOfTheTraingProvider) {
		this.nameOfTheTraingProvider = nameOfTheTraingProvider;
	}

	public String getTrainingProviderAccrediciationNumber() {
		return trainingProviderAccrediciationNumber;
	}

	public void setTrainingProviderAccrediciationNumber(String trainingProviderAccrediciationNumber) {
		this.trainingProviderAccrediciationNumber = trainingProviderAccrediciationNumber;
	}

	public String getTrainingProviderContactDetails() {
		return trainingProviderContactDetails;
	}

	public void setTrainingProviderContactDetails(String trainingProviderContactDetails) {
		this.trainingProviderContactDetails = trainingProviderContactDetails;
	}

	public String getIsTrainingProviderPrivatePublic() {
		return isTrainingProviderPrivatePublic;
	}

	public void setIsTrainingProviderPrivatePublic(String isTrainingProviderPrivatePublic) {
		this.isTrainingProviderPrivatePublic = isTrainingProviderPrivatePublic;
	}

	public String getLearnerProvince() {
		return learnerProvince;
	}

	public void setLearnerProvince(String learnerProvince) {
		this.learnerProvince = learnerProvince;
	}

	public String getLearnerLocalDistrictMunciplaity() {
		return learnerLocalDistrictMunciplaity;
	}

	public void setLearnerLocalDistrictMunciplaity(String learnerLocalDistrictMunciplaity) {
		this.learnerLocalDistrictMunciplaity = learnerLocalDistrictMunciplaity;
	}

	public String getSpecifyLearnerResidentialArea() {
		return specifyLearnerResidentialArea;
	}

	public void setSpecifyLearnerResidentialArea(String specifyLearnerResidentialArea) {
		this.specifyLearnerResidentialArea = specifyLearnerResidentialArea;
	}

	public String getIsTheLearnerResidentialAreaUrbanRural() {
		return isTheLearnerResidentialAreaUrbanRural;
	}

	public void setIsTheLearnerResidentialAreaUrbanRural(String isTheLearnerResidentialAreaUrbanRural) {
		this.isTheLearnerResidentialAreaUrbanRural = isTheLearnerResidentialAreaUrbanRural;
	}

	public String getIsTheProgrammeSetaIndustryFunded() {
		return isTheProgrammeSetaIndustryFunded;
	}

	public void setIsTheProgrammeSetaIndustryFunded(String isTheProgrammeSetaIndustryFunded) {
		this.isTheProgrammeSetaIndustryFunded = isTheProgrammeSetaIndustryFunded;
	}

	public Double getAmountSpentPerLearner() {
		return amountSpentPerLearner;
	}

	public void setAmountSpentPerLearner(Double amountSpentPerLearner) {
		this.amountSpentPerLearner = amountSpentPerLearner;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDisability() {
		return disability;
	}

	public void setDisability(String disability) {
		this.disability = disability;
	}

	public String getYouth() {
		return youth;
	}

	public void setYouth(String youth) {
		this.youth = youth;
	}

	public String getNonRsaCitizen() {
		return nonRsaCitizen;
	}

	public void setNonRsaCitizen(String nonRsaCitizen) {
		this.nonRsaCitizen = nonRsaCitizen;
	}

	public QmrEnteredCompletedEnum getQmrEnteredCompleted() {
		return qmrEnteredCompleted;
	}

	public void setQmrEnteredCompleted(QmrEnteredCompletedEnum qmrEnteredCompleted) {
		this.qmrEnteredCompleted = qmrEnteredCompleted;
	}

	public Date getDateTheLearnerEnteredTheLearningProgramme() {
		return dateTheLearnerEnteredTheLearningProgramme;
	}

	public void setDateTheLearnerEnteredTheLearningProgramme(Date dateTheLearnerEnteredTheLearningProgramme) {
		this.dateTheLearnerEnteredTheLearningProgramme = dateTheLearnerEnteredTheLearningProgramme;
	}

	public Date getActualStartDateOfTheLearningProgramme() {
		return actualStartDateOfTheLearningProgramme;
	}

	public void setActualStartDateOfTheLearningProgramme(Date actualStartDateOfTheLearningProgramme) {
		this.actualStartDateOfTheLearningProgramme = actualStartDateOfTheLearningProgramme;
	}

	public Date getDateTheLearnerCompletedTheLearningProgramme() {
		return dateTheLearnerCompletedTheLearningProgramme;
	}

	public void setDateTheLearnerCompletedTheLearningProgramme(Date dateTheLearnerCompletedTheLearningProgramme) {
		this.dateTheLearnerCompletedTheLearningProgramme = dateTheLearnerCompletedTheLearningProgramme;
	}

	public Date getDateRecivedCertificateLearningProgramme() {
		return dateRecivedCertificateLearningProgramme;
	}

	public void setDateRecivedCertificateLearningProgramme(Date dateRecivedCertificateLearningProgramme) {
		this.dateRecivedCertificateLearningProgramme = dateRecivedCertificateLearningProgramme;
	}

	public Integer getAgeOfLearner() {
		return ageOfLearner;
	}

	public void setAgeOfLearner(Integer ageOfLearner) {
		this.ageOfLearner = ageOfLearner;
	}

	public EmploymentStatusEnum getEmployedUnEmployed() {
		return employedUnEmployed;
	}

	public void setEmployedUnEmployed(EmploymentStatusEnum employedUnEmployed) {
		this.employedUnEmployed = employedUnEmployed;
	}

}