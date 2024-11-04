package haj.com.service.lookup;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import haj.com.dao.lookup.LearnerAchievementTypeDAO;
import haj.com.entity.lookup.LearnerAchievementType;
import haj.com.framework.AbstractService;

// TODO: Auto-generated Javadoc
/**
 * The Class LearnerAchievementTypeService.
 */
public class LearnerAchievementTypeService extends AbstractService {
	/** The dao. */
	private LearnerAchievementTypeDAO dao = new LearnerAchievementTypeDAO();

	/**
	 * Get all LearnerAchievementType.
	 *
	 * @author TechFinium
	 * @return a list of {@link haj.com.entity.LearnerAchievementType}
	 * @throws Exception the exception
	 * @see   LearnerAchievementType
	 */
	public List<LearnerAchievementType> allLearnerAchievementType() throws Exception {
	  	return dao.allLearnerAchievementType();
	}


	/**
	 * Create or update LearnerAchievementType.
	 *
	 * @author TechFinium
	 * @param entity the entity
	 * @throws Exception the exception
	 * @see     LearnerAchievementType
	 */
	public void create(LearnerAchievementType entity) throws Exception  {
		if (dao.findUniqueCode(entity) != null) throw new Exception("Code must be unique");
		   if (entity.getId() ==null) {
			 entity.setCreateDate(new java.util.Date());
			 this.dao.create(entity);
	   }
		else
		 this.dao.update(entity);
	}


	/**
	 * Update  LearnerAchievementType.
	 *
	 * @author TechFinium
	 * @param entity the entity
	 * @throws Exception the exception
	 * @see    LearnerAchievementType
	 */
	public void update(LearnerAchievementType entity) throws Exception  {
		this.dao.update(entity);
	}

	/**
	 * Delete  LearnerAchievementType.
	 *
	 * @author TechFinium
	 * @param entity the entity
	 * @throws Exception the exception
	 * @see    LearnerAchievementType
	 */
	public void delete(LearnerAchievementType entity) throws Exception  {
		this.dao.delete(entity);
	}

	/**
	 * Find object by primary key.
	 *
	 * @author TechFinium
	 * @param id the id
	 * @return a {@link haj.com.entity.LearnerAchievementType}
	 * @throws Exception the exception
	 * @see    LearnerAchievementType
	 */
	public LearnerAchievementType findByKey(long id) throws Exception {
       return dao.findByKey(id);
	}

	/**
	 * Find LearnerAchievementType by description.
	 *
	 * @author TechFinium
	 * @param desc the desc
	 * @return a list of {@link haj.com.entity.LearnerAchievementType}
	 * @throws Exception the exception
	 * @see    LearnerAchievementType
	 */
	public List<LearnerAchievementType> findByName(String desc) throws Exception {
		return dao.findByName(desc);
	}
	
	/**
	 * Lazy load LearnerAchievementType.
	 *
	 * @param first from key
	 * @param pageSize no of rows to fetch
	 * @return a list of {@link haj.com.entity.LearnerAchievementType}
	 * @throws Exception the exception
	 */
	public List<LearnerAchievementType> allLearnerAchievementType(int first, int pageSize) throws Exception {
		return dao.allLearnerAchievementType(Long.valueOf(first), pageSize);
	}
		
	
	/**
	 * Get count of LearnerAchievementType for lazy load.
	 *
	 * @author TechFinium
	 * @return Number of rows in the LearnerAchievementType
	 * @throws Exception the exception
	 */
	public Long count() throws Exception {
	       return dao.count(LearnerAchievementType.class);
	}
	
    /**
     * Lazy load LearnerAchievementType with pagination, filter, sorting.
     *
     * @author TechFinium
     * @param class1 LearnerAchievementType class
     * @param first the first
     * @param pageSize the page size
     * @param sortField the sort field
     * @param sortOrder the sort order
     * @param filters the filters
     * @return  a list of {@link haj.com.entity.LearnerAchievementType} containing data
     * @throws Exception the exception
     */	
	@SuppressWarnings("unchecked")
	public List<LearnerAchievementType> allLearnerAchievementType(Class<LearnerAchievementType> class1, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) throws Exception {
		return ( List<LearnerAchievementType>)dao.sortAndFilter(class1,first,pageSize,sortField,sortOrder,filters);
	
	}
	
    /**
     * Get count of LearnerAchievementType for lazy load with filters.
     *
     * @author TechFinium
     * @param entity LearnerAchievementType class
     * @param filters the filters
     * @return Number of rows in the LearnerAchievementType entity
     * @throws Exception the exception
     */	
	public int count(Class<LearnerAchievementType> entity, Map<String, Object> filters) throws Exception {
		return  dao.count(entity, filters);
	}
}
