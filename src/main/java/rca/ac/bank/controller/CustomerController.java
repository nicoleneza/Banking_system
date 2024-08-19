package rca.ac.bank.controller;

import jakarta.persistence.Access;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import rca.ac.bank.entity.Customer;
import rca.ac.bank.payload.response.ApiResponse;
import rca.ac.bank.service.AccountService;
import rca.ac.bank.service.CustomerService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Api/customer")
public class CustomerController {
    @Autowired
    public CustomerService customerService;

    @PostMapping("/newCustomer")
    public ResponseEntity<ApiResponse> createCustomer(@RequestParam Customer customer){
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("created new customer",createdCustomer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCustomer(@PathVariable UUID id){
        Customer customer = customerService.getCustomer(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("success!!",customer));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCustomer(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "100") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("done!!", customerService.getAllCustomer(pageable)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(UUID id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("deleted!!"));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable UUID id, @RequestBody Customer customer){
        Customer updatedCustomer = customerService.updateCustomer(id,customer);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("updated successfully!!",updatedCustomer));
    }
}
