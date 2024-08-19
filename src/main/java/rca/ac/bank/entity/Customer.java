package rca.ac.bank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "firstname",nullable = false)
    private String firstname;

    @Column(name = "lastname",nullable = false)
    private String lastname;

    @NotBlank(message = "email is required")
    @Email(message = "not an email")
    @Column(name = "email", nullable = false)
    private String email;

    @Min(value=0,message = "the balance cannot be zero")
    private double balance;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<>();

    @NotBlank(message = "phone number is required")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,10}$", message = "invalid number")
    @Column(name = "mobile_phone", nullable = false,unique = true)
    private int mobile_phone;

    @Column(name = "lastUpdateTime",nullable = false)
    private LocalDateTime lastUpdateTime;

    @PreUpdate
    public void setLastUpdateTime() {
        this.lastUpdateTime = LocalDateTime.now();
    }
}
