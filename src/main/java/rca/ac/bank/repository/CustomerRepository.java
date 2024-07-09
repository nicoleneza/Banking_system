package rca.ac.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rca.ac.bank.entity.Account;
import rca.ac.bank.entity.Customer;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Account,UUID> {

}
