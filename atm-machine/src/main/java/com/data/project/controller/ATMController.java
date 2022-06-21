package com.data.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.data.project.entity.Accounts;
import com.data.project.model.AccountsModel;
import com.data.project.model.AccountsWithdrawalModel;
import com.data.project.service.ATMService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class ATMController {


	@Autowired
	ATMService atmService; 

	
	@PostMapping("/checkBalance")
	public ResponseEntity<AccountsModel> findBalance( @RequestBody Accounts accounts) throws Exception
	{		
		
		AccountsModel accountsModel = atmService.calculateAccountBalance(accounts);
		return  ResponseEntity.ok().body(accountsModel);

	}
	
	@PostMapping("/withdrawMoney")
	public ResponseEntity<AccountsWithdrawalModel> withdrawAmount( @RequestBody Accounts accounts,@RequestParam int amount)
	{
		
		AccountsWithdrawalModel accountsWithdrawalModel =  atmService.withdraw(accounts,amount);
		return  ResponseEntity.ok().body(accountsWithdrawalModel);
	
	}

	
}
