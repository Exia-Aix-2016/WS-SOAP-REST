package com.bank.paymentmgmt.facade;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.rmi.Remote;

@WebService(name = "BankingEndpoint")
public interface IBankingServiceEndpoint extends Remote {
    @WebMethod(operationName = "payment")
    Boolean createPayment(@WebParam(name = "cardNumber") String ccNumber, @WebParam(name = "amountPaid") Double amount);
}
