package rca.ac.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
//import rca.ac.bank.dto.AccountDto;
import rca.ac.bank.entity.Account;
import rca.ac.bank.payload.response.ApiResponse;
import rca.ac.bank.service.AccountService;

import java.util.UUID;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    public AccountService accountService;



    @PostMapping
    public ResponseEntity<ApiResponse> createAccount(@Validated @RequestParam UUID customerId){
        Account createdAccount = accountService.createAccount(customerId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("account created successfully",createdAccount));
    }
}
