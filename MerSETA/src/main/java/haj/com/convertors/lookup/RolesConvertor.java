package haj.com.convertors.lookup;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import haj.com.entity.lookup.Roles;
import haj.com.service.lookup.RolesService;

// TODO: Auto-generated Javadoc
/**
 * The Class RolesConvertor.
 */
@FacesConverter(value = "RolesConvertor")
public class RolesConvertor implements Converter {
	 
 	/** The logger. */
 	protected final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * Used by JSF to get a Roles.
	 *
	 * @author TechFinium
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 * @param value the value
	 * @return Roles
	 * @see    Roles
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		} else {

			try {
				return new RolesService()
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
	 * Convert Roles key to String object.
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
		return ""+((Roles)arg2).getId();
	}

/*
       <p:selectOneMenu id="RolesId" value="#{xxxUI.Roles.xxxType}" converter="RolesConvertor" style="width:95%">
         <f:selectItems value="#{RolesUI.RolesList}" var="rv" itemLabel="#{rv.a}" itemValue="#{rv}"/>
       </p:selectOneMenu>
       
        <h:outputLabel value="Roles" for="RolesID"/>
        <p:autoComplete id="RolesID" value="#{RolesUI.Roles.municipality}" completeMethod="#{RolesUI.completeRoles}"
                            var="rv" itemLabel="#{rv.RolesDescription}" itemValue="#{rv}" 
                            forceSelection="true" converter="RolesConvertor" dropdown="true" minQueryLength="3" maxResults="10" >
                 <p:column headerText="Roles" style="white-space: nowrap">#{rv.RolesDescription}</p:column>
       </p:autoComplete>         
       
*/

}