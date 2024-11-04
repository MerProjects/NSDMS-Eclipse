
package com.microsoft.schemas.dynamics.gp._2006._01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="GetReceivablesInvoiceListResult" type="{http://schemas.microsoft.com/dynamics/gp/2006/01}ArrayOfReceivablesInvoiceSummary" minOccurs="0"/&gt;
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
    "getReceivablesInvoiceListResult"
})
@XmlRootElement(name = "GetReceivablesInvoiceListResponse")
public class GetReceivablesInvoiceListResponse {

    @XmlElement(name = "GetReceivablesInvoiceListResult")
    protected ArrayOfReceivablesInvoiceSummary getReceivablesInvoiceListResult;

    /**
     * Gets the value of the getReceivablesInvoiceListResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfReceivablesInvoiceSummary }
     *     
     */
    public ArrayOfReceivablesInvoiceSummary getGetReceivablesInvoiceListResult() {
        return getReceivablesInvoiceListResult;
    }

    /**
     * Sets the value of the getReceivablesInvoiceListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfReceivablesInvoiceSummary }
     *     
     */
    public void setGetReceivablesInvoiceListResult(ArrayOfReceivablesInvoiceSummary value) {
        this.getReceivablesInvoiceListResult = value;
    }

}