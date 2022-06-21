package com.data.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.data.project.entity.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, String>{

	
	//@Query(value = "SELECT * FROM ACCOUNTS acc WHERE acc.account_no=?1 AND acc.pin =?2", nativeQuery = true)
	public Optional<Accounts> findByAccountNoAndPin(String accountNo, int pin);
	

	
}
