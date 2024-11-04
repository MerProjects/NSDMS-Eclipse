package haj.com.ui.lookup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import haj.com.bean.ColumnBeans;
import haj.com.bean.LegacyReportingBean;
import haj.com.entity.lookup.LegacyAssessorQualification;
import haj.com.entity.lookup.LegacyAssessorSkillsProgramme;
import haj.com.exceptions.ValidationException;
import haj.com.framework.AbstractUI;
import haj.com.service.lookup.LegacyAssessorSkillsProgrammeService;
import haj.com.service.lookup.ReportingService;
import haj.com.utils.CSVUtil;

@ManagedBean(name = "legacyassessorskillsprogrammeUI")
@ViewScoped
public class LegacyAssessorSkillsProgrammeUI extends AbstractUI {

	private LegacyAssessorSkillsProgrammeService service = new LegacyAssessorSkillsProgrammeService();
	private List<LegacyAssessorSkillsProgramme> legacyassessorskillsprogrammeList = null;
	private List<LegacyAssessorSkillsProgramme> legacyassessorskillsprogrammefilteredList = null;
	private LegacyAssessorSkillsProgramme legacyassessorskillsprogramme = null;
	private LazyDataModel<LegacyAssessorSkillsProgramme> dataModel;
	private CSVUtil csvUtil = new CSVUtil();
	private List<String> csvTypeSelectionList = new ArrayList<>();
	private String csvTypeSelection;
	private boolean displayDownload = false;
	//reporting
	private ReportingService reportingService = new ReportingService();
	private List<LegacyReportingBean> legacyReportingBeans = new ArrayList<>();
	private Map<String, List<Object>> reportingMap;
	private Map<String, ColumnBeans> columnMap; 

	@PostConstruct
	public void init() {
		try {
			runInit();
		} catch (ValidationException e) {
			addErrorMessage(getEntryLanguage(e.getMessage()));
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
		}
	}

	/**
	 * Initialize method to get all LegacyAssessorSkillsProgramme and prepare a for
	 * a create of a new LegacyAssessorSkillsProgramme
	 * 
	 * @author TechFinium
	 * @see LegacyAssessorSkillsProgramme
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	private void runInit() throws Exception {
		prepareNew();
		legacyassessorskillsprogrammeInfo();
		countAllEntries();
//		reportingMap = reportingService.runReports(LegacyAssessorSkillsProgramme.class, true);
//		legacyReportingBeans = (List<LegacyReportingBean>) (List<?>)reportingService.extractValues(reportingMap, "count"); 
//		columnMap = reportingService.convertToColumns(reportingMap);
	}

	/**
	 * Get all LegacyAssessorSkillsProgramme for data table
	 * 
	 * @author TechFinium
	 * @see LegacyAssessorSkillsProgramme
	 */
	public void legacyassessorskillsprogrammeInfo() {

		dataModel = new LazyDataModel<LegacyAssessorSkillsProgramme>() {

			private static final long serialVersionUID = 1L;
			private List<LegacyAssessorSkillsProgramme> retorno = new ArrayList<LegacyAssessorSkillsProgramme>();

			@Override
			public List<LegacyAssessorSkillsProgramme> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {

				try {
					retorno = service.allLegacyAssessorSkillsProgramme(LegacyAssessorSkillsProgramme.class, first,
							pageSize, sortField, sortOrder, filters);
					dataModel.setRowCount(service.count(LegacyAssessorSkillsProgramme.class, filters));
				} catch (Exception e) {
					logger.fatal(e);
				}
				return retorno;
			}

			@Override
			public Object getRowKey(LegacyAssessorSkillsProgramme obj) {
				return obj.getId();
			}

			@Override
			public LegacyAssessorSkillsProgramme getRowData(String rowKey) {
				for (LegacyAssessorSkillsProgramme obj : retorno) {
					if (obj.getId().equals(Long.valueOf(rowKey)))
						return obj;
				}
				return null;
			}

		};

	}

	/**
	 * Insert LegacyAssessorSkillsProgramme into database
	 * 
	 * @author TechFinium
	 * @see LegacyAssessorSkillsProgramme
	 */
	public void legacyassessorskillsprogrammeInsert() {
		try {
			service.create(this.legacyassessorskillsprogramme);
			prepareNew();
			addInfoMessage(super.getEntryLanguage("update.successful"));
			legacyassessorskillsprogrammeInfo();
		} catch (ValidationException e) {
			addErrorMessage(getEntryLanguage(e.getMessage()));
		} catch (Exception e) {
			super.addCallBackParm("validationFailed", true);
			addErrorMessage(e.getMessage(), e);
		}
	}

