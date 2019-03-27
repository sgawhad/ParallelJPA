package com.ParallelProjectTest.service;

import java.sql.SQLException;

import com.ParallelProjectTest.Exception.BalanceException;
import com.ParallelProjectTest.Exception.WalletException;
import com.ParallelProjectTest.bean.CustomerBean;
import com.ParallelProjectTest.dao.Transaction;

public interface IService {

	String CHOICE = "[1-7]{1}";
	String ACC_NO = "[0-9]{1,3}";
	String AMOUNT = "[0-9]{1,10}";
	String USER_NAME_PATTERN = "[a-zA-Z]{1,10}";
	String MOBILE_PATTERN = "[0-9]{10}";
	String EMAIL_PATTERN = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
	

	boolean validateChoice(String choice);

	boolean validateAcntNo(String acnNto);

	boolean validateAmount(String amount);

	boolean validateUserName(String userName);

	boolean validateMobileNo(String mobileNo);

	boolean validateEmail(String email);

	CustomerBean creatAccount(String name, String mobile, String email)
			throws WalletException, SQLException;

	boolean logIn(String acntNo) throws NumberFormatException, SQLException;

	Double showBalance(String acntNo) throws NumberFormatException, SQLException;

	void deposit(String acntNo, String amount) throws NumberFormatException,
			SQLException;

	void withdraw(String acntNo, String amount) throws BalanceException,
			NumberFormatException, SQLException;

	void fundTransfer(String sendAccNo, String recAcntNo, String amount)
			throws BalanceException, NumberFormatException, SQLException;

	String printTransactions(String acntNo);
	
	CustomerBean showDetails (int acntNo);

}
