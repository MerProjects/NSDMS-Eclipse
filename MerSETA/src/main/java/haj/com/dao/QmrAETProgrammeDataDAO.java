package haj.com.dao;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import haj.com.entity.QmrAETProgrammeData;
import haj.com.entity.enums.EmploymentStatusEnum;
import haj.com.entity.enums.QmrEnteredCompletedEnum;
import haj.com.entity.enums.QmrEquityEnum;
import haj.com.entity.enums.QmrGenderEnum;
import haj.com.framework.AbstractDAO;
import haj.com.framework.AbstractDataProvider;
import haj.com.provider.MySQLProvider;

public class QmrAETProgrammeDataDAO extends AbstractDAO  {

	@Override
	public AbstractDataProvider getDataProvider() {
		return new MySQLProvider();
	}

	/**
	 * Get all QmrAETProgrammeData
 	 * @author TechFinium 
 	 * @see    QmrAETProgrammeData
 	 * @return a list of {@link haj.com.entity.QmrAETProgrammeData}
 	 * @throws Exception global exception
 	 */
	@SuppressWarnings("unchecked")
	public List<QmrAETProgrammeData> allQmrAETProgrammeData() throws Exception {
		return (List<QmrAETProgrammeData>)super.getList("select o from QmrAETProgrammeData o");
	}

	/**
	 * Get all QmrAETProgrammeData between from and noRows
 	 * @author TechFinium 
 	 * @param from the from
 	 * @param noRows the no rows
 	 * @see    QmrAETProgrammeData
 	 * @return a list of {@link haj.com.entity.QmrAETProgrammeData}
 	 * @throws Exception global exception
 	 */
	@SuppressWarnings("unchecked")
	public List<QmrAETProgrammeData> allQmrAETProgrammeData(Long from, int noRows) throws Exception {
	 	String hql = "select o from QmrAETProgrammeData o " ;
	    Map<String, Object> parameters = new Hashtable<String, Object>();
	    
		return (List<QmrAETProgrammeData>)super.getList(hql, parameters,from.intValue(),noRows);
	}

	/**
	 * Find object by primary key
 	 * @author TechFinium 
 	 * @param id the id
 	 * @see    QmrAETProgrammeData
 	 * @return a {@link haj.com.entity.QmrAETProgrammeData}
 	 * @throws Exception global exception
 	 */
	public QmrAETProgrammeData findByKey(Long id) throws Exception {
	 	String hql = "select o from QmrAETProgrammeData o where o.id = :id " ;
	    Map<String, Object> parameters = new Hashtable<String, Object>();
	    parameters.put("id", id);
		return (QmrAETProgrammeData)super.getUniqueResult(hql, parameters);
	}

	/**
	 * Find QmrAETProgrammeData by description
 	 * @author TechFinium 
 	 * @param description the description 
 	 * @see    QmrAETProgrammeData
  	 * @return a list of {@link haj.com.entity.QmrAETProgrammeData}
 	 * @throws Exception global exception
 	 */
	@SuppressWarnings("unchecked")
	public List<QmrAETProgrammeData> findByName(String description) throws Exception {
	 	String hql = "select o from QmrAETProgrammeData o where o.description like  :description order by o.desc " ;
	    Map<String, Object> parameters = new Hashtable<String, Object>();
	    parameters.put("description", ""+description.trim()+"%");
		return (List<QmrAETProgrammeData>)super.getList(hql, parameters);
	}
	
	/*
	 * Fetch Data For Reports Start
	 */
	@SuppressWarnings("unchecked")
	public List<QmrAETProgrammeData> findDataForReport(Long qmrFinYearId, List<EmploymentStatusEnum> employmentStatusEnum, QmrEnteredCompletedEnum qmrEnteredCompletedEnum) throws Exception {
	 	String hql = "select o from QmrAETProgrammeData o where o.qmrFinYears.id = :qmrFinYearId " ;
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("qmrFinYearId", qmrFinYearId);
	    if (employmentStatusEnum != null && !employmentStatusEnum.isEmpty()) {
			hql +=" and o.employedUnEmployed in ( ";
			int counter = 1;
			for (EmploymentStatusEnum empStatus : employmentStatusEnum) {
				hql +=":empStatus" + counter;
				parameters.put("empStatus" + counter, empStatus);
				if (counter != employmentStatusEnum.size()) {
					hql +=" , ";
				}
			}
			hql +=" ) ";
		}
	    if (qmrEnteredCompletedEnum != null) {
			hql += " and o.qmrEnteredCompleted = :qmrEnteredCompletedEnum ";
			parameters.put("qmrEnteredCompletedEnum", qmrEnteredCompletedEnum);
		}
		return (List<QmrAETProgrammeData>)super.getList(hql, parameters);
	}
	
