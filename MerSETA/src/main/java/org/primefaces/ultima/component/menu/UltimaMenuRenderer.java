package org.primefaces.ultima.component.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.component.api.AjaxSource;
import org.primefaces.component.api.UIOutcomeTarget;
import org.primefaces.component.menu.AbstractMenu;
import org.primefaces.component.menu.BaseMenuRenderer;
import org.primefaces.component.menuitem.UIMenuItem;
import org.primefaces.component.submenu.UISubmenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.Separator;
import org.primefaces.model.menu.Submenu;
import org.primefaces.util.ComponentUtils;
import org.primefaces.util.WidgetBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class UltimaMenuRenderer.
 */
public class UltimaMenuRenderer extends BaseMenuRenderer {

    /* (non-Javadoc)
     * @see org.primefaces.component.menu.BaseMenuRenderer#encodeMarkup(javax.faces.context.FacesContext, org.primefaces.component.menu.AbstractMenu)
     */
    @Override
    protected void encodeMarkup(FacesContext context, AbstractMenu abstractMenu) throws IOException {
        UltimaMenu menu = (UltimaMenu) abstractMenu;
        ResponseWriter writer = context.getResponseWriter();
        String style = menu.getStyle();
        String styleClass = menu.getStyleClass();    
        styleClass = (styleClass == null) ? "ultima-menu clearfix" : "ultima-menu clearfix" + styleClass;
        
        writer.startElement("ul", menu);  
        writer.writeAttribute("id", menu.getClientId(context), "id");
        writer.writeAttribute("class", styleClass, "styleClass");
        if(style != null) writer.writeAttribute("style", style, "style");
        
        if(menu.getElementsCount() > 0) {
            encodeElements(context, menu, menu.getElements());
        }
        
        writer.endElement("ul");
    }
    
