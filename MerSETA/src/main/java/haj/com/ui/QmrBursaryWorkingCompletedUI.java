package  haj.com.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import haj.com.bean.QmrQuarterCountBean;
import haj.com.bean.QmrScriptTwoBean;
import haj.com.bean.QmrSummaryBean;
import haj.com.entity.QmrBursaryData;
import haj.com.entity.QmrFinYears;
import haj.com.exceptions.ValidationException;
import haj.com.framework.AbstractUI;
import haj.com.service.QmrBursaryDataService;
import haj.com.service.QmrFinYearsService;

/**
 * The Class QmrBursaryUnemployedEnteredUI.
 */
@ManagedBean(name = "qmrBursaryWorkingCompletedUI")
@ViewScoped
public class QmrBursaryWorkingCompletedUI extends AbstractUI {
	
	/* Entity Levels */
	private Integer selectedFinYear = null;
	private QmrFinYears selectedQuarter = null;
	
	/* Service levels */
	private QmrFinYearsService qmrFinYearsService = new QmrFinYearsService();
	private QmrBursaryDataService service = new QmrBursaryDataService();
	
	/* Lazy Data Model */
	private LazyDataModel<QmrFinYears> qmrFinYearsDataModel;
	private LazyDataModel<QmrBursaryData> savedDataModel;
	
