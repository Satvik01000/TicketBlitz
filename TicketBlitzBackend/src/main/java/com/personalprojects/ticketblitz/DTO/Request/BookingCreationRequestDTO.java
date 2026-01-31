package com.personalprojects.ticketblitz.DTO.Request;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BookingCreationRequestDTO {
    private UUID showId;
    private UUID seatId;
    private UUID userId;
}
