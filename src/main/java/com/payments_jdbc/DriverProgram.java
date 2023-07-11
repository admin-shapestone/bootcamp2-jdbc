package com.payments_jdbc;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DriverProgram {

	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException, SQLException {

		Scanner sc = new Scanner(System.in);
		System.out.println("1 for connetion");
		System.out.println("2 for  account Id details ");
		System.out.println("3 for all account holder details in asscending order based on names");
		System.out.println("4 for print records in current week");
		System.out.println("5 for check your account balance");
		System.out.println("6 for delete Account");
		System.out.println("7 for Update Account");

		int option = sc.nextInt();

		ObjectMapper mapper = new ObjectMapper();

		List<Accounts_pojo> accounts = mapper.readValue(new File("Accounts.json"),
				new TypeReference<List<Accounts_pojo>>() {
				});

		List<Payments_Pojo> payments = mapper.readValue(new File("Payments.json"),
				new TypeReference<List<Payments_Pojo>>() {
				});

		if (option == 1) {

			JdbcConection.create_Insertdata(accounts, payments);

		}

		else if (option == 2) {
			System.out.println("Enter your account Id ");
			long AccountId = sc.nextLong();
			boolean accountExists = false;
			for (Accounts_pojo account : accounts) {
				if (account.getAccountId() == AccountId) {
					accountExists = true;
					break;
				}
			}

			if (accountExists) {
				JdbcConection.fetchAccountIdDetails(AccountId);
			} else {
				System.out.println("Enter a valid Account Id");
			}
		}

		else if (option == 3) {
			JdbcConection.accountDetailsInAsscendingOrder();
		}

		else if (option == 4) {
			JdbcConection.printRecordsInCurrentWeek();
		}

		else if (option == 5) {
			System.out.println("Enter your account Id ");

			String AccountId = sc.next();

			if (AccountId.matches("[0-9]{10}")) {

				JdbcConection.accountBalance(AccountId);
			} else {
				System.out.println("Enter a valid accontId");
			}
		}

		else if (option == 6) {

			System.out.println("Which Id do you want to delete.Enthe ID");
			String id = sc.next();
			if (id.matches("[0-9]{10}")) {
				JdbcConection.deleteAccount(id);
			} else {
				System.out.println("Enter a valid account Id");
			}
		}

		else if (option == 7) {
			System.out.println("Which Id do you want to update");
			String AccountId = sc.next();
			System.out.println("Enter New Id Value ");
			String newAccountId = sc.next();
			JdbcConection.updateAccount(AccountId, newAccountId);

		}

	}

}
