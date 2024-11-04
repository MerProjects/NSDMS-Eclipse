package haj.com.service.lookup;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import haj.com.dao.lookup.JobTitleDAO;
import haj.com.entity.lookup.JobTitle;
import haj.com.framework.AbstractService;

public class JobTitleService extends AbstractService {
	
	/** The dao. */
	private JobTitleDAO dao = new JobTitleDAO();
	
	private static JobTitleService jobTitleService;
	public static synchronized JobTitleService instance() {
		if (jobTitleService == null) {
			jobTitleService = new JobTitleService();
		}
		return jobTitleService;
	}


	
	/**
	 * Get all JobTitle
 	 * @author TechFinium 
 	 * @see   JobTitle
 	 * @return a list of {@link haj.com.entity.JobTitle}
	 * @throws Exception the exception
 	 */
	public List<JobTitle> allJobTitle() throws Exception {
	  	return dao.allJobTitle();
	}


	/**
	 * Create or update JobTitle.
	 *
	 * @author TechFinium
	 * @param entity the entity
	 * @throws Exception the exception
	 * @see     JobTitle
	 */
	public void create(JobTitle entity) throws Exception  {
	   if (entity.getId() ==null) {
		 this.dao.create(entity);
	   }
		else
		 this.dao.update(entity);
	}


	/**
	 * Update  JobTitle.
	 *
	 * @author TechFinium
	 * @param entity the entity
	 * @throws Exception the exception
	 * @see    JobTitle
	 */
	public void update(JobTitle entity) throws Exception  {
		this.dao.update(entity);
	}

	/**
	 * Delete  JobTitle.
	 *
	 * @author TechFinium
	 * @param entity the entity
	 * @throws Exception the exception
	 * @see    JobTitle
	 */
	public void delete(JobTitle entity) throws Exception  {
		this.dao.delete(entity);
	}

	/**
	 * Find object by primary key.
	 *
	 * @author TechFinium
	 * @param id the id
	 * @return a {@link haj.com.entity.JobTitle}
	 * @throws Exception the exception
	 * @see    JobTitle
	 */
	public JobTitle findByKey(long id) throws Exception {
       return dao.findByKey(id);
	}

	/**
	 * Find JobTitle by description.
	 *
	 * @author TechFinium
	 * @param desc the desc
	 * @return a list of {@link haj.com.entity.JobTitle}
	 * @throws Exception the exception
	 * @see    JobTitle
	 */
	public List<JobTitle> findByName(String desc) throws Exception {
		return dao.findByName(desc);
	}
	
	/**
	 * Lazy load JobTitle
	 * @param first from key
	 * @param pageSize no of rows to fetch
	 * @return a list of {@link haj.com.entity.JobTitle}
	 * @throws Exception the exception
	 */
	public List<JobTitle> allJobTitle(int first, int pageSize) throws Exception {
		return dao.allJobTitle(Long.valueOf(first), pageSize);
	}
		
	
	/**
	 * Get count of JobTitle for lazy load
	 * @author TechFinium 
	 * @return Number of rows in the JobTitle
	 * @throws Exception the exception
	 */
	public Long count() throws Exception {
	       return dao.count(JobTitle.class);
	}
	
    /**
     * Lazy load JobTitle with pagination, filter, sorting
     * @author TechFinium 
     * @param class1 JobTitle class
     * @param first the first
     * @param pageSize the page size
     * @param sortField the sort field
     * @param sortOrder the sort order
     * @param filters the filters
     * @return  a list of {@link haj.com.entity.JobTitle} containing data
     * @throws Exception the exception
     */	
	@SuppressWarnings("unchecked")
	public List<JobTitle> allJobTitle(Class<JobTitle> class1, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) throws Exception {
		return ( List<JobTitle>)dao.sortAndFilter(class1,first,pageSize,sortField,sortOrder,filters);
	
	}
	
    /**
     * Get count of JobTitle for lazy load with filters
     * @author TechFinium 
     * @param entity JobTitle class
     * @param filters the filters
     * @return Number of rows in the JobTitle entity
     * @throws Exception the exception     
     */	
	public int count(Class<JobTitle> entity, Map<String, Object> filters) throws Exception {
		return  dao.count(entity, filters);
	}
}
