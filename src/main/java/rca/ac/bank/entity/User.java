package rca.ac.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rca.ac.bank.enums.Gender;
import rca.ac.bank.enums.UserStatus;

import java.io.File;
//import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @Column(name = "email")
    public String email;

    @Column(name = "firstname")
    public String firstname;

    @Column(name = "lastname")
    public String lastname;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    public Gender gender;


    public File profileImage;
    public String activationCode;

    public UserStatus status = UserStatus.PENDING;
    public LocalDateTime activationCodeExpires;


}
