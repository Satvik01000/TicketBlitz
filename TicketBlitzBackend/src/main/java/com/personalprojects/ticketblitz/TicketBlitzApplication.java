package com.personalprojects.ticketblitz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TicketBlitzApplication {

  public static void main(String[] args) {
    SpringApplication.run(TicketBlitzApplication.class, args);
  }
}
