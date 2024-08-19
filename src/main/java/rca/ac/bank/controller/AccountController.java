package rca.ac.bank.controller;

import org.apache.coyote.Constants;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
//import rca.ac.bank.dto.AccountDto;
import rca.ac.bank.entity.Account;
import rca.ac.bank.payload.response.ApiResponse;
import rca.ac.bank.service.AccountService;
//import rca.ac.bank.utils.Constants;

import java.util.UUID;

@RestController
@RequestMapping("/api/account")
public class AccountController  {

    @Autowired
    public AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createAccount(@Validated @RequestParam UUID customerId){
        Account createdAccount = accountService.createAccount(customerId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("account created successfully",createdAccount));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAccountById(@PathVariable UUID id){
        Account getAccount = accountService.getAccountById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("account retrieved",getAccount));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllAccounts(@RequestParam(value = "page",defaultValue = "0") int page,@RequestParam(value = "size",defaultValue = "100") int size){
        Pageable pageable = PageRequest.of(page,size,Sort.Direction.ASC,"id");
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("all accounts received!!",accountService.getAll(pageable)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAccount(@PathVariable UUID id){
        accountService.deleteAccount(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("deleted!!"));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateAccount(@PathVariable UUID id, Account account){
        Account newAccount = accountService.updateAccount(id, account);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("updated!!", newAccount));
    }

}
