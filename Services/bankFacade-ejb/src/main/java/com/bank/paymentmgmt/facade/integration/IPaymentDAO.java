package com.bank.paymentmgmt.facade.integration;

import com.bank.paymentmgmt.facade.domain.Payment;

import java.util.List;

public interface IPaymentDAO {

    //Create
    Payment add(Payment payment);

    //Read
    Payment find(Long id);
    List<Payment> getAllPayments();

    //Delete
    Payment delete(Long id);


}
