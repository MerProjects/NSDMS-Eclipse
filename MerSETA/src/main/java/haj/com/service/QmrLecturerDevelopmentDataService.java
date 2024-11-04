package haj.com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import haj.com.bean.CounterBean;
import haj.com.bean.QmrQuarterCountBean;
import haj.com.bean.QmrScriptOneBean;
import haj.com.bean.QmrSummaryBean;
import haj.com.constants.HAJConstants;
import haj.com.dao.QmrLecturerDevelopmentDataDAO;
import haj.com.entity.QmrFinYears;
import haj.com.entity.QmrLecturerDevelopmentData;
import haj.com.entity.enums.EmploymentStatusEnum;
import haj.com.entity.enums.LearnerStatusEnum;
import haj.com.entity.enums.QmrEnteredCompletedEnum;
import haj.com.entity.enums.QmrEquityEnum;
import haj.com.entity.enums.QmrGenderEnum;
import haj.com.entity.enums.QmrTypeSelectionEnum;
import haj.com.entity.lookup.Equity;
import haj.com.entity.lookup.Gender;
import haj.com.framework.AbstractService;
import haj.com.service.lookup.EquityService;

// TODO: Auto-generated Javadoc
/**
 * The Class QmrLecturerDevelopmentDataService.
 */
public class QmrLecturerDevelopmentDataService extends AbstractService {
	/** The dao. */
	private QmrLecturerDevelopmentDataDAO dao = new QmrLecturerDevelopmentDataDAO();
	
	/** The Service Levels */
	private GenderService genderService = null;
	private EquityService equityService = null;
	private QmrFinYearsService qmrFinYearsService = null;

	/**
	 * Get all QmrLecturerDevelopmentData.
	 *
	 * @author TechFinium
	 * @return a list of {@link haj.com.entity.QmrLecturerDevelopmentData}
	 * @throws Exception the exception
	 * @see QmrLecturerDevelopmentData
	 */
	public List<QmrLecturerDevelopmentData> allQmrLecturerDevelopmentData() throws Exception {
		return dao.allQmrLecturerDevelopmentData();
	}

	/**
	 * Create or update QmrLecturerDevelopmentData.
	 *
	 * @author TechFinium
	 * @param entity the entity
	 * @throws Exception the exception
	 * @see QmrLecturerDevelopmentData
	 */
	public void create(QmrLecturerDevelopmentData entity) throws Exception {
		if (entity.getId() == null) {
			entity.setCreateDate(new java.util.Date());
			this.dao.create(entity);
		} else
			this.dao.update(entity);
	}

	/**
	 * Update QmrLecturerDevelopmentData.
	 *
	 * @author TechFinium
	 * @param entity the entity
	 * @throws Exception the exception
	 * @see QmrLecturerDevelopmentData
	 */
	public void update(QmrLecturerDevelopmentData entity) throws Exception {
		this.dao.update(entity);
	}

	/**
	 * Delete QmrLecturerDevelopmentData.
	 *
	 * @author TechFinium
	 * @param entity the entity
	 * @throws Exception the exception
	 * @see QmrLecturerDevelopmentData
	 */
	public void delete(QmrLecturerDevelopmentData entity) throws Exception {
		this.dao.delete(entity);
	}

	/**
	 * Find object by primary key.
	 *
	 * @author TechFinium
	 * @param id the id
	 * @return a {@link haj.com.entity.QmrLecturerDevelopmentData}
	 * @throws Exception the exception
	 * @see QmrLecturerDevelopmentData
	 */
	public QmrLecturerDevelopmentData findByKey(long id) throws Exception {
		return dao.findByKey(id);
	}

	/**
	 * Find QmrLecturerDevelopmentData by description.
	 *
	 * @author TechFinium
	 * @param desc the desc
	 * @return a list of {@link haj.com.entity.QmrLecturerDevelopmentData}
	 * @throws Exception the exception
	 * @see QmrLecturerDevelopmentData
	 */
	public List<QmrLecturerDevelopmentData> findByName(String desc) throws Exception {
		return dao.findByName(desc);
	}

	/**
	 * Lazy load QmrLecturerDevelopmentData.
	 *
	 * @param first    from key
	 * @param pageSize no of rows to fetch
	 * @return a list of {@link haj.com.entity.QmrLecturerDevelopmentData}
	 * @throws Exception the exception
	 */
	public List<QmrLecturerDevelopmentData> allQmrLecturerDevelopmentData(int first, int pageSize) throws Exception {
		return dao.allQmrLecturerDevelopmentData(Long.valueOf(first), pageSize);
	}

	/**
	 * Get count of QmrLecturerDevelopmentData for lazy load.
	 *
	 * @author TechFinium
	 * @return Number of rows in the QmrLecturerDevelopmentData
	 * @throws Exception the exception
	 */
	public Long count() throws Exception {
		return dao.count(QmrLecturerDevelopmentData.class);
	}

