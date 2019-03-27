package com.ParallelProjectTest.ui;

import java.sql.SQLException;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ParallelProjectTest.Exception.BalanceException;
import com.ParallelProjectTest.Exception.WalletException;
import com.ParallelProjectTest.service.Services;

public class ExecutiorMain {
	public static void main(String[] args) throws BalanceException,
			WalletException, SQLException {

		Scanner sc = new Scanner(System.in);
		String name;
		String mobile;
		String email;
		String acntNo;

		System.out.println("Welcome to XYZ wallet");

		while (true) {

			System.out.println("Choose a service");
			System.out.println("1.Create Account \n2.Login\n3.Exit");
			Services service = new Services();

			String choice = sc.next();
			boolean isValid = service.validateChoice((choice));
			if (isValid) {
				int uChoiser = 0;
				try {
					uChoiser = Integer.parseInt(choice);
				} catch (NumberFormatException e) {
					System.out.println("Enter only number");
				}

				if (uChoiser == 1) {
					System.out.println("Enter User Details to creat account");
					while (true) {
						System.out.println("Enter Name");
						name = sc.next();
						boolean isValid1 = service.validateUserName(name);
						if (isValid1) {
							break;
						}
						System.out.println("Enter Name Properly");
					}

					while (true) {
						System.out.println("Enter Mobile number");
						mobile = sc.next();
						boolean isValid1 = service.validateMobileNo(mobile);
						if (isValid1) {
							break;
						}
						System.out.println("Enter 10 digit number properly");

					}

					while (true) {
						System.out.println("Enter email id");
						email = sc.next();
						boolean isValid1 = service.validateEmail(email);
						if (isValid1)
							break;
						System.out
								.println("Enter email with @<domain_name>.com ");

					}

					System.out.println(service
							.creatAccount(name, mobile, email));

				} else if (uChoiser == 2) {
					// while (true) {
					System.out.println("Enter Account No to login");
					acntNo = sc.next();
					boolean isValid1 = service.validateAcntNo(acntNo);

					if (isValid1) {
						logoutloop: while (true) {
							while (true) {
								if (service.logIn(acntNo)) {
									System.out.println("Choose Your Service");
									System.out.println("1. Show Balance ");
									System.out.println("2. Deposite ");
									System.out.println("3. Withdraw");
									System.out.println("4. Fund transfer ");
									System.out.println("5. Ptint Transaction");
									System.out
											.println("6. Show Account Details");
									System.out.println("7. Logout");

									String userChoice = sc.next();

									switch (userChoice) {
									case "1":
										System.out
												.println("Your account balance is "
														+ service
																.showBalance(acntNo));
										break;

									case "2":
										System.out
												.println("------Deposit Money-------");
										System.out
												.println("Enter Amount to deposit in your account");
										String amountD = sc.next();

										if (service.validateAmount(amountD)) {
											service.deposit(acntNo, amountD);
											System.out
													.println(amountD
															+ " Deposited to your account ");
										} else {
											System.out
													.println("Enter amount properly");
										}
										break;

									case "3":
										System.out.println("3. Withdraw");
										System.out
												.println("Enter Amount to withdraw from your account");
										String amountW = sc.next();
										if (service.validateAmount(amountW)) {

											service.withdraw(acntNo, amountW);
										} else {
											System.out
													.println("Enter amount properly");
										}
										break;

									case "4":
										System.out.println("4. Fund transfer ");
										System.out
												.println("Enter receivers account no");
										String recAcntNo = sc.next();
										System.out
												.println("Enter amount to transfer");
										String amountT = sc.next();
										service.fundTransfer(acntNo, recAcntNo,
												amountT);
										break;

									case "5":
										System.out
												.println("5. Ptint Transaction");
										service.printTransactions(acntNo);
										break;

									case "6":
										System.out
												.println("6. Show Account Details");
										System.out.println(service
												.showDetails(Integer
														.parseInt(acntNo)));
										break;

									case "7":
										System.out
												.println("Your account is exited");

										break logoutloop;

									default:
										System.out
												.println("Enter From 1 to 7 ");
										break;
									}

									if (userChoice.equals("7")) {
										break;
									}
								} else {
									System.out
											.println("Account doesnot exist ");
//									acntNo = sc.next();
									break logoutloop;
								}

							}
						}
					}
					// }
				} else if (uChoiser == 3) {
					System.out.println("Thank you !!!");
					System.exit(0);
				}
			}

		}

	}

}
