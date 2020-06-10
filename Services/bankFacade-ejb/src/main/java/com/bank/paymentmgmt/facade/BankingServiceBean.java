package com.bank.paymentmgmt.facade;

import com.bank.paymentmgmt.facade.domain.Payment;
import com.bank.paymentmgmt.facade.integration.IPaymentDAO;
import com.bank.paymentmgmt.facade.integration.PaymentDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@Stateless
@WebService(
        endpointInterface = "com.bank.paymentmgmt.facade.IBankingServiceEndpoint",
        portName = "BankingPort",
        serviceName = "BankingService")
public class BankingServiceBean implements IBankingServiceEndpoint, IBankingServiceRemote {

    @Inject
    private IPaymentDAO paymentDAO;
    @Override
    public Boolean createPayment(String ccNumber, Double amount) {
        if(ccNumber.length()== 10 ){

            Payment payment = new Payment();
            payment.setCcNumber(ccNumber);
            payment.setAmount(amount);

            payment = paymentDAO.add(payment);

            paymentDAO.getAllPayments();
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<Payment> lookupAllStoredPayments() {
        return paymentDAO.getAllPayments();
    }

    @Override
    public Payment lookupStoredPayment(Long id) {
        return paymentDAO.find(id);
    }

    @Override
    public Payment deleteStoredPayment(Long id) {
        return paymentDAO.delete(id);
    }
}
