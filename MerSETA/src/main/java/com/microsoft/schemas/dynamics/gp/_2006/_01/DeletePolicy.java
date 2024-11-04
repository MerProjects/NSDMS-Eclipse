
package com.microsoft.schemas.dynamics.gp._2006._01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.microsoft.schemas.dynamics._2006._01.Context;
import com.microsoft.schemas.dynamics.security._2006._01.RoleKey;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="policyKey" type="{http://schemas.microsoft.com/dynamics/gp/2006/01}PolicyKey" minOccurs="0"/&gt;
 *         &lt;element name="roleKey" type="{http://schemas.microsoft.com/dynamics/security/2006/01}RoleKey" minOccurs="0"/&gt;
 *         &lt;element name="context" type="{http://schemas.microsoft.com/dynamics/2006/01}Context" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "policyKey",
    "roleKey",
    "context"
})
@XmlRootElement(name = "DeletePolicy")
public class DeletePolicy {

    protected PolicyKey policyKey;
    protected RoleKey roleKey;
    protected Context context;

    /**
     * Gets the value of the policyKey property.
     * 
     * @return
     *     possible object is
     *     {@link PolicyKey }
     *     
     */
    public PolicyKey getPolicyKey() {
        return policyKey;
    }

    /**
     * Sets the value of the policyKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link PolicyKey }
     *     
     */
    public void setPolicyKey(PolicyKey value) {
        this.policyKey = value;
    }

    /**
     * Gets the value of the roleKey property.
     * 
     * @return
     *     possible object is
     *     {@link RoleKey }
     *     
     */
    public RoleKey getRoleKey() {
        return roleKey;
    }

    /**
     * Sets the value of the roleKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoleKey }
     *     
     */
    public void setRoleKey(RoleKey value) {
        this.roleKey = value;
    }

    /**
     * Gets the value of the context property.
     * 
     * @return
     *     possible object is
     *     {@link Context }
     *     
     */
    public Context getContext() {
        return context;
    }

    /**
     * Sets the value of the context property.
     * 
     * @param value
     *     allowed object is
     *     {@link Context }
     *     
     */
    public void setContext(Context value) {
        this.context = value;
    }

}