package com.resort.management.payment.dto;

import com.resort.management.payment.entity.PaymentStatus;
import jakarta.validation.constraints.NotNull;

public class PaymentUpdateStatusRequest {

    @NotNull
    private PaymentStatus status;

    // optional reference when paid
    private String transactionRef;

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    public String getTransactionRef() { return transactionRef; }
    public void setTransactionRef(String transactionRef) { this.transactionRef = transactionRef; }
}
