package com.data.project.model;

import java.io.Serializable;

public class AccountsModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String accountNo;
	
	private int balance;
	
	private int maximumWithdrawalLimit;


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

	public int getMaximumWithdrawalLimit() {
		return maximumWithdrawalLimit;
	}

	public void setMaximumWithdrawalLimit(int maximumWithdrawalLimit) {
		this.maximumWithdrawalLimit = maximumWithdrawalLimit;
	}

	@Override
	public String toString() {
		return "AccountsModel [accountNo=" + accountNo + ", balance=" + balance + ", maximumWithdrawalLimit="
				+ maximumWithdrawalLimit + "]";
	}



	
	
}
