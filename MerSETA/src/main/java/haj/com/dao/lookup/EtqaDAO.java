package haj.com.dao.lookup;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import haj.com.constants.HAJConstants;
import haj.com.entity.lookup.Etqa;
import haj.com.framework.AbstractDAO;
import haj.com.framework.AbstractDataProvider;
import haj.com.provider.MySQLProvider;

// TODO: Auto-generated Javadoc
/**
 * The Class EtqaDAO.
 */
public class EtqaDAO extends AbstractDAO  {

	/* (non-Javadoc)
	 * @see haj.com.framework.AbstractDAO#getDataProvider()
	 */
	@Override
	public AbstractDataProvider getDataProvider() {
		return new MySQLProvider();
	}

	/**
	 * Get all Etqa.
	 *
	 * @author TechFinium
	 * @return a list of {@link haj.com.entity.Etqa}
	 * @throws Exception global exception
	 * @see    Etqa
	 */
	@SuppressWarnings("unchecked")
	public List<Etqa> allEtqa() throws Exception {
		return (List<Etqa>)super.getList("select o from Etqa o");
	}
	
	/**
	 * Get all Etqa.
	 *
	 * @author TechFinium
	 * @return a list of {@link haj.com.entity.Etqa}
	 * @throws Exception global exception
	 * @see    Etqa
	 */
	@SuppressWarnings("unchecked")
	public List<Etqa> allEtqaRemoveMerSeta() throws Exception {
		return (List<Etqa>)super.getList("select o from Etqa o where o.code <> '"+HAJConstants.HOSTING_MERSETA_ETQA+"'");
	}
	
	@SuppressWarnings("unchecked")
	public List<Etqa> allQctoEtqa() throws Exception {
		return (List<Etqa>)super.getList("select o from Etqa o where o.code = '"+HAJConstants.QCTO_ETQA_CODE+"'");
	}
	
	

	/**
	 * Get all Etqa between from and noRows.
	 *
	 * @author TechFinium
	 * @param from the from
	 * @param noRows the no rows
	 * @return a list of {@link haj.com.entity.Etqa}
	 * @throws Exception global exception
	 * @see    Etqa
	 */
	@SuppressWarnings("unchecked")
	public List<Etqa> allEtqa(Long from, int noRows) throws Exception {
	 	String hql = "select o from Etqa o " ;
	    Map<String, Object> parameters = new Hashtable<String, Object>();
	    
		return (List<Etqa>)super.getList(hql, parameters,from.intValue(),noRows);
	}

	/**
	 * Find object by primary key.
	 *
	 * @author TechFinium
	 * @param id the id
	 * @return a {@link haj.com.entity.Etqa}
	 * @throws Exception global exception
	 * @see    Etqa
	 */
	public Etqa findByKey(Long id) throws Exception {
	 	String hql = "select o from Etqa o where o.id = :id " ;
	    Map<String, Object> parameters = new Hashtable<String, Object>();
	    parameters.put("id", id);
		return (Etqa)super.getUniqueResult(hql, parameters);
	}
	
	/**
	 * Find by code.
	 *
	 * @param code the code
	 * @return the etqa
	 * @throws Exception the exception
	 */
	public Etqa findByCode(String code) throws Exception {
	 	String hql = "select o from Etqa o where o.code = :code " ;
	    Map<String, Object> parameters = new Hashtable<String, Object>();
	    parameters.put("code", code);
		return (Etqa)super.getUniqueResult(hql, parameters);
	}
	
	/**
	 * Find Etqa by description.
	 *
	 * @author TechFinium
	 * @param description the description
	 * @return a list of {@link haj.com.entity.Etqa}
	 * @throws Exception global exception
	 * @see    Etqa
	 */
	@SuppressWarnings("unchecked")
	public List<Etqa> findByName(String description) throws Exception {
	 	String hql = "select o from Etqa o where o.description like  :description order by o.desc " ;
	    Map<String, Object> parameters = new Hashtable<String, Object>();
	    parameters.put("description", ""+description.trim()+"%");
		return (List<Etqa>)super.getList(hql, parameters);
	}
}
