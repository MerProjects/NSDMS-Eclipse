package haj.com.entity.lookup.temp;
// Generated Oct 24, 2017 7:30:08 AM by Hibernate Tools 4.3.1


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
 * SaqaSpecificOutcome generated by hbm2java.
 */
@Entity
@Table(name="saqa_specific_outcome_temp")
public class SaqaSpecificOutcomeTemp  implements IDataEntity {


     /** The id. */
     private Long id;
     
     /** The saqa unitstandard. */
     private SaqaUnitstandardTemp saqaUnitstandard;
     
     /** The outcomeid. */
     private Integer outcomeid;
     
     /** The outcome notes. */
     private String outcomeNotes;
     
     /** The outcomerange. */
     private String outcomerange;
     
     /** The outcome title. */
     private String outcomeTitle;
     
     /** The unitstandardid. */
     private Integer unitstandardid;

    /**
     * Instantiates a new saqa specific outcome.
     */
    public SaqaSpecificOutcomeTemp() {
    }

    /**
     * Instantiates a new saqa specific outcome.
     *
     * @param saqaUnitstandard the saqa unitstandard
     * @param outcomeid the outcomeid
     * @param outcomeNotes the outcome notes
     * @param outcomerange the outcomerange
     * @param outcomeTitle the outcome title
     * @param unitstandardid the unitstandardid
     */
    public SaqaSpecificOutcomeTemp(SaqaUnitstandardTemp saqaUnitstandard, Integer outcomeid, String outcomeNotes, String outcomerange, String outcomeTitle, Integer unitstandardid) {
       this.saqaUnitstandard = saqaUnitstandard;
       this.outcomeid = outcomeid;
       this.outcomeNotes = outcomeNotes;
       this.outcomerange = outcomerange;
       this.outcomeTitle = outcomeTitle;
       this.unitstandardid = unitstandardid;
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
 * Gets the saqa unitstandard.
 *
 * @return the saqa unitstandard
 */
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="unitstandard_id")
    public SaqaUnitstandardTemp getSaqaUnitstandard() {
        return this.saqaUnitstandard;
    }
    
    /**
     * Sets the saqa unitstandard.
     *
     * @param saqaUnitstandard the new saqa unitstandard
     */
    public void setSaqaUnitstandard(SaqaUnitstandardTemp saqaUnitstandard) {
        this.saqaUnitstandard = saqaUnitstandard;
    }

    
    /**
     * Gets the outcomeid.
     *
     * @return the outcomeid
     */
    @Column(name="outcomeid")
    public Integer getOutcomeid() {
        return this.outcomeid;
    }
    
    /**
     * Sets the outcomeid.
     *
     * @param outcomeid the new outcomeid
     */
    public void setOutcomeid(Integer outcomeid) {
        this.outcomeid = outcomeid;
    }

    
    /**
     * Gets the outcome notes.
     *
     * @return the outcome notes
     */
    @Column(name="outcome_notes")
    public String getOutcomeNotes() {
        return this.outcomeNotes;
    }
    
    /**
     * Sets the outcome notes.
     *
     * @param outcomeNotes the new outcome notes
     */
    public void setOutcomeNotes(String outcomeNotes) {
        this.outcomeNotes = outcomeNotes;
    }

    
    /**
     * Gets the outcomerange.
     *
     * @return the outcomerange
     */
    @Column(name="outcomerange")
    public String getOutcomerange() {
        return this.outcomerange;
    }
    
    /**
     * Sets the outcomerange.
     *
     * @param outcomerange the new outcomerange
     */
    public void setOutcomerange(String outcomerange) {
        this.outcomerange = outcomerange;
    }

    
    /**
     * Gets the outcome title.
     *
     * @return the outcome title
     */
    @Column(name="outcome_title")
    public String getOutcomeTitle() {
        return this.outcomeTitle;
    }
    
    /**
     * Sets the outcome title.
     *
     * @param outcomeTitle the new outcome title
     */
    public void setOutcomeTitle(String outcomeTitle) {
        this.outcomeTitle = outcomeTitle;
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




}


