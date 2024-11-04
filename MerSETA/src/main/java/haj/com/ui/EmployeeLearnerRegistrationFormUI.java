package haj.com.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.context.PrimePartialResponseWriter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import haj.com.constants.HAJConstants;
import haj.com.entity.Address;
import haj.com.entity.Appointment;
import haj.com.entity.Company;
import haj.com.entity.CompanyLearners;
import haj.com.entity.CompanyLearnersTransfer;
import haj.com.entity.CompanyQualifications;
import haj.com.entity.Doc;
import haj.com.entity.Employees;
import haj.com.entity.NonSetaCompany;
import haj.com.entity.ProjectImplementationPlan;
import haj.com.entity.Sites;
import haj.com.entity.TrainingProviderApplication;
import haj.com.entity.UserQualifications;
import haj.com.entity.Users;
import haj.com.entity.UsersDisability;
import haj.com.entity.UsersLanguage;
import haj.com.entity.UsersTrainingProvider;
import haj.com.entity.WorkPlaceApproval;
import haj.com.entity.enums.ApprovalEnum;
import haj.com.entity.enums.CompanyStatusEnum;
import haj.com.entity.enums.ConfigDocProcessEnum;
import haj.com.entity.enums.EmploymentStatusEnum;
import haj.com.entity.enums.IdPassportEnum;
import haj.com.entity.enums.InterventionTypeEnum;
import haj.com.entity.enums.LearnerStatusEnum;
import haj.com.entity.enums.YesNoEnum;
import haj.com.entity.lookup.DesignatedTradeLevel;
import haj.com.entity.lookup.DesignatedTradeType;
import haj.com.entity.lookup.DisabilityRating;
import haj.com.entity.lookup.Funding;
import haj.com.entity.lookup.InterventionType;
import haj.com.entity.lookup.Learnership;
import haj.com.entity.lookup.Qualification;
import haj.com.entity.lookup.QualificationType;
import haj.com.entity.lookup.RejectReasons;
import haj.com.entity.lookup.SkillsProgram;
import haj.com.entity.lookup.SkillsSet;
import haj.com.entity.lookup.UnitStandards;
import haj.com.exceptions.ValidationException;
import haj.com.framework.AbstractUI;
import haj.com.service.AddressService;
import haj.com.service.CompanyLearnersService;
import haj.com.service.CompanyLearnersTradeTestService;
import haj.com.service.CompanyQualificationsService;
import haj.com.service.CompanyService;
import haj.com.service.CompanyUnitStandardService;
import haj.com.service.CompanyUsersService;
import haj.com.service.DocService;
import haj.com.service.EmployeeCompanyLearnersService;
import haj.com.service.EmployeesService;
import haj.com.service.NonSetaCompanyService;
import haj.com.service.ProjectImplementationPlanService;
import haj.com.service.SitesService;
import haj.com.service.TasksService;
import haj.com.service.TrainingProviderApplicationService;
import haj.com.service.TrainingProviderSkillsProgrammeService;
import haj.com.service.TrainingProviderSkillsSetService;
import haj.com.service.UserQualificationsService;
import haj.com.service.UsersDisabilityService;
import haj.com.service.UsersLanguageService;
import haj.com.service.UsersService;
import haj.com.service.UsersTrainingProviderService;
import haj.com.service.WorkPlaceApprovalService;
import haj.com.service.lookup.DesignatedTradeLevelService;
import haj.com.service.lookup.DisabilityRatingService;
import haj.com.service.lookup.FundingService;
import haj.com.service.lookup.LearnershipService;
import haj.com.service.lookup.RejectReasonsService;
import haj.com.service.lookup.SaqaUsQualificationService;
import haj.com.utils.GenericUtility;
import haj.com.validators.CheckID;

import static haj.com.constants.HAJConstants.SKILLS_PROGRAM_LIST;
import static haj.com.constants.HAJConstants.SKILLS_SET_LIST;

@ManagedBean(name = "employeeLearnerRegistrationFormUI")
@ViewScoped
public class EmployeeLearnerRegistrationFormUI extends AbstractUI {

	/** The idpassport. */
	private IdPassportEnum idpassport = IdPassportEnum.Passport;

	/** The idnumber. */
	@CheckID(message = "Not a valid RSA ID number")
	private String idnumber;
	private String accreditedQuallMssg;
	private String nonCredidBearingDescription;

	private String workplaceApprovalMsg;

	/** The passport number. */
	private String passportNumber;
	private String documentBoxID;

	/** The search user passport or id UI. */
	@ManagedProperty(value = "#{searchUserPassportOrIdUI}")
	private SearchUserPassportOrIdUI searchUserPassportOrIdUI;

	/** The search company UI. */
	@ManagedProperty(value = "#{searchCompanyUI}")
	private SearchCompanyUI searchCompanyUI;

	/** The user. */
	private Users user;
	private Users gaurdian;
	private boolean requireGaurdian = true;

	/** The users service. */
	private UsersService usersService = new UsersService();
	private UsersLanguageService usersLanguageService = new UsersLanguageService();

	/** The company. */
	private Company company;

	/** The training provider. */
	private Company trainingProvider;

	/** The training provider. */
	private NonSetaCompany nonSetaCompany;

	private Company hostCompany;

	/** The company service. */
	private CompanyService companyService = new CompanyService();

	/** The company users service. */
	private CompanyUsersService companyUsersService = new CompanyUsersService();
	private NonSetaCompanyService nonSetaCompanyService = new NonSetaCompanyService();
	private CompanyQualificationsService companyQualificationsService = new CompanyQualificationsService();
	private TrainingProviderApplicationService trainingProviderApplicationService = new TrainingProviderApplicationService();
	private CompanyLearnersTradeTestService companyLearnersTradeTestService = new CompanyLearnersTradeTestService();

	/** The doc. */
	private Doc doc;

	/** Addresses. */
	private Address residentialAddress;

	/** The postal address. */
	private Address postalAddress;

	/** The birth address. */
	private Address birthAddress;

	/** The birth address. */
	private Address legalGaurdianPostalAddress;

	/** The birth address. */
	private Address legalGaurdianResidentialAddress;

	/** The copy address. */
	private boolean copyAddress;
	/** The copy address. */
	private boolean copyAddress1;

	/** The copy address. */
	private boolean copyAddress2;

	/** This is for the UsersQualification. */
	private QualificationType qualificationType;

	/** The users qualification. */
	private UserQualifications usersQualification;

	/** The qualification. */
	private Qualification qualification;

	/** The users qualification service. */
	private UserQualificationsService usersQualificationService = new UserQualificationsService();

	/** The users qualification list. */
	private List<UserQualifications> usersQualificationList;

	private boolean homeLanguageSelected = false;
	private boolean signoff = false;
	private boolean showInfo = false;
	private boolean show = false;
	private boolean showTP = false;
	private boolean isTrainingProviderApplication = false;
	private boolean disableFields = false;

	private DesignatedTradeType designatedTradeType;
	/** The User Language */
	private UsersLanguage usersLanguage = new UsersLanguage();
	private UsersDisability usersDisability = new UsersDisability();

	/**
	 * This is for UsersTraingProvider Details After the correct training company is
	 * selected.
	 */
	private UsersTrainingProvider usersTrainingProvider;

	private List<RejectReasons> selectedRejectReason = new ArrayList<>();
	private List<RejectReasons> rejectReason = new ArrayList<>();
	/** The User Language List */
	private ArrayList<UsersLanguage> usersLanguageList = new ArrayList<>();
	private List<UsersDisability> usersDisabilityList = new ArrayList<>();

	/** The users training provider service. */
	private UsersTrainingProviderService usersTrainingProviderService = new UsersTrainingProviderService();
	private WorkPlaceApprovalService workPlaceApprovalService = new WorkPlaceApprovalService();
	private UsersDisabilityService usersDisabilityService = new UsersDisabilityService();

	/** Screen Booleans. */
	private Boolean sectionAbool;// Learn Reg

	/** The section bbool. */
	private Boolean sectionBbool;// Company Info

	/** The section cbool. */
	private Boolean sectionCbool;// Trainers Info

	/** The section dbool. */
	private Boolean sectionDbool;// Trainers More Detail

	/** The section uploadbool. */
	private Boolean sectionUploadbool;// UploadDocs

	/** The Appointment. */
	private Appointment appointment = new Appointment();

	private InterventionType interventionType;
	private Qualification selectedQualification;
	private SkillsProgram selectedSkillsProgram;
	private SkillsSet selectedSkillsSet;
	private UnitStandards selectedUnitStandards;

	private CompanyLearners companylearners;
	private CompanyLearnersTransfer companyLearnersTransfer;
	private CompanyLearnersService companyLearnersService = new CompanyLearnersService();
	private EmployeeCompanyLearnersService employeecompanyLearnersService = new EmployeeCompanyLearnersService();
	private boolean skillsSet;
	private boolean skillsProgram;
	private boolean shortCreditBearing;
	private boolean nonCreditBearing;
	private boolean showQual;

	private String regNumber;

	private ConfigDocProcessEnum configForRejectReasons;
	private boolean rejectReasonFound = false;

	public Date minDate;

	// private LazyDataModel<CompanyQualifications> qualAccredetedCompanyDataModel;
	private CompanyQualifications selectedCompanyQualification;
	private List<Sites> sitesList = null;
	private boolean useCompanyAsSite = false;
	private Sites selectedSite = null;
	private SitesService sitesService = new SitesService();

	private boolean showsignedDate = false;
	private Date today = GenericUtility.getStartOfDay(new Date());

	private WorkPlaceApproval workPlaceApproval;

