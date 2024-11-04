
package com.microsoft.schemas.dynamics.gp._2006._01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReceivablesDistribution complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReceivablesDistribution"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://schemas.microsoft.com/dynamics/gp/2006/01}Distribution"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Key" type="{http://schemas.microsoft.com/dynamics/gp/2006/01}ReceivablesDistributionKey" minOccurs="0"/&gt;
 *         &lt;element name="IsPosted" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReceivablesDistribution", propOrder = {
    "key",
    "isPosted"
})
public class ReceivablesDistribution
    extends Distribution
{

    @XmlElement(name = "Key")
    protected ReceivablesDistributionKey key;
    @XmlElement(name = "IsPosted", required = true, type = Boolean.class, nillable = true)
    protected Boolean isPosted;

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link ReceivablesDistributionKey }
     *     
     */
    public ReceivablesDistributionKey getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReceivablesDistributionKey }
     *     
     */
    public void setKey(ReceivablesDistributionKey value) {
        this.key = value;
    }

    /**
     * Gets the value of the isPosted property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsPosted() {
        return isPosted;
    }

    /**
     * Sets the value of the isPosted property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPosted(Boolean value) {
        this.isPosted = value;
    }

}
