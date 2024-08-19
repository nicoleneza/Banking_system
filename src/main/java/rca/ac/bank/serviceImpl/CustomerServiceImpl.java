package rca.ac.bank.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import rca.ac.bank.entity.Account;
import rca.ac.bank.entity.Customer;
import rca.ac.bank.exceptions.ResourceNotFoundException;
import rca.ac.bank.repository.AccountRepository;
import rca.ac.bank.repository.CustomerRepository;
import rca.ac.bank.service.CustomerService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    public CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(UUID id) {
        return customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer","id",id));
    }

    @Override
    public Page<Customer> getAllCustomer(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public void  deleteCustomer(UUID id){
        customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer","id",id));
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(UUID id, Customer customer){
        Customer ExistingCustomer = customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer","id",id));
        ExistingCustomer.setFirstname(customer.getFirstname());
        ExistingCustomer.setLastname(customer.getLastname());
        ExistingCustomer.setEmail(customer.getEmail());
        ExistingCustomer.setBalance(customer.getBalance());
        ExistingCustomer.setMobile_phone(customer.getMobile_phone());
        ExistingCustomer.setLastUpdateTime(customer.getLastUpdateTime());
        return customerRepository.save(ExistingCustomer);
    }
}