	/**
	 * Lazy load QmrLecturerDevelopmentData with pagination, filter, sorting.
	 *
	 * @author TechFinium
	 * @param class1    QmrLecturerDevelopmentData class
	 * @param first     the first
	 * @param pageSize  the page size
	 * @param sortField the sort field
	 * @param sortOrder the sort order
	 * @param filters   the filters
	 * @return a list of {@link haj.com.entity.QmrLecturerDevelopmentData} containing data
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	public List<QmrLecturerDevelopmentData> allQmrLecturerDevelopmentData(Class<QmrLecturerDevelopmentData> class1, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) throws Exception {
		return (List<QmrLecturerDevelopmentData>) dao.sortAndFilter(class1, first, pageSize, sortField, sortOrder, filters);

	}

	/**
	 * Get count of QmrLecturerDevelopmentData for lazy load with filters.
	 *
	 * @author TechFinium
	 * @param entity  QmrLecturerDevelopmentData class
	 * @param filters the filters
	 * @return Number of rows in the QmrLecturerDevelopmentData entity
	 * @throws Exception the exception
	 */
	public int count(Class<QmrLecturerDevelopmentData> entity, Map<String, Object> filters) throws Exception {
		return dao.count(entity, filters);
	}
	
	public void lpmFM005Download() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();

		// Service Layers
		UsersService usersService= new UsersService();
		
		// Text Field Parameters
//		Users learnerUsers = UsersService.findByUsers();

		
		// TABLE LISTS

		
		// Jasper Parameters for Text Fields
			params.put("learner", "learnerUsers");

			params.put("employerContactPerson", "employerContactPersonUsers");

			params.put("skillsDevProviderContactPerson", "skillsDevProviderContactPersonUsers");

			params.put("new_employer", "newEmployerCompany");

			params.put("skillsDevProvider", "skillsDevProviderCompany");

			params.put("ofo_codes", "OfoCodes");

		params.put("applicationReason", "String");
		params.put("applicationReason", "String");
		params.put("contractAgreementNum", "String");
		params.put("skillsDevProviderAccreditationNum", "String");

		params.put("isMinor", "Boolean");

		// Jasper Parameters for Table Lists
//		if (!addressList.isEmpty()) {
//			params.put("AddressList", new JRBeanCollectionDataSource(addressList));
//		}

		JasperService.addFormTemplateParams(params);
		JasperService.addImage(params, "checkbox-marked.png", "checked_image");
		JasperService.addImage(params, "checkbox-outline.png", "unchecked_image");
		JasperService js = new JasperService();
		js.createReportFromDBtoServletOutputStream("LPM-FM-005.jasper", params, "LPM-FM-005.pdf");
	}
	
	public void lpmFM018BDownload() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();

		// Service Layers
		UsersService usersService= new UsersService();
		
		// Text Field Parameters
//		Users learnerUsers = UsersService.findByUsers();

		
		// TABLE LISTS

		
		// Jasper Parameters for Text Fields
			params.put("learner", "learnerUsers");
		
			params.put("employerContactPerson", "employerContactPersonUsers");

			params.put("new_employer", "newEmployerCompany");
		
		params.put("applicationReason", "String");
		params.put("learnershipNum", "String");
		params.put("leanership", "String");
		
		params.put("isMinor", "Boolean");

		// Jasper Parameters for Table Lists
//		if (!addressList.isEmpty()) {
//			params.put("AddressList", new JRBeanCollectionDataSource(addressList));
//		}

		JasperService.addFormTemplateParams(params);
		JasperService.addImage(params, "checkbox-marked.png", "checked_image");
		JasperService.addImage(params, "checkbox-outline.png", "unchecked_image");
		JasperService js = new JasperService();
		js.createReportFromDBtoServletOutputStream("LPM-FM-018-B.jasper", params, "LPM-FM-018-B.pdf");
	}
	
	public void lpmAD008Download() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		
		// Service Layers
		UsersService usersService= new UsersService();
		
		// Text Field Parameters
//		Users learnerUsers = UsersService.findByUsers();
		
		
		// TABLE LISTS
		
		
		// Jasper Parameters for Text Fields
		params.put("company_id", "Long");
		params.put("learners_id", "Long");
		
		// Jasper Parameters for Table Lists
//		if (!addressList.isEmpty()) {
//			params.put("AddressList", new JRBeanCollectionDataSource(addressList));
//		}
		
		JasperService.addFormTemplateParams(params);
		JasperService.addImage(params, "checkbox-marked.png", "checked_image");
		JasperService.addImage(params, "checkbox-outline.png", "unchecked_image");
		JasperService js = new JasperService();
		js.createReportFromDBtoServletOutputStream("LPM-AD-008.jasper", params, "LPM-AD-008.pdf");
	}
	
	public void lpmAD002Download() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		
		// Service Layers
		UsersService usersService= new UsersService();
		
		// Text Field Parameters
//		Users learnerUsers = UsersService.findByUsers();
		
		
		// TABLE LISTS
		
		
		// Jasper Parameters for Text Fields
		params.put("company_id", "Long");
		params.put("learners_id", "Long");
		
		// Jasper Parameters for Table Lists
