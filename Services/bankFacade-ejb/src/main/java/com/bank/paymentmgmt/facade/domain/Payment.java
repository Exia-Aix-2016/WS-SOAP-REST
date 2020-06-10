package com.bank.paymentmgmt.facade.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Payment implements Serializable {


    @XmlAttribute
    private Long id;
    @XmlAttribute
    private PaymentStatus status;
    @XmlAttribute
    private String ccNumber;
    @XmlAttribute
    private Double amount;

    //ID
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    //STATUS
    public PaymentStatus getStatus() {
        return status;
    }
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    //CCNUMBER
    public String getCcNumber() {
        return ccNumber;
    }
    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    //AMOUNT
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", status=" + status +
                ", ccNumber='" + ccNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
