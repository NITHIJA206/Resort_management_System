package com.resort.management.payment.service.impl;

import com.resort.management.exception.ResourceNotFoundException;
import com.resort.management.payment.dto.PaymentCreateRequest;
import com.resort.management.payment.dto.PaymentResponse;
import com.resort.management.payment.dto.PaymentUpdateStatusRequest;
import com.resort.management.payment.entity.Payment;
import com.resort.management.payment.entity.PaymentStatus;
import com.resort.management.payment.repository.PaymentRepository;
import com.resort.management.payment.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentResponse createPayment(PaymentCreateRequest request) {
        Payment payment = new Payment();
        payment.setBookingId(request.getBookingId());
        payment.setAmount(request.getAmount());
        payment.setMethod(request.getMethod());
        payment.setStatus(PaymentStatus.PENDING);

        Payment saved = paymentRepository.save(payment);
        return toResponse(saved);
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
        return toResponse(payment);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public List<PaymentResponse> getPaymentsByBookingId(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId).stream().map(this::toResponse).toList();
    }

    @Override
    public PaymentResponse updateStatus(Long id, PaymentUpdateStatusRequest request) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));

        payment.setStatus(request.getStatus());

        if (request.getTransactionRef() != null && !request.getTransactionRef().isBlank()) {
            payment.setTransactionRef(request.getTransactionRef());
        }

        // set paidAt automatically when PAID
        if (request.getStatus() == PaymentStatus.PAID && payment.getPaidAt() == null) {
            payment.setPaidAt(LocalDateTime.now());
        }

        Payment saved = paymentRepository.save(payment);
        return toResponse(saved);
    }

    @Override
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }

    private PaymentResponse toResponse(Payment p) {
        PaymentResponse r = new PaymentResponse();
        r.setId(p.getId());
        r.setBookingId(p.getBookingId());
        r.setAmount(p.getAmount());
        r.setMethod(p.getMethod());
        r.setStatus(p.getStatus());
        r.setTransactionRef(p.getTransactionRef());
        r.setCreatedAt(p.getCreatedAt());
        r.setPaidAt(p.getPaidAt());
        return r;
    }
}
