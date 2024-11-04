package haj.com.convertors.lookup;

import haj.com.entity.lookup.DateChangeReasons;
import haj.com.service.lookup.DateChangeReasonsService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@FacesConverter(value = "DateChangeReasonsConvertor")
public class DateChangeReasonsConvertor implements Converter {
	 protected final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * Used by JSF to get a DateChangeReasons
 	 * @author TechFinium 
 	 * @see    DateChangeReasons
 	 * @return DateChangeReasons
 	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		} else {

			try {
				return new DateChangeReasonsService()
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
	 * Convert DateChangeReasons key to String object
 	 * @author TechFinium 
 	 * @see    String
 	 * @return String
 	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) return "";
		if (arg2 instanceof String) return arg2.toString();
		return ""+((DateChangeReasons)arg2).getId();
	}

/*
       <p:selectOneMenu id="DateChangeReasonsId" value="#{xxxUI.DateChangeReasons.xxxType}" converter="DateChangeReasonsConvertor" style="width:95%">
         <f:selectItems value="#{DateChangeReasonsUI.DateChangeReasonsList}" var="rv" itemLabel="#{rv.a}" itemValue="#{rv}"/>
       </p:selectOneMenu>
       
        <h:outputLabel value="DateChangeReasons" for="DateChangeReasonsID"/>
        <p:autoComplete id="DateChangeReasonsID" value="#{DateChangeReasonsUI.DateChangeReasons.municipality}" completeMethod="#{DateChangeReasonsUI.completeDateChangeReasons}"
                            var="rv" itemLabel="#{rv.DateChangeReasonsDescription}" itemValue="#{rv}" 
                            forceSelection="true" converter="DateChangeReasonsConvertor" dropdown="true" minQueryLength="3" maxResults="10" >
                 <p:column headerText="DateChangeReasons" style="white-space: nowrap">#{rv.DateChangeReasonsDescription}</p:column>
       </p:autoComplete>         
       
*/

}