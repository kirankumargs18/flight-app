package com.globallogic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passengerId;

    @Size(max = 20, message = "First name can contain max of 20 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Firstname should accept only alphabets")
    private String firstName;

    @Size(max = 20, message = "Last Name can contain max of 20 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last Name should accept only alphabets")
    private String lastName;

    @Email(regexp = ".+@.+\\..{2,3}", message = "Invalid email")
    private String email;
}
