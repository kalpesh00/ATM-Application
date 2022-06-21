package com.data.project.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.project.entity.Accounts;
import com.data.project.entity.AtmNotes;
import com.data.project.exception.AccountBalanceLimitException;
import com.data.project.exception.AccountInvalidException;
import com.data.project.exception.AtmAmountLimitException;
import com.data.project.exception.InvalidDispenseAmountException;
import com.data.project.exception.PINInvalidException;
import com.data.project.model.AccountsModel;
import com.data.project.model.AccountsWithdrawalModel;
import com.data.project.repository.AccountsRepository;
import com.data.project.repository.AtmNotesRepository;

@Service
public class ATMService {

	
	@Autowired
	AccountsRepository accountRepo;
	
	@Autowired
	AtmNotesRepository atmNotesRepo;
	
	
	//Calculates the Account Balance for the input Account No. 
	
	@Transactional
	public AccountsModel calculateAccountBalance(Accounts accounts) {
		
		AccountsModel accountModel = new AccountsModel();
		
		String accountNo = accounts.getAccountNo();
		int pin = accounts.getPin();
		
		
		Optional<Accounts> accountObj = accountRepo.findById(accountNo);
		
		//Validation of Account No.
		
		if(!accountObj.isPresent())
		{
			throw new AccountInvalidException("Account No is Invalid");
		}
		else
		{
			Accounts accountsData = accountObj.get();
			
			// Validation of PIN
			if(!(accountsData.getPin()==pin))
			{
				throw new PINInvalidException("PIN is Invalid");
			}
			
			accountModel.setAccountNo(accountNo);
			accountModel.setBalance(accountsData.getOpeningBalance());

			List<AtmNotes> atmNotesList = atmNotesRepo.findAll();
			
			
			//Finding the Total Amount in ATM
			
			int totalATMAmount = 0;
			if(atmNotesList.size()>0)
			{			
				
				for(int i=0;i<atmNotesList.size();i++)
				{
					totalATMAmount = totalATMAmount+(atmNotesList.get(i).getNoteType() * atmNotesList.get(i).getNoteCount());
				}
				
			}
			
			//Logic for Finding the withdrawal Limit
			
			int maxWithdrawalMoneyAccount = accountsData.getOpeningBalance()+accountsData.getOverDraft();
			
			if(maxWithdrawalMoneyAccount<=totalATMAmount)
			{
				accountModel.setMaximumWithdrawalLimit(maxWithdrawalMoneyAccount);
			}
			else
			{
				accountModel.setMaximumWithdrawalLimit(totalATMAmount);
			}
			
		}

		return accountModel;
	}

	
	@Transactional
	public AccountsWithdrawalModel withdraw(Accounts accounts, int amount) {
		
		AccountsWithdrawalModel accWithdrawalModel = new AccountsWithdrawalModel();
		
		String accountNo = accounts.getAccountNo();
		int pin = accounts.getPin();
		
		Optional<Accounts> accountObj = accountRepo.findById(accountNo);
		
		// Account No. Validation
		if(!accountObj.isPresent())
		{
			throw new AccountInvalidException("Account No is Invalid");
		}
		else
		{
			Accounts accountsData =  accountObj.get();
			
			// PIN Validation
			
			if(!(accountsData.getPin()==pin))
			{
				throw new PINInvalidException("PIN is Invalid");
			}
			
			List<AtmNotes> atmNotesList = atmNotesRepo.findAllByOrderByNoteTypeDesc();
			
			HashMap<Integer, Integer> noteCountMap = new LinkedHashMap<Integer, Integer>(); 
			
			
			int totalATMAmount=0;
			if(atmNotesList.size()>0)
			{
				for(int i=0;i<atmNotesList.size();i++)
				{
					totalATMAmount = totalATMAmount+(atmNotesList.get(i).getNoteType() * atmNotesList.get(i).getNoteCount());
					
					noteCountMap.put(atmNotesList.get(i).getNoteType(), atmNotesList.get(i).getNoteCount());
					
				}
			}
			
			// Validation of ATM Withdrawal Limit
			
			if(totalATMAmount<amount)
			{
				throw new AtmAmountLimitException("ATM has less amount. ATM withdrawal limit is "+totalATMAmount);
			}
			else if(amount>(accountsData.getOpeningBalance()+accountsData.getOverDraft()))
			{
				int withdrawalAmount = accountsData.getOpeningBalance()+accountsData.getOverDraft();
				throw new AccountBalanceLimitException("Balance is low. ATM withdrawal limit is "+withdrawalAmount);
			}
			
			// Logic for finding the note count and note type based on the amount requested
			
			int tempAmount = amount;
			
			List<AtmNotes> withdrawNotesList = new LinkedList<AtmNotes>(); 
			
			int i=0;
			while(tempAmount>0 && i<atmNotesList.size())
			{
				int noteType = atmNotesList.get(i).getNoteType();
				int divisor = tempAmount/noteType;
				int noteCount =  atmNotesList.get(i).getNoteCount();
				
				if(divisor==0 || noteCount==0 )
					{
					i++;
					continue;	
					}
				
				
				if(divisor<=noteCount)
				{
					AtmNotes atmNotesObj = new AtmNotes();
					atmNotesObj.setNoteCount(divisor);
					atmNotesObj.setNoteType(noteType);
					withdrawNotesList.add(atmNotesObj);
					
					tempAmount = tempAmount-(divisor*noteType);
				}
				else
				{
					AtmNotes atmNotesObj = new AtmNotes();
					atmNotesObj.setNoteCount(noteCount);
					atmNotesObj.setNoteType(noteType);
					withdrawNotesList.add(atmNotesObj);
					
					tempAmount = tempAmount-(noteCount*noteType);
				}
				i++;
			}
			
			if(tempAmount>0)
			{
				throw new InvalidDispenseAmountException("Invalid Amount Entered");
			}

			// DB Operations for updating Accounts and ATMNotes Table
			
			for(int j=0;j<withdrawNotesList.size();j++)
			{
				AtmNotes atmNotes = withdrawNotesList.get(j);
				
				int withdrawnNoteCount = atmNotes.getNoteCount();
				int currentNoteCount = noteCountMap.get(atmNotes.getNoteType());
				
				atmNotes.setNoteCount(currentNoteCount-withdrawnNoteCount);
				
				atmNotesRepo.save(atmNotes);
				
				atmNotes.setNoteCount(withdrawnNoteCount);
				
			}
			
			int newBalance = accountsData.getOpeningBalance() - amount;
			accountsData.setOpeningBalance(newBalance);
			if(newBalance<0)
			{
				accountsData.setOpeningBalance(0);
				int newOverDraft = accountsData.getOverDraft()+newBalance;
				accountsData.setOverDraft(newOverDraft);
			}
			
			accountRepo.save(accountsData);
			
			accWithdrawalModel.setAccountNo(accountNo);
			accWithdrawalModel.setNotesList(withdrawNotesList);
			accWithdrawalModel.setStatus("Success");
			accWithdrawalModel.setWithdrawalAmount(amount);
			accWithdrawalModel.setBalance(accountsData.getOpeningBalance()+accountsData.getOverDraft());
		}
	
		return accWithdrawalModel;
	}

	
}
