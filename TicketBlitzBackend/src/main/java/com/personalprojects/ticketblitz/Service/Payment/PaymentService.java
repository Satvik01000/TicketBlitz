package com.personalprojects.ticketblitz.Service.Payment;

import com.personalprojects.ticketblitz.DTO.Response.PaymentLinkResponseDTO;
import java.util.UUID;

public interface PaymentService {
  PaymentLinkResponseDTO initiatePayment(UUID bookingId, Double amount);
}
