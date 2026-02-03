package com.personalprojects.ticketblitz.Exceptions;

public class SeatAlreadyBookedException extends RuntimeException {
  public SeatAlreadyBookedException(String msg) {
    super(msg);
  }
}
