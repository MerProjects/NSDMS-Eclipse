package haj.com.convertors.lookup;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import haj.com.entity.lookup.DGYear;
import haj.com.entity.lookup.JobTitle;
import haj.com.service.lookup.DGYearService;

// TODO: Auto-generated Javadoc
/**
 * The Class DGYearConvertor.
 */
@FacesConverter(value = "DGYearConvertor")
public class DGYearConvertor implements Converter {
	/** The logger. */
 	protected final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * Used by JSF to get a DGYear.
	 *
	 * @author TechFinium
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 * @param value the value
	 * @return DGYear
	 * @see    DGYear
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		} else {

			try {
				return new DGYearService()
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
	 * Convert DGYear key to String object.
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
		if (arg2 == null) return "";
		if (arg2 instanceof String) return arg2.toString();
		return ""+((DGYear)arg2).getId();
	}

/*
       <p:selectOneMenu id="DGYearId" value="#{xxxUI.DGYear.xxxType}" converter="DGYearConvertor" style="width:95%">
         <f:selectItems value="#{DGYearUI.DGYearList}" var="rv" itemLabel="#{rv.a}" itemValue="#{rv}"/>
       </p:selectOneMenu>
       
        <h:outputLabel value="DGYear" for="DGYearID"/>
        <p:autoComplete id="DGYearID" value="#{DGYearUI.DGYear.municipality}" completeMethod="#{DGYearUI.completeDGYear}"
                            var="rv" itemLabel="#{rv.DGYearDescription}" itemValue="#{rv}" 
                            forceSelection="true" converter="DGYearConvertor" dropdown="true" minQueryLength="3" maxResults="10" >
                 <p:column headerText="DGYear" style="white-space: nowrap">#{rv.DGYearDescription}</p:column>
       </p:autoComplete>         
       
*/

}
