package com.personalprojects.ticketblitz.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentLinkResponseDTO {
  public String paymentUrl;
  public String cancelUrl;
}
