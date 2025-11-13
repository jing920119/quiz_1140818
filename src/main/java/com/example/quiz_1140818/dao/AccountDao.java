package com.example.quiz_1140818.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.quiz_1140818.entity.Account;

import jakarta.transaction.Transactional;

@Repository
public interface AccountDao extends JpaRepository<Account,String>{

	
	@Transactional
	@Modifying
	@Query(value = "insert into account(account, password) values (?1,?2)",nativeQuery = true)
	public void addInfo(String account,String password);
	
	@Query(value = "select count(account) from account where account = ?1",nativeQuery = true)
	public int selectCountByAccount(String account);
	
	@Query(value = "select * from account where account = ?1",nativeQuery = true)
	public Account selectByAccount(String account);
}
