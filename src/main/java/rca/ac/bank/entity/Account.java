package rca.ac.bank.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "Account_number", nullable = false, unique = true)
    private int accountNumber;

    @Column(name = "owner",nullable = false)
    private String accountHolder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer  customer;

    @Column(name = "balance", nullable = false)
    private double balance;
}
