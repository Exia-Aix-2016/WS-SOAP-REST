package com.store.business.logic;



import com.store.integration.BankingEndpoint;
import com.store.integration.BankingService;

import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;


@Stateless
@Soap
public class SoapPaymentValidator implements IPaymentValidator {

    @WebServiceRef(BankingService.class)
    private BankingEndpoint bankingEndpoint;
    @Override
    public Boolean process(String ccNumber, Double amount) {
        return bankingEndpoint.payment(ccNumber, amount);
    }
}
