package com.tavant.practicalrestapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tavant.practicalrestapi.exception.AccountNotFoundException;
import com.tavant.practicalrestapi.exception.EmptyObjectException;
import com.tavant.practicalrestapi.exception.NoDataFoundException;
import com.tavant.practicalrestapi.model.Account;
import com.tavant.practicalrestapi.repository.AccountRepository;

@RestController
@RequestMapping("/api/account")
public class EmployeeController {

	@Autowired
	AccountRepository accountRepository;

	@GetMapping
	public String getAccountNumber() {
		return "Hello Sir Welcome to Our Bank!!!";
	}

	
    @GetMapping("/all") 
    public List<Account> getAllAccount() throws NoDataFoundException {

    
        return Optional.ofNullable(
                accountRepository.findAll().isEmpty() ? null :accountRepository.findAll()
                ).orElseThrow(()->new NoDataFoundException("NO RECORDS FOUND"));
        
    }
    
    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getAccountNumber(@PathVariable("accountNumber") Integer id) throws AccountNotFoundException {

        
        Optional<Account> optional = accountRepository.findById(id);
        
        if(optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }
        else {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EmployeeNotFoundException("RECORD NOT FOUND"));
            throw new AccountNotFoundException("Account Not Found");
        }
    }
	
    
    
    @PutMapping("/{accountNumber}")
    public ResponseEntity<Account> updateAccount(@PathVariable("accountNumber")
    Integer id , @Valid @RequestBody Account accountDetails)
            throws AccountNotFoundException {
        
        Account account = accountRepository.findById(id).
                orElseThrow(()->new AccountNotFoundException("Account Not Found"));
                
        account.setEmail(accountDetails.getEmail());
        account.setAddress(accountDetails.getAddress());
        account.setFirstName(accountDetails.getFirstName());
        account.setLastName(accountDetails.getLastName());
        
        
        final Account newDetails= accountRepository.save(account);
        return ResponseEntity.ok(newDetails);
    }
    
    
    
    @PostMapping
	public Account addAccount(@RequestBody @Valid Account account) throws EmptyObjectException {

		if (account.getAccountNumber() == null)
			throw new EmptyObjectException("No Account");
		return accountRepository.save(account);

	}
    
    @DeleteMapping("/{accountNumber}" )
    public void deleteAccount(@PathVariable("accountNumber") Integer id) throws AccountNotFoundException
    {
    	Optional<Account> optional = accountRepository.findById(id);
        
        if(optional.isPresent()) {
        	accountRepository.deleteById(id);
        }
        else {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EmployeeNotFoundException("RECORD NOT FOUND"));
            throw new AccountNotFoundException("Records Not Found");
        }
    	
    }
}