	/**
	 * Update LegacyAssessorSkillsProgramme in database
	 * 
	 * @author TechFinium
	 * @see LegacyAssessorSkillsProgramme
	 */
	public void legacyassessorskillsprogrammeUpdate() {
		try {
			service.update(this.legacyassessorskillsprogramme);
			addInfoMessage(super.getEntryLanguage("update.successful"));
			legacyassessorskillsprogrammeInfo();
		} catch (ValidationException e) {
			addErrorMessage(getEntryLanguage(e.getMessage()));
		} catch (Exception e) {
			super.addCallBackParm("validationFailed", true);
			addErrorMessage(e.getMessage(), e);
		}
	}

	/**
	 * Delete LegacyAssessorSkillsProgramme from database
	 * 
	 * @author TechFinium
	 * @see LegacyAssessorSkillsProgramme
	 */
	public void legacyassessorskillsprogrammeDelete() {
		try {
			service.delete(this.legacyassessorskillsprogramme);
			prepareNew();
			legacyassessorskillsprogrammeInfo();
			super.addWarningMessage(super.getEntryLanguage("row.deleted"));
		} catch (ValidationException e) {
			addErrorMessage(getEntryLanguage(e.getMessage()));
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
		}
	}

	/**
	 * Create new instance of LegacyAssessorSkillsProgramme
	 * 
	 * @author TechFinium
	 * @see LegacyAssessorSkillsProgramme
	 */
	public void prepareNew() {
		legacyassessorskillsprogramme = new LegacyAssessorSkillsProgramme();
	}

	/*
	 * public List<SelectItem> getLegacyAssessorSkillsProgrammeDD() {
	 * List<SelectItem> l =new ArrayList<SelectItem>(); l.add(new
	 * SelectItem(Long.valueOf(-1L),"-------------Select-------------"));
	 * legacyassessorskillsprogrammeInfo(); for (LegacyAssessorSkillsProgramme ug :
	 * legacyassessorskillsprogrammeList) { // l.add(new
	 * SelectItem(ug.getUserGroupId(), ug.getUserGroupDesc())); } return l; }
	 */

	/**
	 * Complete.
	 *
	 * @param desc the desc
	 * @return the list
	 */
	public List<LegacyAssessorSkillsProgramme> complete(String desc) {
		List<LegacyAssessorSkillsProgramme> l = null;
		try {
			l = service.findByName(desc);
		} catch (ValidationException e) {
			addErrorMessage(getEntryLanguage(e.getMessage()));
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
		}
		return l;
	}

	public List<LegacyAssessorSkillsProgramme> getLegacyAssessorSkillsProgrammeList() {
		return legacyassessorskillsprogrammeList;
	}

	public void setLegacyAssessorSkillsProgrammeList(
			List<LegacyAssessorSkillsProgramme> legacyassessorskillsprogrammeList) {
		this.legacyassessorskillsprogrammeList = legacyassessorskillsprogrammeList;
	}

	public LegacyAssessorSkillsProgramme getLegacyassessorskillsprogramme() {
		return legacyassessorskillsprogramme;
	}

	public void setLegacyassessorskillsprogramme(LegacyAssessorSkillsProgramme legacyassessorskillsprogramme) {
		this.legacyassessorskillsprogramme = legacyassessorskillsprogramme;
	}

	public List<LegacyAssessorSkillsProgramme> getLegacyAssessorSkillsProgrammefilteredList() {
		return legacyassessorskillsprogrammefilteredList;
	}

	/**
	 * Prepare auto complete data
	 * 
	 * @author TechFinium
	 * @param legacyassessorskillsprogrammefilteredList the new
	 *                                                  legacyassessorskillsprogrammefilteredList
	 *                                                  list
	 * @see LegacyAssessorSkillsProgramme
	 */
	public void setLegacyAssessorSkillsProgrammefilteredList(
			List<LegacyAssessorSkillsProgramme> legacyassessorskillsprogrammefilteredList) {
		this.legacyassessorskillsprogrammefilteredList = legacyassessorskillsprogrammefilteredList;
	}

