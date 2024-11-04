package haj.com.convertors.lookup;

import haj.com.entity.lookup.AetProgrammeLevel;
import haj.com.service.lookup.AetProgrammeLevelService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@FacesConverter(value = "AetProgrammeLevelConvertor")
public class AetProgrammeLevelConvertor implements Converter {
	 protected final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * Used by JSF to get a AetProgrammeLevel
 	 * @author TechFinium 
 	 * @see    AetProgrammeLevel
 	 * @return AetProgrammeLevel
 	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		} else {

			try {
				return new AetProgrammeLevelService()
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
	 * Convert AetProgrammeLevel key to String object
 	 * @author TechFinium 
 	 * @see    String
 	 * @return String
 	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) return "";
		if (arg2 instanceof String) return arg2.toString();
		return ""+((AetProgrammeLevel)arg2).getId();
	}

/*
       <p:selectOneMenu id="AetProgrammeLevelId" value="#{xxxUI.AetProgrammeLevel.xxxType}" converter="AetProgrammeLevelConvertor" style="width:95%">
         <f:selectItems value="#{AetProgrammeLevelUI.AetProgrammeLevelList}" var="rv" itemLabel="#{rv.a}" itemValue="#{rv}"/>
       </p:selectOneMenu>
       
        <h:outputLabel value="AetProgrammeLevel" for="AetProgrammeLevelID"/>
        <p:autoComplete id="AetProgrammeLevelID" value="#{AetProgrammeLevelUI.AetProgrammeLevel.municipality}" completeMethod="#{AetProgrammeLevelUI.completeAetProgrammeLevel}"
                            var="rv" itemLabel="#{rv.AetProgrammeLevelDescription}" itemValue="#{rv}" 
                            forceSelection="true" converter="AetProgrammeLevelConvertor" dropdown="true" minQueryLength="3" maxResults="10" >
                 <p:column headerText="AetProgrammeLevel" style="white-space: nowrap">#{rv.AetProgrammeLevelDescription}</p:column>
       </p:autoComplete>         
       
*/

}