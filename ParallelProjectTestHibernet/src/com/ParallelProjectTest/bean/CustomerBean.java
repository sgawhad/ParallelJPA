package com.ParallelProjectTest.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
// compulsory
@Table(name = "bankuserdetails")
public class CustomerBean {

	@Id
	// compulsory
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", length = 5)
	private int acntNo; // auto generated

	@Column(name = "Name", length = 20)
	private String name;

	@Column(name = "Mobile", length = 10)
	private long mobile;

	@Column(name = "email", length = 20)
	private String email;
	
	@Column(name="balance",length=10)
	private double balance;
	
	

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAcntNo() {
		return acntNo;
	}

	public void setAcntNo(int acntNo) {
		this.acntNo = acntNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CustomerBean() {
	}

	public CustomerBean(int acntNo, String name, long mobile, String email) {
		this.acntNo = acntNo;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.balance=0;
	}

	@Override
	public String toString() {
		return "Account No = " + acntNo + "\nName = " + name + "\nMobile No = "
				+ mobile + "\nEmail Id = " + email + "\n";
	}

}
