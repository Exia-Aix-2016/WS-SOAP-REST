package com.bank.paymentmgmt.facade;

import com.bank.paymentmgmt.facade.domain.Payment;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface IBankingServiceRemote extends IBankingServiceEndpoint {

    List<Payment> lookupAllStoredPayments();

    Payment lookupStoredPayment(Long id);

    Payment deleteStoredPayment(Long id);
}
