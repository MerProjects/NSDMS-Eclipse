package haj.com.convertors.lookup;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import haj.com.entity.lookup.EnrolmentType;
import haj.com.service.lookup.EnrolmentTypeService;

// TODO: Auto-generated Javadoc
/**
 * The Class EnrolmentTypeConvertor.
 */
@FacesConverter(value = "EnrolmentTypeConvertor")
public class EnrolmentTypeConvertor implements Converter {
	 
 	/** The logger. */
 	protected final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * Used by JSF to get a EnrolmentType.
	 *
	 * @author TechFinium
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 * @param value the value
	 * @return EnrolmentType
	 * @see    EnrolmentType
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		} else {

			try {
				return new EnrolmentTypeService()
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
	 * Convert EnrolmentType key to String object.
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
		return ""+((EnrolmentType)arg2).getId();
	}

/*
       <p:selectOneMenu id="EnrolmentTypeId" value="#{xxxUI.EnrolmentType.xxxType}" converter="EnrolmentTypeConvertor" style="width:95%">
         <f:selectItems value="#{EnrolmentTypeUI.EnrolmentTypeList}" var="rv" itemLabel="#{rv.a}" itemValue="#{rv}"/>
       </p:selectOneMenu>
       
        <h:outputLabel value="EnrolmentType" for="EnrolmentTypeID"/>
        <p:autoComplete id="EnrolmentTypeID" value="#{EnrolmentTypeUI.EnrolmentType.municipality}" completeMethod="#{EnrolmentTypeUI.completeEnrolmentType}"
                            var="rv" itemLabel="#{rv.EnrolmentTypeDescription}" itemValue="#{rv}" 
                            forceSelection="true" converter="EnrolmentTypeConvertor" dropdown="true" minQueryLength="3" maxResults="10" >
                 <p:column headerText="EnrolmentType" style="white-space: nowrap">#{rv.EnrolmentTypeDescription}</p:column>
       </p:autoComplete>         
       
*/

}