    /**
     * Encode elements.
     *
     * @param context the context
     * @param menu the menu
     * @param elements the elements
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void encodeElements(FacesContext context, AbstractMenu menu, List<MenuElement> elements) throws IOException {
        int size = elements.size();
        
        for (int i = 0; i < size; i++) {
            encodeElement(context, menu, elements.get(i));
        }
    }
    
    /**
     * Encode element.
     *
     * @param context the context
     * @param menu the menu
     * @param element the element
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void encodeElement(FacesContext context, AbstractMenu menu, MenuElement element) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        
        if(element.isRendered()) {
            if(element instanceof MenuItem) {
                MenuItem menuItem = (MenuItem) element;
                String menuItemClientId = (menuItem instanceof UIComponent) ? menuItem.getClientId() : menu.getClientId(context) + "_" + menuItem.getClientId();
                String containerStyle = menuItem.getContainerStyle();
                String containerStyleClass = menuItem.getContainerStyleClass();

                writer.startElement("li", null);
                writer.writeAttribute("id", menuItemClientId, null);
                writer.writeAttribute("role", "menuitem", null);
                
                if(containerStyle != null) writer.writeAttribute("style", containerStyle, null);
                if(containerStyleClass != null) writer.writeAttribute("class", containerStyleClass, null);

                encodeMenuItem(context, menu, menuItem);

                writer.endElement("li");
            }
            else if(element instanceof Submenu) {
                Submenu submenu = (Submenu) element;
                String submenuClientId = (submenu instanceof UIComponent) ? ((UIComponent) submenu).getClientId() : menu.getClientId(context) + "_" + submenu.getId();
                String style = submenu.getStyle();
                String styleClass = submenu.getStyleClass();
                boolean expanded = submenu.isExpanded();
                styleClass = styleClass != null ? styleClass : "";
                styleClass = expanded ? "active-menuitem " + styleClass : styleClass;
                
                writer.startElement("li", null);
                writer.writeAttribute("id", submenuClientId, null);
                writer.writeAttribute("role", "menuitem", null);
                
                if(style != null) writer.writeAttribute("style", style, null);
                if(styleClass != null) writer.writeAttribute("class", styleClass, null);

                encodeSubmenu(context, menu, submenu);

                writer.endElement("li");
            }
            else if(element instanceof Separator) {
                encodeSeparator(context, (Separator) element);
            }
        }
    }
    
    /**
     * Encode submenu.
     *
     * @param context the context
     * @param menu the menu
     * @param submenu the submenu
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void encodeSubmenu(FacesContext context, AbstractMenu menu, Submenu submenu) throws IOException{
		ResponseWriter writer = context.getResponseWriter();
        String icon = submenu.getIcon();
        String label = submenu.getLabel();
        int childrenElementsCount = submenu.getElementsCount();
        boolean expanded = submenu.isExpanded();
        
        writer.startElement("a", null);
        writer.writeAttribute("href", "#", null);
        writer.writeAttribute("class", "ripplelink", null);
        
        encodeItemIcon(context, icon);

        if(label != null) {          
            writer.startElement("span", null);
            writer.writeText(label, null);
            writer.endElement("span");
            
            writer.startElement("span", null);
            writer.writeAttribute("class", "ink animate", null);
            writer.endElement("span");
            
            if(submenu instanceof UISubmenu) {
                encodeBadge(context, ((UISubmenu) submenu).getAttributes().get("badge"));
            }
            
            encodeToggleIcon(context, submenu, childrenElementsCount); 
        }
               
        writer.endElement("a");
        
        if(label != null && ((UIComponent)submenu).getParent() instanceof UltimaMenu) {
            encodeTooltip(context, label);
        }

        //submenus and menuitems
        if(childrenElementsCount > 0) {
            writer.startElement("ul", null);
            writer.writeAttribute("role", "menu", null);
            if(expanded) writer.writeAttribute("style", "display:block", null);
			encodeElements(context, menu, submenu.getElements());
			writer.endElement("ul");
        }
	}
    
    /**
     * Encode item icon.
     *
     * @param context the context
     * @param icon the icon
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void encodeItemIcon(FacesContext context, String icon) throws IOException {
        if(icon != null) {
            ResponseWriter writer = context.getResponseWriter();

            writer.startElement("i", null);
            
            if(icon.contains("fa fa-")) {
                writer.writeAttribute("class", icon, null);
            }
            else {
                writer.writeAttribute("class", "material-icons", null);
                writer.writeText(icon, null);
            }
            
            writer.endElement("i");
        }
    }
    
    /**
     * Encode toggle icon.
     *
     * @param context the context
     * @param submenu the submenu
     * @param childrenElementsCount the children elements count
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void encodeToggleIcon(FacesContext context, Submenu submenu, int childrenElementsCount) throws IOException {
        if(childrenElementsCount > 0) {
            ResponseWriter writer = context.getResponseWriter();
            
            writer.startElement("i", null);
            writer.writeAttribute("class", "material-icons", null);
            writer.write("&#xE313;");
            writer.endElement("i");
        }
    }
    
    /**
     * Encode badge.
     *
     * @param context the context
     * @param value the value
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void encodeBadge(FacesContext context, Object value) throws IOException {
        if(value != null) {
            ResponseWriter writer = context.getResponseWriter();

            writer.startElement("span", null);
            writer.writeAttribute("class", "menuitem-badge", null);
            writer.writeText(value.toString(), null);
            writer.endElement("span");
        }
    }

    /* (non-Javadoc)
     * @see org.primefaces.component.menu.BaseMenuRenderer#encodeSeparator(javax.faces.context.FacesContext, org.primefaces.model.menu.Separator)
     */
    @Override
    protected void encodeSeparator(FacesContext context, Separator separator) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String style = separator.getStyle();
        String styleClass = separator.getStyleClass();
        styleClass = styleClass == null ? "Separator" : "Separator " + styleClass;

        //title
        writer.startElement("li", null);
        writer.writeAttribute("class", styleClass, null);
        if(style != null) {
            writer.writeAttribute("style", style, null);
        }
        
