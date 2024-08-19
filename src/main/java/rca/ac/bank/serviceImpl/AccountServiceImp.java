package rca.ac.bank.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rca.ac.bank.controller.AccountController;
import rca.ac.bank.exceptions.ResourceNotFoundException;
import rca.ac.bank.entity.Account;
import rca.ac.bank.entity.Customer;
import rca.ac.bank.repository.AccountRepository;
import rca.ac.bank.repository.CustomerRepository;
import rca.ac.bank.service.AccountService;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {

    @Autowired
    public final CustomerRepository customerRepository;
    @Autowired
    public final AccountRepository accountRepository;

    @Override
    public Account createAccount(UUID customerId){

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));

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

    @Override
    public Account getAccountById(UUID id){
        return accountRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Account","id",id));
    }

    @Override
    public Page<Account> getAll(Pageable pageable){
        return accountRepository.findAll(pageable);
    }

    @Override
    public void deleteAccount(UUID id) {
        //first find the account
        accountRepository.findById(id).orElseThrow(()->new RuntimeException("not found"));
        accountRepository.deleteById(id);
    }

    public Account updateAccount(UUID id, Account account){
        Account existingAccount = accountRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Account","id",id));
          existingAccount.setAccountNumber(account.getAccountNumber());
          existingAccount.setBalance(account.getBalance());
          return accountRepository.save(existingAccount);
    }
}

