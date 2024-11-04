package haj.com.dao.lookup;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import haj.com.framework.AbstractDAO;
import haj.com.framework.AbstractDataProvider;
import haj.com.provider.MySQLProvider;
import haj.com.entity.lookup.MeetingAgenda;

public class MeetingAgendaDAO extends AbstractDAO  {

	@Override
	public AbstractDataProvider getDataProvider() {
		return new MySQLProvider();
	}

	/**
	 * Get all MeetingAgenda
 	 * @author TechFinium 
 	 * @see    MeetingAgenda
 	 * @return a list of {@link haj.com.entity.MeetingAgenda}
 	 * @throws Exception global exception
 	 */
	@SuppressWarnings("unchecked")
	public List<MeetingAgenda> allMeetingAgenda() throws Exception {
		return (List<MeetingAgenda>)super.getList("select o from MeetingAgenda o");
	}

	/**
	 * Get all MeetingAgenda between from and noRows
 	 * @author TechFinium 
 	 * @param from the from
 	 * @param noRows the no rows
 	 * @see    MeetingAgenda
 	 * @return a list of {@link haj.com.entity.MeetingAgenda}
 	 * @throws Exception global exception
 	 */
	@SuppressWarnings("unchecked")
	public List<MeetingAgenda> allMeetingAgenda(Long from, int noRows) throws Exception {
	 	String hql = "select o from MeetingAgenda o " ;
	    Map<String, Object> parameters = new Hashtable<String, Object>();
	    
		return (List<MeetingAgenda>)super.getList(hql, parameters,from.intValue(),noRows);
	}

	/**
	 * Find object by primary key
 	 * @author TechFinium 
 	 * @param id the id
 	 * @see    MeetingAgenda
 	 * @return a {@link haj.com.entity.MeetingAgenda}
 	 * @throws Exception global exception
 	 */
	public MeetingAgenda findByKey(Long id) throws Exception {
	 	String hql = "select o from MeetingAgenda o where o.id = :id " ;
	    Map<String, Object> parameters = new Hashtable<String, Object>();
	    parameters.put("id", id);
		return (MeetingAgenda)super.getUniqueResult(hql, parameters);
	}

	/**
	 * Find MeetingAgenda by description
 	 * @author TechFinium 
 	 * @param description the description 
 	 * @see    MeetingAgenda
  	 * @return a list of {@link haj.com.entity.MeetingAgenda}
 	 * @throws Exception global exception
 	 */
	@SuppressWarnings("unchecked")
	public List<MeetingAgenda> findByName(String description) throws Exception {
	 	String hql = "select o from MeetingAgenda o where o.description like  :description order by o.desc " ;
	    Map<String, Object> parameters = new Hashtable<String, Object>();
	    parameters.put("description", ""+description.trim()+"%");
		return (List<MeetingAgenda>)super.getList(hql, parameters);
	}
}