        writer.endElement("li");
    }
    
    /* (non-Javadoc)
     * @see org.primefaces.component.menu.BaseMenuRenderer#encodeMenuItem(javax.faces.context.FacesContext, org.primefaces.component.menu.AbstractMenu, org.primefaces.model.menu.MenuItem)
     */
    @Override
    protected void encodeMenuItem(FacesContext context, AbstractMenu menu, MenuItem menuitem) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String title = menuitem.getTitle();
        boolean disabled = menuitem.isDisabled();
        Object value = menuitem.getValue();
        String style = menuitem.getStyle();
        String styleClass = menuitem.getStyleClass();

        writer.startElement("a", null);
        if(title != null) writer.writeAttribute("title", title, null);
        if(style != null) writer.writeAttribute("style", style, null);
        if(styleClass != null) writer.writeAttribute("class", styleClass, null);

        if(disabled) {
            writer.writeAttribute("href", "#", null);
            writer.writeAttribute("onclick", "return false;", null);
        }
        else {
            String onclick = menuitem.getOnclick();

            //GET
            if(menuitem.getUrl() != null || menuitem.getOutcome() != null) {                
                String targetURL = getTargetURL(context, (UIOutcomeTarget) menuitem);
                writer.writeAttribute("href", targetURL, null);

                if(menuitem.getTarget() != null) {
                    writer.writeAttribute("target", menuitem.getTarget(), null);
                }
            }
            //POST
            else {
                writer.writeAttribute("href", "#", null);

                UIComponent form = ComponentUtils.findParentForm(context, menu);
                if(form == null) {
                    throw new FacesException("MenuItem must be inside a form element");
                }

                String command;
                if(menuitem.isDynamic()) {
                    String menuClientId = menu.getClientId(context);
                    Map<String,List<String>> params = menuitem.getParams();
                    if(params == null) {
                        params = new LinkedHashMap<String, List<String>>();
                    }
                    List<String> idParams = new ArrayList<String>();
                    idParams.add(menuitem.getId());
                    params.put(menuClientId + "_menuid", idParams);

                    command = menuitem.isAjax() ? buildAjaxRequest(context, menu, (AjaxSource) menuitem, form, params) : buildNonAjaxRequest(context, menu, form, menuClientId, params, true);
                } 
                else {
                    command = menuitem.isAjax() ? buildAjaxRequest(context, (AjaxSource) menuitem, form) : buildNonAjaxRequest(context, ((UIComponent) menuitem), form, ((UIComponent) menuitem).getClientId(context), true);
                }

                onclick = (onclick == null) ? command : onclick + ";" + command;
            }

            if(onclick != null) {
                writer.writeAttribute("onclick", onclick, null);
            }
        }

        encodeMenuItemContent(context, menu, menuitem);
 
        writer.endElement("a");
        
        if(value != null && ((UIComponent)menuitem).getParent() instanceof UltimaMenu) {
            encodeTooltip(context, value);
        }
	}
    
    /* (non-Javadoc)
     * @see org.primefaces.component.menu.BaseMenuRenderer#encodeMenuItemContent(javax.faces.context.FacesContext, org.primefaces.component.menu.AbstractMenu, org.primefaces.model.menu.MenuItem)
     */
    @Override
    protected void encodeMenuItemContent(FacesContext context, AbstractMenu menu, MenuItem menuitem) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String icon = menuitem.getIcon();
        Object value = menuitem.getValue();
                
        if(menuitem instanceof UIMenuItem) {
            encodeBadge(context, ((UIMenuItem) menuitem).getAttributes().get("badge"));
        }
        encodeItemIcon(context, icon);

        if(value != null) {
            writer.startElement("span", null);
            writer.writeText(value, "value");
            writer.endElement("span");
        }
    }
    
    /**
     * Encode tooltip.
     *
     * @param context the context
     * @param value the value
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void encodeTooltip(FacesContext context, Object value) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        
        writer.startElement("div", null);
            writer.writeAttribute("class", "layout-menu-tooltip", null);
            writer.startElement("div", null);
            writer.writeAttribute("class", "layout-menu-tooltip-arrow", null);
            writer.endElement("div");
            writer.startElement("div", null);
            writer.writeAttribute("class", "layout-menu-tooltip-text", null);
            writer.writeText(value, null);
            writer.endElement("div");
        writer.endElement("div");
    }

    /* (non-Javadoc)
     * @see org.primefaces.component.menu.BaseMenuRenderer#encodeScript(javax.faces.context.FacesContext, org.primefaces.component.menu.AbstractMenu)
     */
    @Override
    protected void encodeScript(FacesContext context, AbstractMenu abstractMenu) throws IOException {
        UltimaMenu menu = (UltimaMenu) abstractMenu;
        String clientId = menu.getClientId(context);
        WidgetBuilder wb = getWidgetBuilder(context);
        wb.init("Ultima", menu.resolveWidgetVar(), clientId).finish();
    }
    
}
