package com.data.project;

import org.h2.command.dml.MergeUsing.When;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.data.project.entity.Accounts;
import com.data.project.entity.AtmNotes;
import com.data.project.exception.AccountInvalidException;
import com.data.project.exception.PINInvalidException;
import com.data.project.model.AccountsModel;
import com.data.project.repository.AccountsRepository;
import com.data.project.repository.AtmNotesRepository;
import com.data.project.service.ATMService;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class AtmMachineApplicationTests {

	
	@Mock
	AccountsRepository accountRepo;
	
	@Mock
	AtmNotesRepository atmNotesRepo;
	
	@InjectMocks
	ATMService atmService;
	
	
	 @BeforeEach
	    void setUp() {
	        Accounts accounts =
	        		Accounts.builder()
	                .accountNo("123456789")
	                .pin(1234)
	                .openingBalance(800)
	                .overDraft(200)
	                .build();
	        
	        List<AtmNotes> atmNotesList = new LinkedList<AtmNotes>();
	        
	        AtmNotes atmNotes1 = 
	        		AtmNotes.builder()
	        		.noteType(50)
	        		.noteCount(10)
	        		.build();
	        AtmNotes atmNotes2 = 
	        		AtmNotes.builder()
	        		.noteType(20)
	        		.noteCount(10)
	        		.build();
	        AtmNotes atmNotes3 = 
	        		AtmNotes.builder()
	        		.noteType(10)
	        		.noteCount(10)
	        		.build();
	        AtmNotes atmNotes4 = 
	        		AtmNotes.builder()
	        		.noteType(5)
	        		.noteCount(10)
	        		.build();
	        
	        atmNotesList.add(atmNotes1);
	        atmNotesList.add(atmNotes2);
	        atmNotesList.add(atmNotes3);
	        atmNotesList.add(atmNotes4);
	       
	    }

	 
		@Test
		public void calculateAccountBalanceInvalidAccountTest() {

			Accounts accounts = new Accounts();
			accounts.setAccountNo("1234567");
			accounts.setPin(1234);
			
			
			System.out.println(accountRepo.findAll());
			
			given(accountRepo.findById(accounts.getAccountNo())).willReturn(Optional.empty());
			
			assertThrows(AccountInvalidException.class, () -> {
				atmService.calculateAccountBalance(accounts);
	        });

		}
	 
	
//	@Test
//	public void calculateAccountBalanceInvalidPinTest() {
//
//		Accounts accounts = new Accounts();
//		accounts.setAccountNo("123456789");
//		accounts.setPin(12);
//		
//		//AccountsModel accountModel = new AccountsModel();
//		
//		System.out.println(accountRepo.findAll());
//		
//		given(accountRepo.findById(accounts.getAccountNo())).willReturn(Optional.of(accounts));
//		
//		
//		assertThrows(PINInvalidException.class, () -> {
//			atmService.calculateAccountBalance(accounts);
//        });
//	
//	}
	

	@Test
	public void calculateAccountBalanceTest() {

		Accounts accounts = new Accounts();
		accounts.setAccountNo("123456789");
		accounts.setPin(1234);
		
		//AccountsModel accountModel = new AccountsModel();
		
		System.out.println(accountRepo.findAll());
		
		given(accountRepo.findById(accounts.getAccountNo())).willReturn(Optional.of(accounts));

		atmService.calculateAccountBalance(accounts);
	
	}
	
	@Test
	public void withdrawAmountInvalidAccountTest() {

		Accounts accounts = new Accounts();
		accounts.setAccountNo("1234567");
		accounts.setPin(1234);
		
		int amount =100;
		
		System.out.println(accountRepo.findAll());
		
		given(accountRepo.findById(accounts.getAccountNo())).willReturn(Optional.empty());
		
		assertThrows(AccountInvalidException.class, () -> {
			atmService.withdraw(accounts,amount);
        });

	}
//	
//	@Test
//	public void withdrawAmountInvalidPinTest() {
//
//		Accounts accounts = new Accounts();
//		accounts.setAccountNo("123456789");
//		accounts.setPin(123);
//		
//		int amount =100;
//		
//		System.out.println(accountRepo.findAll());
//		
//		given(accountRepo.findById(accounts.getAccountNo())).willReturn(Optional.of(accounts));
//		
//		assertThrows(PINInvalidException.class, () -> {
//			atmService.withdraw(accounts,amount);
//        });
//
//	}
//	
//
//	@Test
//	public void withdrawAmountTest() {
//
//		Accounts accounts = new Accounts();
//		accounts.setAccountNo("123456789");
//		accounts.setPin(1234);
//		
//		int amount =100;
//		
//		System.out.println(accountRepo.findAll());
//		
//		given(accountRepo.findById(accounts.getAccountNo())).willReturn(Optional.of(accounts));
//		
//		atmService.withdraw(accounts,amount);
//		
//
//	}
	

}
