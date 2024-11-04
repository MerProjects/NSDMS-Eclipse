package haj.com.convertors;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import haj.com.entity.AssessorModeratorCompany;
import haj.com.service.AssessorModeratorCompanyService;

// TODO: Auto-generated Javadoc
/**
 * The Class AssessorModeratorCompanyConvertor.
 */
@FacesConverter(value = "AssessorModeratorCompanyConvertor")
public class AssessorModeratorCompanyConvertor implements Converter {
	 
 	/** The logger. */
 	protected final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * Used by JSF to get a AssessorModeratorCompany.
	 *
	 * @author TechFinium
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 * @param value the value
	 * @return AssessorModeratorCompany
	 * @see    AssessorModeratorCompany
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		} else {

			try {
				return new AssessorModeratorCompanyService()
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
	 * Convert AssessorModeratorCompany key to String object.
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
		return ""+((AssessorModeratorCompany)arg2).getId();
	}

/*
       <p:selectOneMenu id="AssessorModeratorCompanyId" value="#{xxxUI.AssessorModeratorCompany.xxxType}" converter="AssessorModeratorCompanyConvertor" style="width:95%">
         <f:selectItems value="#{AssessorModeratorCompanyUI.AssessorModeratorCompanyList}" var="rv" itemLabel="#{rv.a}" itemValue="#{rv}"/>
       </p:selectOneMenu>
       
        <h:outputLabel value="AssessorModeratorCompany" for="AssessorModeratorCompanyID"/>
        <p:autoComplete id="AssessorModeratorCompanyID" value="#{AssessorModeratorCompanyUI.AssessorModeratorCompany.municipality}" completeMethod="#{AssessorModeratorCompanyUI.completeAssessorModeratorCompany}"
                            var="rv" itemLabel="#{rv.AssessorModeratorCompanyDescription}" itemValue="#{rv}" 
                            forceSelection="true" converter="AssessorModeratorCompanyConvertor" dropdown="true" minQueryLength="3" maxResults="10" >
                 <p:column headerText="AssessorModeratorCompany" style="white-space: nowrap">#{rv.AssessorModeratorCompanyDescription}</p:column>
       </p:autoComplete>         
       
*/

}
