package haj.com.service.lookup;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import haj.com.dao.lookup.OrganisedLabourUnionDAO;
import haj.com.entity.lookup.OrganisedLabourUnion;
import haj.com.framework.AbstractService;

// TODO: Auto-generated Javadoc
/**
 * The Class OrganisedLabourUnionService.
 */
public class OrganisedLabourUnionService extends AbstractService {
	/** The dao. */
	private OrganisedLabourUnionDAO dao = new OrganisedLabourUnionDAO();

	/**
	 * Get all OrganisedLabourUnion.
	 *
	 * @author TechFinium
	 * @return a list of {@link haj.com.entity.OrganisedLabourUnion}
	 * @throws Exception the exception
	 * @see   OrganisedLabourUnion
	 */
	public List<OrganisedLabourUnion> allOrganisedLabourUnion() throws Exception {
	  	return dao.allOrganisedLabourUnion();
	}


	/**
	 * Create or update OrganisedLabourUnion.
	 *
	 * @author TechFinium
	 * @param entity the entity
	 * @throws Exception the exception
	 * @see     OrganisedLabourUnion
	 */
	public void create(OrganisedLabourUnion entity) throws Exception  {
	   if (entity.getId() ==null) {
		 this.dao.create(entity);
	   }
		else
		 this.dao.update(entity);
	}


	/**
	 * Update  OrganisedLabourUnion.
	 *
	 * @author TechFinium
	 * @param entity the entity
	 * @throws Exception the exception
	 * @see    OrganisedLabourUnion
	 */
	public void update(OrganisedLabourUnion entity) throws Exception  {
		this.dao.update(entity);
	}

	/**
	 * Delete  OrganisedLabourUnion.
	 *
	 * @author TechFinium
	 * @param entity the entity
	 * @throws Exception the exception
	 * @see    OrganisedLabourUnion
	 */
	public void delete(OrganisedLabourUnion entity) throws Exception  {
		this.dao.delete(entity);
	}

	/**
	 * Find object by primary key.
	 *
	 * @author TechFinium
	 * @param id the id
	 * @return a {@link haj.com.entity.OrganisedLabourUnion}
	 * @throws Exception the exception
	 * @see    OrganisedLabourUnion
	 */
	public OrganisedLabourUnion findByKey(long id) throws Exception {
       return dao.findByKey(id);
	}

	/**
	 * Find OrganisedLabourUnion by description.
	 *
	 * @author TechFinium
	 * @param desc the desc
	 * @return a list of {@link haj.com.entity.OrganisedLabourUnion}
	 * @throws Exception the exception
	 * @see    OrganisedLabourUnion
	 */
	public List<OrganisedLabourUnion> findByName(String desc) throws Exception {
		return dao.findByName(desc);
	}
	
	/**
	 * Lazy load OrganisedLabourUnion.
	 *
	 * @param first from key
	 * @param pageSize no of rows to fetch
	 * @return a list of {@link haj.com.entity.OrganisedLabourUnion}
	 * @throws Exception the exception
	 */
	public List<OrganisedLabourUnion> allOrganisedLabourUnion(int first, int pageSize) throws Exception {
		return dao.allOrganisedLabourUnion(Long.valueOf(first), pageSize);
	}
		
	
	/**
	 * Get count of OrganisedLabourUnion for lazy load.
	 *
	 * @author TechFinium
	 * @return Number of rows in the OrganisedLabourUnion
	 * @throws Exception the exception
	 */
	public Long count() throws Exception {
	       return dao.count(OrganisedLabourUnion.class);
	}
	
    /**
     * Lazy load OrganisedLabourUnion with pagination, filter, sorting.
     *
     * @author TechFinium
     * @param class1 OrganisedLabourUnion class
     * @param first the first
     * @param pageSize the page size
     * @param sortField the sort field
     * @param sortOrder the sort order
     * @param filters the filters
     * @return  a list of {@link haj.com.entity.OrganisedLabourUnion} containing data
     * @throws Exception the exception
     */	
	@SuppressWarnings("unchecked")
	public List<OrganisedLabourUnion> allOrganisedLabourUnion(Class<OrganisedLabourUnion> class1, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) throws Exception {
		return ( List<OrganisedLabourUnion>)dao.sortAndFilter(class1,first,pageSize,sortField,sortOrder,filters);
	
	}
	
    /**
     * Get count of OrganisedLabourUnion for lazy load with filters.
     *
     * @author TechFinium
     * @param entity OrganisedLabourUnion class
     * @param filters the filters
     * @return Number of rows in the OrganisedLabourUnion entity
     * @throws Exception the exception
     */	
	public int count(Class<OrganisedLabourUnion> entity, Map<String, Object> filters) throws Exception {
		return  dao.count(entity, filters);
	}
}