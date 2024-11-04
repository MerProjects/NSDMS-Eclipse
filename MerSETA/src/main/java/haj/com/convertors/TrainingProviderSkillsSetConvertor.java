package haj.com.convertors;

import haj.com.entity.TrainingProviderSkillsSet;
import haj.com.service.TrainingProviderSkillsSetService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@FacesConverter(value = "TrainingProviderSkillsSetConvertor")
public class TrainingProviderSkillsSetConvertor implements Converter {
	 protected final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * Used by JSF to get a TrainingProviderSkillsSet
 	 * @author TechFinium 
 	 * @see    TrainingProviderSkillsSet
 	 * @return TrainingProviderSkillsSet
 	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		} else {

			try {
				return new TrainingProviderSkillsSetService()
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
	 * Convert TrainingProviderSkillsSet key to String object
 	 * @author TechFinium 
 	 * @see    String
 	 * @return String
 	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) return "";
		if (arg2 instanceof String) return arg2.toString();
		return ""+((TrainingProviderSkillsSet)arg2).getId();
	}

/*
       <p:selectOneMenu id="TrainingProviderSkillsSetId" value="#{xxxUI.TrainingProviderSkillsSet.xxxType}" converter="TrainingProviderSkillsSetConvertor" style="width:95%">
         <f:selectItems value="#{TrainingProviderSkillsSetUI.TrainingProviderSkillsSetList}" var="rv" itemLabel="#{rv.a}" itemValue="#{rv}"/>
       </p:selectOneMenu>
       
        <h:outputLabel value="TrainingProviderSkillsSet" for="TrainingProviderSkillsSetID"/>
        <p:autoComplete id="TrainingProviderSkillsSetID" value="#{TrainingProviderSkillsSetUI.TrainingProviderSkillsSet.municipality}" completeMethod="#{TrainingProviderSkillsSetUI.completeTrainingProviderSkillsSet}"
                            var="rv" itemLabel="#{rv.TrainingProviderSkillsSetDescription}" itemValue="#{rv}" 
                            forceSelection="true" converter="TrainingProviderSkillsSetConvertor" dropdown="true" minQueryLength="3" maxResults="10" >
                 <p:column headerText="TrainingProviderSkillsSet" style="white-space: nowrap">#{rv.TrainingProviderSkillsSetDescription}</p:column>
       </p:autoComplete>         
       
*/

}
