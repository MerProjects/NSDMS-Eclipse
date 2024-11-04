
package com.microsoft.schemas.dynamics.gp._2006._01;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfReturnMaterialAuthorizationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfReturnMaterialAuthorizationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ReturnMaterialAuthorizationType" type="{http://schemas.microsoft.com/dynamics/gp/2006/01}ReturnMaterialAuthorizationType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfReturnMaterialAuthorizationType", propOrder = {
    "returnMaterialAuthorizationType"
})
public class ArrayOfReturnMaterialAuthorizationType {

    @XmlElement(name = "ReturnMaterialAuthorizationType", nillable = true)
    @XmlSchemaType(name = "string")
    protected List<ReturnMaterialAuthorizationType> returnMaterialAuthorizationType;

    /**
     * Gets the value of the returnMaterialAuthorizationType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the returnMaterialAuthorizationType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReturnMaterialAuthorizationType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReturnMaterialAuthorizationType }
     * 
     * 
     */
    public List<ReturnMaterialAuthorizationType> getReturnMaterialAuthorizationType() {
        if (returnMaterialAuthorizationType == null) {
            returnMaterialAuthorizationType = new ArrayList<ReturnMaterialAuthorizationType>();
        }
        return this.returnMaterialAuthorizationType;
    }

}