	public Integer findDataForReportCount(Long qmrFinYearId, List<EmploymentStatusEnum> employmentStatusEnum, QmrEnteredCompletedEnum qmrEnteredCompletedEnum) throws Exception {
	 	String hql = "select count(o) from QmrAETProgrammeData o where o.qmrFinYears.id = :qmrFinYearId " ;
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("qmrFinYearId", qmrFinYearId);
	    if (employmentStatusEnum != null && !employmentStatusEnum.isEmpty()) {
			hql +=" and o.employedUnEmployed in ( ";
			int counter = 1;
			for (EmploymentStatusEnum empStatus : employmentStatusEnum) {
				hql +=":empStatus" + counter;
				parameters.put("empStatus" + counter, empStatus);
				if (counter != employmentStatusEnum.size()) {
					hql +=" , ";
				}
			}
			hql +=" ) ";
		}
	    if (qmrEnteredCompletedEnum != null) {
			hql += " and o.qmrEnteredCompleted = :qmrEnteredCompletedEnum ";
			parameters.put("qmrEnteredCompletedEnum", qmrEnteredCompletedEnum);
		}
	    return ((Long)super.getUniqueResult(hql, parameters)).intValue();
	}
	/*
	 * Fetch Data For Reports End
	 */
	
	/* 
	 * Summary Count Reports: Start 
	 */
	
	/* Counts by Disability Identified */
	public Integer countByDisabilityIdentified(Long qmrFinYearId, EmploymentStatusEnum employedUnEmployed, QmrEnteredCompletedEnum qmrEnteredCompletedEnum, Boolean disabilityIdentifiedValue) throws Exception {
	 	String hql = "select count(o) from QmrAETProgrammeData o where o.qmrFinYears.id = :qmrFinYearId and o.qmrEnteredCompleted = :qmrEnteredCompletedEnum and o.employedUnEmployed = :employedUnEmployed "
	 			+ " and o.disabilityIdentified = :disabilityIdentifiedValue" ;
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("qmrFinYearId", qmrFinYearId);
	    parameters.put("employedUnEmployed", employedUnEmployed);
	    parameters.put("qmrEnteredCompletedEnum", qmrEnteredCompletedEnum);
	    parameters.put("disabilityIdentifiedValue", disabilityIdentifiedValue);
		return ((Long)super.getUniqueResult(hql, parameters)).intValue();
	}
	
	public Integer countByDisabilityIdentified(Long qmrFinYearId, QmrEnteredCompletedEnum qmrEnteredCompletedEnum, Boolean disabilityIdentifiedValue) throws Exception {
	 	String hql = "select count(o) from QmrAETProgrammeData o where o.qmrFinYears.id = :qmrFinYearId and o.qmrEnteredCompleted = :qmrEnteredCompletedEnum "
	 			+ " and o.disabilityIdentified = :disabilityIdentifiedValue" ;
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("qmrFinYearId", qmrFinYearId);
	    parameters.put("qmrEnteredCompletedEnum", qmrEnteredCompletedEnum);
	    parameters.put("disabilityIdentifiedValue", disabilityIdentifiedValue);
		return ((Long)super.getUniqueResult(hql, parameters)).intValue();
	}
	
