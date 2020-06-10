package com.bank.paymentmgmt.facade.integration;

import com.bank.paymentmgmt.facade.domain.Payment;
import com.bank.paymentmgmt.facade.domain.PaymentStatus;

import javax.enterprise.inject.Default;
import javax.faces.bean.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
@Default //Can be removed, I let it here for educative purpose, >See Qualifier mechanisms.<
public class PaymentDAO implements IPaymentDAO, Serializable {

    private AtomicLong count = new AtomicLong(1);
    private Map<Long, Payment> payments = new ConcurrentHashMap<>();

    @Override
    public Payment add(Payment payment) {
        if (payment == null) return null;
        payment.setId(count.getAndIncrement());
        payment.setStatus(PaymentStatus.VALIDATED);
        payments.put(payment.getId(), payment);
        return payment;
    }

    @Override
    public Payment find(Long id) {
        if(id < 0) return null;

        return payments.get(id);
    }

    @Override
    public List<Payment> getAllPayments() {
        List<Payment> paymentList = new ArrayList<>(payments.values());

        return paymentList;
    }

    @Override
    public Payment delete(Long id) {
        Payment deletedPayment = payments.remove(id);

        if(deletedPayment == null){
            return null;
        }
        deletedPayment.setStatus(PaymentStatus.CANCELLED);
        return  deletedPayment;
    }
}
