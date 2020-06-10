package com.bank.paymentmgmt.facade.domain;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum PaymentStatus {
    VALIDATED,
    CANCELLED
}
