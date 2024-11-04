package haj.com.entity.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import haj.com.entity.StrategicPriorities;
import haj.com.service.StrategicPrioritiesService;

public class StrategicPrioritiesDatamodel extends LazyDataModel<StrategicPriorities> {

	private static final long serialVersionUID = 1L;
	private List<StrategicPriorities> retorno = new ArrayList<StrategicPriorities>();
	private StrategicPrioritiesService service = new StrategicPrioritiesService();

	public StrategicPrioritiesDatamodel() {
		super();
	}

	@Override
	public List<StrategicPriorities> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		try {
			retorno = service.allStrategicPriorities(StrategicPriorities.class, first, pageSize, sortField, sortOrder, filters);
			setRowCount(service.count(StrategicPriorities.class, filters));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	@Override
	public Object getRowKey(StrategicPriorities obj) {
		return obj.getId();
	}

	@Override
	public StrategicPriorities getRowData(String rowKey) {
		for (StrategicPriorities obj : retorno) {
			if (obj.getId().equals(Long.valueOf(rowKey))) return obj;
		}
		return null;
	}

}