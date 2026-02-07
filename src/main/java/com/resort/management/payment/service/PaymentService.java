package com.resort.management.payment.service;

import com.resort.management.payment.dto.PaymentCreateRequest;
import com.resort.management.payment.dto.PaymentResponse;
import com.resort.management.payment.dto.PaymentUpdateStatusRequest;

import java.util.List;

public interface PaymentService {
    PaymentResponse createPayment(PaymentCreateRequest request);
    PaymentResponse getPaymentById(Long id);
    List<PaymentResponse> getAllPayments();
    List<PaymentResponse> getPaymentsByBookingId(Long bookingId);
    PaymentResponse updateStatus(Long id, PaymentUpdateStatusRequest request);
    void deletePayment(Long id);
}