	/* Counts by Disability Identified With gender and equity */
	public Integer countByDisabilityIdentifiedWithGenderAndEquity(Long qmrFinYearId,  EmploymentStatusEnum employedUnEmployed, QmrEnteredCompletedEnum qmrEnteredCompletedEnum, Boolean disabilityIdentifiedValue,
			QmrGenderEnum qmrGender, QmrEquityEnum qmrEquity) throws Exception {
	 	String hql = "select count(o) from QmrAETProgrammeData o where o.qmrFinYears.id = :qmrFinYearId and o.qmrEnteredCompleted = :qmrEnteredCompletedEnum and o.employedUnEmployed = :employedUnEmployed "
	 			+ " and o.disabilityIdentified = :disabilityIdentifiedValue " 
	 			+ " and o.qmrGender = :qmrGender and o.qmrEquity = :qmrEquity";
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("qmrFinYearId", qmrFinYearId);
	    parameters.put("employedUnEmployed", employedUnEmployed);
	    parameters.put("qmrEnteredCompletedEnum", qmrEnteredCompletedEnum);
	    parameters.put("disabilityIdentifiedValue", disabilityIdentifiedValue);
	    parameters.put("qmrGender", qmrGender);
	    parameters.put("qmrEquity", qmrEquity);
		return ((Long)super.getUniqueResult(hql, parameters)).intValue();
	}
	
	public Integer countByDisabilityIdentifiedWithGender(Long qmrFinYearId, QmrEnteredCompletedEnum qmrEnteredCompletedEnum, Boolean disabilityIdentifiedValue,
			QmrGenderEnum qmrGender) throws Exception {
	 	String hql = "select count(o) from QmrAETProgrammeData o where o.qmrFinYears.id = :qmrFinYearId and o.qmrEnteredCompleted = :qmrEnteredCompletedEnum "
	 			+ " and o.disabilityIdentified = :disabilityIdentifiedValue " 
	 			+ " and o.qmrGender = :qmrGender ";
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("qmrFinYearId", qmrFinYearId);
	    parameters.put("qmrEnteredCompletedEnum", qmrEnteredCompletedEnum);
	    parameters.put("disabilityIdentifiedValue", disabilityIdentifiedValue);
	    parameters.put("qmrGender", qmrGender);
		return ((Long)super.getUniqueResult(hql, parameters)).intValue();
	}
	
	/* Counts by Equity and Gender */
	public Integer countByEquityGender(Long qmrFinYearId, EmploymentStatusEnum employedUnEmployed, QmrEnteredCompletedEnum qmrEnteredCompletedEnum, QmrGenderEnum qmrGender, QmrEquityEnum qmrEquity) throws Exception {
	 	String hql = "select count(o) from QmrAETProgrammeData o where o.qmrFinYears.id = :qmrFinYearId and o.qmrEnteredCompleted = :qmrEnteredCompletedEnum and o.employedUnEmployed = :employedUnEmployed "
	 			+ " and o.qmrGender = :qmrGender and o.qmrEquity = :qmrEquity" ;
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("qmrFinYearId", qmrFinYearId);
	    parameters.put("employedUnEmployed", employedUnEmployed);
	    parameters.put("qmrEnteredCompletedEnum", qmrEnteredCompletedEnum);
	    parameters.put("qmrGender", qmrGender);
	    parameters.put("qmrEquity", qmrEquity);
		return ((Long)super.getUniqueResult(hql, parameters)).intValue();
	}
	
	public Integer countByEquityGender(Long qmrFinYearId, QmrEnteredCompletedEnum qmrEnteredCompletedEnum, QmrGenderEnum qmrGender, QmrEquityEnum qmrEquity) throws Exception {
	 	String hql = "select count(o) from QmrAETProgrammeData o where o.qmrFinYears.id = :qmrFinYearId and o.qmrEnteredCompleted = :qmrEnteredCompletedEnum "
	 			+ " and o.qmrGender = :qmrGender and o.qmrEquity = :qmrEquity" ;
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("qmrFinYearId", qmrFinYearId);
	    parameters.put("qmrEnteredCompletedEnum", qmrEnteredCompletedEnum);
	    parameters.put("qmrGender", qmrGender);
	    parameters.put("qmrEquity", qmrEquity);
		return ((Long)super.getUniqueResult(hql, parameters)).intValue();
	}
	
