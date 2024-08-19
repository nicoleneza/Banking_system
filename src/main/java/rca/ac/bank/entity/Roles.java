package rca.ac.bank.entity;

import jakarta.persistence.*;
import lombok.*;
import rca.ac.bank.enums.Role;

import java.util.UUID;

@Entity
@Table(name = "role")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @Enumerated
    public Role role;
    public String description;
}
