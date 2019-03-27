package com.ParallelProjectTest.dao;

public class Transaction {

	private int acntNo;
	private String type;
	private double balance, amount;

	public Transaction(int acntNo, String type, double balance, double amount) {
		super();
		this.acntNo = acntNo;
		this.type = type;
		this.balance = balance;
		this.amount = amount;
	}

	public void print(int acntNo) {
		if (this.acntNo == acntNo) {
			System.out.println(type + "\t" + amount + "\t" + balance);
		}
	}

}
