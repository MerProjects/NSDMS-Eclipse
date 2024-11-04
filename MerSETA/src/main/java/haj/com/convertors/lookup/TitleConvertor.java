package haj.com.convertors.lookup;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import haj.com.entity.lookup.Title;
import haj.com.service.lookup.TitleService;

// TODO: Auto-generated Javadoc
/**
 * The Class TitleConvertor.
 */
@FacesConverter(value = "TitleConvertor")
public class TitleConvertor implements Converter {
	 
 	/** The logger. */
 	protected final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * Used by JSF to get a Title.
	 *
	 * @author TechFinium
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 * @param value the value
	 * @return Title
	 * @see    Title
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		} else {

			try {
				return new TitleService()
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
	 * Convert Title key to String object.
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
		if (arg2 == null) {
			return "";
		}
		return ""+((Title)arg2).getId();
	}

/*
       <p:selectOneMenu id="TitleId" value="#{xxxUI.Title.xxxType}" converter="TitleConvertor" style="width:95%">
         <f:selectItems value="#{TitleUI.TitleList}" var="rv" itemLabel="#{rv.a}" itemValue="#{rv}"/>
       </p:selectOneMenu>
       
        <h:outputLabel value="Title" for="TitleID"/>
        <p:autoComplete id="TitleID" value="#{TitleUI.Title.municipality}" completeMethod="#{TitleUI.completeTitle}"
                            var="rv" itemLabel="#{rv.TitleDescription}" itemValue="#{rv}" 
                            forceSelection="true" converter="TitleConvertor" dropdown="true" minQueryLength="3" maxResults="10" >
                 <p:column headerText="Title" style="white-space: nowrap">#{rv.TitleDescription}</p:column>
       </p:autoComplete>         
       
*/

}