	/* Counts by RSA Citizen */
	public Integer countByRsaCitizen(Long qmrFinYearId, EmploymentStatusEnum employedUnEmployed, QmrEnteredCompletedEnum qmrEnteredCompletedEnum, Boolean rsaCitizen) throws Exception {
	 	String hql = "select count(o) from QmrAETProgrammeData o where o.qmrFinYears.id = :qmrFinYearId and o.qmrEnteredCompleted = :qmrEnteredCompletedEnum and o.employedUnEmployed = :employedUnEmployed "
	 			+ " and o.rsaCitizen = :rsaCitizen " ;
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("qmrFinYearId", qmrFinYearId);
	    parameters.put("employedUnEmployed", employedUnEmployed);
	    parameters.put("qmrEnteredCompletedEnum", qmrEnteredCompletedEnum);
	    parameters.put("rsaCitizen", rsaCitizen);
		return ((Long)super.getUniqueResult(hql, parameters)).intValue();
	}
	
	public Integer countByRsaCitizen(Long qmrFinYearId, QmrEnteredCompletedEnum qmrEnteredCompletedEnum, Boolean rsaCitizen) throws Exception {
	 	String hql = "select count(o) from QmrAETProgrammeData o where o.qmrFinYears.id = :qmrFinYearId and o.qmrEnteredCompleted = :qmrEnteredCompletedEnum "
	 			+ " and o.rsaCitizen = :rsaCitizen " ;
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("qmrFinYearId", qmrFinYearId);
	    parameters.put("qmrEnteredCompletedEnum", qmrEnteredCompletedEnum);
	    parameters.put("rsaCitizen", rsaCitizen);
		return ((Long)super.getUniqueResult(hql, parameters)).intValue();
	}
	
	/* Counts by RSA Citizen With gender and equity */
	public Integer countByRsaCitizenWithGenderAndEquity(Long qmrFinYearId, EmploymentStatusEnum employedUnEmployed, QmrEnteredCompletedEnum qmrEnteredCompletedEnum, Boolean rsaCitizen,
			QmrGenderEnum qmrGender, QmrEquityEnum qmrEquity) throws Exception {
	 	String hql = "select count(o) from QmrAETProgrammeData o where o.qmrFinYears.id = :qmrFinYearId and o.qmrEnteredCompleted = :qmrEnteredCompletedEnum and o.employedUnEmployed = :employedUnEmployed "
	 			+ " and o.rsaCitizen = :rsaCitizen " 
	 			+ " and o.qmrGender = :qmrGender and o.qmrEquity = :qmrEquity";
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("qmrFinYearId", qmrFinYearId);
	    parameters.put("employedUnEmployed", employedUnEmployed);
	    parameters.put("qmrEnteredCompletedEnum", qmrEnteredCompletedEnum);
	    parameters.put("rsaCitizen", rsaCitizen);
	    parameters.put("qmrGender", qmrGender);
	    parameters.put("qmrEquity", qmrEquity);
		return ((Long)super.getUniqueResult(hql, parameters)).intValue();
	}
	
	public Integer countByRsaCitizenWithGender(Long qmrFinYearId, QmrEnteredCompletedEnum qmrEnteredCompletedEnum, Boolean rsaCitizen,
			QmrGenderEnum qmrGender) throws Exception {
	 	String hql = "select count(o) from QmrAETProgrammeData o where o.qmrFinYears.id = :qmrFinYearId and o.qmrEnteredCompleted = :qmrEnteredCompletedEnum "
	 			+ " and o.rsaCitizen = :rsaCitizen " 
	 			+ " and o.qmrGender = :qmrGender ";
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("qmrFinYearId", qmrFinYearId);
	    parameters.put("qmrEnteredCompletedEnum", qmrEnteredCompletedEnum);
	    parameters.put("rsaCitizen", rsaCitizen);
	    parameters.put("qmrGender", qmrGender);
		return ((Long)super.getUniqueResult(hql, parameters)).intValue();
	}
	
	/* Counts By Youth */
	public Integer countByYouthIdentfied(Long qmrFinYearId, EmploymentStatusEnum employedUnEmployed, QmrEnteredCompletedEnum qmrEnteredCompletedEnum, Integer youthAmount) throws Exception {
	 	String hql = "select count(o) from QmrAETProgrammeData o where o.qmrFinYears.id = :qmrFinYearId and o.qmrEnteredCompleted = :qmrEnteredCompletedEnum and o.employedUnEmployed = :employedUnEmployed "
	 			+ " and o.ageIndicator <= :youthAmount " ;
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("qmrFinYearId", qmrFinYearId);
	    parameters.put("employedUnEmployed", employedUnEmployed);
	    parameters.put("qmrEnteredCompletedEnum", qmrEnteredCompletedEnum);
	    parameters.put("youthAmount", youthAmount);
		return ((Long)super.getUniqueResult(hql, parameters)).intValue();
	}
	
