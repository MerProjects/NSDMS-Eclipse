package haj.com.convertors;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import haj.com.sars.SarsLeviesPaid;
import haj.com.service.SarsLeviesPaidService;

// TODO: Auto-generated Javadoc
/**
 * The Class SarsLeviesPaidConvertor.
 */
@FacesConverter(value = "SarsLeviesPaidConvertor")
public class SarsLeviesPaidConvertor implements Converter {
	 
 	/** The logger. */
 	protected final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * Used by JSF to get a SarsLeviesPaid.
	 *
	 * @author TechFinium
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 * @param value the value
	 * @return SarsLeviesPaid
	 * @see    SarsLeviesPaid
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		} else {

			try {
				return new SarsLeviesPaidService()
						.findByKey(Integer.valueOf(value));
			} catch (NumberFormatException e) {
				logger.fatal(e);
			} catch (Exception e) {
				logger.fatal(e);
			}

		}
	    return null;
	}


	/**
	 * Convert SarsLeviesPaid key to String object.
	 *
	 * @author TechFinium
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 * @param arg2 the arg 2
	 * @return String
	 * @see    String
	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return ""+((SarsLeviesPaid)arg2).getId();
	}

/*
       <p:selectOneMenu id="SarsLeviesPaidId" value="#{xxxUI.SarsLeviesPaid.xxxType}" converter="SarsLeviesPaidConvertor" style="width:95%">
         <f:selectItems value="#{SarsLeviesPaidUI.SarsLeviesPaidList}" var="rv" itemLabel="#{rv.a}" itemValue="#{rv}"/>
       </p:selectOneMenu>
       
        <h:outputLabel value="SarsLeviesPaid" for="SarsLeviesPaidID"/>
        <p:autoComplete id="SarsLeviesPaidID" value="#{SarsLeviesPaidUI.SarsLeviesPaid.municipality}" completeMethod="#{SarsLeviesPaidUI.completeSarsLeviesPaid}"
                            var="rv" itemLabel="#{rv.SarsLeviesPaidDescription}" itemValue="#{rv}" 
                            forceSelection="true" converter="SarsLeviesPaidConvertor" dropdown="true" minQueryLength="3" maxResults="10" >
                 <p:column headerText="SarsLeviesPaid" style="white-space: nowrap">#{rv.SarsLeviesPaidDescription}</p:column>
       </p:autoComplete>         
       
*/

}