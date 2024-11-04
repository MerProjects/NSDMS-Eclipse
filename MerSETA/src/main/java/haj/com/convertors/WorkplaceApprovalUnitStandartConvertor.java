package haj.com.convertors;

import haj.com.entity.WorkplaceApprovalUnitStandart;
import haj.com.service.WorkplaceApprovalUnitStandartService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@FacesConverter(value = "WorkplaceApprovalUnitStandartConvertor")
public class WorkplaceApprovalUnitStandartConvertor implements Converter {
	 protected final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * Used by JSF to get a WorkplaceApprovalUnitStandart
 	 * @author TechFinium 
 	 * @see    WorkplaceApprovalUnitStandart
 	 * @return WorkplaceApprovalUnitStandart
 	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		} else {

			try {
				return new WorkplaceApprovalUnitStandartService()
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
	 * Convert WorkplaceApprovalUnitStandart key to String object
 	 * @author TechFinium 
 	 * @see    String
 	 * @return String
 	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) return "";
		if (arg2 instanceof String) return arg2.toString();
		return ""+((WorkplaceApprovalUnitStandart)arg2).getId();
	}

/*
       <p:selectOneMenu id="WorkplaceApprovalUnitStandartId" value="#{xxxUI.WorkplaceApprovalUnitStandart.xxxType}" converter="WorkplaceApprovalUnitStandartConvertor" style="width:95%">
         <f:selectItems value="#{WorkplaceApprovalUnitStandartUI.WorkplaceApprovalUnitStandartList}" var="rv" itemLabel="#{rv.a}" itemValue="#{rv}"/>
       </p:selectOneMenu>
       
        <h:outputLabel value="WorkplaceApprovalUnitStandart" for="WorkplaceApprovalUnitStandartID"/>
        <p:autoComplete id="WorkplaceApprovalUnitStandartID" value="#{WorkplaceApprovalUnitStandartUI.WorkplaceApprovalUnitStandart.municipality}" completeMethod="#{WorkplaceApprovalUnitStandartUI.completeWorkplaceApprovalUnitStandart}"
                            var="rv" itemLabel="#{rv.WorkplaceApprovalUnitStandartDescription}" itemValue="#{rv}" 
                            forceSelection="true" converter="WorkplaceApprovalUnitStandartConvertor" dropdown="true" minQueryLength="3" maxResults="10" >
                 <p:column headerText="WorkplaceApprovalUnitStandart" style="white-space: nowrap">#{rv.WorkplaceApprovalUnitStandartDescription}</p:column>
       </p:autoComplete>         
       
*/

}