	public LazyDataModel<LegacyAssessorSkillsProgramme> getDataModel() {
		return dataModel;
	}

	public void setDataModel(LazyDataModel<LegacyAssessorSkillsProgramme> dataModel) {
		this.dataModel = dataModel;
	}

	public void countAllEntries() {
		try {
			displayDownload = false;
			int entriesCount = service.countAllResults();
			if (entriesCount < 65000) {
				displayDownload = true;
			}
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
		}
	}

	public void prepTypeSelection() {
		try {
			csvTypeSelectionList = new ArrayList<>();
			csvTypeSelectionList.add(",");
			csvTypeSelectionList.add(";");
			csvTypeSelectionList.add("|");
			csvTypeSelectionList.add("-");
			csvTypeSelection = ",";
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			if (csvTypeSelection == null || csvTypeSelection.isEmpty()) {
				csvTypeSelection = ",";
			}
			logger.info("Starting file upload");
			List<LegacyAssessorSkillsProgramme> csvDataList = csvUtil.getObjects(LegacyAssessorSkillsProgramme.class,
					event.getFile().getInputstream(), csvTypeSelection);
			logger.info("Finished parsing upload. number of entries: " + csvDataList.size());
			service.saveCsvUploadData(csvDataList);
			addInfoMessage("Upload Complete");
			logger.info("Finished persisting upload");
			legacyassessorskillsprogrammeInfo();
			countAllEntries();
		} catch (ConstraintViolationException e) {
			addErrorMessage("ConstraintViolationException");
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
		} finally {
			csvUtil = new CSVUtil();
		}
	}

	public void clearUploadedEntries() {
		try {
			service.deleteUploadedEntries();
			addInfoMessage("data cleared");
			legacyassessorskillsprogrammeInfo();
			countAllEntries();
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
		}
	}

	public void runValidiations() {
		try {
			service.validiationRun();
			addInfoMessage("Action Complete");
			legacyassessorskillsprogrammeInfo();
		} catch (Exception e) {
			addErrorMessage(e.getMessage(), e);
		}
	}

	public List<String> getCsvTypeSelectionList() {
		return csvTypeSelectionList;
	}

	public void setCsvTypeSelectionList(List<String> csvTypeSelectionList) {
		this.csvTypeSelectionList = csvTypeSelectionList;
	}

	public String getCsvTypeSelection() {
		return csvTypeSelection;
	}

	public void setCsvTypeSelection(String csvTypeSelection) {
		this.csvTypeSelection = csvTypeSelection;
	}

	public List<LegacyAssessorSkillsProgramme> getLegacyassessorskillsprogrammeList() {
		return legacyassessorskillsprogrammeList;
	}

	public void setLegacyassessorskillsprogrammeList(
			List<LegacyAssessorSkillsProgramme> legacyassessorskillsprogrammeList) {
		this.legacyassessorskillsprogrammeList = legacyassessorskillsprogrammeList;
	}

	public List<LegacyAssessorSkillsProgramme> getLegacyassessorskillsprogrammefilteredList() {
		return legacyassessorskillsprogrammefilteredList;
	}

	public void setLegacyassessorskillsprogrammefilteredList(
			List<LegacyAssessorSkillsProgramme> legacyassessorskillsprogrammefilteredList) {
		this.legacyassessorskillsprogrammefilteredList = legacyassessorskillsprogrammefilteredList;
	}

	public CSVUtil getCsvUtil() {
		return csvUtil;
	}

	public void setCsvUtil(CSVUtil csvUtil) {
		this.csvUtil = csvUtil;
	}

	public boolean isDisplayDownload() {
		return displayDownload;
	}

	public void setDisplayDownload(boolean displayDownload) {
		this.displayDownload = displayDownload;
	}

	public List<LegacyReportingBean> getLegacyReportingBeans() {
		return legacyReportingBeans;
	}

	public void setLegacyReportingBeans(List<LegacyReportingBean> legacyReportingBeans) {
		this.legacyReportingBeans = legacyReportingBeans;
	}

	public Map<String, List<Object>> getReportingMap() {
		return reportingMap;
	}

	public void setReportingMap(Map<String, List<Object>> reportingMap) {
		this.reportingMap = reportingMap;
	}

	public Map<String, ColumnBeans> getColumnMap() {
		return columnMap;
	}

	public void setColumnMap(Map<String, ColumnBeans> columnMap) {
		this.columnMap = columnMap;
	}

}
