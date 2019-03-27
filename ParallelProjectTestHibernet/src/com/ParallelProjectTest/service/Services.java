package com.ParallelProjectTest.service;

import java.sql.SQLException;

import com.ParallelProjectTest.Exception.BalanceException;
import com.ParallelProjectTest.Exception.WalletException;
import com.ParallelProjectTest.bean.CustomerBean;
import com.ParallelProjectTest.dao.Dao;
import com.ParallelProjectTest.dao.Transaction;

public class Services implements IService {
	Dao dao = new Dao();

	@Override
	public boolean validateChoice(String choice) {
		if (choice.matches(choice))
			return true;
		return false;
	}

	@Override
	public boolean validateAcntNo(String acnNto) {
		if (acnNto.matches(ACC_NO))
			return true;

		return false;
	}

	@Override
	public boolean validateUserName(String userName) {
		if (userName.matches(USER_NAME_PATTERN))
			return true;
		return false;
	}

	@Override
	public boolean validateMobileNo(String mobileNo) {
		if (mobileNo.matches(MOBILE_PATTERN))
			return true;
		return false;
	}

	@Override
	public boolean validateEmail(String email) {
		if (email.matches(EMAIL_PATTERN))
			return true;
		return false;
	}

	@Override
	public boolean validateAmount(String amount) {
		if (amount.matches(AMOUNT))
			return true;
		return false;
	}

	@Override
	public CustomerBean creatAccount(String name, String mobile, String email)
			throws WalletException, SQLException {
		int acntNo = (int) (Math.random() * 1000);
	return	dao.creatAccount(acntNo, name, mobile, email);
	}

	@Override
	public boolean logIn(String acntNo) throws NumberFormatException,
			SQLException {
		return dao.logIn(acntNo);

	}

	@Override
	public Double showBalance(String acntNo) throws NumberFormatException,
			SQLException {
		return dao.showBalance(acntNo);
	}

	@Override
	public void deposit(String acntNo, String amount)
			throws NumberFormatException, SQLException {
		dao.deposit(acntNo, amount);
	}

	@Override
	public void withdraw(String acntNo, String amount) throws BalanceException,
			NumberFormatException, SQLException {
		dao.withdraw(acntNo, amount);
	}

	@Override
	public void fundTransfer(String sendAccNo, String recAcntNo, String amount)
			throws BalanceException, NumberFormatException, SQLException {
		dao.fundTransfer(sendAccNo, recAcntNo, amount);
	}

	@Override
	public String printTransactions(String acntNo) {
		return dao.printTransaction(acntNo);
	}

	@Override
	public CustomerBean showDetails(int acntNo) {
		return dao.showDetails(acntNo);
	}

}