	public Integer countByYouthIdentfied(Long qmrFinYearId, QmrEnteredCompletedEnum qmrEnteredCompletedEnum, Integer youthAmount) throws Exception {
	 	String hql = "select count(o) from QmrAETProgrammeData o where o.qmrFinYears.id = :qmrFinYearId and o.qmrEnteredCompleted = :qmrEnteredCompletedEnum "
	 			+ " and o.ageIndicator <= :youthAmount " ;
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("qmrFinYearId", qmrFinYearId);
	    parameters.put("qmrEnteredCompletedEnum", qmrEnteredCompletedEnum);
	    parameters.put("youthAmount", youthAmount);
		return ((Long)super.getUniqueResult(hql, parameters)).intValue();
	}
	
	/* Counts By Youth With gender and equity */
	public Integer countByYouthIdentfiedWithGenderAndEquity(Long qmrFinYearId, EmploymentStatusEnum employedUnEmployed, QmrEnteredCompletedEnum qmrEnteredCompletedEnum, Integer youthAmount, 
			QmrGenderEnum qmrGender, QmrEquityEnum qmrEquity) throws Exception {
	 	String hql = "select count(o) from QmrAETProgrammeData o where o.qmrFinYears.id = :qmrFinYearId and o.qmrEnteredCompleted = :qmrEnteredCompletedEnum and o.employedUnEmployed = :employedUnEmployed "
	 			+ " and o.ageIndicator <= :youthAmount " 
	 			+ " and o.qmrGender = :qmrGender and o.qmrEquity = :qmrEquity" ;
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("qmrFinYearId", qmrFinYearId);
	    parameters.put("employedUnEmployed", employedUnEmployed);
	    parameters.put("qmrEnteredCompletedEnum", qmrEnteredCompletedEnum);
	    parameters.put("youthAmount", youthAmount);
	    parameters.put("qmrGender", qmrGender);
	    parameters.put("qmrEquity", qmrEquity);
		return ((Long)super.getUniqueResult(hql, parameters)).intValue();
	}
	
	public Integer countByYouthIdentfiedWithGender(Long qmrFinYearId, QmrEnteredCompletedEnum qmrEnteredCompletedEnum, Integer youthAmount, 
			QmrGenderEnum qmrGender) throws Exception {
	 	String hql = "select count(o) from QmrAETProgrammeData o where o.qmrFinYears.id = :qmrFinYearId and o.qmrEnteredCompleted = :qmrEnteredCompletedEnum "
	 			+ " and o.ageIndicator <= :youthAmount " 
	 			+ " and o.qmrGender = :qmrGender " ;
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("qmrFinYearId", qmrFinYearId);
	    parameters.put("qmrEnteredCompletedEnum", qmrEnteredCompletedEnum);
	    parameters.put("youthAmount", youthAmount);
	    parameters.put("qmrGender", qmrGender);
		return ((Long)super.getUniqueResult(hql, parameters)).intValue();
	}
	
	public Integer countTotalEntries(Long qmrFinYearId, EmploymentStatusEnum employedUnEmployed, QmrEnteredCompletedEnum qmrEnteredCompletedEnum) throws Exception {
	 	String hql = "select count(o) from QmrAETProgrammeData o where o.qmrFinYears.id = :qmrFinYearId and o.qmrEnteredCompleted = :qmrEnteredCompletedEnum and o.employedUnEmployed = :employedUnEmployed " ;
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("qmrFinYearId", qmrFinYearId);
	    parameters.put("employedUnEmployed", employedUnEmployed);
	    parameters.put("qmrEnteredCompletedEnum", qmrEnteredCompletedEnum);
		return ((Long)super.getUniqueResult(hql, parameters)).intValue();
	}
	/* 
	 * Count Reports: End 
	 */
}

