package rca.ac.bank.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rca.ac.bank.entity.Customer;

import java.util.UUID;

@Service
public interface CustomerService {
    Customer createCustomer(Customer customer);

    Customer getCustomer(UUID id);

    Object getAllCustomer(Pageable pageable);

    void deleteCustomer(UUID id);

    Customer updateCustomer(UUID id, Customer customer);
}
