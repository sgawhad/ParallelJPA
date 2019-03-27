package com.ParallelProjectTest.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ParallelProjectTest.Exception.BalanceException;
import com.ParallelProjectTest.Exception.WalletException;
import com.ParallelProjectTest.bean.CustomerBean;
import com.ParallelProjectTest.bean.TransactionsSummary;

public class Dao implements IDao {

	static Map<Integer, CustomerBean> detiMap = new HashMap<Integer, CustomerBean>();

	static Map<Integer, Double> balMap = new HashMap<Integer, Double>();

	Transaction[] txns = new Transaction[10];
	int idx;

	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("JPA-PU");
	EntityManager em = factory.createEntityManager();

	// EntityManager em1= new EntityManager();

	@Override
	public CustomerBean creatAccount(int acntNo, String name, String mobile,
			String email) throws WalletException, SQLException {
		try {
			em.getTransaction().begin();

			CustomerBean cust = new CustomerBean(acntNo, name,
					Long.parseLong(mobile), email);

			em.persist(cust);

			TransactionsSummary trns = new TransactionsSummary(0, 0, "IB", cust);

			em.persist(trns);

			em.getTransaction().commit();

			System.out.println("Customer added to database.");

			// detiMap.put(acntNo, cust);
			//
			// balMap.put(acntNo, 0.0); // As initial balance is zero

			// txns[idx++] = new Transaction(acntNo, "Initial", 0, 0);
			return em.find(CustomerBean.class, (acntNo));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			em.close();
			factory.close();
		}
		return null;

	}

	@Override
	public boolean logIn(String acntNo) throws NumberFormatException,
			SQLException {

		CustomerBean cust = em.find(CustomerBean.class,
				Integer.parseInt(acntNo));
		if (cust == null) {
			return false;
		}
		// System.out.println("Through JPA" + cust);
		return true;

	}

	@Override
	public Double showBalance(String acntNo) throws NumberFormatException,
			SQLException {
		CustomerBean bal = em
				.find(CustomerBean.class, Integer.parseInt(acntNo));
		return bal.getBalance();

		// return balMap.get(Integer.parseInt(acntNo));
	}

	@Override
	public void deposit(String acntNo, String amount)
			throws NumberFormatException, SQLException {

		em.getTransaction().begin();

		CustomerBean bal = em
				.find(CustomerBean.class, Integer.parseInt(acntNo));
		double balance = bal.getBalance() + Double.parseDouble(amount);
		bal.setBalance(balance);
		em.merge(bal);
		TransactionsSummary trns = new TransactionsSummary(
				Integer.parseInt(amount), balance, "CR", bal);
		em.persist(trns);

		em.getTransaction().commit();
		// balMap.put(Integer.parseInt(acntNo), (balMap.get(Integer
		// .parseInt(acntNo)) + Double.parseDouble(amount)));
		//
		// txns[idx++] = new Transaction(Integer.parseInt(acntNo), "CR",
		// balMap.get(Integer.parseInt(acntNo)),
		// Double.parseDouble(amount));

	}

	@Override
	public void withdraw(String acntNo, String amount) throws BalanceException,
			NumberFormatException, SQLException {

		em.getTransaction().begin();

		CustomerBean bal = em
				.find(CustomerBean.class, Integer.parseInt(acntNo));

		if (bal.getBalance() > Double.parseDouble(amount)) {
			double balance = bal.getBalance() - Double.parseDouble(amount);
			bal.setBalance(balance);
			em.merge(bal);
			TransactionsSummary trns = new TransactionsSummary(
					Integer.parseInt(amount), balance, "WR", bal);
			em.persist(trns);

			em.getTransaction().commit();

		} else {
			System.out
					.println("You have insufficient balance. Please add money first");
		}
		/*
		 * if (Double.parseDouble(amount) <=
		 * balMap.get(Integer.parseInt(acntNo))) {
		 * 
		 * balMap.put(Integer.parseInt(acntNo), (balMap.get(Integer
		 * .parseInt(acntNo)) - Double.parseDouble(amount)));
		 * 
		 * System.out.println(amount + " withdraw from your account");
		 * 
		 * txns[idx++] = new Transaction(Integer.parseInt(acntNo), "WR",
		 * balMap.get(Integer.parseInt(acntNo)), Double.parseDouble(amount));
		 * 
		 * } else { System.out
		 * .println("You have insufficient balance. Please add money first"); }
		 */
	}

	@Override
	public void fundTransfer(String sendAccNo, String recAcntNo, String amount)
			throws BalanceException, NumberFormatException, SQLException {

		em.getTransaction().begin();

		CustomerBean balRec = em.find(CustomerBean.class,
				Integer.parseInt(recAcntNo));
		if (balRec == null) {
			System.out.println("Receiver not found");
		}
		CustomerBean balSender = em.find(CustomerBean.class,
				Integer.parseInt(sendAccNo));

		if (balSender.getBalance() > Double.parseDouble(amount)) {

			double balanceSender = balSender.getBalance()
					- Double.parseDouble(amount);
			balSender.setBalance(balanceSender);
			em.merge(balSender);
			TransactionsSummary trnsSend = new TransactionsSummary(
					Double.parseDouble(amount), balanceSender, "FT", balSender);
			em.persist(trnsSend);

			double balanceReceiver = balRec.getBalance()
					+ Double.parseDouble(amount);
			balRec.setBalance(balanceReceiver);
			em.merge(balRec);
			TransactionsSummary trnsRec = new TransactionsSummary(
					Double.parseDouble(amount), balanceReceiver, "FT", balRec);
			em.persist(trnsRec);
			em.getTransaction().commit();

		} else {
			System.out.println("Insufficent balance");
		}

	}

	/*
	 * if (balMap.containsKey(Integer.parseInt(recAcntNo))) {
	 * 
	 * if (Double.parseDouble(amount) <= balMap.get(Integer
	 * .parseInt(sendAccNo))) {
	 * 
	 * balMap.put( Integer.parseInt(sendAccNo),
	 * balMap.get(Integer.parseInt(sendAccNo)) - Double.parseDouble(amount));
	 * 
	 * balMap.put( Integer.parseInt(recAcntNo),
	 * balMap.get(Integer.parseInt(recAcntNo)) + Double.parseDouble(amount));
	 * 
	 * System.out.println(amount + " Transfer done ");
	 * 
	 * txns[idx++] = new Transaction(Integer.parseInt(sendAccNo), "FT",
	 * balMap.get(Integer.parseInt(sendAccNo)), Double.parseDouble(amount));
	 * 
	 * txns[idx++] = new Transaction(Integer.parseInt(recAcntNo), "FT",
	 * balMap.get(Integer.parseInt(recAcntNo)), Double.parseDouble(amount));
	 * 
	 * }
	 * 
	 * } else { System.out
	 * .println("Receiver not found \nPlease enter valid details"); }
	 */

	@Override
	public String printTransaction(String acntNo) {
		for (int i = 0; i < idx; i++) {
			txns[i].print(Integer.parseInt(acntNo));
		}
		return null;
	}

	@Override
	public CustomerBean showDetails(int acntNo) {
		return detiMap.get(acntNo);
	}

}
