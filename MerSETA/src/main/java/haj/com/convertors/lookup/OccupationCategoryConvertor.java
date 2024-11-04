package haj.com.convertors.lookup;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import haj.com.entity.lookup.OccupationCategory;
import haj.com.service.lookup.OccupationCategoryService;

// TODO: Auto-generated Javadoc
/**
 * The Class OccupationCategoryConvertor.
 */
@FacesConverter(value = "OccupationCategoryConvertor")
public class OccupationCategoryConvertor implements Converter {
	 
 	/** The logger. */
 	protected final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * Used by JSF to get a OccupationCategory.
	 *
	 * @author TechFinium
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 * @param value the value
	 * @return OccupationCategory
	 * @see    OccupationCategory
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		} else {

			try {
				return new OccupationCategoryService()
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
	 * Convert OccupationCategory key to String object.
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
		if (arg2 == null)
			return "";
		if (arg2 instanceof String)
			return arg2.toString();
		return ""+((OccupationCategory)arg2).getId();
	}

/*
       <p:selectOneMenu id="OccupationCategoryId" value="#{xxxUI.OccupationCategory.xxxType}" converter="OccupationCategoryConvertor" style="width:95%">
         <f:selectItems value="#{OccupationCategoryUI.OccupationCategoryList}" var="rv" itemLabel="#{rv.a}" itemValue="#{rv}"/>
       </p:selectOneMenu>
       
        <h:outputLabel value="OccupationCategory" for="OccupationCategoryID"/>
        <p:autoComplete id="OccupationCategoryID" value="#{OccupationCategoryUI.OccupationCategory.municipality}" completeMethod="#{OccupationCategoryUI.completeOccupationCategory}"
                            var="rv" itemLabel="#{rv.OccupationCategoryDescription}" itemValue="#{rv}" 
                            forceSelection="true" converter="OccupationCategoryConvertor" dropdown="true" minQueryLength="3" maxResults="10" >
                 <p:column headerText="OccupationCategory" style="white-space: nowrap">#{rv.OccupationCategoryDescription}</p:column>
       </p:autoComplete>         
       
*/

}
