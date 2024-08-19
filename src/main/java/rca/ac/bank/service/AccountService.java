package rca.ac.bank.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
//import rca.ac.bank.dto.AccountDto;
import rca.ac.bank.entity.Account;

import java.util.UUID;

@Service
public interface AccountService {

    Account createAccount(UUID customerId);

    Account getAccountById(UUID id);

    Page<Account> getAll(Pageable pageable);

    void deleteAccount(UUID id);

    Account updateAccount(UUID id, Account account);
}
