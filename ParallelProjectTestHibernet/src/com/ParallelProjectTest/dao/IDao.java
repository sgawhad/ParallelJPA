package com.ParallelProjectTest.dao;

import java.sql.SQLException;

import com.ParallelProjectTest.Exception.BalanceException;
import com.ParallelProjectTest.Exception.WalletException;
import com.ParallelProjectTest.bean.CustomerBean;

public interface IDao {

	CustomerBean creatAccount(int acntNo,String name, String mobile, String email) throws WalletException, SQLException;

	boolean logIn(String acntNo) throws NumberFormatException, SQLException;
	
	Double showBalance (String acntNo) throws NumberFormatException, SQLException;
	
	void deposit(String acntNo,String amount) throws NumberFormatException, SQLException;
	
	void withdraw(String acntNo,String amount) throws BalanceException, NumberFormatException, SQLException;
	
	void fundTransfer(String sendAccNo,String recAcntNo, String amount) throws BalanceException, NumberFormatException, SQLException;
	
	String printTransaction(String acntNo);
	
	CustomerBean showDetails (int acntNo);
	
	

}
