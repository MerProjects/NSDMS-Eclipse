
package com.microsoft.schemas.dynamics.gp._2006._01;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSalesDocumentSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSalesDocumentSummary"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SalesDocumentSummary" type="{http://schemas.microsoft.com/dynamics/gp/2006/01}SalesDocumentSummary" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSalesDocumentSummary", propOrder = {
    "salesDocumentSummary"
})
public class ArrayOfSalesDocumentSummary {

    @XmlElement(name = "SalesDocumentSummary", nillable = true)
    protected List<SalesDocumentSummary> salesDocumentSummary;

    /**
     * Gets the value of the salesDocumentSummary property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the salesDocumentSummary property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSalesDocumentSummary().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SalesDocumentSummary }
     * 
     * 
     */
    public List<SalesDocumentSummary> getSalesDocumentSummary() {
        if (salesDocumentSummary == null) {
            salesDocumentSummary = new ArrayList<SalesDocumentSummary>();
        }
        return this.salesDocumentSummary;
    }

}
