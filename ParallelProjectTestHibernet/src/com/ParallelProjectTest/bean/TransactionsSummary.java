package com.ParallelProjectTest.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
// compulsory
@Table(name = "transactions")
public class TransactionsSummary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sr_no")
	private int srNo;

	@Column(name = "amount", length = 10)
	private double amount;

	@Column(name = "balance", length = 10)
	private double balance;

	@Column(name = "type", length = 10)
	private String type;

	@ManyToOne
	private CustomerBean customer;

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CustomerBean getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}

	public TransactionsSummary() {
	}

	public TransactionsSummary(double amount, double balance, String type,
			CustomerBean customer) {
		super();
		this.amount = amount;
		this.balance = balance;
		this.type = type;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return type + "\t" + amount + "\t" + balance;
	}

	public String print() {
		return type + "\t" + amount + "\t" + balance;
	}

}
