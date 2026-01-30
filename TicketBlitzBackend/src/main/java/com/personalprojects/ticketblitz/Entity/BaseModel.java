package com.personalprojects.ticketblitz.Entity;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
  @Id
  @GeneratedValue
  @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID")
  private UUID id;
}
