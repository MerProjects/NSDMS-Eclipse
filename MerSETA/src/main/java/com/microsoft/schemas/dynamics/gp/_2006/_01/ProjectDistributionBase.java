
package com.microsoft.schemas.dynamics.gp._2006._01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProjectDistributionBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProjectDistributionBase"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://schemas.microsoft.com/dynamics/gp/2006/01}BusinessObject"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AuditTrailCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CreditAmount" type="{http://schemas.microsoft.com/dynamics/gp/2006/01}MoneyAmount" minOccurs="0"/&gt;
 *         &lt;element name="DebitAmount" type="{http://schemas.microsoft.com/dynamics/gp/2006/01}MoneyAmount" minOccurs="0"/&gt;
 *         &lt;element name="GLAccountKey" type="{http://schemas.microsoft.com/dynamics/gp/2006/01}GLAccountNumberKey" minOccurs="0"/&gt;
 *         &lt;element name="DistributionTypeKey" type="{http://schemas.microsoft.com/dynamics/gp/2006/01}DistributionTypeKey" minOccurs="0"/&gt;
 *         &lt;element name="Reference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProjectDistributionBase", propOrder = {
    "auditTrailCode",
    "creditAmount",
    "debitAmount",
    "glAccountKey",
    "distributionTypeKey",
    "reference"
})
@XmlSeeAlso({
    ProjectTimesheetDistribution.class,
    ProjectEmployeeExpenseDistribution.class,
    ProjectMiscellaneousLogDistribution.class
})
public abstract class ProjectDistributionBase
    extends BusinessObject
{

    @XmlElement(name = "AuditTrailCode")
    protected String auditTrailCode;
    @XmlElement(name = "CreditAmount")
    protected MoneyAmount creditAmount;
    @XmlElement(name = "DebitAmount")
    protected MoneyAmount debitAmount;
    @XmlElement(name = "GLAccountKey")
    protected GLAccountNumberKey glAccountKey;
    @XmlElement(name = "DistributionTypeKey")
    protected DistributionTypeKey distributionTypeKey;
    @XmlElement(name = "Reference")
    protected String reference;

    /**
     * Gets the value of the auditTrailCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuditTrailCode() {
        return auditTrailCode;
    }

    /**
     * Sets the value of the auditTrailCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuditTrailCode(String value) {
        this.auditTrailCode = value;
    }

    /**
     * Gets the value of the creditAmount property.
     * 
     * @return
     *     possible object is
     *     {@link MoneyAmount }
     *     
     */
    public MoneyAmount getCreditAmount() {
        return creditAmount;
    }

    /**
     * Sets the value of the creditAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link MoneyAmount }
     *     
     */
    public void setCreditAmount(MoneyAmount value) {
        this.creditAmount = value;
    }

    /**
     * Gets the value of the debitAmount property.
     * 
     * @return
     *     possible object is
     *     {@link MoneyAmount }
     *     
     */
    public MoneyAmount getDebitAmount() {
        return debitAmount;
    }

    /**
     * Sets the value of the debitAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link MoneyAmount }
     *     
     */
    public void setDebitAmount(MoneyAmount value) {
        this.debitAmount = value;
    }

    /**
     * Gets the value of the glAccountKey property.
     * 
     * @return
     *     possible object is
     *     {@link GLAccountNumberKey }
     *     
     */
    public GLAccountNumberKey getGLAccountKey() {
        return glAccountKey;
    }

    /**
     * Sets the value of the glAccountKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link GLAccountNumberKey }
     *     
     */
    public void setGLAccountKey(GLAccountNumberKey value) {
        this.glAccountKey = value;
    }

    /**
     * Gets the value of the distributionTypeKey property.
     * 
     * @return
     *     possible object is
     *     {@link DistributionTypeKey }
     *     
     */
    public DistributionTypeKey getDistributionTypeKey() {
        return distributionTypeKey;
    }

    /**
     * Sets the value of the distributionTypeKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link DistributionTypeKey }
     *     
     */
    public void setDistributionTypeKey(DistributionTypeKey value) {
        this.distributionTypeKey = value;
    }

    /**
     * Gets the value of the reference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference(String value) {
        this.reference = value;
    }

}
