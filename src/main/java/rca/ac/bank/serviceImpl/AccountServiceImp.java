package rca.ac.bank.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import rca.ac.bank.ResourceNotFoundException;
import rca.ac.bank.entity.Account;
import rca.ac.bank.entity.Customer;
import rca.ac.bank.repository.AccountRepository;
import rca.ac.bank.repository.CustomerRepository;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImp {

    @Autowired
    public final CustomerRepository customerRepository;
    public final AccountRepository accountRepository;

    public Account createAccount(UUID customerId){

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId)).getCustomer();

        String accountNum = generateAccountNumber();
        Account account = new Account();
        account.setAccountNumber(Integer.parseInt(accountNum));
        account.setBalance(0);
        account.setCustomer(customer);
        return accountRepository.save(account);
    }

    private String generateAccountNumber(){
        Random random = new Random();
        int digits = 10;
        StringBuilder sb = new StringBuilder();

        for(int i = 0;i<10;i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }


}

