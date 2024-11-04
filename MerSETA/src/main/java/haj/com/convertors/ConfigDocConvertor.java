package haj.com.convertors;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import haj.com.entity.ConfigDoc;
import haj.com.service.ConfigDocService;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigDocConvertor.
 */
@FacesConverter(value = "ConfigDocConvertor")
public class ConfigDocConvertor implements Converter {
	 
 	/** The logger. */
 	protected final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * Used by JSF to get a ConfigDoc.
	 *
	 * @author TechFinium
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 * @param value the value
	 * @return ConfigDoc
	 * @see    ConfigDoc
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		} else {

			try {
				return new ConfigDocService()
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
	 * Convert ConfigDoc key to String object.
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
		return ""+((ConfigDoc)arg2).getId();
	}

/*
       <p:selectOneMenu id="ConfigDocId" value="#{xxxUI.ConfigDoc.xxxType}" converter="ConfigDocConvertor" style="width:95%">
         <f:selectItems value="#{ConfigDocUI.ConfigDocList}" var="rv" itemLabel="#{rv.a}" itemValue="#{rv}"/>
       </p:selectOneMenu>
       
        <h:outputLabel value="ConfigDoc" for="ConfigDocID"/>
        <p:autoComplete id="ConfigDocID" value="#{ConfigDocUI.ConfigDoc.municipality}" completeMethod="#{ConfigDocUI.completeConfigDoc}"
                            var="rv" itemLabel="#{rv.ConfigDocDescription}" itemValue="#{rv}" 
                            forceSelection="true" converter="ConfigDocConvertor" dropdown="true" minQueryLength="3" maxResults="10" >
                 <p:column headerText="ConfigDoc" style="white-space: nowrap">#{rv.ConfigDocDescription}</p:column>
       </p:autoComplete>         
       
*/

}