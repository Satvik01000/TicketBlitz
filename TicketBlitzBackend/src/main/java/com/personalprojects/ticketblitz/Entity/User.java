package com.personalprojects.ticketblitz.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "ticket_blitz_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseModel {
  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String userName;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;
}