	private TrainingProviderApplication trainingProviderApplication;
	private LazyDataModel<TrainingProviderApplication> trainingProviderApplicationModel;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		try {
			runInit();
		} catch (ValidationException e) {
			addErrorMessage(getEntryLanguage(e.getMessage(), e.getParams()));
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	/**
	 * Run init.
	 *
	 * @throws Exception the exception
	 */
	private void runInit() throws Exception {
		minDate = new Date();
		companylearners = null;
		if (super.getParameter("id", false) != null) {
			configForRejectReasons = getSessionUI().getTask().getWorkflowProcess();
			if (getSessionUI().getTask() != null && getSessionUI().getTask().getWorkflowProcess() == ConfigDocProcessEnum.LearnerRegistrationByMerseta) {
				getSessionUI().setTask(TasksService.instance().findByKey(getSessionUI().getTask().getId()));

				this.companylearners = employeecompanyLearnersService.findByKey(getSessionUI().getTask().getTargetKey(), ConfigDocProcessEnum.LearnerRegistrationByMerseta);
				employeecompanyLearnersService.prepareNewRegistration(ConfigDocProcessEnum.LearnerRegistrationByMerseta, this.companylearners);

				if (companylearners.getStatus() == ApprovalEnum.Rejected) {
					populateRejectReasons();
				}
				if (companylearners.getHostCompany() != null && companylearners.getHostCompany().getId() != null) {
					this.hostCompany = companyService.findByKey(companylearners.getHostCompany().getId());
					prepareHostCompanyAfterSearch();
					companylearners.setHostCompany(hostCompany);
				}
				usersLanguageList = (ArrayList<UsersLanguage>) usersLanguageService.findByUser(companylearners.getUser());
				usersDisabilityList = usersDisabilityService.findByKeyUser(companylearners.getUser());
				populateExistingLearner();

				if (getSessionUI().getTask().getFirstInProcess() != null && getSessionUI().getTask().getFirstInProcess()) {
					showsignedDate = true;
					disableFields = true;
				}
			}
		} else {

			prepareNewCompanyLearner();

			if (super.getParameter("companyId", true) != null) {
				this.trainingProvider = companyService.findByKey((Long) super.getParameter("companyId", true));
				isTrainingProviderApplication = true;
				if (trainingProvider != null && trainingProvider.getId() != null) {
					List<Users> list = companyUsersService.findCompanyContactPersonList(trainingProvider.getId());
					if (list != null && list.size() > 0) {
						this.trainingProvider.setContactPerson(list.get(0));
					}

					if (trainingProviderApplicationService.findByCompany(trainingProvider) != null && trainingProviderApplicationService.findByCompany(trainingProvider).size() > 0) {
						trainingProvider.setTrainingProviderApplication(trainingProviderApplicationService.findByCompany(trainingProvider).get(0));
					}
				}
			}

			if (super.getParameter("userId", false) != null) {
				this.user = usersService.findByGUID((String) super.getParameter("userId", false));
				BooleanDefault();
				prepareUserAfterSearch();
			} else {
				clearCurrentLearner();
			}
		}
	}

	private void prepareNewCompanyLearner() {
		this.companylearners = new CompanyLearners();
		this.company = new Company();
		this.company.setDoneSearch(false);
		this.user = new Users();
		this.birthAddress = new Address();
		this.legalGaurdianPostalAddress = new Address();
		this.legalGaurdianResidentialAddress = new Address();
		searchSetObject();
	}

	private void populateExistingLearner() throws Exception {
		applyInterventionData();
		this.company = companylearners.getEmployer();
		this.user = companylearners.getUser();
		this.trainingProvider = companylearners.getCompany();
		if (companylearners.getCompany() != null && companylearners.getCompany().getId() != null) {
			List<Users> list = companyUsersService.findCompanyContactPersonList(companylearners.getCompany().getId());
			if (list != null && list.size() > 0) {
				this.trainingProvider.setContactPerson(list.get(0));
			}

			if (trainingProviderApplicationService.findByCompany(companylearners.getCompany()) != null && trainingProviderApplicationService.findByCompany(companylearners.getCompany()).size() > 0) {
				trainingProvider.setTrainingProviderApplication(trainingProviderApplicationService.findByCompany(companylearners.getCompany()).get(0));
			}
		}

		if (companylearners.getEmployer() != null && companylearners.getEmployer().getId() != null) {
			if (trainingProviderApplicationService.findByCompany(companylearners.getEmployer()) != null && trainingProviderApplicationService.findByCompany(companylearners.getEmployer()).size() > 0) {
				company.setTrainingProviderApplication(trainingProviderApplicationService.findByCompany(companylearners.getEmployer()).get(0));
				companylearners.getEmployer().setTrainingProviderApplication(trainingProviderApplicationService.findByCompany(companylearners.getEmployer()).get(0));
			}
		}
		if (this.company != null) {
			if (this.company.getPostalAddress() != null && this.company.getPostalAddress().getId() != null) {
				this.company.setPostalAddress(AddressService.instance().findByKey(this.company.getPostalAddress().getId()));
			} else {
				this.company.setPostalAddress(new Address());
			}
		}
		if (this.trainingProvider != null) {
			if (this.trainingProvider.getPostalAddress() != null && this.trainingProvider.getPostalAddress().getId() != null) {
				this.trainingProvider.setPostalAddress(AddressService.instance().findByKey(this.trainingProvider.getPostalAddress().getId()));
			} else {
				this.trainingProvider.setPostalAddress(new Address());
			}
		}
		setsearchComplete();
	}

	private void setsearchComplete() throws Exception {
		user.setExistingUser(true);
		user.setRegFieldsDone(true);
		company.setExistingCompany(true);
		company.setNonLevyPaying(false);
		company.setDoneSearch(true);
		Users companyContactPerson = new Users();
		if (company != null && company.getId() != null) {
			List<Users> list = companyUsersService.findCompanyContactPersonList(company.getId());
			if (list != null && list.size() > 0) {
				companyContactPerson = list.get(0);
				company.setContactPerson(companyContactPerson);
			}

		}
		if (this.user.getResidentialAddress() != null) {
			this.residentialAddress = this.user.getResidentialAddress();
		} else {
			this.residentialAddress = new Address();
		}
		if (this.user.getPostalAddress() != null) {
			this.postalAddress = this.user.getPostalAddress();
			if (this.postalAddress.getSameAddress() != null)
				;
			this.copyAddress = this.postalAddress.getSameAddress();
		} else {
			this.postalAddress = new Address();
			this.copyAddress = false;
		}

		if (this.user.getBirthAddress() != null) {
			this.birthAddress = user.getBirthAddress();
			if (birthAddress.getSameAddress() != null)
				this.copyAddress1 = birthAddress.getSameAddress();
		} else {
			this.birthAddress = new Address();
			this.copyAddress1 = false;
		}
		checkRequireGaurdian();
	}

	/**
	 * Store file.
	 *
	 * @param event the event
	 */
	public void storeFile(FileUploadEvent event) {
		try {
			doc.setData(event.getFile().getContents());
			doc.setOriginalFname(event.getFile().getFileName());
			doc.setExtension(FilenameUtils.getExtension(doc.getOriginalFname()));
			doc.setUsers(getSessionUI().getActiveUser());
			if (doc.getId() != null) {
				DocService.instance().uploadNewVersion(doc, getSessionUI().getActiveUser());
			}
			super.runClientSideExecute("PF('dlgUpload').hide()");
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	public void completeCompanyLearnersToRegion() {

		try {

			employeecompanyLearnersService.completeCompanyLearnersToRegion(companylearners, getSessionUI().getActiveUser(), getSessionUI().getTask());
			getSessionUI().setTask(null);
			ajaxRedirectToDashboard();

		} catch (Exception e) {
			// addWarningMessage(super.getEntryLanguage("could.not.save"));
			addErrorMessage(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	public void approveCompanyLearner() {

		try {
			employeecompanyLearnersService.approveCompanyLearner(companylearners, getSessionUI().getActiveUser(), getSessionUI().getTask());
			getSessionUI().setTask(null);
			ajaxRedirectToDashboard();
		} catch (Exception e) {
			addWarningMessage(super.getEntryLanguage("could.not.save"));
			addErrorMessage(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	public void finalApproveWorkflow() {
		try {
			if (signoff == false) {
				throw new Exception("Please signoff before proceeding");
			}
			employeecompanyLearnersService.finalApproveWorkflow(companylearners, getSessionUI().getActiveUser(), getSessionUI().getTask(), documentBoxID);
			ajaxRedirectToDashboard();

		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	/**
	 * Finish registration.
	 */
	public void finishRegistration() {
		try {
			this.user.setRegFieldsDone(true);
			user.getDocs().forEach(doc -> {
				if ((doc.getData() == null || doc.getData().length == 0) && doc.getConfigDoc().getRequiredDocument() != null && doc.getConfigDoc().getRequiredDocument()) {
					this.user.setRegFieldsDone(false);
					addErrorMessage("Upload " + doc.getConfigDoc().getName() + " for " + user.getFirstName() + " " + user.getLastName());
				}
			});
			if (this.user.isRegFieldsDone()) {
				usersService.createLearnerAndDocs(this.user, company);
				addInfoMessage(super.getEntryLanguage("registration.is.complete"));
				clearCurrentLearner();
				this.trainingProvider = new Company();
				// this.company = new Company();
				this.usersTrainingProvider = new UsersTrainingProvider();
			}
		} catch (Exception e) {
			addWarningMessage(super.getEntryLanguage("could.not.save"));
			addErrorMessage(e.getMessage(), e);
		}
	}

	/**
	 * Clear current learner.
	 */
	public void clearCurrentLearner() {
		BooleanDefault();
		this.user = null;
		this.interventionType = new InterventionType();
		this.qualification = new Qualification();
		this.qualificationType = new QualificationType();
		this.usersQualificationList = new ArrayList<UserQualifications>();
		this.usersQualification = new UserQualifications();
		if (this.company.getId() != null) {
			this.company.setDoneSearch(true);
		} else {
			this.company = new Company();
			this.company.setDoneSearch(false);
		}

		this.companylearners.setInterventionType(new InterventionType());
		this.companylearners.setQualification(new Qualification());
		this.companylearners.setDocs(new ArrayList<Doc>());
		this.companylearners = new CompanyLearners();

		showInfo = false;
		clearInputFields();
		searchSetObject();
	}

	/**
	 * Clear current company.
	 */
	public void clearCurrentCompany() {
		BooleanCompany();
		if (this.company.getId() == null)
			this.company = null;
		searchSetObject();
		addInfoMessage(super.getEntryLanguage("company.section.cleared"));
	}

	/**
	 * Clear current training provider.
	 */
	public void clearCurrentTrainingProvider() {
		BooleanTrainingCompany();
		this.trainingProvider = null;
		searchSetObject();
		addInfoMessage(super.getEntryLanguage("training.provider.section.cleared"));
	}

	public void clearCurrentCompanyValidation() {
		BooleanCompany();
		if (this.company.getId() == null)
			this.company = null;
		searchSetObject();
	}

	/**
	 * Prepare upload section.
	 */
	public void prepareUploadSection() {
		BooleanUpload();
		searchSetObject();
		addInfoMessage(super.getEntryLanguage("upload.documents.required"));
	}

	/**
	 * Prepare training providers details.
	 */
	public void prepareTrainingProvidersDetails() {
		try {

			BooleanTrainingCompanyDetails();
			this.usersTrainingProvider = new UsersTrainingProvider();
			this.usersTrainingProvider = usersTrainingProviderService.findByUserCompany(this.user.getId(), this.trainingProvider.getId());

			if (this.usersTrainingProvider.getId() == null) {
				this.usersTrainingProvider.setCompany(this.trainingProvider);
				this.usersTrainingProvider.setUser(this.user);
				addInfoMessage(super.getEntryLanguage("please.provide.details.as.needed"));
			} else
				addInfoMessage(super.getEntryLanguage("details.found"));

			searchSetObject();
		} catch (Exception e) {
			addWarningMessage(super.getEntryLanguage("problem.with.training.provider.details"));
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	public List<RejectReasons> getRejectReasonsByProcess() {
		RejectReasonsService rejectReasonsService = new RejectReasonsService();
		List<RejectReasons> l = null;
		try {
			l = rejectReasonsService.findByProcess(configForRejectReasons);
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
		return l;
	}

	/**
	 * This completely saves users, addresses, qualifications. This also sends out a
	 * mail for a student usersService.createLearner(this.user); if it is a newly
	 * registered User
	 */
	public void saveLearnerRegistration() {
		try {
			startLearnerReg(false);
		} catch (javax.validation.ConstraintViolationException e) {
			extractValidationErrors(e);
			// System.out.println(getValidationErrors());
			// addErrorMessage(getValidationErrors());
		} catch (ValidationException e) {
			addErrorMessage(e.getMessage());
		} catch (Exception e) {
			addErrorMessage(super.getEntryLanguage("issue.with.registration"), e);
			e.printStackTrace();
		}
	}

	public void submitLearnerRegistration() {
		String error = "";
		try {

			// Learner Validation
			error = validateLearner();
			if (error.length() > 0 || !error.matches("")) {
				addErrorMessage(error);
			} else {
				// if (interventionType.getForm().matches("015")) {
				if (usersLanguageList.size() <= 0) {
					throw new Exception("Select atleast one language");
				}
				// }
				startLearnerReg(true);
			}

		} catch (ValidationException e) {
			addErrorMessage(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}

	private void startLearnerReg(boolean sendWorkflow) throws Exception {

		/*
		 * if(this.user.getDisability() !=null && this.user.getDisability() ==
		 * YesNoEnum.Yes) { if(usersDisabilityList.size() == 0) { throw new
		 * Exception("Please provide atleast one user disability"); } }
		 */

		cpyAddresses();
		this.user.setResidentialAddress(this.residentialAddress);
		this.user.setPostalAddress(this.postalAddress);
		// this.user.setBirthAddress(this.birthAddress);
		if (requireGaurdian) {
			this.user.getLegalGaurdian().setResidentialAddress(legalGaurdianResidentialAddress);
			this.user.getLegalGaurdian().setPostalAddress(legalGaurdianPostalAddress);
		}
		if (company == null || company.getId() == null) {
			throw new Exception("Please provide your training company");
		}
		if (companylearners.getInterventionType() == null) {
			super.ajaxRedirect("/pages/tp/learners.jsf");
			throw new Exception("Please provide intervention type");
		}

		Qualification qualification = companyLearnersService.getCompanyLearnerQualification(companylearners);

		if (this.interventionType.getWorkplaceApprovalRequired() != null && this.interventionType.getWorkplaceApprovalRequired() && qualification != null && qualification.getWorkplaceApprovalRequired()) {

			if (!companyLearnersService.checkMentorsRatio(qualification, company)) {
				addInfoMessage("Number of learners have exceeded the learner-mentor ratio");
			}
		}

		/*
		 * if (createWorkplaceApproval) { SDFCompany primarySDF =
		 * companyService.findPrimarySDF(companyService.findByKey(company.getId())); if
		 * (primarySDF == null) { throw new
		 * Exception("Unable to locate Primary SDF for: " + company.getCompanyName()); }
		 * companyLearnersService.createWorkplaceApproval(this.trainingProvider,
		 * qualification, getSessionUI().getActiveUser());
		 * addInfoMessage("Your workplace approval application has been submitted"); }
		 */
		/*
		 * if(this.interventionType.getForm().equals("015")) { if (sitesList.size() != 0
		 * && !useCompanyAsSite) { if (selectedSite == null) { throw new
		 * Exception("Select A Site Before Proceeding"); }else {
		 * companylearners.setSite(selectedSite); } } }
		 */

		employeecompanyLearnersService.createLearner(getSessionUI().getActiveUser(), this.user, company, trainingProvider, companylearners, sendWorkflow, requireGaurdian, usersLanguageList, usersDisabilityList);

		super.setParameter("companyId", null, true);
		super.setParameter("employerId", null, true);
		addInfoMessage(super.getEntryLanguage("update.successful"));
		clearCurrentLearner();
		super.ajaxRedirect("/pages/employeeslearnersregistration.jsf");
	}

	public void completeCompanyLearners() {
		try {
			employeecompanyLearnersService.completeCompanyLearners(companylearners, getSessionUI().getActiveUser(), getSessionUI().getTask());
			getSessionUI().setTask(null);
			ajaxRedirectToDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	public void rejectCompanyLearners() {
		try {
			if (selectedRejectReason.size() == 0)
				throw new Exception("Please select a reject reason");
			employeecompanyLearnersService.rejectCompanyLearners(companylearners, getSessionUI().getActiveUser(), getSessionUI().getTask(), selectedRejectReason);
			ajaxRedirectToDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	public void finalApproveCompanyLearners() {
		try {
			if (signoff) {
				companylearners.setSignoff(signoff);
				employeecompanyLearnersService.finalApproveWorkflow(companylearners, getSessionUI().getActiveUser(), getSessionUI().getTask(), documentBoxID);
				ajaxRedirectToDashboard();
			} else {
				addErrorMessage("Please signof ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	public void finalRejectCompanyLearners() {
		try {
			if (selectedRejectReason.size() == 0)
				throw new Exception("Please select a reject reason");
			employeecompanyLearnersService.finalRejectCompanyLearners(companylearners, getSessionUI().getActiveUser(), getSessionUI().getTask(), selectedRejectReason);
			ajaxRedirectToDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	private void populateRejectReasons() {
		RejectReasonsService rs = new RejectReasonsService();
		try {
			rejectReason = rs.locateReasonsSelectedByTargetKeyClassAndProcess(companylearners.getId(), CompanyLearners.class.getName(), getSessionUI().getTask().getWorkflowProcess());
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	/**
	 * this saves a CompanyUser and sets the address.
	 */
	public void assignCompanyUser() {
		try {
			companyUsersService.createCompanyUsers(this.company, this.user);
			cpyAddresses();

			AddressService.instance().saveAddresses(this.residentialAddress, this.postalAddress);
			this.company.setResidentialAddress(this.residentialAddress);
			this.company.setPostalAddress(this.postalAddress);
			companyService.update(this.company);
			addInfoMessage(super.getEntryLanguage("user.was.successfully.add"));
			clearCurrentTrainingProvider();
		} catch (Exception e) {
			addWarningMessage(super.getEntryLanguage("issue.with.company.user.assignment"));
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	/**
	 * this saves a UsersTrainingProvider and sets the address.
	 */
	public void saveTrainingProvider() {
		try {
			if (this.trainingProvider.getId() == null) {
				companyService.create(this.trainingProvider);
				cpyAddresses();

				AddressService.instance().saveAddresses(this.residentialAddress, this.postalAddress);
				this.trainingProvider.setResidentialAddress(this.residentialAddress);
				this.trainingProvider.setPostalAddress(this.postalAddress);
				companyService.update(this.trainingProvider);
			} else
				companyService.update(this.trainingProvider);
			addInfoMessage(super.getEntryLanguage("user.assigned.to.training.provider"));
			prepareTrainingProvidersDetails();
		} catch (Exception e) {
			addWarningMessage(super.getEntryLanguage("issue.with.training.provider.user.assignment"));
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	/**
	 * this saves a CompanyUser and sets the address.
	 */
	public void assignUsersTrainingProvider() {
		try {
			usersTrainingProviderService.create(this.usersTrainingProvider);
			addInfoMessage(super.getEntryLanguage("training.provider.details.updated"));
			prepareUploadSection();
		} catch (Exception e) {
			addWarningMessage(super.getEntryLanguage("issue.with.saving.training.provider.details"));
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	/**
	 * This adds UsersQualification to a list which then gets saved when method
	 * saveLearnerRegistration() is excuted.
	 */
	public void saveQualification() {
		try {
			UserQualifications uq = new UserQualifications();
			uq.setQualification(this.qualification);
			uq.setQualificationType(this.qualificationType);
			this.usersQualificationList.add(uq);
			this.qualification = new Qualification();
			this.qualificationType = new QualificationType();
			addInfoMessage(super.getEntryLanguage("qualification.added"));
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	/**
	 * This removes UsersQualification from a list Also does a check on
	 * UsersQualification.getId() if not null remove from mySql DB
	 */
	public void removeQualification() {
		try {
			this.usersQualificationList.remove(this.usersQualification);
			if (this.usersQualification.getId() != null)
				usersQualificationService.delete(this.usersQualification);
			this.usersQualification = new UserQualifications();
			addInfoMessage(super.getEntryLanguage("qualification.removed"));
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	public void selectTradeLevel() {
		try {
			setDesignatedTradeType(this.designatedTradeType);
			companylearners.setDesignatedTradeType(this.designatedTradeType);
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	public List<DesignatedTradeLevel> getSelectedDesignatedTradeLevel() {
		DesignatedTradeLevelService designatedTradeLevelService = new DesignatedTradeLevelService();
		List<DesignatedTradeLevel> l = null;
		try {
			l = designatedTradeLevelService.allDesignatedTradeLevelByDesignatedTradeType(this.designatedTradeType);
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
		return l;
	}

	public List<ProjectImplementationPlan> getSelectedProjectImplementationPlan() {
		ProjectImplementationPlanService projectImplementationPlanService = new ProjectImplementationPlanService();
		List<ProjectImplementationPlan> l = null;
		try {
			l = projectImplementationPlanService.findByCompanyAndInterventionType(company, interventionType);
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
		return l;
	}

	public List<Learnership> getSelectedLearnership() {
		LearnershipService learnershipPlanService = new LearnershipService();
		List<Learnership> l = null;
		try {
			l = learnershipPlanService.findByQualification(companylearners.getQualification());
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
		return l;
	}

	public List<Learnership> getSelectedLearnershipAll() {
		LearnershipService learnershipPlanService = new LearnershipService();
		List<Learnership> l = null;
		try {
			l = learnershipPlanService.findAllQualification();
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
		return l;
	}

	public List<Funding> getSelectedFunding() {
		FundingService fundingService = new FundingService();
		List<Funding> l = null;
		try {
			l = fundingService.findByExcludeMerSETAKey();
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
		return l;
	}

	/**
	 * This gets a return from AbstractUIInterface.
	 *
	 * @param object the object
	 */
	@Override
	public void callBackMethod(Object object) {
		try {

			if (object instanceof Users) {

				if (sitesList.size() != 0 && !useCompanyAsSite) {
					if (selectedSite == null) {
						throw new Exception("Select A Site Before Proceeding");
					} else {
						companylearners.setSite(selectedSite);
					}
				}

				if (user == null) {
					this.user = (Users) object;
					if (user != null && user.getRsaIDNumber() != null && user.getValidationStatus() != null && !user.getValidationStatus().matches("Failure")) {
						if (user.getRsaIDNumber() != null && !user.getValidationStatus().matches("Failure")) {
							if (this.user != null && this.user.getId() != null) {
								if (companyLearnersService.getCompanyLearnerQualification(companylearners) != null && this.interventionType.getArpl() != null && this.interventionType.getArpl()) {
									companyLearnersTradeTestService.validateUserAllowedArpl(companyLearnersService.getCompanyLearnerQualification(companylearners), this.user, true);
								}
							}
						} else {
							clearCurrentLearner();
							clearCurrentCompanyValidation();
							throw new Exception("User with this ID Number is deceased as per Department of Home Affairs records");
						}
					}else if (user.getPassportNumber() != null ){
						if (this.user != null && this.user.getId() != null) {
							if (companylearners != null && companyLearnersService.getCompanyLearnerQualification(companylearners) != null && this.interventionType.getArpl() != null && this.interventionType.getArpl()) {
								companyLearnersTradeTestService.validateUserAllowedArpl(companyLearnersService.getCompanyLearnerQualification(companylearners), this.user, true);
							}
						}
					} else {
						clearCurrentLearner();
						clearCurrentCompanyValidation();
						throw new Exception("User with this ID Number does not exist");
					}
				} else {
					user.setLegalGaurdian((Users) object);
				}
				// pupulateUserByEmployee();

				if (this.companylearners == null) {
					this.companylearners = new CompanyLearners();
				}
				searchSetObject();
				prepareUserAfterSearch();
			} else if (object instanceof Company) {
				if (this.company != null && !this.company.isDoneSearch()) {
					this.company = (Company) object;
					if (company == null || company.getId() == null) {
						company = null;
						throw new Exception("The company does not exist");
					}
					if (this.company.getId() == null) {
						this.company.setCompanyStatus(CompanyStatusEnum.NonMersetaCompany);
					}

					companyService.resolveContactPerson(company);
					validateProviderWorkplaceApproval();
					checkSmeQualificationMentor();

					searchSetObject();
					prepareCompanyAfterSearch();
					populateCompanySites();
				} else {
					this.hostCompany = (Company) object;
					if (hostCompany == null || hostCompany.getId() == null) {
						hostCompany = null;
						throw new Exception("The company does not exist");
					}
					if (this.hostCompany.getId() == null) {
						this.hostCompany.setCompanyStatus(CompanyStatusEnum.NonMersetaCompany);
					} else {
						prepareHostCompanyAfterSearch();
						companylearners.setHostCompany(hostCompany);
					}
					// searchSetObject();
					// prepareCompanyAfterSearch();
				}
			} else if (object instanceof Company && this.sectionCbool) {
				this.trainingProvider = (Company) object;
				searchSetObject();
				prepareCompanyAfterSearch();
			}
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	/*
	 * private void pupulateUserByEmployee() throws Exception { EmployeesService
	 * employeesService = new EmployeesService(); Employees employee =
	 * employeesService.findEmployeeByIdAndCompany(this.user, this.company); if
	 * (employee != null) { if(this.user.getId() ==null) {
	 * this.user.setFirstName(employee.getFirstName());
	 * this.user.setLastName(employee.getLastName());
	 * this.user.setIdType(employee.getIdType());
	 * this.user.setEmploymentStatus(employee.getEmploymentStatus());
	 * this.user.setDateOfBirth(employee.getDateOfBirth());
	 * this.user.setRsaIDNumber(employee.getRsaIDNumber());
	 * this.user.setPassportNumber(employee.getPassportNumber());
	 * this.user.setDisabilityStatus(employee.getDisability());
	 * this.user.setEquity(employee.getEquity());
	 * this.user.setGender(employee.getGender());
	 * this.user.setOfoCodes(employee.getOfoCode());
	 * this.user.setEmploymentStatus(EmploymentStatusEnum.Employed);
	 * this.companylearners.setEmploymentStatus(EmploymentStatusEnum.Employed);
	 * this.companylearners.setOfoCodes(employee.getOfoCode());
	 * this.companylearners.setEmploymentType(employee.getEmploymentType()); }else {
	 * CompanyLearners cl = companyLearnersService.findClByUser(this.user);
	 * if(cl!=null) {
	 * this.companylearners.setEmploymentStatus(cl.getEmploymentStatus());
	 * this.companylearners.setOfoCodes(cl.getOfoCodes()); if(cl.getEmploymentType()
	 * !=null) { this.companylearners.setEmploymentType(cl.getEmploymentType()); }
	 * if(cl.getEmploymentContractStartDate() != null) {
	 * this.companylearners.setEmploymentContractStartDate(cl.
	 * getEmploymentContractStartDate()); } if(cl.getEmploymentContractEndDate() !=
	 * null) { this.companylearners.setEmploymentContractEndDate(cl.
	 * getEmploymentContractEndDate()); }
	 * this.companylearners.setEmploymentStatus(EmploymentStatusEnum.Employed);
	 * this.companylearners.setLastSchoolYear(cl.getLastSchoolYear());
	 * this.companylearners.setPreviousSchools(cl.getPreviousSchools());
	 * this.companylearners.setHighestEducationEnum(cl.getHighestEducationEnum());
	 * this.companylearners.setHighestQualification(cl.getHighestQualification());
	 * if(cl.getOfoCodes() != null) {
	 * this.companylearners.setOfoCodes(cl.getOfoCodes()); } } } }else {
	 * //clearCurrentLearner(); this.user = null; throw new
	 * Exception("You Can Only Register Learners Who Are Employees For "+
	 * this.company.getCompanyName()); } }
	 */

	public void applySaqaData() {
		try {
			if (SKILLS_PROGRAM_LIST.contains(companylearners.getInterventionType().getId())) {
				companyLearnersService.applySkillsProgram(companylearners);
			} else if (SKILLS_SET_LIST.contains(companylearners.getInterventionType().getId())) {
				companyLearnersService.applySkillsSet(companylearners);
			} else {
				companyLearnersService.applySaqaData(companylearners);
			}
		} catch (ValidationException e) {
			addErrorMessage(getEntryLanguage(e.getMessage(), e.getParams()));
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	public void applyDGYearData() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void applyInterventionData() {
		try {

			checkInfoToDisplay();
			// showInfo = true;
			if (companylearners.getInterventionType() != null) {

				if (SKILLS_PROGRAM_LIST.contains(companylearners.getInterventionType().getId())) {
					this.skillsProgram = true;
					this.skillsSet = false;
					this.shortCreditBearing = false;
					this.nonCreditBearing = false;
					companylearners.setSkillsSet(null);
					companylearners.setUnitStandard(null);
				} else if (SKILLS_SET_LIST.contains(companylearners.getInterventionType().getId())) {
					this.skillsProgram = false;
					this.skillsSet = true;
					this.shortCreditBearing = false;
					this.nonCreditBearing = false;
					companylearners.setSkillsProgram(null);
					companylearners.setUnitStandard(null);
				} else if (companylearners.getInterventionType().getId() == HAJConstants.CREDIT_BEARING_SHORT_COURSE) {
					companylearners.setSkillsProgram(null);
					companylearners.setSkillsSet(null);
					this.skillsProgram = false;
					this.skillsSet = false;
					this.nonCreditBearing = false;
					this.shortCreditBearing = true;
				} else if (companylearners.getInterventionType().getId() == HAJConstants.NON_CREDIT_BEARING_SHORT_COURSE) {
					companylearners.setSkillsProgram(null);
					companylearners.setSkillsSet(null);
					this.skillsProgram = false;
					this.skillsSet = false;
					this.shortCreditBearing = false;
					this.nonCreditBearing = true;
				} else {
					companylearners.setSkillsProgram(null);
					companylearners.setSkillsSet(null);
					companylearners.setUnitStandard(null);
					this.skillsProgram = false;
					this.skillsSet = false;
					this.shortCreditBearing = false;
					this.nonCreditBearing = false;
				}

				/*
				 * if (getSessionUI().getTask() == null) {
				 * if(!companylearners.getInterventionType().getDescription().contains(
				 * "Bursaries")) { List<Doc>list = new ArrayList<Doc>(); for(Doc doc :
				 * companylearners.getDocs()) {
				 * if(!doc.getConfigDoc().getName().contains("Bursaries")) { list.add(doc); } }
				 * companylearners.setDocs(list); } else { companylearners.getDocs().clear();
				 * companyLearnersService.prepareNewRegistration(ConfigDocProcessEnum.Learner,
				 * this.companylearners); } } else {
				 * companyLearnersService.prepareNewRegistration(ConfigDocProcessEnum.Learner,
				 * this.companylearners); }
				 */

				companyLearnersService.applyInterventionData(companylearners);
				populateCompanySites();
				calculateEndDate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	private void checkInfoToDisplay() {

		if (this.interventionType != null && this.interventionType.getId() != null) {
			if (SKILLS_PROGRAM_LIST.contains(this.interventionType.getId())) {
				showQual = false;
			} else if (SKILLS_SET_LIST.contains(this.interventionType.getId())) {
				showQual = false;
			} else if (this.interventionType.getId() == HAJConstants.CREDIT_BEARING_SHORT_COURSE) {
				showQual = false;
			} else if (this.interventionType.getId() == HAJConstants.NON_CREDIT_BEARING_SHORT_COURSE) {
				showQual = false;
			} else if (this.interventionType.getId() == HAJConstants.OCCUPATIONAL_CERTIFICATE_ID) {
				showQual = false;
			} else if (this.interventionType.getInterventionTypeEnum() == InterventionTypeEnum.Learnership) {
				showQual = false;
			} else if (this.interventionType.getInterventionTypeEnum() == InterventionTypeEnum.Apprenticeship) {
				showQual = false;
			} else {
				showQual = true;
			}

		}
	}

	public void startDateFilterData() {
		try {
			applyInterventionData();
			if (companylearners.getInterventionLevel() != null) {
				calculateEndDate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	public void clearInputFields() {
		skillsSet = false;
		skillsProgram = false;
		shortCreditBearing = false;
		nonCreditBearing = false;
		showQual = false;
		showInfo = false;
		selectedQualification = null;
		selectedSkillsProgram = null;
		selectedSkillsSet = null;
		selectedUnitStandards = null;
		// this.company.setDoneSearch(false);
		checkInfoToDisplay();
	}

	public void clearAllFields() {
		// this.trainingProvider=null;
		this.company = null;
		this.user = null;
		this.interventionType = null;
		skillsSet = false;
		skillsProgram = false;
		shortCreditBearing = false;
		nonCreditBearing = false;
		showQual = false;
		showInfo = false;
		selectedQualification = null;
		selectedSkillsProgram = null;
		selectedSkillsSet = null;
		selectedUnitStandards = null;
	}

	private void calculateEndDate() {
		if (companylearners.getCommencementDate() != null && companylearners.getInterventionType().getDuration() != null) {
			this.companylearners.setCompletionDate(GenericUtility.addMonthsToDate(this.companylearners.getCommencementDate(), this.companylearners.getInterventionType().getDuration()));
		}
	}

	/**
	 * Search set object.
	 */
	public void searchSetObject() {
		getSearchCompanyUI().setObject(this);
		getSearchUserPassportOrIdUI().setObject(this);
	}

	/**
	 * Prepare user after search.
	 *
	 * @throws Exception the exception
	 */
	public void prepareUserAfterSearch() throws Exception {
		employeecompanyLearnersService.prepareNewLearnershipDevelopmentRegistrationDocs(ConfigDocProcessEnum.LearnerRegistrationByMerseta, companylearners, companylearners, null);
		resolveAddresses();
		checkRequireGaurdian();
		loadUserLanguage();
		loadUsersDisability();
	}

	public void checkRequireGaurdian() throws Exception {
		if (user.getMaried() == null || user.getMaried() == YesNoEnum.No) {
			requireGaurdian = this.user.getDateOfBirth() != null && GenericUtility.getAge(this.user.getDateOfBirth()) < 18;
			if (requireGaurdian) {
				this.gaurdian = new Users();
				if (user.getLegalGaurdian() == null) {
					this.user.setLegalGaurdian(gaurdian);
				} else if (user.getLegalGaurdian() != null) {
					if (this.user.getLegalGaurdian().getResidentialAddress() != null && this.user.getLegalGaurdian().getResidentialAddress().getId() != null) {
						this.user.getLegalGaurdian().setResidentialAddress(AddressService.instance().findByKey(this.user.getLegalGaurdian().getResidentialAddress().getId()));
						this.legalGaurdianPostalAddress = this.user.getLegalGaurdian().getPostalAddress();
					} else {
						this.user.getLegalGaurdian().setResidentialAddress(new Address());
					}

					if (this.user.getLegalGaurdian().getPostalAddress() != null && this.user.getLegalGaurdian().getPostalAddress().getId() != null) {
						this.user.getLegalGaurdian().setPostalAddress(AddressService.instance().findByKey(this.user.getLegalGaurdian().getPostalAddress().getId()));
						this.legalGaurdianPostalAddress = this.user.getLegalGaurdian().getPostalAddress();
					} else {
						this.user.getLegalGaurdian().setPostalAddress(new Address());
					}

					if (this.user.getLegalGaurdian().getPostalAddress() != null && this.user.getLegalGaurdian().getPostalAddress().getId() != null) {
						this.legalGaurdianPostalAddress = this.user.getLegalGaurdian().getPostalAddress();
						this.copyAddress2 = this.legalGaurdianPostalAddress.getSameAddress() != null ? this.legalGaurdianPostalAddress.getSameAddress() : true;
					} else {
						this.legalGaurdianPostalAddress = new Address();
						this.copyAddress2 = false;
					}
				}
			}
		} else {
			requireGaurdian = false;
		}
	}

	public void resetRequidGuidian() {
		if (requireGaurdian) {
			if (user.getMaried() == YesNoEnum.Yes) {
				requireGaurdian = false;
				user.setLegalGaurdian(null);
			}
			/*
			 * if(user!=null && user.getMaritalStatus()!=null) {
			 * if(user.getMaritalStatus().getId() != 5) { requireGaurdian = false;
			 * user.setLegalGaurdian(null); } }
			 */
		}
	}

	public void findUserByIDOrPassport() throws Exception {
		Users user = new Users();
		switch (idpassport) {
		case RsaId:
			user = usersService.findByRsaIdJoinAddress(this.idnumber);
			break;
		case Passport:
			user = usersService.findByPassportNumberJoinAddress(this.passportNumber);
			break;
		default:
			user = new Users();
			break;
		}
		if (user == null) {
			user = new Users();
			user.setRsaIDNumber(idnumber);
			user.setPassportNumber(passportNumber);
			user.setExistingUser(false);
			user.setRegFieldsDone(false);
		} else {
			user.setExistingUser(true);
			user.setRegFieldsDone(true);
		}
		if (idpassport == IdPassportEnum.RsaId) {
			try {
				GenericUtility.calcIDData(user);
			} catch (Exception e) {
				e.printStackTrace();
				addErrorMessage(e.getMessage(), e);
			}
		}
		user.setDoneSearch(true);

	}

	public void prepareHostCompanyAfterSearch() throws Exception {

		if (hostCompany != null && hostCompany.getId() != null) {
			List<Users> list = companyUsersService.findCompanyContactPersonList(hostCompany.getId());
			if (list != null && list.size() > 0) {
				this.hostCompany.setContactPerson(list.get(0));
			}
		}
	}

	/**
	 * Prepare company after search.
	 *
	 * @throws Exception the exception
	 */
	public void prepareCompanyAfterSearch() throws Exception {
		if (company != null && company.getId() != null) {
			List<Users> list = companyUsersService.findCompanyContactPersonList(company.getId());
			if (list != null && list.size() > 0) {
				this.company.setContactPerson(list.get(0));
			}

			if (this.company.getResidentialAddress() != null) {
				this.residentialAddress = this.company.getResidentialAddress();
			} else {
				this.company.setResidentialAddress(new Address());
			}
			if (this.company.getPostalAddress() != null) {
				this.postalAddress = this.company.getPostalAddress();
				if (this.postalAddress != null && this.postalAddress.getSameAddress() != null) {
					this.copyAddress = this.postalAddress.getSameAddress();
				}
				if (copyAddress && this.company.getResidentialAddress() != null) {
					this.postalAddress = this.company.getResidentialAddress();
				}
			} else {
				this.company.setResidentialAddress(new Address());
				this.company.setPostalAddress(new Address());
				this.copyAddress = false;
			}
		}
	}

	/**
	 * Prepare qualifications after search.
	 *
	 * @throws Exception the exception
	 */
	public void prepareQualificationsAfterSearch() throws Exception {
		this.usersQualificationList = new ArrayList<UserQualifications>();
		if (this.user.getId() != null)
			this.usersQualificationList = usersQualificationService.findByUserIdLeftJoin(this.user.getId());
	}

	/**
	 * Address Copy, Clear, Find.
	 */
	public void cpyAddresses() {
		try {
			if (this.copyAddress) {
				AddressService.instance().copyFromToAddress(this.residentialAddress, this.postalAddress);
			}

			if (this.copyAddress1) {
				AddressService.instance().copyFromToAddress(this.residentialAddress, this.birthAddress);
			}

			if (this.copyAddress2) {
				AddressService.instance().copyFromToAddress(this.legalGaurdianResidentialAddress, this.legalGaurdianPostalAddress);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
		this.postalAddress.setSameAddress(this.copyAddress);
	}

	/**
	 * Clear postal.
	 */
	public void clearPostal() {
		if (!this.copyAddress) {
			AddressService.instance().clearAddress(this.postalAddress);
		}
	}

	public void clearPostal1() {
		if (!this.copyAddress1) {
			AddressService.instance().clearAddress(this.birthAddress);
		}
	}

	public void clearPostal2() {
		if (!this.copyAddress2) {
			AddressService.instance().clearAddress(this.legalGaurdianPostalAddress);
		}
	}

	/**
	 * Resolve addresses.
	 */
	public void resolveAddresses() {
		if (this.sectionAbool) {// user
			if (this.user.getResidentialAddress() != null) {
				this.residentialAddress = this.user.getResidentialAddress();
			} else {
				this.residentialAddress = new Address();
			}
			if (this.user.getPostalAddress() != null) {
				this.postalAddress = this.user.getPostalAddress();
				if (postalAddress.getSameAddress() != null)
					this.copyAddress = this.postalAddress.getSameAddress();
			} else {
				this.postalAddress = new Address();
				this.copyAddress = false;
			}
			if (this.user.getBirthAddress() != null) {
				this.birthAddress = user.getBirthAddress();
				if (birthAddress.getSameAddress() != null)
					this.copyAddress1 = this.birthAddress.getSameAddress();
			} else {
				this.birthAddress = new Address();
				this.copyAddress1 = false;
			}

		} else if (this.sectionBbool) {// Company assigned to User
			if (this.company.getResidentialAddress() != null) {
				this.residentialAddress = this.company.getResidentialAddress();
			} else {
				this.residentialAddress = new Address();
			}

			if (this.company.getPostalAddress() != null) {
				this.postalAddress = this.company.getPostalAddress();
				this.copyAddress = this.postalAddress.getSameAddress();
			} else {
				this.postalAddress = new Address();
				this.copyAddress = false;
			}
		} else if (this.sectionCbool) {// Training provider
			if (this.trainingProvider.getResidentialAddress() != null) {
				this.residentialAddress = this.trainingProvider.getResidentialAddress();
			} else {
				this.residentialAddress = new Address();
			}
			if (this.trainingProvider.getPostalAddress() != null) {
				this.postalAddress = this.trainingProvider.getPostalAddress();
				this.copyAddress = this.postalAddress.getSameAddress();
			} else {
				this.postalAddress = new Address();
				this.copyAddress = false;
			}
		}

	}

	public void continueRegistration() {
		try {
			if (this.interventionType == null || this.interventionType.getForm() == null || this.interventionType.equals("")) {
				showInfo = false;
				throw new Exception("There is no form number for this intervention");
			}

			this.companylearners.setInterventionType(this.interventionType);
			this.companylearners.setQualification(this.selectedQualification);
			this.companylearners.setSkillsProgram(this.selectedSkillsProgram);
			this.companylearners.setSkillsSet(this.selectedSkillsSet);
			this.companylearners.setUnitStandard(selectedUnitStandards);

			if (!checkIfShouldDisplayTP(this.interventionType)) {
				if (!isTrainingProviderApplication) {
					// this.company = trainingProvider;
				}
			}

			if (this.interventionType != null && this.interventionType.getForSdpAccreditaion() != null && this.interventionType.getForSdpAccreditaion()) {
				validateSdpAccreditation();
			} else {
				showInfo = true;
			}

			if (interventionType.getCode().equals("2") || interventionType.getCode().equals("18") || interventionType.getCode().equals("19")) {
				show = true;
			}

			if (interventionType.getId() == HAJConstants.NON_CREDIT_BEARING_SHORT_COURSE) {
				this.companylearners.setNonCredidBearingDescription(nonCredidBearingDescription);
			} else {
				this.companylearners.setNonCredidBearingDescription(null);
			}

			applySaqaData();
			applyInterventionData();
			employeecompanyLearnersService.prepareNewRegistration(ConfigDocProcessEnum.LearnerRegistrationByMerseta, this.companylearners);
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	private boolean checkIfShouldDisplayTP(InterventionType interventiontype) {
		boolean showTrainingProvider = false;
		if (SKILLS_PROGRAM_LIST.contains(interventiontype.getId()) || SKILLS_SET_LIST.contains(interventiontype.getId()) || interventiontype.getId() == HAJConstants.CREDIT_BEARING_SHORT_COURSE || interventiontype.getInterventionTypeEnum() == InterventionTypeEnum.Learnership) {
			showTrainingProvider = true;
			showTP = true;
		}
		return showTrainingProvider;
	}

	private void validateSdpAccreditation() throws Exception {
		if (companylearners.getInterventionType().getId() == HAJConstants.CREDIT_BEARING_SHORT_COURSE) {
			if (CompanyUnitStandardService.instance().countfindByCompany(trainingProvider, companylearners.getUnitStandard(), true) > 0) {
				showInfo = true;
			} else {
				showInfo = false;
				trainingproviderapplicationInfo();
				accreditedQuallMssg = "The company selected is not accredited for unit standard " + companylearners.getUnitStandard().getTitle() + ". Select one of the company that is accredited below";
				super.runClientSideExecute("PF('accreCompanyForQualDialog').show()");
			}
		} else if (SKILLS_SET_LIST.contains(companylearners.getInterventionType().getId())) {
			if (TrainingProviderSkillsSetService.instance().countfindByCompany(trainingProvider, companylearners.getSkillsSet(), true) > 0) {

				showInfo = true;
			} else {
				showInfo = false;
				trainingproviderapplicationInfo();
				accreditedQuallMssg = "The company selected is not accredited for skills set " + companylearners.getSkillsSet().getDescription() + ". Select one of the company that is accredited below";
				super.runClientSideExecute("PF('accreCompanyForQualDialog').show()");
			}

		} else if (SKILLS_PROGRAM_LIST.contains(companylearners.getInterventionType().getId())) {
			if (TrainingProviderSkillsProgrammeService.instance().countfindByCompany(trainingProvider, companylearners.getSkillsProgram(), true) > 0) {
				showInfo = true;
			} else {
				showInfo = false;
				trainingproviderapplicationInfo();
				accreditedQuallMssg = "The company selected is not accredited for skills programme " + companylearners.getSkillsProgram().getDescription() + ". Select one of the company that is accredited below";
				super.runClientSideExecute("PF('accreCompanyForQualDialog').show()");
			}

		} else {
			Qualification qualification = companyLearnersService.getCompanyLearnerQualification(companylearners);
			if (isTrainingProviderApplication && this.trainingProvider.getId() != null) {
				int count = companyQualificationsService.countfindByCompany(this.trainingProvider, qualification, true);
				if (count < 1) {
					showInfo = false;
					trainingproviderapplicationInfo();
					accreditedQuallMssg = "The company selected is not accredited for " + qualification.getDescription() + ". Select one of the company that is accredited below";
					super.runClientSideExecute("PF('accreCompanyForQualDialog').show()");
					// throw new Exception("The company selected is not accredited for " +
					// qualification.getDescription());
				} else {
					showInfo = true;
				}

			}
		}
	}

	public void prepPreferredTrainingProvider() {
		try {
			// if users selects a TP then TP becomes the employer
			if (isTrainingProviderApplication) {
				this.company = this.trainingProvider;
				this.company.setNonLevyPaying(false);
				this.company.setDoneSearch(true);
				prepareCompanyAfterSearch();
			}

			this.trainingProvider = trainingProviderApplication.getCompany();
			this.companylearners.setTrainingProviderApplication(trainingProviderApplication);
			if (this.companylearners.getTrainingProviderApplication() != null && this.companylearners.getTrainingProviderApplication().getTrainingSite() != null && this.companylearners.getTrainingProviderApplication().getTrainingSite().getId() != null ) {
				this.companylearners.setTrainingSite(this.companylearners.getTrainingProviderApplication().getTrainingSite());
			}
			this.trainingProvider.setTrainingProviderApplication(trainingProviderApplication);
			showInfo = true;
			if (this.trainingProvider != null && trainingProvider.getId() != null) {
				List<Users> list = companyUsersService.findCompanyContactPersonList(trainingProvider.getId());
				if (this.trainingProvider.getResidentialAddress() != null) {
					trainingProvider.setResidentialAddress(AddressService.instance().findByKey(this.trainingProvider.getResidentialAddress().getId()));
				} else {
					trainingProvider.setResidentialAddress(new Address());
				}
				if (this.trainingProvider.getPostalAddress() != null) {
					trainingProvider.setPostalAddress(AddressService.instance().findByKey(this.trainingProvider.getPostalAddress().getId()));
				} else {
					trainingProvider.setPostalAddress(new Address());
				}

				if (list != null && list.size() > 0) {
					this.trainingProvider.setContactPerson(list.get(0));
				}
				
				/*if (trainingProviderApplicationService.findByCompany(trainingProvider) != null && trainingProviderApplicationService.findByCompany(trainingProvider).size() > 0) {
					trainingProvider.setTrainingProviderApplication(trainingProviderApplicationService.findByCompany(trainingProvider).get(0));
				}*/
			}
			/*
			 * if (this.interventionType != null &&
			 * this.interventionType.getWorkplaceApprovalRequired() != null &&
			 * this.interventionType.getWorkplaceApprovalRequired()) {
			 * validateWorkplaceApproval(); } else { showInfo = true; }
			 */
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}
	
	public void trainingproviderapplicationInfo() {
		 
		
		trainingProviderApplicationModel = new LazyDataModel<TrainingProviderApplication>() { 
			 
			   private static final long serialVersionUID = 1L; 
			   private List<TrainingProviderApplication> retorno = new  ArrayList<TrainingProviderApplication>();
			   
			   @Override 
			   public List<TrainingProviderApplication> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
			   
				try {
					if(SKILLS_PROGRAM_LIST.contains(companylearners.getInterventionType().getId())) {
						TrainingProviderSkillsProgrammeService trainingProviderSkillsProgrammeService = new TrainingProviderSkillsProgrammeService();
						//filters.put("skillsProgramID", companylearners.getSkillsProgram().getId());
						retorno = trainingProviderSkillsProgrammeService.allTrainingProviderApplication(first, pageSize, sortField, sortOrder, filters, companylearners.getSkillsProgram().getId(), ApprovalEnum.Approved);
						trainingProviderApplicationModel.setRowCount(trainingProviderSkillsProgrammeService.countAllTrainingProviderApplication(filters));
					}else if(SKILLS_SET_LIST.contains(companylearners.getInterventionType().getId())) {
						TrainingProviderSkillsSetService trainingProviderSkillsSetService = new TrainingProviderSkillsSetService();
						//filters.put("skillsSetID", companylearners.getSkillsSet().getId());
						retorno = trainingProviderSkillsSetService.allTrainingProviderApplication(first, pageSize, sortField, sortOrder, filters, companylearners.getSkillsSet().getId(), ApprovalEnum.Approved);
						trainingProviderApplicationModel.setRowCount(trainingProviderSkillsSetService.countAllTrainingProviderApplication(filters));
					}else if(companylearners.getInterventionType().getId() == HAJConstants.CREDIT_BEARING_SHORT_COURSE) {
						//filters.put("unitStandardID", companylearners.getUnitStandard().getId());
						retorno = trainingProviderApplicationService.allTrainingProviderApplicationCompanyUnitStandard(first, pageSize, sortField, sortOrder, filters, companylearners.getUnitStandard().getId(), ApprovalEnum.Approved);
						trainingProviderApplicationModel.setRowCount(trainingProviderApplicationService.countAllTrainingProviderApplicationCompanyUnitStandard(filters));
					}else {
						//filters.put("qualificationID", companylearners.getQualification().getId());
						retorno = trainingProviderApplicationService.allTrainingProviderApplicationCompanyQualifications(first, pageSize, sortField, sortOrder, filters, companylearners.getQualification().getId(), ApprovalEnum.Approved);
						trainingProviderApplicationModel.setRowCount(trainingProviderApplicationService.countAllTrainingProviderApplicationCompanyQualifications(filters));
					}					
				} catch (Exception e) {
					e.printStackTrace();
					logger.fatal(e);
				} 
			    return retorno; 
			   }
			   
			    @Override
			    public Object getRowKey(TrainingProviderApplication obj) {
			        return obj.getId();
			    }
			    
			    @Override
			    public TrainingProviderApplication getRowData(String rowKey) {
			        for(TrainingProviderApplication obj : retorno) {
			            if(obj.getId().equals(Long.valueOf(rowKey)))
			                return obj;
			        }
			        return null;
			    }			    
			    
			  }; 
	}

	/*
	 * public void qualAccredetedCompaniesInfo(Qualification qualification) {
	 * 
	 * CompanyQualificationsService companyQualificationsService = new
	 * CompanyQualificationsService(); //qualAccredetedCompanyDataModel = new
	 * LazyDataModel<CompanyQualifications>() {
	 * 
	 * private static final long serialVersionUID = 1L; private
	 * List<CompanyQualifications> retorno = new ArrayList<CompanyQualifications>();
	 * 
	 * @Override public List<CompanyQualifications> load(int first, int pageSize,
	 * String sortField, SortOrder sortOrder, Map<String, Object> filters) {
	 * 
	 * try {
	 * 
	 * filters.put("qualID", qualification.getId());
	 * 
	 * retorno =
	 * companyQualificationsService.allAcceptedCompanyQualificationsTest(first,
	 * pageSize, sortField, sortOrder, filters);
	 * qualAccredetedCompanyDataModel.setRowCount(companyQualificationsService.
	 * countAcceptedCompaniesTest(filters)); retorno =
	 * companyQualificationsService.allAcceptedCompanyQualifications(first,
	 * pageSize, sortField, sortOrder, filters);
	 * qualAccredetedCompanyDataModel.setRowCount(companyQualificationsService.
	 * countAcceptedCompanies(filters)); } catch (Exception e) { logger.fatal(e); }
	 * return retorno; }
	 * 
	 * @Override public Object getRowKey(CompanyQualifications obj) { return
	 * obj.getId(); }
	 * 
	 * @Override public CompanyQualifications getRowData(String rowKey) { for
	 * (CompanyQualifications obj : retorno) { if
	 * (obj.getId().equals(Long.valueOf(rowKey))) return obj; } return null; }
	 * 
	 * }; }
	 */

	public void validateProviderWorkplaceApproval() throws Exception {
		if(company.getOrganisationType() == null) {
			throw new Exception("The company does not have organasation type. Please contact your administrator");
		}else {
			Qualification qualification = companyLearnersService.getCompanyLearnerQualification(companylearners);
			if (this.interventionType.getWorkplaceApprovalRequired() && qualification != null && qualification.getWorkplaceApprovalRequired() != null && qualification.getWorkplaceApprovalRequired() && this.company != null && this.company.getId() != null) {
				if (workPlaceApprovalService.checkForExistingRecords(this.company, qualification, companylearners) == 0) {
					workplaceApprovalMsg = "The company selected does not have workplace approval for " + qualification.getDescription() + " and associated " + this.interventionType.getDescription() + ", where applicable. " + "A task will be created for you to complete workplace approval. " + "The learner can only be registered if workplace approval has been granted.";
					showInfo = false;
					super.runClientSideExecute("PF('wpaCompanyForQualDialog').show()");
				} else {
					if (companylearners.getSite() != null) {
						workPlaceApproval = workPlaceApprovalService.findApprovedWorkPlaceApprovalByCompanyAndQualificationAndSites(company, qualification, ApprovalEnum.Approved, companylearners.getSite().getId());
					} else {
						workPlaceApproval = workPlaceApprovalService.findApprovedWorkPlaceApprovalByCompanyAndQualification(company, qualification, ApprovalEnum.Approved);
					}
					showInfo = true;
				}
			}
		}
	}

	public void checkSmeQualificationMentor() throws Exception {
		if(this.interventionType.getInterventionTypeEnum() == InterventionTypeEnum.Apprenticeship || this.interventionType.getInterventionTypeEnum() == InterventionTypeEnum.Learnership) {
			Qualification qualification = companyLearnersService.getCompanyLearnerQualification(companylearners);
			if (this.interventionType.getWorkplaceApprovalRequired() && qualification != null && qualification.getWorkplaceApprovalRequired() != null && qualification.getWorkplaceApprovalRequired()) {
				if (companylearners.getSite() != null) {
					workPlaceApproval = workPlaceApprovalService.findApprovedWorkPlaceApprovalByCompanyAndQualificationAndSites(company, qualification, ApprovalEnum.Approved, companylearners.getSite().getId());
				} else {
					workPlaceApproval = workPlaceApprovalService.findApprovedWorkPlaceApprovalByCompanyAndQualification(company, qualification, ApprovalEnum.Approved);
				}
				if (companyLearnersService.checkSmeQualificationMentor(qualification, workPlaceApproval)) {
					showInfo = true;
				} else {
					clearInputFields();
					showInfo = false;
					addErrorMessage("No available mentor(s) for this qualification");
				}
			}
		}		
	}

	public void createWorkplaceApproval() {
		try {
			String msg = "";
			Qualification qualification = companyLearnersService.getCompanyLearnerQualification(companylearners);
			if (this.interventionType.getWorkplaceApprovalRequired()) {
				if (qualification != null && this.trainingProvider != null && this.trainingProvider.getId() != null && workPlaceApprovalService.checkForExistingRecords(this.trainingProvider, qualification, null, null) == 0) {
					// createWorkplaceApproval = true;
					companyLearnersService.createWorkplaceApproval(this.trainingProvider, qualification, getSessionUI().getActiveUser());
					msg = "Your application has been submitted";
				} else if (qualification != null && this.company != null && this.company.getId() != null && workPlaceApprovalService.checkForExistingRecords(this.company, qualification, null, null) == 0) {
					// createWorkplaceApproval = true;
					companyLearnersService.createWorkplaceApproval(this.company, qualification, getSessionUI().getActiveUser());
					msg = "Your application has been submitted";
				} else {
					msg = "Workplace Approval has already been submitted for this qualification";
				}
			}
			// this.user = null;
			showInfo = false;
			super.runClientSideExecute("PF('wpaCompanyForQualDialog').hide()");
			if (msg != "") {
				addErrorMessage(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	public void cancelWorkplaceApproval() {
		try {
			super.runClientSideExecute("PF('wpaCompanyForQualDialog').hide()");
			showInfo = false;
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	public List<Qualification> completeQualification(String desc) {
		// QualificationService qualificationService = new QualificationService();
		List<Qualification> l = null;
		try {
			l = companyQualificationsService.findQualificationAutocomplete(this.trainingProvider, true);
		} catch (ValidationException e) {
			addErrorMessage(getEntryLanguage(e.getMessage(), e.getParams()));
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
		return l;
	}

	public void findNonSetaCompany() {
		try {
			if (nonSetaCompanyService.findRegistrationNumber(regNumber) == null) {
				nonSetaCompany = nonSetaCompanyService.findRegistrationNumber(regNumber);
			} else {
				addInfoMessage("Company does not exist ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	public List<DisabilityRating> getSelectItemsDisabilityRating() {
		DisabilityRatingService disabilityRatingService = new DisabilityRatingService();
		List<DisabilityRating> l = null;
		try {
			if (this.user.getDisabilityStatus() != null) {
				l = disabilityRatingService.findByDisability(this.user.getDisabilityStatus().getId());
			} else if (this.usersDisability.getDisabilityStatus() != null) {
				l = disabilityRatingService.findByDisability(this.usersDisability.getDisabilityStatus().getId());
			}
		} catch (ValidationException e) {
			addErrorMessage(getEntryLanguage(e.getMessage(), e.getParams()));
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
		}
		return l;
	}

	public List<DisabilityRating> getSelectItemsUsersDisabilityRating() {
		DisabilityRatingService disabilityRatingService = new DisabilityRatingService();
		List<DisabilityRating> l = null;
		try {
			if (this.usersDisability.getDisabilityStatus() != null) {
				l = disabilityRatingService.findByDisability(this.usersDisability.getDisabilityStatus().getId());
			}
		} catch (ValidationException e) {
			addErrorMessage(getEntryLanguage(e.getMessage(), e.getParams()));
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
		}
		return l;
	}

	public void clearDisabilityRating() {
		try {
			// this.user.setDisabilityRating(null);
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	public List<RejectReasons> getRejectReasons() {
		RejectReasonsService rejectReasonsService = new RejectReasonsService();
		List<RejectReasons> l = null;
		try {
			l = rejectReasonsService.findByProcess(getSessionUI().getTask().getWorkflowProcess());
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
		return l;
	}

	/**
	 * Boolean default.
	 */
	public void BooleanDefault() {
		this.sectionAbool = true;
		this.sectionBbool = false;
		this.sectionCbool = false;
		this.sectionDbool = false;
		this.sectionUploadbool = false;
	}

	/**
	 * Boolean company.
	 */
	public void BooleanCompany() {
		this.sectionAbool = false;
		this.sectionBbool = true;
		this.sectionCbool = false;
		this.sectionDbool = false;
		this.sectionUploadbool = false;
	}

	/**
	 * Boolean training company.
	 */
	public void BooleanTrainingCompany() {
		this.sectionAbool = false;
		this.sectionBbool = false;
		this.sectionCbool = true;
		this.sectionDbool = false;
		this.sectionUploadbool = false;
	}

	/**
	 * Boolean training company details.
	 */
	public void BooleanTrainingCompanyDetails() {
		this.sectionAbool = false;
		this.sectionBbool = false;
		this.sectionCbool = false;
		this.sectionDbool = true;
		this.sectionUploadbool = false;
	}

	/**
	 * Boolean upload.
	 */
	public void BooleanUpload() {
		this.sectionAbool = false;
		this.sectionBbool = false;
		this.sectionCbool = false;
		this.sectionDbool = false;
		this.sectionUploadbool = true;
	}

	/**
	 * Go to previous.
	 *
	 * @throws Exception the exception
	 */
	public void goToPrevious() throws Exception {

		if (this.sectionAbool) {
			runInit();
		} else if (this.sectionBbool) {
			this.sectionAbool = true;
			this.sectionBbool = false;
			this.sectionCbool = false;
			this.sectionDbool = false;
			this.sectionUploadbool = false;
		} else if (this.sectionCbool) {
			this.sectionAbool = false;
			this.sectionBbool = true;
			this.sectionCbool = false;
			this.sectionDbool = false;
			this.sectionUploadbool = false;
		} else if (this.sectionDbool) {
			this.sectionAbool = false;
			this.sectionBbool = false;
			this.sectionCbool = true;
			this.sectionDbool = false;
			this.sectionUploadbool = false;
		} else if (this.sectionUploadbool) {
			this.sectionAbool = false;
			this.sectionBbool = false;
			this.sectionCbool = false;
			this.sectionDbool = true;
			this.sectionUploadbool = false;
		}
	}

	public void populateCompanySites() {
		try {

			if (this.company != null && this.company.getId() != null) {
				sitesList = sitesService.findByCompany(this.company);
			} /*
				 * else if(this.trainingProvider !=null && this.trainingProvider.getId()!=null)
				 * { sitesList = sitesService.findByCompany(this.trainingProvider); }
				 */

		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	public List<Sites> completeSites(String desc) {
		List<Sites> l = null;
		try {
			if (this.company != null && this.company.getId() != null) {
				l = sitesService.findByNameAndCompany(desc, this.company);
			} /*
				 * else if(this.trainingProvider !=null && this.trainingProvider.getId()!=null)
				 * { l = sitesService.findByNameAndCompany(desc, this.trainingProvider); }
				 */
		} catch (ValidationException e) {
			addErrorMessage(getEntryLanguage(e.getMessage(), e.getParams()));
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
		}
		return l;
	}

	public void loadUserLanguage() {
		try {
			if (user.getId() != null) {
				usersLanguageList = (ArrayList<UsersLanguage>) usersLanguageService.findByUser(user);
				for (UsersLanguage ul : usersLanguageList) {
					if (ul.getHomeLanguage() != null && ul.getHomeLanguage()) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	public void loadUsersDisability() {
		try {
			if (user.getId() != null) {
				usersDisabilityList = usersDisabilityService.findByKeyUser(this.user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	public void languagePreCheck() throws Exception {
		if (usersLanguage != null && usersLanguage.getLanguage() != null) {
			for (UsersLanguage ul : usersLanguageList) {
				if (ul != null && ul.getLanguage() != null && usersLanguage != null && usersLanguage.getId() != null && usersLanguage.getRead() != null && ul.getLanguage().getId() == usersLanguage.getLanguage().getId()) {
					throw new Exception("Language already exist on your language list");
				}
			}
		} else {
			throw new Exception("Select a language");
		}
	}

	public void usersDisabilityPreCheck() throws Exception {
		for (UsersDisability ud : usersDisabilityList) {
			if (ud != null && ud.getDisabilityStatus() != null && usersDisability != null && ud.getDisabilityStatus().getId() == usersDisability.getDisabilityStatus().getId()) {
				throw new Exception("Disability already exist on your disability list");
			}
		}
	}

	public void addLanguage() {
		try {

			languagePreCheck();
			if (usersLanguage != null && usersLanguage.getRead() != null) {
				usersLanguageList.add(usersLanguage);
				if (usersLanguage.getHomeLanguage() != null && usersLanguage.getHomeLanguage()) {
					// uncomment to allow user to select only 1 home language
					homeLanguageSelected = true;
				}
				usersLanguage = new UsersLanguage();
			} else {
				addErrorMessage("Select a language or speak, read and write");
			}

		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}

	}

	public void addUsersDisability() {
		try {
			usersDisabilityPreCheck();
			if (usersDisability != null && usersDisability.getDisabilityStatus() != null) {
				usersDisabilityList.add(usersDisability);
				usersDisability = new UsersDisability();
			} else {
				addErrorMessage("Select a disability and disability rating");
			}

		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	public void prepareLanguageUpdate() {
		usersLanguageList.remove(usersLanguage);
		if (usersLanguage.getHomeLanguage() != null && usersLanguage.getHomeLanguage()) {
			// uncomment to allow user to select only 1 home language
			homeLanguageSelected = false;
		}
	}

	public void prepareUsersDisabilityUpdate() {
		usersDisabilityList.remove(usersDisability);
	}

	public void removeLanguageFromList() {
		usersLanguageList.remove(usersLanguage);
		if (usersLanguage.getId() != null) {
			try {
				usersLanguageService.delete(usersLanguage);
			} catch (Exception e) {
				e.printStackTrace();
				addErrorMessage(e.getMessage(), e);
			}
		}
		if (usersLanguageList.size() == 0) {
			homeLanguageSelected = false;
		}
		usersLanguage = new UsersLanguage();
	}

	public void removeUsersDisabilityFromList() {
		usersDisabilityList.remove(usersDisability);
		if (usersDisability.getId() != null) {
			try {
				usersDisabilityService.delete(usersDisability);
			} catch (Exception e) {
				e.printStackTrace();
				addErrorMessage(e.getMessage(), e);
			}
		}
		usersDisability = new UsersDisability();
	}

	private Qualification selectedUnitStandartQualification;

	public List<Qualification> completeUnitStandartQualification(String desc) {
		SaqaUsQualificationService saqaUsQualificationService = new SaqaUsQualificationService();
		List<Qualification> l = null;
		try {
			l = saqaUsQualificationService.findSaqaUsQualificationByUnitStandardId(selectedUnitStandards.getCode());
		} catch (ValidationException e) {
			addErrorMessage(getEntryLanguage(e.getMessage(), e.getParams()));
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
		}
		return l;
	}

	public List<Qualification> getSelectItemsQualification() {
		SaqaUsQualificationService saqaUsQualificationService = new SaqaUsQualificationService();
		List<Qualification> l = null;
		try {
			l = saqaUsQualificationService.findSaqaUsQualificationByUnitStandardId(selectedUnitStandards.getCode());
		} catch (ValidationException e) {
			addErrorMessage(getEntryLanguage(e.getMessage(), e.getParams()));
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
		}
		return l;
	}

	public void finalApprovalFileManagementWorkflow() {
		try {
			companyLearnersService.finalApprovalFileManagementWorkflow(companylearners, getSessionUI().getActiveUser(), getSessionUI().getTask());
			ajaxRedirectToDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	public void finalRejectFileManagementWorkflow() {
		try {
			if (selectedRejectReason.size() == 0) {
				throw new Exception("Select One Reason For Rejection Before Proceeding");
			}
			companyLearnersService.finalRejectFileManagementWorkflow(companylearners, getSessionUI().getActiveUser(), getSessionUI().getTask(), selectedRejectReason);
			ajaxRedirectToDashboard();

		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(e.getMessage(), e);
		}
	}

	public void clearSelectedUnitStandartQualification() {
		selectedUnitStandartQualification = null;
		// checkInfoToDisplay();
	}

	/**
	 * Getter and Setters.
	 *
	 * @return the search user passport or id UI
	 */
	public SearchUserPassportOrIdUI getSearchUserPassportOrIdUI() {
		return searchUserPassportOrIdUI;
	}

	/**
	 * Sets the search user passport or id UI.
	 *
	 * @param searchUserPassportOrIdUI the new search user passport or id UI
	 */
	public void setSearchUserPassportOrIdUI(SearchUserPassportOrIdUI searchUserPassportOrIdUI) {
		this.searchUserPassportOrIdUI = searchUserPassportOrIdUI;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public Users getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(Users user) {
		this.user = user;
	}

	/**
	 * Gets the residential address.
	 *
	 * @return the residential address
	 */
	public Address getResidentialAddress() {
		return residentialAddress;
	}

	/**
	 * Sets the residential address.
	 *
	 * @param residentialAddress the new residential address
	 */
	public void setResidentialAddress(Address residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	/**
	 * Gets the postal address.
	 *
	 * @return the postal address
	 */
	public Address getPostalAddress() {
		return postalAddress;
	}

	/**
	 * Sets the postal address.
	 *
	 * @param postalAddress the new postal address
	 */
	public void setPostalAddress(Address postalAddress) {
		this.postalAddress = postalAddress;
	}

	/**
	 * Gets the qualification type.
	 *
	 * @return the qualification type
	 */
	public QualificationType getQualificationType() {
		return qualificationType;
	}

	/**
	 * Sets the qualification type.
	 *
	 * @param qualificationType the new qualification type
	 */
	public void setQualificationType(QualificationType qualificationType) {
		this.qualificationType = qualificationType;
	}

	/**
	 * Gets the qualification.
	 *
	 * @return the qualification
	 */
	public Qualification getQualification() {
		return qualification;
	}

	/**
	 * Sets the qualification.
	 *
	 * @param qualification the new qualification
	 */
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	/**
	 * Gets the users qualification list.
	 *
	 * @return the users qualification list
	 */
	public List<UserQualifications> getUsersQualificationList() {
		return usersQualificationList;
	}

	/**
	 * Sets the users qualification list.
	 *
	 * @param usersQualificationList the new users qualification list
	 */
	public void setUsersQualificationList(List<UserQualifications> usersQualificationList) {
		this.usersQualificationList = usersQualificationList;
	}

	/**
	 * Gets the users qualification.
	 *
	 * @return the users qualification
	 */
	public UserQualifications getUsersQualification() {
		return usersQualification;
	}

	/**
	 * Sets the users qualification.
	 *
	 * @param usersQualification the new users qualification
	 */
	public void setUsersQualification(UserQualifications usersQualification) {
		this.usersQualification = usersQualification;
	}

	/**
	 * Gets the search company UI.
	 *
	 * @return the search company UI
	 */
	public SearchCompanyUI getSearchCompanyUI() {
		return searchCompanyUI;
	}

	/**
	 * Sets the search company UI.
	 *
	 * @param searchCompanyUI the new search company UI
	 */
	public void setSearchCompanyUI(SearchCompanyUI searchCompanyUI) {
		this.searchCompanyUI = searchCompanyUI;
	}

	/**
	 * Gets the company.
	 *
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company the new company
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * Gets the training provider.
	 *
	 * @return the training provider
	 */
	public Company getTrainingProvider() {
		return trainingProvider;
	}

	/**
	 * Sets the training provider.
	 *
	 * @param trainingProvider the new training provider
	 */
	public void setTrainingProvider(Company trainingProvider) {
		this.trainingProvider = trainingProvider;
	}

	/**
	 * Gets the section abool.
	 *
	 * @return the section abool
	 */
	public Boolean getSectionAbool() {
		return sectionAbool;
	}

	/**
	 * Sets the section abool.
	 *
	 * @param sectionAbool the new section abool
	 */
	public void setSectionAbool(Boolean sectionAbool) {
		this.sectionAbool = sectionAbool;
	}

	/**
	 * Gets the section bbool.
	 *
	 * @return the section bbool
	 */
	public Boolean getSectionBbool() {
		return sectionBbool;
	}

	/**
	 * Sets the section bbool.
	 *
	 * @param sectionBbool the new section bbool
	 */
	public void setSectionBbool(Boolean sectionBbool) {
		this.sectionBbool = sectionBbool;
	}

	/**
	 * Gets the section cbool.
	 *
	 * @return the section cbool
	 */
	public Boolean getSectionCbool() {
		return sectionCbool;
	}

	/**
	 * Sets the section cbool.
	 *
	 * @param sectionCbool the new section cbool
	 */
	public void setSectionCbool(Boolean sectionCbool) {
		this.sectionCbool = sectionCbool;
	}

	/**
	 * Gets the section dbool.
	 *
	 * @return the section dbool
	 */
	public Boolean getSectionDbool() {
		return sectionDbool;
	}

	/**
	 * Sets the section dbool.
	 *
	 * @param sectionDbool the new section dbool
	 */
	public void setSectionDbool(Boolean sectionDbool) {
		this.sectionDbool = sectionDbool;
	}

	/**
	 * Gets the section uploadbool.
	 *
	 * @return the section uploadbool
	 */
	public Boolean getSectionUploadbool() {
		return sectionUploadbool;
	}

	/**
	 * Sets the section uploadbool.
	 *
	 * @param sectionUploadbool the new section uploadbool
	 */
	public void setSectionUploadbool(Boolean sectionUploadbool) {
		this.sectionUploadbool = sectionUploadbool;
	}

	/**
	 * Gets the users training provider.
	 *
	 * @return the users training provider
	 */
	public UsersTrainingProvider getUsersTrainingProvider() {
		return usersTrainingProvider;
	}

	/**
	 * Sets the users training provider.
	 *
	 * @param usersTrainingProvider the new users training provider
	 */
	public void setUsersTrainingProvider(UsersTrainingProvider usersTrainingProvider) {
		this.usersTrainingProvider = usersTrainingProvider;
	}

	/**
	 * Gets the doc.
	 *
	 * @return the doc
	 */
	public Doc getDoc() {
		return doc;
	}

	/**
	 * Sets the doc.
	 *
	 * @param doc the new doc
	 */
	public void setDoc(Doc doc) {
		this.doc = doc;
	}

	public CompanyLearners getCompanylearners() {
		return companylearners;
	}

	public void setCompanylearners(CompanyLearners companylearners) {
		this.companylearners = companylearners;
	}

	public Users getGaurdian() {
		return gaurdian;
	}

	public void setGaurdian(Users gaurdian) {
		this.gaurdian = gaurdian;
	}

	public boolean isRequireGaurdian() {
		return requireGaurdian;
	}

	public void setRequireGaurdian(boolean requireGaurdian) {
		this.requireGaurdian = requireGaurdian;
	}

	public boolean isCopyAddress() {
		return copyAddress;
	}

	public void setCopyAddress(boolean copyAddress) {
		this.copyAddress = copyAddress;
	}

	public boolean isSkillsSet() {
		return skillsSet;
	}

	public void setSkillsSet(boolean skillsSet) {
		this.skillsSet = skillsSet;
	}

	public boolean isSkillsProgram() {
		return skillsProgram;
	}

	public void setSkillsProgram(boolean skillsProgram) {
		this.skillsProgram = skillsProgram;
	}

	public boolean isShortCreditBearing() {
		return shortCreditBearing;
	}

	public void setShortCreditBearing(boolean shortCreditBearing) {
		this.shortCreditBearing = shortCreditBearing;
	}

	public CompanyLearnersTransfer getCompanyLearnersTransfer() {
		return companyLearnersTransfer;
	}

	public void setCompanyLearnersTransfer(CompanyLearnersTransfer companyLearnersTransfer) {
		this.companyLearnersTransfer = companyLearnersTransfer;
	}

	/**
	 * @return the appointment
	 */
	public Appointment getAppointment() {
		return appointment;
	}

	/**
	 * @param appointment the appointment to set
	 */
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public List<RejectReasons> getSelectedRejectReason() {
		return selectedRejectReason;
	}

	public void setSelectedRejectReason(List<RejectReasons> selectedRejectReason) {
		this.selectedRejectReason = selectedRejectReason;
	}

	public boolean isSignoff() {
		return signoff;
	}

	public void setSignoff(boolean signoff) {
		this.signoff = signoff;
	}

	public List<RejectReasons> getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(List<RejectReasons> rejectReason) {
		this.rejectReason = rejectReason;
	}

	public NonSetaCompany getNonSetaCompany() {
		return nonSetaCompany;
	}

	public void setNonSetaCompany(NonSetaCompany nonSetaCompany) {
		this.nonSetaCompany = nonSetaCompany;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public boolean isShowInfo() {
		return showInfo;
	}

	public void setShowInfo(boolean showInfo) {
		this.showInfo = showInfo;
	}

	public String validateLearner() throws Exception {
		String error = "";
		if (this.user.getId() != null) {
			ArrayList<CompanyLearners> companyLearnersList = (ArrayList<CompanyLearners>) companyLearnersService.allLearnersByUser(this.user);
			if (companyLearnersList.size() > 0) {
				for (CompanyLearners cl : companyLearnersList) {
					if (cl.getLearnerStatus() != LearnerStatusEnum.Terminated && cl.getLearnerStatus() != LearnerStatusEnum.Withdrawn && cl.getLearnerStatus() != LearnerStatusEnum.Rejected) {
						if(companylearners.getInterventionType().getBusary() != null && companylearners.getInterventionType().getBusary()) {
							/*if(cl.getLearnerStatus() == LearnerStatusEnum.Registered && cl.getQualification() !=null &&  cl.getQualification().getId() == companylearners.getQualification().getId()) {
								error = "Please do a learner completion for previous application before attempting a new registration";
							}else if(cl.getLearnerStatus() == LearnerStatusEnum.Application && cl.getQualification() !=null && cl.getQualification().getId() == companylearners.getQualification().getId()) {
								error = "Please do a learner completion for previous application before attempting a new registration";
							}*/
							
							if(cl.getLearnerStatus() == LearnerStatusEnum.Registered) {
								if(cl.getQualification() !=null &&  cl.getQualification().getId().equals(companylearners.getQualification().getId())) {
									error = "Please do a learner completion for previous application before attempting a new registration";
									break;
								}
							}else if(cl.getLearnerStatus() == LearnerStatusEnum.Application) {
								if(cl.getQualification() !=null && cl.getQualification().getId().equals(companylearners.getQualification().getId())) {
									error = "Please do a learner completion for previous application before attempting a new registration";
									break;
								}
							}else if(cl.getLearnerStatus() == LearnerStatusEnum.Completed) {
								companylearners.setNewBursary(false);
							}
						}else if (companylearners.getInterventionType().getInterventionTypeEnum() == InterventionTypeEnum.Apprenticeship && cl.getInterventionType().getInterventionTypeEnum() == InterventionTypeEnum.Apprenticeship) {
							if(cl.getLearnerStatus() == LearnerStatusEnum.Registered) {
								error = "Learner cannot be registered for more than 1 apprenticeship at same time unless one is terminated";
								break;
							}else if(cl.getLearnerStatus() == LearnerStatusEnum.Application ) {
								error = "Learner cannot be registered for more than 1 apprenticeship at same time unless one is terminated!!!";
								break;
							}
						}else if (companylearners.getInterventionType().getInterventionTypeEnum() == InterventionTypeEnum.Learnership &&  cl.getInterventionType().getInterventionTypeEnum() == InterventionTypeEnum.Learnership) {
							if(cl.getLearnerStatus() == LearnerStatusEnum.Registered) {
								error = "Learner cannot be registered for more than 1 learnership at same time unless one is terminated";
								break;
							}else if(cl.getLearnerStatus() == LearnerStatusEnum.Application ) {
								error = "Learner cannot be registered for more than 1 learnership at same time unless one is terminated !!!";
								break;
							}
						} else if (companylearners.getInterventionType().getInterventionTypeEnum() == InterventionTypeEnum.Apprenticeship && cl.getInterventionType().getDescription().contains("TVET")) {
							if(cl.getLearnerStatus() == LearnerStatusEnum.Registered) {
								error = "Learner cannot be registered for more than 1 learnership/apprenticeship at same time unless one is terminated";
								break;
							}else if(cl.getLearnerStatus() == LearnerStatusEnum.Application ) {
								error = "Learner cannot be registered for more than 1 learnership/apprenticeship at same time unless one is terminated";
								break;
							}
						} else if (cl.getInterventionType().getInterventionTypeEnum() == InterventionTypeEnum.Learnership && companylearners.getInterventionType().getDescription().contains("TVET")) {
							if(cl.getLearnerStatus() == LearnerStatusEnum.Registered) {
								error = "Learner cannot be registered for more than 1 learnership/apprenticeship at same time unless one is terminated";
								break;
							}else if(cl.getLearnerStatus() == LearnerStatusEnum.Application ) {
								error = "Learner cannot be registered for more than 1 learnership/apprenticeship at same time unless one is terminated";
								break;
							}
						}else if(SKILLS_PROGRAM_LIST.contains(companylearners.getInterventionType().getId())) {
							if(cl.getSkillsProgram() != null &&  companylearners.getSkillsProgram() != null && cl.getSkillsProgram().getId().equals(companylearners.getSkillsProgram().getId() )) {	
									error = "Learner cannot be registered for the same skills programme at the same time";
									break;
							}
						}else if(SKILLS_SET_LIST.contains(companylearners.getInterventionType().getId())) {
							if(cl.getSkillsSet() != null && companylearners.getSkillsSet() != null && cl.getSkillsSet().getId().equals(companylearners.getSkillsSet().getId())) {	
									error = "Learner cannot be registered for the same skills programme at the same time";
									break;
							}
						}else if(companylearners.getInterventionType().getId() == HAJConstants.CREDIT_BEARING_SHORT_COURSE) {
							if(cl.getUnitStandard() != null && companylearners.getUnitStandard() != null && cl.getUnitStandard().getId().equals(companylearners.getUnitStandard().getId()) ) {	
								error = "Learner cannot be registered for the same unit standard at the same time";
								break;
							}
						}else if(companylearners.getQualification() != null && cl.getQualification() != null) {
							if(cl.getLearnerStatus() == LearnerStatusEnum.Registered && companylearners.getInterventionType().getId().equals(cl.getInterventionType().getId()) && cl.getQualification() != null && companylearners.getQualification() != null && cl.getQualification().getId().equals(companylearners.getQualification().getId())) {
								error = "Learner cannot be registered for the same qualification at the same time";
								break;
							}else if(cl.getLearnerStatus() == LearnerStatusEnum.Application && companylearners.getInterventionType().getId() == cl.getInterventionType().getId() && cl.getQualification() != null && companylearners.getQualification() != null && cl.getQualification().getId().equals(companylearners.getQualification().getId())) {
								error = "Learner cannot be registered for the same qualification at the same time";
								break;
							}
						}
					}
				}
			}
		}

		return error;
	}
	/*
	 * public List<Qualification> completeQualification(String desc) {
	 * QualificationService qualificationService = new QualificationService();
	 * List<Qualification> l = null; try { //l =
	 * qualificationService.findByCompanyQualifications(this.trainingProvider,
	 * true); } catch (ValidationException e) {
	 * addErrorMessage(getEntryLanguage(e.getMessage(), e.getParams())); } catch
	 * (Exception e) { addErrorMessage(e.getMessage(), e); } return l; }
	 */

	public InterventionType getInterventionType() {
		return interventionType;
	}

	public void setInterventionType(InterventionType interventionType) {
		this.interventionType = interventionType;
	}

	public Qualification getSelectedQualification() {
		return selectedQualification;
	}

	public void setSelectedQualification(Qualification selectedQualification) {
		this.selectedQualification = selectedQualification;
	}

	public DesignatedTradeType getDesignatedTradeType() {
		return designatedTradeType;
	}

	public void setDesignatedTradeType(DesignatedTradeType designatedTradeType) {
		this.designatedTradeType = designatedTradeType;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public boolean isRejectReasonFound() {
		return rejectReasonFound;
	}

	public void setRejectReasonFound(boolean rejectReasonFound) {
		this.rejectReasonFound = rejectReasonFound;
	}

	public Address getBirthAddress() {
		return birthAddress;
	}

	public void setBirthAddress(Address birthAddress) {
		this.birthAddress = birthAddress;
	}

	public Address getLegalGaurdianResidentialAddress() {
		return legalGaurdianResidentialAddress;
	}

	public void setLegalGaurdianResidentialAddress(Address legalGaurdianResidentialAddress) {
		this.legalGaurdianResidentialAddress = legalGaurdianResidentialAddress;
	}

	public Address getLegalGaurdianPostalAddress() {
		return legalGaurdianPostalAddress;
	}

	public void setLegalGaurdianPostalAddress(Address legalGaurdianPostalAddress) {
		this.legalGaurdianPostalAddress = legalGaurdianPostalAddress;
	}

	public boolean isCopyAddress1() {
		return copyAddress1;
	}

	public void setCopyAddress1(boolean copyAddress1) {
		this.copyAddress1 = copyAddress1;
	}

	public boolean isCopyAddress2() {
		return copyAddress2;
	}

	public void setCopyAddress2(boolean copyAddress2) {
		this.copyAddress2 = copyAddress2;
	}

	public IdPassportEnum getIdpassport() {
		return idpassport;
	}

	public void setIdpassport(IdPassportEnum idpassport) {
		this.idpassport = idpassport;
	}

	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public boolean isShowQual() {
		return showQual;
	}

	public void setShowQual(boolean showQual) {
		this.showQual = showQual;
	}

	public boolean isNonCreditBearing() {
		return nonCreditBearing;
	}

	public void setNonCreditBearing(boolean nonCreditBearing) {
		this.nonCreditBearing = nonCreditBearing;
	}

	public UsersLanguage getUsersLanguage() {
		return usersLanguage;
	}

	public void setUsersLanguage(UsersLanguage usersLanguage) {
		this.usersLanguage = usersLanguage;
	}

	public ArrayList<UsersLanguage> getUsersLanguageList() {
		return usersLanguageList;
	}

	public void setUsersLanguageList(ArrayList<UsersLanguage> usersLanguageList) {
		this.usersLanguageList = usersLanguageList;
	}

	public boolean isHomeLanguageSelected() {
		return homeLanguageSelected;
	}

	public void setHomeLanguageSelected(boolean homeLanguageSelected) {
		this.homeLanguageSelected = homeLanguageSelected;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public SkillsProgram getSelectedSkillsProgram() {
		return selectedSkillsProgram;
	}

	public void setSelectedSkillsProgram(SkillsProgram selectedSkillsProgram) {
		this.selectedSkillsProgram = selectedSkillsProgram;
	}

	public SkillsSet getSelectedSkillsSet() {
		return selectedSkillsSet;
	}

	public void setSelectedSkillsSet(SkillsSet selectedSkillsSet) {
		this.selectedSkillsSet = selectedSkillsSet;
	}

	public UnitStandards getSelectedUnitStandards() {
		return selectedUnitStandards;
	}

	public void setSelectedUnitStandards(UnitStandards selectedUnitStandards) {
		this.selectedUnitStandards = selectedUnitStandards;
	}

	public String getDocumentBoxID() {
		return documentBoxID;
	}

	public void setDocumentBoxID(String documentBoxID) {
		this.documentBoxID = documentBoxID;
	}

	public String getAccreditedQuallMssg() {
		return accreditedQuallMssg;
	}

	public void setAccreditedQuallMssg(String accreditedQuallMssg) {
		this.accreditedQuallMssg = accreditedQuallMssg;
	}

	/*
	 * public LazyDataModel<CompanyQualifications>
	 * getQualAccredetedCompanyDataModel() { return qualAccredetedCompanyDataModel;
	 * }
	 * 
	 * public void
	 * setQualAccredetedCompanyDataModel(LazyDataModel<CompanyQualifications>
	 * qualAccredetedCompanyDataModel) { this.qualAccredetedCompanyDataModel =
	 * qualAccredetedCompanyDataModel; }
	 */

	public CompanyQualifications getSelectedCompanyQualification() {
		return selectedCompanyQualification;
	}

	public void setSelectedCompanyQualification(CompanyQualifications selectedCompanyQualification) {
		this.selectedCompanyQualification = selectedCompanyQualification;
	}

	public Qualification getSelectedUnitStandartQualification() {
		return selectedUnitStandartQualification;
	}

	public void setSelectedUnitStandartQualification(Qualification selectedUnitStandartQualification) {
		this.selectedUnitStandartQualification = selectedUnitStandartQualification;
	}

	public String getWorkplaceApprovalMsg() {
		return workplaceApprovalMsg;
	}

	public void setWorkplaceApprovalMsg(String workplaceApprovalMsg) {
		this.workplaceApprovalMsg = workplaceApprovalMsg;
	}

	public UsersDisability getUsersDisability() {
		return usersDisability;
	}

	public void setUsersDisability(UsersDisability usersDisability) {
		this.usersDisability = usersDisability;
	}

	public List<UsersDisability> getUsersDisabilityList() {
		return usersDisabilityList;
	}

	public void setUsersDisabilityList(List<UsersDisability> usersDisabilityList) {
		this.usersDisabilityList = usersDisabilityList;
	}

	public Company getHostCompany() {
		return hostCompany;
	}

	public void setHostCompany(Company hostCompany) {
		this.hostCompany = hostCompany;
	}

	public String getNonCredidBearingDescription() {
		return nonCredidBearingDescription;
	}

	public void setNonCredidBearingDescription(String nonCredidBearingDescription) {
		this.nonCredidBearingDescription = nonCredidBearingDescription;
	}

	public List<Sites> getSitesList() {
		return sitesList;
	}

	public void setSitesList(List<Sites> sitesList) {
		this.sitesList = sitesList;
	}

	public boolean isUseCompanyAsSite() {
		return useCompanyAsSite;
	}

	public void setUseCompanyAsSite(boolean useCompanyAsSite) {
		this.useCompanyAsSite = useCompanyAsSite;
	}

	public Sites getSelectedSite() {
		return selectedSite;
	}

	public void setSelectedSite(Sites selectedSite) {
		this.selectedSite = selectedSite;
	}

	public boolean isShowsignedDate() {
		return showsignedDate;
	}

	public void setShowsignedDate(boolean showsignedDate) {
		this.showsignedDate = showsignedDate;
	}

	public boolean isShowTP() {
		return showTP;
	}

	public void setShowTP(boolean showTP) {
		this.showTP = showTP;
	}

	public boolean isDisableFields() {
		return disableFields;
	}

	public void setDisableFields(boolean disableFields) {
		this.disableFields = disableFields;
	}

	public TrainingProviderApplication getTrainingProviderApplication() {
		return trainingProviderApplication;
	}

	public LazyDataModel<TrainingProviderApplication> getTrainingProviderApplicationModel() {
		return trainingProviderApplicationModel;
	}

	public void setTrainingProviderApplication(TrainingProviderApplication trainingProviderApplication) {
		this.trainingProviderApplication = trainingProviderApplication;
	}

	public void setTrainingProviderApplicationModel(LazyDataModel<TrainingProviderApplication> trainingProviderApplicationModel) {
		this.trainingProviderApplicationModel = trainingProviderApplicationModel;
	}
}