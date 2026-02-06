package com.personalprojects.ticketblitz.Service.Payment;

import com.personalprojects.ticketblitz.DTO.Response.PaymentLinkResponseDTO;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImple implements PaymentService {

  @Override
  public PaymentLinkResponseDTO initiatePayment(UUID bookingId, Double amount) {
    // 1. Link for Success (Calls confirm)
    String successLink = "http://localhost:8080/api/booking/confirm/" + bookingId;

    // 2. Link for User Cancellation (Calls cancel)
    String cancelLink = "http://localhost:8080/api/booking/cancel/" + bookingId;

    return new PaymentLinkResponseDTO(successLink, cancelLink);
  }
}
