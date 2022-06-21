package com.data.project.model;

import java.io.Serializable;
import java.util.List;

import com.data.project.entity.AtmNotes;

public class AccountsWithdrawalModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String accountNo;
	
	private int balance;
	
	private List<AtmNotes> notesList;
	
	private int withdrawalAmount;
	
	private String status;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}


	public int getWithdrawalAmount() {
		return withdrawalAmount;
	}

	public void setWithdrawalAmount(int withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<AtmNotes> getNotesList() {
		return notesList;
	}

	public void setNotesList(List<AtmNotes> notesList) {
		this.notesList = notesList;
	}

	@Override
	public String toString() {
		return "AccountsWithdrawalModel [accountNo=" + accountNo + ", balance=" + balance + ", notesList=" + notesList
				+ ", withdrawalAmount=" + withdrawalAmount + ", status=" + status + "]";
	}

	
}
