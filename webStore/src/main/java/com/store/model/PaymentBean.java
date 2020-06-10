package com.store.model;



import com.store.business.logic.IPaymentValidator;
import com.store.business.logic.Rest;
import com.store.business.logic.Soap;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named(value = "paymentModel")
public class PaymentBean {
    private String ccNumber;
    private Double amount;

    @Inject @Soap
    private IPaymentValidator paymentValidatorSoap;

    @Inject @Rest
    private IPaymentValidator paymentValidatorRest;

    public String doPaymentWithSoap(){

        Boolean isValid = paymentValidatorSoap.process(ccNumber, amount);

        if(isValid){
            return "valid";
        }else {
            return "invalid";
        }
    }

    public String doPaymentWithRest(){

        Boolean isValid = paymentValidatorRest.process(ccNumber, amount);

        if(isValid){
            return "valid";
        }else {
            return "invalid";
        }
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
}
