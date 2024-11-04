package haj.com.convertors.lookup;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import haj.com.entity.lookup.NextRefreshYear;
import haj.com.service.lookup.NextRefreshYearService;

@FacesConverter(value = "NextRefreshYearConvertor")
public class NextRefreshYearConvertor implements Converter {
	 protected final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * Used by JSF to get a NextRefreshYear
 	 * @author TechFinium 
 	 * @see    NextRefreshYear
 	 * @return NextRefreshYear
 	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		} else {

			try {
				return new NextRefreshYearService()
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
	 * Convert NextRefreshYear key to String object
 	 * @author TechFinium 
 	 * @see    String
 	 * @return String
 	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) return "";
		if (arg2 instanceof String) return arg2.toString();
		return ""+((NextRefreshYear)arg2).getId();
	}

/*
       <p:selectOneMenu id="NextRefreshYearId" value="#{xxxUI.NextRefreshYear.xxxType}" converter="NextRefreshYearConvertor" style="width:95%">
         <f:selectItems value="#{NextRefreshYearUI.NextRefreshYearList}" var="rv" itemLabel="#{rv.a}" itemValue="#{rv}"/>
       </p:selectOneMenu>
       
        <h:outputLabel value="NextRefreshYear" for="NextRefreshYearID"/>
        <p:autoComplete id="NextRefreshYearID" value="#{NextRefreshYearUI.NextRefreshYear.municipality}" completeMethod="#{NextRefreshYearUI.completeNextRefreshYear}"
                            var="rv" itemLabel="#{rv.NextRefreshYearDescription}" itemValue="#{rv}" 
                            forceSelection="true" converter="NextRefreshYearConvertor" dropdown="true" minQueryLength="3" maxResults="10" >
                 <p:column headerText="NextRefreshYear" style="white-space: nowrap">#{rv.NextRefreshYearDescription}</p:column>
       </p:autoComplete>         
       
*/

}