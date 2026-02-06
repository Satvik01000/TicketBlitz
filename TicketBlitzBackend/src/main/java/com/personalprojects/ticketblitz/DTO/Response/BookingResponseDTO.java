package com.personalprojects.ticketblitz.DTO.Response;

import com.personalprojects.ticketblitz.Entity.BookingStatus;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingResponseDTO {
  private UUID bookingId;
  private BookingStatus status;
  private String paymentUrl;
  private String cancelUrl;
}
