package com.data.project.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Accounts implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String accountNo;
	
	private int pin;
	
	private int openingBalance;
	
	private int overDraft;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(int openingBalance) {
		this.openingBalance = openingBalance;
	}

	public int getOverDraft() {
		return overDraft;
	}

	public void setOverDraft(int overDraft) {
		this.overDraft = overDraft;
	}

	@Override
	public String toString() {
		return "Accounts [accountNo=" + accountNo + ", pin=" + pin + ", openingBalance=" + openingBalance
				+ ", overDraft=" + overDraft + "]";
	}
	
	
}
