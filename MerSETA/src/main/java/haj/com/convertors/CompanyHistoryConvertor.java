package haj.com.convertors;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import haj.com.entity.CompanyHistory;
import haj.com.service.CompanyHistoryService;

@FacesConverter(value = "CompanyHistoryConvertor")
public class CompanyHistoryConvertor implements Converter {
	 protected final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * Used by JSF to get a CompanyHistory
 	 * @author TechFinium 
 	 * @see    CompanyHistory
 	 * @return CompanyHistory
 	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		} else {

			try {
				return new CompanyHistoryService()
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
	 * Convert CompanyHistory key to String object
 	 * @author TechFinium 
 	 * @see    String
 	 * @return String
 	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) return "";
		if (arg2 instanceof String) return arg2.toString();
		return ""+((CompanyHistory)arg2).getId();
	}

/*
       <p:selectOneMenu id="CompanyHistoryId" value="#{xxxUI.CompanyHistory.xxxType}" converter="CompanyHistoryConvertor" style="width:95%">
         <f:selectItems value="#{CompanyHistoryUI.CompanyHistoryList}" var="rv" itemLabel="#{rv.a}" itemValue="#{rv}"/>
       </p:selectOneMenu>
       
        <h:outputLabel value="CompanyHistory" for="CompanyHistoryID"/>
        <p:autoComplete id="CompanyHistoryID" value="#{CompanyHistoryUI.CompanyHistory.municipality}" completeMethod="#{CompanyHistoryUI.completeCompanyHistory}"
                            var="rv" itemLabel="#{rv.CompanyHistoryDescription}" itemValue="#{rv}" 
                            forceSelection="true" converter="CompanyHistoryConvertor" dropdown="true" minQueryLength="3" maxResults="10" >
                 <p:column headerText="CompanyHistory" style="white-space: nowrap">#{rv.CompanyHistoryDescription}</p:column>
       </p:autoComplete>         
       
*/

}
