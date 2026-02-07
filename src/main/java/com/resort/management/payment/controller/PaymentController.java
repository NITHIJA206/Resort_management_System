package com.resort.management.payment.controller;

import com.resort.management.payment.dto.PaymentCreateRequest;
import com.resort.management.payment.dto.PaymentResponse;
import com.resort.management.payment.dto.PaymentUpdateStatusRequest;
import com.resort.management.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin // remove/adjust when you configure frontend domain
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // CREATE payment
    @PostMapping
    public PaymentResponse create(@Valid @RequestBody PaymentCreateRequest request) {
        return paymentService.createPayment(request);
    }

    // GET all payments
    @GetMapping
    public List<PaymentResponse> getAll() {
        return paymentService.getAllPayments();
    }

    // GET payment by id
    @GetMapping("/{id}")
    public PaymentResponse getById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    // GET payments by bookingId
    @GetMapping("/booking/{bookingId}")
    public List<PaymentResponse> getByBooking(@PathVariable Long bookingId) {
        return paymentService.getPaymentsByBookingId(bookingId);
    }

    // UPDATE status (paid/failed/refunded)
    @PatchMapping("/{id}/status")
    public PaymentResponse updateStatus(@PathVariable Long id,
                                        @Valid @RequestBody PaymentUpdateStatusRequest request) {
        return paymentService.updateStatus(id, request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
}
