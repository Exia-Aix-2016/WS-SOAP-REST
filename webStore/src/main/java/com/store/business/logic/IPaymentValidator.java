package com.store.business.logic;

import javax.ejb.Local;

@Local
public interface IPaymentValidator {
    Boolean process(String  ccNumber, Double amount);
}
