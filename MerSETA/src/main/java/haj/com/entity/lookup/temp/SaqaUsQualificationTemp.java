package haj.com.entity.lookup.temp;
// Generated Oct 23, 2017 5:04:50 PM by Hibernate Tools 4.3.1


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import haj.com.framework.IDataEntity;

// TODO: Auto-generated Javadoc
/**
 * SaqaUsQualification generated by hbm2java.
 */
@Entity
@Table(name="saqa_us_qualification_temp")
public class SaqaUsQualificationTemp  implements IDataEntity {


     /** The id. */
     private Long id;
     
     /** The saqa qualification. */
     private SaqaQualificationTemp saqaQualification;
     
     /** The nqfleveldescription. */
     private String nqfleveldescription;
     
     /** The nqflevelg 2 description. */
     private String nqflevelg2description;
     
     /** The qualificationid. */
     private Integer qualificationid;
     
     /** The unitstandardid. */
     private Integer unitstandardid;
     
     /** The unitstdnumberofcredits. */
     private Integer unitstdnumberofcredits;
     
     /** The unitstdtitle. */
     private String unitstdtitle;
     
     /** The usqualtypedescription. */
     private String usqualtypedescription;

    /**
     * Instantiates a new saqa us qualification.
     */
    public SaqaUsQualificationTemp() {
    }

    /**
     * Instantiates a new saqa us qualification.
     *
     * @param saqaQualification the saqa qualification
     * @param nqfleveldescription the nqfleveldescription
     * @param nqflevelg2description the nqflevelg 2 description
     * @param qualificationid the qualificationid
     * @param unitstandardid the unitstandardid
     * @param unitstdnumberofcredits the unitstdnumberofcredits
     * @param unitstdtitle the unitstdtitle
     * @param usqualtypedescription the usqualtypedescription
     */
    public SaqaUsQualificationTemp(SaqaQualificationTemp saqaQualification, String nqfleveldescription, String nqflevelg2description, Integer qualificationid, Integer unitstandardid, Integer unitstdnumberofcredits, String unitstdtitle, String usqualtypedescription) {
       this.saqaQualification = saqaQualification;
       this.nqfleveldescription = nqfleveldescription;
       this.nqflevelg2description = nqflevelg2description;
       this.qualificationid = qualificationid;
       this.unitstandardid = unitstandardid;
       this.unitstdnumberofcredits = unitstdnumberofcredits;
       this.unitstdtitle = unitstdtitle;
       this.usqualtypedescription = usqualtypedescription;
    }
   
     /**
      * Gets the id.
      *
      * @return the id
      */
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

/**
 * Gets the saqa qualification.
 *
 * @return the saqa qualification
 */
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="qualification_id")
    public SaqaQualificationTemp getSaqaQualification() {
        return this.saqaQualification;
    }
    
    /**
     * Sets the saqa qualification.
     *
     * @param saqaQualification the new saqa qualification
     */
    public void setSaqaQualification(SaqaQualificationTemp saqaQualificationTemp) {
        this.saqaQualification = saqaQualificationTemp;
    }

    
    /**
     * Gets the nqfleveldescription.
     *
     * @return the nqfleveldescription
     */
    @Column(name="nqfleveldescription")
    public String getNqfleveldescription() {
        return this.nqfleveldescription;
    }
    
    /**
     * Sets the nqfleveldescription.
     *
     * @param nqfleveldescription the new nqfleveldescription
     */
    public void setNqfleveldescription(String nqfleveldescription) {
        this.nqfleveldescription = nqfleveldescription;
    }

    
    /**
     * Gets the nqflevelg 2 description.
     *
     * @return the nqflevelg 2 description
     */
    @Column(name="nqflevelg2DESCRIPTION")
    public String getNqflevelg2description() {
        return this.nqflevelg2description;
    }
    
    /**
     * Sets the nqflevelg 2 description.
     *
     * @param nqflevelg2description the new nqflevelg 2 description
     */
    public void setNqflevelg2description(String nqflevelg2description) {
        this.nqflevelg2description = nqflevelg2description;
    }

    
    /**
     * Gets the qualificationid.
     *
     * @return the qualificationid
     */
    @Column(name="qualificationid")
    public Integer getQualificationid() {
        return this.qualificationid;
    }
    
    /**
     * Sets the qualificationid.
     *
     * @param qualificationid the new qualificationid
     */
    public void setQualificationid(Integer qualificationid) {
        this.qualificationid = qualificationid;
    }

    
    /**
     * Gets the unitstandardid.
     *
     * @return the unitstandardid
     */
    @Column(name="unitstandardid")
    public Integer getUnitstandardid() {
        return this.unitstandardid;
    }
    
    /**
     * Sets the unitstandardid.
     *
     * @param unitstandardid the new unitstandardid
     */
    public void setUnitstandardid(Integer unitstandardid) {
        this.unitstandardid = unitstandardid;
    }

    
    /**
     * Gets the unitstdnumberofcredits.
     *
     * @return the unitstdnumberofcredits
     */
    @Column(name="unitstdnumberofcredits")
    public Integer getUnitstdnumberofcredits() {
        return this.unitstdnumberofcredits;
    }
    
    /**
     * Sets the unitstdnumberofcredits.
     *
     * @param unitstdnumberofcredits the new unitstdnumberofcredits
     */
    public void setUnitstdnumberofcredits(Integer unitstdnumberofcredits) {
        this.unitstdnumberofcredits = unitstdnumberofcredits;
    }

    
    /**
     * Gets the unitstdtitle.
     *
     * @return the unitstdtitle
     */
    @Column(name="unitstdtitle", length=500)
    public String getUnitstdtitle() {
        return this.unitstdtitle;
    }
    
    /**
     * Sets the unitstdtitle.
     *
     * @param unitstdtitle the new unitstdtitle
     */
    public void setUnitstdtitle(String unitstdtitle) {
        this.unitstdtitle = unitstdtitle;
    }

    
    /**
     * Gets the usqualtypedescription.
     *
     * @return the usqualtypedescription
     */
    @Column(name="usqualtypedescription", length=100)
    public String getUsqualtypedescription() {
        return this.usqualtypedescription;
    }
    
    /**
     * Sets the usqualtypedescription.
     *
     * @param usqualtypedescription the new usqualtypedescription
     */
    public void setUsqualtypedescription(String usqualtypedescription) {
        this.usqualtypedescription = usqualtypedescription;
    }




}


