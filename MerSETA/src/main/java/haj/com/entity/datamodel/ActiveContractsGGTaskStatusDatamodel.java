package haj.com.entity.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import haj.com.entity.ActiveContracts;
import haj.com.service.ActiveContractsService;
import haj.com.service.lookup.RegionTownService;

public class ActiveContractsGGTaskStatusDatamodel extends LazyDataModel<ActiveContracts> {

	private static final long serialVersionUID = 1L;
	private List<ActiveContracts> retorno = new ArrayList<ActiveContracts>();
	private ActiveContractsService service = new ActiveContractsService();

	public ActiveContractsGGTaskStatusDatamodel() {
		super();
	}

	@Override
	public List<ActiveContracts> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		try {
			//retorno = service.allActiveContracts(ActiveContracts.class, first, pageSize, sortField, sortOrder, filters);
			retorno = service.allActiveContractsDGStatusSearch(ActiveContracts.class, first, pageSize, sortField, sortOrder, filters);
			for (ActiveContracts activeContracts : retorno) {
				activeContracts.getDgAllocationParent().getWsp().getCompany().setRegionTown(RegionTownService.instance().findByTown(activeContracts.getDgAllocationParent().getWsp().getCompany().getResidentialAddress().getTown()));
			}
			//setRowCount(service.count(ActiveContracts.class, filters));
			setRowCount(service.countDGStatusSearch(ActiveContracts.class, filters));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	@Override
	public Object getRowKey(ActiveContracts obj) {
		return obj.getId();
	}

	@Override
	public ActiveContracts getRowData(String rowKey) {
		for (ActiveContracts obj : retorno) {
			if (obj.getId().equals(Long.valueOf(rowKey))) return obj;
		}
		return null;
	}

}