//		if (!addressList.isEmpty()) {
//			params.put("AddressList", new JRBeanCollectionDataSource(addressList));
//		}
		
		JasperService.addFormTemplateParams(params);
		JasperService.addImage(params, "checkbox-marked.png", "checked_image");
		JasperService.addImage(params, "checkbox-outline.png", "unchecked_image");
		JasperService js = new JasperService();
		js.createReportFromDBtoServletOutputStream("LPM-AD-002.jasper", params, "LPM-AD-002.pdf");
	}
	
	private List<QmrSummaryBean> generateSummaryReport(Integer finYearPassed, List<QmrTypeSelectionEnum> qmrTypeSelectionEnumList, List<EmploymentStatusEnum> employmentStatusEnum, List<LearnerStatusEnum> learnerStatusEnumList, QmrEnteredCompletedEnum qmrEnteredCompletedEnum) throws Exception {

		List<QmrEquityEnum> qmrEquityEnumList;
		List<QmrGenderEnum> qmrGenderEnumList;

		List<QmrSummaryBean> reportList = QmrGenericService.instance().getQmrSummaryReportTemplate();
		if (qmrFinYearsService == null) {
			qmrFinYearsService = new QmrFinYearsService();
		}
		List<QmrFinYears> allQuarters = qmrFinYearsService.findByFinYearsStart(finYearPassed);
		for (QmrSummaryBean report : reportList) {
			// loop through quarters to find results
			for (QmrFinYears qmrFinYears : allQuarters) {
				Integer finalResult = 0;
				switch (report.getQmrReportType()) {
				case DisabilityCount:
					// check if data generated
					if (qmrFinYears.getDataGenerated() == null || !qmrFinYears.getDataGenerated()) {
						// use generic scripts
						List<CounterBean> cb = QmrGenericService.instance().qmrScriptCountDisability(qmrEnteredCompletedEnum, qmrFinYears.getFromDate(), qmrFinYears.getToDate(), qmrTypeSelectionEnumList, employmentStatusEnum, learnerStatusEnumList);
						if (!cb.isEmpty() && cb.get(0) != null && cb.get(0).getCounter() != null) {
							finalResult = cb.get(0).getCounter().intValue();
						}
					} else {
						// data generated use main entity
//						finalResult = dao.countByDisabilityIdentified(qmrFinYears.getId(), employmentStatusEnum.get(0), qmrEnteredCompletedEnum, true);
						finalResult = dao.countByDisabilityIdentified(qmrFinYears.getId(), qmrEnteredCompletedEnum, true);
					}
					break;
				case EquityGender:
					// check if data generated
					if (qmrFinYears.getDataGenerated() == null || !qmrFinYears.getDataGenerated()) {
						// use generic scripts
						qmrEquityEnumList = new ArrayList<>();
						qmrEquityEnumList.add(report.getQmrEquityEnum());

						qmrGenderEnumList = new ArrayList<>();
						qmrGenderEnumList.add(report.getQmrGenderEnum());
						List<CounterBean> cb = QmrGenericService.instance().qmrScriptGenerEquityCount(qmrEnteredCompletedEnum, qmrFinYears.getFromDate(), qmrFinYears.getToDate(), qmrTypeSelectionEnumList, employmentStatusEnum, learnerStatusEnumList, qmrEquityEnumList, qmrGenderEnumList);
						if (!cb.isEmpty() && cb.get(0) != null && cb.get(0).getCounter() != null) {
							finalResult = cb.get(0).getCounter().intValue();
						}
					} else {
						// data generated use main entity
//						finalResult = dao.countByEquityGender(qmrFinYears.getId(), employmentStatusEnum.get(0), qmrEnteredCompletedEnum, report.getQmrGenderEnum(), report.getQmrEquityEnum());
						finalResult = dao.countByEquityGender(qmrFinYears.getId(), qmrEnteredCompletedEnum, report.getQmrGenderEnum(), report.getQmrEquityEnum());
					}
					break;
				case NonRSACitizenCount:
					// check if data generated
					if (qmrFinYears.getDataGenerated() == null || !qmrFinYears.getDataGenerated()) {
						// use generic scripts
						List<CounterBean> cb = QmrGenericService.instance().qmrScriptNonRsaCitizenCount(qmrEnteredCompletedEnum, qmrFinYears.getFromDate(), qmrFinYears.getToDate(), qmrTypeSelectionEnumList, employmentStatusEnum, learnerStatusEnumList);
						if (!cb.isEmpty() && cb.get(0) != null && cb.get(0).getCounter() != null) {
							finalResult = cb.get(0).getCounter().intValue();
						}
					} else {
						// data generated use main entity
//						finalResult = dao.countByRsaCitizen(qmrFinYears.getId(), employmentStatusEnum.get(0), qmrEnteredCompletedEnum, false);
						finalResult = dao.countByRsaCitizen(qmrFinYears.getId(), qmrEnteredCompletedEnum, false);
					}
					break;
				case YouthCount:
					// check if data generated
					if (qmrFinYears.getDataGenerated() == null || !qmrFinYears.getDataGenerated()) {
						// use generic scripts
						List<CounterBean> cb = QmrGenericService.instance().qmrScriptYouthCount(qmrEnteredCompletedEnum, qmrFinYears.getFromDate(), qmrFinYears.getToDate(), qmrTypeSelectionEnumList, employmentStatusEnum, learnerStatusEnumList, HAJConstants.QMR_AGE_YOUTH);
						if (!cb.isEmpty() && cb.get(0) != null && cb.get(0).getCounter() != null) {
							finalResult = cb.get(0).getCounter().intValue();
						}
					} else {
						// data generated use main entity
//						finalResult = dao.countByYouthIdentfied(qmrFinYears.getId(), employmentStatusEnum.get(0), qmrEnteredCompletedEnum, HAJConstants.QMR_AGE_YOUTH);
						finalResult = dao.countByYouthIdentfied(qmrFinYears.getId(), qmrEnteredCompletedEnum, HAJConstants.QMR_AGE_YOUTH);
					}
					break;
				default:
					break;
				}
				// save result
				switch (qmrFinYears.getFinYearQuarters()) {
				case QuarterOne:
					report.setQuarterOneCount(finalResult);
					break;
				case QuarterTwo:
					report.setQuarterTwoCount(finalResult);
					break;
				case QuarterThree:
					report.setQuarterThreeCount(finalResult);
					break;
				case QuarterFour:
					report.setQuarterFourCount(finalResult);
					break;
				default:
					break;
				}
			}
			// sum up total
			Integer total = 0;
			if (report.getQuarterOneCount() != null && report.getQuarterOneCount() != 0)
				total += report.getQuarterOneCount();
			if (report.getQuarterTwoCount() != null && report.getQuarterTwoCount() != 0)
				total += report.getQuarterTwoCount();
			if (report.getQuarterThreeCount() != null && report.getQuarterThreeCount() != 0)
				total += report.getQuarterThreeCount();
			if (report.getQuarterFourCount() != null && report.getQuarterFourCount() != 0)
				total += report.getQuarterFourCount();

			report.setTotal(total);
		}
		return reportList;
	}

	private List<QmrQuarterCountBean> generateCountReport(QmrFinYears selectedQuarter, List<QmrTypeSelectionEnum> qmrTypeSelectionEnumList, List<EmploymentStatusEnum> employmentStatusEnum, List<LearnerStatusEnum> learnerStatusEnumList, QmrEnteredCompletedEnum qmrEnteredCompletedEnum) throws Exception {
		List<QmrGenderEnum> qmrGenderEnumList;
		List<QmrEquityEnum> qmrEquityEnumList;

		List<QmrQuarterCountBean> reportList = QmrGenericService.instance().getQmrSummaryReportTemplateForQuarter();

		for (QmrQuarterCountBean report : reportList) {
			Integer maleCount = 0;
			Integer femaleCount = 0;
			switch (report.getQmrReportType()) {
			case DisabilityCount:
				// check if data generated
				if (selectedQuarter.getDataGenerated() == null || !selectedQuarter.getDataGenerated()) {
					// use generic scripts

					// male count
					qmrGenderEnumList = new ArrayList<>();
					qmrGenderEnumList.add(QmrGenderEnum.MALE);
					List<CounterBean> cbMale = QmrGenericService.instance().qmrScriptCountDisabilityWithGender(qmrEnteredCompletedEnum, selectedQuarter.getFromDate(), selectedQuarter.getToDate(), qmrTypeSelectionEnumList, employmentStatusEnum, learnerStatusEnumList, qmrGenderEnumList);
					if (!cbMale.isEmpty() && cbMale.get(0) != null && cbMale.get(0).getCounter() != null) {
						maleCount = cbMale.get(0).getCounter().intValue();
					}

					// female count
					qmrGenderEnumList = new ArrayList<>();
					qmrGenderEnumList.add(QmrGenderEnum.FEMALE);
					List<CounterBean> cbFemale = QmrGenericService.instance().qmrScriptCountDisabilityWithGender(qmrEnteredCompletedEnum, selectedQuarter.getFromDate(), selectedQuarter.getToDate(), qmrTypeSelectionEnumList, employmentStatusEnum, learnerStatusEnumList, qmrGenderEnumList);
					if (!cbFemale.isEmpty() && cbFemale.get(0) != null && cbFemale.get(0).getCounter() != null) {
						femaleCount = cbFemale.get(0).getCounter().intValue();
					}

				} else {
					// data generated use main entity
					// male
//					maleCount = dao.countByDisabilityIdentifiedWithGenderAndEquity(selectedQuarter.getId(), employmentStatusEnum.get(0), qmrEnteredCompletedEnum, true, QmrGenderEnum.MALE, report.getQmrEquityEnum());
					maleCount = dao.countByDisabilityIdentifiedWithGender(selectedQuarter.getId(), qmrEnteredCompletedEnum, true, QmrGenderEnum.MALE);
					// female
//					femaleCount = dao.countByDisabilityIdentifiedWithGenderAndEquity(selectedQuarter.getId(), employmentStatusEnum.get(0), qmrEnteredCompletedEnum, true, QmrGenderEnum.FEMALE, report.getQmrEquityEnum());
					femaleCount = dao.countByDisabilityIdentifiedWithGender(selectedQuarter.getId(), qmrEnteredCompletedEnum, true, QmrGenderEnum.FEMALE);
				}
				break;
			case EquityGender:
				// check if data generated
				if (selectedQuarter.getDataGenerated() == null || !selectedQuarter.getDataGenerated()) {
					// use generic scripts

					// male count
					qmrGenderEnumList = new ArrayList<>();
					qmrGenderEnumList.add(QmrGenderEnum.MALE);
					qmrEquityEnumList = new ArrayList<>();
					qmrEquityEnumList.add(report.getQmrEquityEnum());
					List<CounterBean> cbMale = QmrGenericService.instance().qmrScriptGenerEquityCount(qmrEnteredCompletedEnum, selectedQuarter.getFromDate(), selectedQuarter.getToDate(), qmrTypeSelectionEnumList, employmentStatusEnum, learnerStatusEnumList, qmrEquityEnumList, qmrGenderEnumList);
					if (!cbMale.isEmpty() && cbMale.get(0) != null && cbMale.get(0).getCounter() != null) {
						maleCount = cbMale.get(0).getCounter().intValue();
					}

					// female count
					qmrGenderEnumList = new ArrayList<>();
					qmrGenderEnumList.add(QmrGenderEnum.FEMALE);
					qmrEquityEnumList = new ArrayList<>();
					qmrEquityEnumList.add(report.getQmrEquityEnum());
					List<CounterBean> cbFemale = QmrGenericService.instance().qmrScriptGenerEquityCount(qmrEnteredCompletedEnum, selectedQuarter.getFromDate(), selectedQuarter.getToDate(), qmrTypeSelectionEnumList, employmentStatusEnum, learnerStatusEnumList, qmrEquityEnumList, qmrGenderEnumList);
					if (!cbFemale.isEmpty() && cbFemale.get(0) != null && cbFemale.get(0).getCounter() != null) {
						femaleCount = cbFemale.get(0).getCounter().intValue();
					}

				} else {
					// data generated use main entity
					// male count
//					maleCount = dao.countByEquityGender(selectedQuarter.getId(), employmentStatusEnum.get(0), qmrEnteredCompletedEnum, QmrGenderEnum.MALE, report.getQmrEquityEnum());
					maleCount = dao.countByEquityGender(selectedQuarter.getId(), qmrEnteredCompletedEnum, QmrGenderEnum.MALE, report.getQmrEquityEnum());
					// female count
//					femaleCount = dao.countByEquityGender(selectedQuarter.getId(), employmentStatusEnum.get(0), qmrEnteredCompletedEnum, QmrGenderEnum.FEMALE, report.getQmrEquityEnum());
					femaleCount = dao.countByEquityGender(selectedQuarter.getId(), qmrEnteredCompletedEnum, QmrGenderEnum.FEMALE, report.getQmrEquityEnum());
				}
				break;
			case NonRSACitizenCount:
				// check if data generated
				if (selectedQuarter.getDataGenerated() == null || !selectedQuarter.getDataGenerated()) {
					// use generic scripts

					// male count
					qmrGenderEnumList = new ArrayList<>();
					qmrGenderEnumList.add(QmrGenderEnum.MALE);
					List<CounterBean> cbMale = QmrGenericService.instance().qmrScriptNonRsaCitizenCountWithGender(qmrEnteredCompletedEnum, selectedQuarter.getFromDate(), selectedQuarter.getToDate(), qmrTypeSelectionEnumList, employmentStatusEnum, learnerStatusEnumList, qmrGenderEnumList);
					if (!cbMale.isEmpty() && cbMale.get(0) != null && cbMale.get(0).getCounter() != null) {
						maleCount = cbMale.get(0).getCounter().intValue();
					}

					// female count
					qmrGenderEnumList = new ArrayList<>();
					qmrGenderEnumList.add(QmrGenderEnum.FEMALE);
					List<CounterBean> cbFemale = QmrGenericService.instance().qmrScriptNonRsaCitizenCountWithGender(qmrEnteredCompletedEnum, selectedQuarter.getFromDate(), selectedQuarter.getToDate(), qmrTypeSelectionEnumList, employmentStatusEnum, learnerStatusEnumList, qmrGenderEnumList);
					if (!cbFemale.isEmpty() && cbFemale.get(0) != null && cbFemale.get(0).getCounter() != null) {
						femaleCount = cbFemale.get(0).getCounter().intValue();
					}

				} else {
					// data generated use main entity
					// male count
//					maleCount = dao.countByRsaCitizenWithGenderAndEquity(selectedQuarter.getId(), employmentStatusEnum.get(0), qmrEnteredCompletedEnum, false, QmrGenderEnum.MALE, report.getQmrEquityEnum());
					maleCount = dao.countByRsaCitizenWithGender(selectedQuarter.getId(), qmrEnteredCompletedEnum, false, QmrGenderEnum.MALE);
					// female count
//					femaleCount = dao.countByRsaCitizenWithGenderAndEquity(selectedQuarter.getId(), employmentStatusEnum.get(0), qmrEnteredCompletedEnum, false, QmrGenderEnum.FEMALE, report.getQmrEquityEnum());
					femaleCount = dao.countByRsaCitizenWithGender(selectedQuarter.getId(), qmrEnteredCompletedEnum, false, QmrGenderEnum.FEMALE);
				}
				break;
			case YouthCount:
				// check if data generated
				if (selectedQuarter.getDataGenerated() == null || !selectedQuarter.getDataGenerated()) {
					// use generic scripts

					// male count
					qmrGenderEnumList = new ArrayList<>();
					qmrGenderEnumList.add(QmrGenderEnum.MALE);
					List<CounterBean> cbMale = QmrGenericService.instance().qmrScriptYouthCountWithGender(qmrEnteredCompletedEnum, selectedQuarter.getFromDate(), selectedQuarter.getToDate(), qmrTypeSelectionEnumList, employmentStatusEnum, learnerStatusEnumList, HAJConstants.QMR_AGE_YOUTH, qmrGenderEnumList);
					if (!cbMale.isEmpty() && cbMale.get(0) != null && cbMale.get(0).getCounter() != null) {
						maleCount = cbMale.get(0).getCounter().intValue();
					}

					// female count
					qmrGenderEnumList = new ArrayList<>();
					qmrGenderEnumList.add(QmrGenderEnum.FEMALE);
					qmrEquityEnumList = new ArrayList<>();
					qmrEquityEnumList.add(report.getQmrEquityEnum());
					List<CounterBean> cbFemale = QmrGenericService.instance().qmrScriptYouthCountWithGender(qmrEnteredCompletedEnum, selectedQuarter.getFromDate(), selectedQuarter.getToDate(), qmrTypeSelectionEnumList, employmentStatusEnum, learnerStatusEnumList, HAJConstants.QMR_AGE_YOUTH, qmrGenderEnumList);
					if (!cbFemale.isEmpty() && cbFemale.get(0) != null && cbFemale.get(0).getCounter() != null) {
						femaleCount = cbFemale.get(0).getCounter().intValue();
					}

				} else {
					// data generated use main entity
					// male count
//					maleCount = dao.countByYouthIdentfiedWithGenderAndEquity(selectedQuarter.getId(), employmentStatusEnum.get(0), qmrEnteredCompletedEnum, HAJConstants.QMR_AGE_YOUTH, QmrGenderEnum.MALE, report.getQmrEquityEnum());
					maleCount = dao.countByYouthIdentfiedWithGender(selectedQuarter.getId(), qmrEnteredCompletedEnum, HAJConstants.QMR_AGE_YOUTH, QmrGenderEnum.MALE);
					// female count
//					femaleCount = dao.countByYouthIdentfiedWithGenderAndEquity(selectedQuarter.getId(), employmentStatusEnum.get(0), qmrEnteredCompletedEnum, HAJConstants.QMR_AGE_YOUTH, QmrGenderEnum.FEMALE, report.getQmrEquityEnum());
					femaleCount = dao.countByYouthIdentfiedWithGender(selectedQuarter.getId(), qmrEnteredCompletedEnum, HAJConstants.QMR_AGE_YOUTH, QmrGenderEnum.FEMALE);
				}
				break;
			default:
				break;
			}

			if (maleCount != null) {
				report.setMaleCount(maleCount);
			} else {
				report.setMaleCount(0);
			}
			if (femaleCount != null) {
				report.setFemaleCount(femaleCount);
			} else {
				report.setFemaleCount(0);
			}

			// sum up total
			Integer total = 0;
			if (report.getMaleCount() != null && report.getMaleCount() != 0)
				total += report.getMaleCount();
			if (report.getFemaleCount() != null && report.getFemaleCount() != 0)
				total += report.getFemaleCount();
			report.setTotal(total);
		}

		return reportList;
	}
	
	/*
	 * Generate Summary Report:
	 * Lecturer Development Entered
	 */
	public List<QmrSummaryBean> generateSummaryReportLecturerDevelopmentEntered(Integer finYearPassed) throws Exception {
		List<QmrTypeSelectionEnum> qmrTypeSelectionEnumList = QmrGenericService.instance().addQmrTypeSelectionEnumToArrayList(QmrTypeSelectionEnum.LECTURER_DEVELOPMENT_PROGRAMMES);
		List<EmploymentStatusEnum> employmentStatusEnum = null;
		List<LearnerStatusEnum> learnerStatusEnumList = QmrGenericService.instance().prepLearnerStatusEnumBasedOnIndicator(1);
		QmrEnteredCompletedEnum qmrEnteredCompletedEnum = QmrEnteredCompletedEnum.Entered;
		return generateSummaryReport(finYearPassed, qmrTypeSelectionEnumList, employmentStatusEnum, learnerStatusEnumList, qmrEnteredCompletedEnum);
	}
	
	/*
	 * Generate Summary Report:
	 * Lecturer Development Completed
	 */
	public List<QmrSummaryBean> generateSummaryReportLecturerDevelopmentCompleted(Integer finYearPassed) throws Exception {
		List<QmrTypeSelectionEnum> qmrTypeSelectionEnumList = QmrGenericService.instance().addQmrTypeSelectionEnumToArrayList(QmrTypeSelectionEnum.LECTURER_DEVELOPMENT_PROGRAMMES);
		List<EmploymentStatusEnum> employmentStatusEnum = null;
		List<LearnerStatusEnum> learnerStatusEnumList = QmrGenericService.instance().prepLearnerStatusEnumBasedOnIndicator(2);
		QmrEnteredCompletedEnum qmrEnteredCompletedEnum = QmrEnteredCompletedEnum.Completed;
		return generateSummaryReport(finYearPassed, qmrTypeSelectionEnumList, employmentStatusEnum, learnerStatusEnumList, qmrEnteredCompletedEnum);
	}

	/*
	 * Generate Count Report: Lecturer Development Entered
	 */
	public List<QmrQuarterCountBean> generateCountReportLecturerDevelopmentEntered(QmrFinYears selectedQuarter) throws Exception {
		List<QmrTypeSelectionEnum> qmrTypeSelectionEnumList = QmrGenericService.instance().addQmrTypeSelectionEnumToArrayList(QmrTypeSelectionEnum.LECTURER_DEVELOPMENT_PROGRAMMES);
		List<EmploymentStatusEnum> employmentStatusEnum = null;
		List<LearnerStatusEnum> learnerStatusEnumList = QmrGenericService.instance().prepLearnerStatusEnumBasedOnIndicator(1);
		QmrEnteredCompletedEnum qmrEnteredCompletedEnum = QmrEnteredCompletedEnum.Entered;
		return generateCountReport(selectedQuarter, qmrTypeSelectionEnumList, employmentStatusEnum, learnerStatusEnumList, qmrEnteredCompletedEnum);
	}

	/*
	 * Generate Count Report: Lecturer Development Completed
	 */
	public List<QmrQuarterCountBean> generateCountReportLecturerDevelopmentCompleted(QmrFinYears selectedQuarter) throws Exception {
		List<QmrTypeSelectionEnum> qmrTypeSelectionEnumList = QmrGenericService.instance().addQmrTypeSelectionEnumToArrayList(QmrTypeSelectionEnum.LECTURER_DEVELOPMENT_PROGRAMMES);
		List<EmploymentStatusEnum> employmentStatusEnum = null;
		List<LearnerStatusEnum> learnerStatusEnumList = QmrGenericService.instance().prepLearnerStatusEnumBasedOnIndicator(2);
		QmrEnteredCompletedEnum qmrEnteredCompletedEnum = QmrEnteredCompletedEnum.Completed;
		return generateCountReport(selectedQuarter, qmrTypeSelectionEnumList, employmentStatusEnum, learnerStatusEnumList, qmrEnteredCompletedEnum);
	}

	/*
	 * Pull Lecturer Development Entered: Data Report Saved Data
	 */
	public List<QmrLecturerDevelopmentData> fetchSavedDataLecturerDevelopmentEnteredForQuarter(QmrFinYears qmrFinYears) throws Exception {
		List<EmploymentStatusEnum> employmentStatusEnum = null;
		QmrEnteredCompletedEnum qmrEnteredCompletedEnum = QmrEnteredCompletedEnum.Entered;
		return dao.findDataForReport(qmrFinYears.getId(), employmentStatusEnum, qmrEnteredCompletedEnum);
	}

	/*
	 * Pull Lecturer Development Completed: Data Report Saved Data
	 */
	public List<QmrLecturerDevelopmentData> fetchSavedDataLecturerDevelopmentCompletedForQuarter(QmrFinYears qmrFinYears) throws Exception {
		List<EmploymentStatusEnum> employmentStatusEnum = null;
		QmrEnteredCompletedEnum qmrEnteredCompletedEnum = QmrEnteredCompletedEnum.Completed;
		return dao.findDataForReport(qmrFinYears.getId(), employmentStatusEnum, qmrEnteredCompletedEnum);
	}

	/*
	 * Pull Lecturer Development Entered: Data Report Not Saved Data
	 */
	public List<QmrScriptOneBean> runLecturerDevelopmentEnteredForQuarterNotSaved(QmrFinYears qmrFinYears) throws Exception {
		/* Prep data for report */
		List<QmrTypeSelectionEnum> qmrTypeSelectionEnumList = QmrGenericService.instance().addQmrTypeSelectionEnumToArrayList(QmrTypeSelectionEnum.LECTURER_DEVELOPMENT_PROGRAMMES);
		List<EmploymentStatusEnum> employmentStatusEnum = null;
		List<LearnerStatusEnum> learnerStatusEnumList = QmrGenericService.instance().prepLearnerStatusEnumBasedOnIndicator(1);
		QmrEnteredCompletedEnum qmrEnteredCompletedEnum = QmrEnteredCompletedEnum.Entered;
		return QmrGenericService.instance().populateLearnershipUnemployedResultsByQuarterNotSaved(qmrFinYears, qmrTypeSelectionEnumList, employmentStatusEnum, learnerStatusEnumList, qmrEnteredCompletedEnum);
	}

	/*
	 * Pull Lecturer Development Completed: Data Report Not Saved Data
	 */
	public List<QmrScriptOneBean> runLecturerDevelopmentCompletedForQuarterNotSaved(QmrFinYears qmrFinYears) throws Exception {
		/* Prep data for report */
		List<QmrTypeSelectionEnum> qmrTypeSelectionEnumList = QmrGenericService.instance().addQmrTypeSelectionEnumToArrayList(QmrTypeSelectionEnum.LECTURER_DEVELOPMENT_PROGRAMMES);
		List<EmploymentStatusEnum> employmentStatusEnum = null;
		List<LearnerStatusEnum> learnerStatusEnumList = QmrGenericService.instance().prepLearnerStatusEnumBasedOnIndicator(2);
		QmrEnteredCompletedEnum qmrEnteredCompletedEnum = QmrEnteredCompletedEnum.Completed;
		return QmrGenericService.instance().populateLearnershipUnemployedResultsByQuarterNotSaved(qmrFinYears, qmrTypeSelectionEnumList, employmentStatusEnum, learnerStatusEnumList, qmrEnteredCompletedEnum);
	}
	
	/**
	 * Generates List<haj.com.entity.QmrLecturerDevelopmentData> for a quarter provided. 
	 * 
	 * @param qmrFinYears
	 * @return List<QmrLecturerDevelopmentData>
	 * @throws Exception
	 */
	public List<QmrLecturerDevelopmentData> generateQuaterData(QmrFinYears qmrFinYears) throws Exception {
		
		List<QmrScriptOneBean> results = null;
		List<QmrLecturerDevelopmentData> transformedResults = null;
		List<QmrLecturerDevelopmentData> returnResults = new ArrayList<>();
		
		results = runLecturerDevelopmentEnteredForQuarterNotSaved(qmrFinYears);
		if (!results.isEmpty()) {
			transformedResults = populateDataIntoReportingForm(results, qmrFinYears, QmrEnteredCompletedEnum.Entered, null);
			if (!transformedResults.isEmpty()) {
				returnResults.addAll(transformedResults);
			}
		}
		
		results = runLecturerDevelopmentCompletedForQuarterNotSaved(qmrFinYears);
		if (!results.isEmpty()) {
			transformedResults = populateDataIntoReportingForm(results, qmrFinYears, QmrEnteredCompletedEnum.Completed, null);
			if (!transformedResults.isEmpty()) {
				returnResults.addAll(transformedResults);
			}
		}
		
		// clear results list
		if (results != null && !results.isEmpty()) {
			results.clear();
		}
		
		// clear transformedResults list
		if (transformedResults != null && !transformedResults.isEmpty()) {
			transformedResults.clear();
		}
		
		return returnResults;
	}
	
	/**
	 * Populates additional information against the entries for correct formatting to be saved and used for reporting
	 * 
	 * @param extractedResults
	 * @param qmrFinYears
	 * @param qmrEnteredCompletedEnum
	 * @param employmentStatusEnum
	 * @return List<QmrLecturerDevelopmentData>
	 * @throws Exception
	 */
	private List<QmrLecturerDevelopmentData> populateDataIntoReportingForm(List<QmrScriptOneBean> extractedResults, QmrFinYears qmrFinYears, QmrEnteredCompletedEnum qmrEnteredCompletedEnum, EmploymentStatusEnum employmentStatusEnum) throws Exception {
		List<QmrLecturerDevelopmentData> results = new ArrayList<>();
		for (QmrScriptOneBean bean : extractedResults) {
			QmrLecturerDevelopmentData newEntry = new QmrLecturerDevelopmentData();
			// copy over the property information
			org.apache.commons.beanutils.BeanUtils.copyProperties(newEntry, bean);
			// populate data manually 
			if (bean.getCompanyLearnerFlatID() != null) {
				newEntry.setCompanyLearnerFlatLink(bean.getCompanyLearnerFlatID().longValue());
			}
			if (bean.getAge() != null) {
				newEntry.setAgeOfLearner(bean.getAge().intValue());
				newEntry.setAgeIndicator(bean.getAge().intValue());
			}
			// set standard data and process
			newEntry.setQmrFinYears(qmrFinYears);
			newEntry.setQmrEnteredCompleted(qmrEnteredCompletedEnum);
			// populate gender
			if (genderService == null) {
				genderService = new GenderService();
			}
			if (newEntry.getGender() != null && newEntry.getGender().trim() != "") {
				Gender gender = genderService.findByGenderNameOneResult(newEntry.getGender());
				if (gender != null && gender.getQmrGender() != null) {
					newEntry.setQmrGender(gender.getQmrGender());
				}
			}			
			// populate equity
			if (equityService == null) {
				equityService = new EquityService();
			}
			if (newEntry.getRace() != null && newEntry.getGender().trim() != "") {
				Equity equity = equityService.findByNameReturnOneResult(newEntry.getRace());
				if (equity != null && equity.getQmrEquity() != null) {
					newEntry.setQmrEquity(equity.getQmrEquity());
				}
			}
			if (employmentStatusEnum != null) {
				newEntry.setEmployedUnEmployed(employmentStatusEnum);
			}
			if (bean.getDisability() != null && !bean.getDisability().trim().isEmpty()) {
				if (bean.getDisability().trim().equalsIgnoreCase("Yes")) {
					newEntry.setDisabilityIdentified(true);
				} else {
					newEntry.setDisabilityIdentified(false);
				}
			} else {
				newEntry.setDisabilityIdentified(false);
			}
			if (bean.getNonRsaCitizen() != null && !bean.getNonRsaCitizen().trim().isEmpty()) {
				if (bean.getNonRsaCitizen().equalsIgnoreCase("Citizen")) {
					newEntry.setRsaCitizen(true);
				}else {
					newEntry.setRsaCitizen(false);
				}
			} else {
				newEntry.setRsaCitizen(false);
			}
			results.add(newEntry);
		}
		return results;
	}
	
	public Integer countAllEntries(Long qmrFinYearId, EmploymentStatusEnum employedUnEmployed, QmrEnteredCompletedEnum qmrEnteredCompletedEnum) throws Exception {
		return dao.countAllEntries(qmrFinYearId, employedUnEmployed, qmrEnteredCompletedEnum);
	}
}