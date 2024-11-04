
package com.microsoft.schemas.dynamics.gp._2006._01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SalesReturnQuantities complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesReturnQuantities"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OnHandQuantity" type="{http://schemas.microsoft.com/dynamics/gp/2006/01}Quantity" minOccurs="0"/&gt;
 *         &lt;element name="ReturnedQuantity" type="{http://schemas.microsoft.com/dynamics/gp/2006/01}Quantity" minOccurs="0"/&gt;
 *         &lt;element name="InUseQuantity" type="{http://schemas.microsoft.com/dynamics/gp/2006/01}Quantity" minOccurs="0"/&gt;
 *         &lt;element name="InServiceQuantity" type="{http://schemas.microsoft.com/dynamics/gp/2006/01}Quantity" minOccurs="0"/&gt;
 *         &lt;element name="DamagedQuantity" type="{http://schemas.microsoft.com/dynamics/gp/2006/01}Quantity" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesReturnQuantities", propOrder = {
    "onHandQuantity",
    "returnedQuantity",
    "inUseQuantity",
    "inServiceQuantity",
    "damagedQuantity"
})
public class SalesReturnQuantities {

    @XmlElement(name = "OnHandQuantity")
    protected Quantity onHandQuantity;
    @XmlElement(name = "ReturnedQuantity")
    protected Quantity returnedQuantity;
    @XmlElement(name = "InUseQuantity")
    protected Quantity inUseQuantity;
    @XmlElement(name = "InServiceQuantity")
    protected Quantity inServiceQuantity;
    @XmlElement(name = "DamagedQuantity")
    protected Quantity damagedQuantity;

    /**
     * Gets the value of the onHandQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Quantity }
     *     
     */
    public Quantity getOnHandQuantity() {
        return onHandQuantity;
    }

    /**
     * Sets the value of the onHandQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Quantity }
     *     
     */
    public void setOnHandQuantity(Quantity value) {
        this.onHandQuantity = value;
    }

    /**
     * Gets the value of the returnedQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Quantity }
     *     
     */
    public Quantity getReturnedQuantity() {
        return returnedQuantity;
    }

    /**
     * Sets the value of the returnedQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Quantity }
     *     
     */
    public void setReturnedQuantity(Quantity value) {
        this.returnedQuantity = value;
    }

    /**
     * Gets the value of the inUseQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Quantity }
     *     
     */
    public Quantity getInUseQuantity() {
        return inUseQuantity;
    }

    /**
     * Sets the value of the inUseQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Quantity }
     *     
     */
    public void setInUseQuantity(Quantity value) {
        this.inUseQuantity = value;
    }

    /**
     * Gets the value of the inServiceQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Quantity }
     *     
     */
    public Quantity getInServiceQuantity() {
        return inServiceQuantity;
    }

    /**
     * Sets the value of the inServiceQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Quantity }
     *     
     */
    public void setInServiceQuantity(Quantity value) {
        this.inServiceQuantity = value;
    }

    /**
     * Gets the value of the damagedQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Quantity }
     *     
     */
    public Quantity getDamagedQuantity() {
        return damagedQuantity;
    }

    /**
     * Sets the value of the damagedQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Quantity }
     *     
     */
    public void setDamagedQuantity(Quantity value) {
        this.damagedQuantity = value;
    }

}