	/* Array Lists */
	private List<Integer> distinctFinYearList = null;
	//private List<QmrBursaryData> savedData = null;
	private List<QmrScriptTwoBean> savedData = null;
	private List<QmrScriptTwoBean> rawData = null;
	private List<QmrSummaryBean> qmrSummaryBeanList = null;
	private List<QmrQuarterCountBean> qmrQuarterCountBeanList = null;

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
			addErrorMessage(e.getMessage(), e);
		}
	}

	private void runInit() throws Exception {
		// identify distinct fin years
		distinctFinYearList = qmrFinYearsService.findDistinctFinYearsStart();
	}
	
	public void selectFinYear(){
		try {
			qmrQuarterCountBeanList = null;
			selectedQuarter = null;
			qmrSummaryBeanList = null;
			qmrFinYearsDataModelInfo();
			addInfoMessage("Financial Year selected");
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
		}
	}
	
	public void qmrFinYearsDataModelInfo() {
		qmrFinYearsDataModel = new LazyDataModel<QmrFinYears>() {
			private static final long serialVersionUID = 1L;
			private List<QmrFinYears> retorno = new ArrayList<>();
			@Override
			public List<QmrFinYears> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				try {
					if (sortField == null || sortField.equals(" ")) {
						sortField = "finYearQuarters";
						sortOrder = SortOrder.ASCENDING;
					}
					if (selectedFinYear != null) {
						retorno = qmrFinYearsService.allQmrFinYearsByFinYear(first, pageSize, sortField, sortOrder, filters, selectedFinYear);
						qmrFinYearsDataModel.setRowCount(qmrFinYearsService.countAllQmrFinYearsByFinYear(filters));
					} else {
						retorno = qmrFinYearsService.allQmrFinYears(QmrFinYears.class, first, pageSize, sortField, sortOrder, filters);
						qmrFinYearsDataModel.setRowCount(qmrFinYearsService.count(QmrFinYears.class, filters));
					}
				} catch (Exception e) {
					logger.fatal(e);
				}
				return retorno;
			}
			@Override
			public Object getRowKey(QmrFinYears obj) {
				return obj.getId();
			}
			@Override
			public QmrFinYears getRowData(String rowKey) {
				for (QmrFinYears obj : retorno) {
					if (obj.getId().equals(Long.valueOf(rowKey))) return obj;
				}
				return null;
			}
		};
	}
	
	public void generateSummaryReport(){
		try {
			if (selectedFinYear == null) {
				addErrorMessage("Unable to locate selected financial year. Contact support!");
			} else {
				qmrSummaryBeanList = service.generateSummaryReportBursaryEmployedCompleted(selectedFinYear);
				addInfoMessage("Summary Report Generated");
			}
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
		}
	}
	
	public void clearSummaryReport(){
		try {
			qmrSummaryBeanList = null;
			addWarningMessage("Summary Report Cleared");
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
		}
	}
	
	public void viewDataForQuarter(){
		try {
			if (selectedQuarter == null) {
				addErrorMessage("Unable to locate selected quarter. Contact support!");
			} else {
				if (getNow().before(selectedQuarter.getFromDate())) {
					selectedQuarter = null;
					addWarningMessage("Please select a different quarter. Current date is before quarter has begun.");
				} else {
					savedData = null;
					rawData = null;
					// summary report
					qmrQuarterCountBeanList = service.generateCountReportBursaryEmployedCompleted(selectedQuarter);
					// commented by vh
					  if (selectedQuarter.getDataGenerated() != null && selectedQuarter.getDataGenerated()) {
						//savedData = service.fetchSavedDataBursaryEmployedCompletedForQuarter(selectedQuarter);
						savedData = service.runBursaryEmployedCompletedForQuarterNotSaved(selectedQuarter);
					} else {
						rawData = service.runBursaryEmployedCompletedForQuarterNotSaved(selectedQuarter);
					}
					//rawData = service.runBursaryEmployedCompletedForQuarterNotSaved(selectedQuarter);
					addInfoMessage("Information populated");
				}
			}
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
		}
	}
	
	public void clearDataForQuarter(){
		try {
			qmrQuarterCountBeanList = null;
			selectedQuarter = null;
			addWarningMessage("Quarter Reports Cleared");
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
		}
	}

	public Integer getSelectedFinYear() {
		return selectedFinYear;
	}

	public void setSelectedFinYear(Integer selectedFinYear) {
		this.selectedFinYear = selectedFinYear;
	}

	public LazyDataModel<QmrFinYears> getQmrFinYearsDataModel() {
		return qmrFinYearsDataModel;
	}

	public void setQmrFinYearsDataModel(LazyDataModel<QmrFinYears> qmrFinYearsDataModel) {
		this.qmrFinYearsDataModel = qmrFinYearsDataModel;
	}

	public List<Integer> getDistinctFinYearList() {
		return distinctFinYearList;
	}

	public void setDistinctFinYearList(List<Integer> distinctFinYearList) {
		this.distinctFinYearList = distinctFinYearList;
	}

	public LazyDataModel<QmrBursaryData> getSavedDataModel() {
		return savedDataModel;
	}

	public void setSavedDataModel(LazyDataModel<QmrBursaryData> savedDataModel) {
		this.savedDataModel = savedDataModel;
	}

	public List<QmrScriptTwoBean> getRawData() {
		return rawData;
	}

	public void setRawData(List<QmrScriptTwoBean> rawData) {
		this.rawData = rawData;
	}

	public List<QmrSummaryBean> getQmrSummaryBeanList() {
		return qmrSummaryBeanList;
	}

	public void setQmrSummaryBeanList(List<QmrSummaryBean> qmrSummaryBeanList) {
		this.qmrSummaryBeanList = qmrSummaryBeanList;
	}

	public List<QmrQuarterCountBean> getQmrQuarterCountBeanList() {
		return qmrQuarterCountBeanList;
	}

	public void setQmrQuarterCountBeanList(List<QmrQuarterCountBean> qmrQuarterCountBeanList) {
		this.qmrQuarterCountBeanList = qmrQuarterCountBeanList;
	}

	public QmrFinYears getSelectedQuarter() {
		return selectedQuarter;
	}

	public void setSelectedQuarter(QmrFinYears selectedQuarter) {
		this.selectedQuarter = selectedQuarter;
	}

	public List<QmrScriptTwoBean> getSavedData() {
		return savedData;
	}

	public void setSavedData(List<QmrScriptTwoBean> savedData) {
		this.savedData = savedData;
	}
	
}
