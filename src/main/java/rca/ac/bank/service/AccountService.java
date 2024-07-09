package rca.ac.bank.service;

import org.springframework.stereotype.Service;
//import rca.ac.bank.dto.AccountDto;
import rca.ac.bank.entity.Account;

import java.util.UUID;

@Service
public interface AccountService {

    Account createAccount(UUID customerId);
}
