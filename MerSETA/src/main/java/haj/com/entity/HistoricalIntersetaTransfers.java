package haj.com.entity;
// Generated Mar 26, 2018 10:14:38 AM by Hibernate Tools 4.3.1


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import haj.com.framework.IDataEntity;

/**
 * HistoricalIntersetaTransfers generated by hbm2java
 */
@Entity
@Table(name="historical_interseta_transfers"

)
public class HistoricalIntersetaTransfers  implements IDataEntity {


     private Long id;
     private String refNo;
     private String transactionType;
     private Integer trnsfrNo;
     private Integer seta;
     private String setaName;
     private String transactionDate;
     private String grantType;
     private Double amount;
     private Integer mersetaSchemeYear;
     private Date transactionDateD;

    public HistoricalIntersetaTransfers() {
    }



     @Id @GeneratedValue(strategy=IDENTITY)


    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name="ref_no", length=50)
    public String getRefNo() {
        return this.refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }


    @Column(name="transaction_type", length=50)
    public String getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }


    @Column(name="TrnsfrNo")
    public Integer getTrnsfrNo() {
        return this.trnsfrNo;
    }

    public void setTrnsfrNo(Integer trnsfrNo) {
        this.trnsfrNo = trnsfrNo;
    }


    @Column(name="seta")
    public Integer getSeta() {
        return this.seta;
    }

    public void setSeta(Integer seta) {
        this.seta = seta;
    }


    @Column(name="seta_name", length=200)
    public String getSetaName() {
        return this.setaName;
    }

    public void setSetaName(String setaName) {
        this.setaName = setaName;
    }


    @Column(name="transaction_date")
    public String getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }


    @Column(name="grant_type")
    public String getGrantType() {
        return this.grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }


    @Column(name="amount", precision=22, scale=0)
    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    @Column(name="merseta_scheme_year")
    public Integer getMersetaSchemeYear() {
        return this.mersetaSchemeYear;
    }

    public void setMersetaSchemeYear(Integer mersetaSchemeYear) {
        this.mersetaSchemeYear = mersetaSchemeYear;
    }



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		HistoricalIntersetaTransfers other = (HistoricalIntersetaTransfers) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}


	@Column(name="transaction_date_d")
	public Date getTransactionDateD() {
		return transactionDateD;
	}



	public void setTransactionDateD(Date transactionDateD) {
		this.transactionDateD = transactionDateD;
	}




}


