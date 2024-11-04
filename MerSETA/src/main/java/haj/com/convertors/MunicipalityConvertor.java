package haj.com.convertors;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import haj.com.entity.Municipality;
import haj.com.service.MunicipalityService;

// TODO: Auto-generated Javadoc
/**
 * The Class MunicipalityConvertor.
 */
@FacesConverter(value = "MunicipalityConvertor")
public class MunicipalityConvertor implements Converter {
	 
 	/** The logger. */
 	protected final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * Used by JSF to get a Municipality.
	 *
	 * @author TechFinium
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 * @param value the value
	 * @return Municipality
	 * @see    Municipality
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		} else {

			try {
				return new MunicipalityService()
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
	 * Convert Municipality key to String object.
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
		return ""+((Municipality)arg2).getId();
	}

/*
       <p:selectOneMenu id="MunicipalityId" value="#{xxxUI.Municipality.xxxType}" converter="MunicipalityConvertor" style="width:95%">
         <f:selectItems value="#{MunicipalityUI.MunicipalityList}" var="rv" itemLabel="#{rv.a}" itemValue="#{rv}"/>
       </p:selectOneMenu>
       
        <h:outputLabel value="Municipality" for="MunicipalityID"/>
        <p:autoComplete id="MunicipalityID" value="#{MunicipalityUI.Municipality.municipality}" completeMethod="#{MunicipalityUI.completeMunicipality}"
                            var="rv" itemLabel="#{rv.MunicipalityDescription}" itemValue="#{rv}" 
                            forceSelection="true" converter="MunicipalityConvertor" dropdown="true" minQueryLength="3" maxResults="10" >
                 <p:column headerText="Municipality" style="white-space: nowrap">#{rv.MunicipalityDescription}</p:column>
       </p:autoComplete>         
       
*/

}