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

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException, SQLException {

		Scanner sc = new Scanner(System.in);
		System.out.println("1 for connetion");
		System.out.println("2 for Create Table");
		System.out.println("3 for Inset data");
		System.out.println("4 for  account Id details ");
		System.out.println("5 for all account holder details in asscending order based on names");
		System.out.println("6 for print records in current week");
		System.out.println("7 for check your account balance");
		System.out.println("8 for delete Account");
		System.out.println("9 for Update Account");

		int option = sc.nextInt();

		ObjectMapper mapper = new ObjectMapper();

		List<Payments_Pojo> payments = mapper.readValue(new File("Payments.json"),
				new TypeReference<List<Payments_Pojo>>() {
				});
		if (option == 1) {

			JdbcConection.getConnection();
		}

		else if (option == 2) {
			JdbcConection.createTable(payments);

		}

		else if (option == 3) {
			JdbcConection.insertDate(payments);
		}

		else if (option == 4) {
			System.out.println("Enter your account Id ");
			String AcountId = sc.next();
			JdbcConection.fetchAccountIdDetails(AcountId);
		}

		else if (option == 5) {
			JdbcConection.AccountDetailsInAsscendingOrder();
		}

		else if (option == 6) {
			JdbcConection.printRecordsInCurrentWeek();
		}

		else if (option == 7) {
			System.out.println("Enter your account Id ");
			String AccountId = sc.next();
			JdbcConection.AccountBalance(AccountId);
		}

		else if (option == 8) {

			System.out.println("Which Id do you want to delete.Enthe ID");
			long id = sc.nextLong();
			JdbcConection.deleteAccount(id);
		}

		else if (option == 9) {
			System.out.println("Which Id do you want to update");
			String AccountId = sc.next();
			System.out.println("Enter New Id Value ");
			String newAccountId = sc.next();

			JdbcConection.updateAccount(AccountId, newAccountId);

		}

	}

}
