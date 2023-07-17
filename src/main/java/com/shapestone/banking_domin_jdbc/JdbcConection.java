package com.shapestone.banking_domin_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;

public class JdbcConection {

	final static String DB_URL = "jdbc:mysql://localhost:3306/bank_domin";
	final static String USER = "root";
	final static String PASSWord = "pkbqyiytuh9#738";

	public static Connection getConnection() throws SQLException {

		Connection connect = DriverManager.getConnection(DB_URL, USER, PASSWord);
		System.out.println("sucessfully connected");
		return connect;
	}

	public static void create_Insertdata(List<Accounts_pojo> accounts, List<Payments_Pojo> payments)
			throws SQLException {
		JdbcConection.getConnection();
		JdbcConection.createAccountsTable();
		JdbcConection.createPaymentsTable();
		JdbcConection.insertDate(payments);
		JdbcConection.insertDataIntoAccounts(accounts);

	}

	public static void createPaymentsTable() throws SQLException {
		Connection connect = getConnection();
		Statement statement = connect.createStatement();

		statement.execute(Table.createTable1());

		System.out.println("payments created sucessfully");

	}

	public static void createAccountsTable() throws SQLException {

		Connection connect = getConnection();
		Statement statement = connect.createStatement();

		String tableAccountQuery = "create table AccountsTable(" + "AccountId BIGINT PRIMARY KEY," + "Name varchar(20),"
				+ "Age INT," + "Gender varchar(10)," + "DateOfJoining Date," + "OpeningBalance DECIMAL(10,2))";

		statement.execute(tableAccountQuery);
		System.out.println("Accounts table created");

	}

	public static void insertDataIntoAccounts(List<Accounts_pojo> accounts) throws SQLException {
		Connection connect = getConnection();
		String insertData = "INSERT INTO accountstable (AccountId,Name,Age,Gender,DateOfjoining,OpeningBalance) "
				+ "values(?,?,?,?,?,?);";
		PreparedStatement statement = connect.prepareStatement(insertData);

		for (Accounts_pojo account : accounts) {
			statement.setLong(1, account.getAccountId());
			statement.setString(2, account.getName());
			statement.setInt(3, account.getAge());
			statement.setString(4, account.getGender());
			statement.setString(5, account.getDateOfJoining());
			statement.setDouble(6, account.getOpeningBalance());
			statement.executeUpdate();

		}

	}

	public static void insertDate(List<Payments_Pojo> payments) throws SQLException {
		Connection connect = getConnection();
		Statement statement = connect.createStatement();
		ArrayList<String> prepareInsertQueries = Table.prepareInsertQueries(payments);

		Table.insertRecordsIntoPayments(prepareInsertQueries, statement);
	}

	public static void fetchAccountIdDetails(long accountId) throws SQLException {

		Connection connect = getConnection();

		String query = "select paymentid,openingbalance,amountpaid,name,dateOfjoining from payments p, accountstable a where p.accountId = a.accountId and a.AccountId = "
				+ accountId;
		PreparedStatement preparedStatement = connect.prepareStatement(query);

		ResultSet resultSet = preparedStatement.executeQuery();

		System.out.println("sucessFully fetched the data");

		while (resultSet.next()) {
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("|%-30s|%-30s|%-30s|%-30s|%-30s|%n", resultSet.getString(1), resultSet.getInt(2),
					resultSet.getBigDecimal(3), resultSet.getString(4), resultSet.getDate(5));
		}
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------------------------");

	}

	public static void accountDetailsInAsscendingOrder() throws SQLException {
		Connection connect = getConnection();

		String query = "select * from Payments,accountstable order by name;";
		PreparedStatement preparedStatement = connect.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			System.out.println(
					"--------------------------------------------------------------------------------------------------");
			System.out.printf("|%-30s|%-30s|%-30s|%n", resultSet.getString("Name"), resultSet.getInt("age"),
					resultSet.getString("AccountId"));
		}
		System.out.println(
				"--------------------------------------------------------------------------------------------------");

		System.out.println("sucessfully names sorted in asscending order");

	}

	public static void printRecordsInCurrentWeek() throws SQLException {

		Connection connect = getConnection();
		String query = "SELECT * FROM accountstable WHERE WEEK(dateOfJoining) = WEEK(CURDATE())";

		PreparedStatement statement = connect.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery();
		System.out.println("Retrieval successful");

		while (resultSet.next()) {
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------");
			System.out.printf("|%-30s|%-30s|%-30s|%-30s|%n", resultSet.getDate("dateOfJoining"),
					resultSet.getInt("age"), resultSet.getString("gender"), resultSet.getString("name"));
		}
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------");
	}

	public static void accountBalance(String accountId) throws SQLException {
		Connection connect = getConnection();
		String query = "SELECT a.accountId,a.name, a.openingbalance, p.purposeOfPayment, p.amountPaid, \r\n"
				+ "       (a.openingbalance - p.amountPaid) AS Account_Balance \r\n" + "FROM accountstable AS a \r\n"
				+ "JOIN payments AS p \r\n" + "ON a.accountId = p.accountId\r\n" + "WHERE a.accountId = ?"
				+ "LIMIT 0, 1000;";
		PreparedStatement statement = connect.prepareStatement(query);
		statement.setString(1, accountId);
		ResultSet resultSet = statement.executeQuery();
		System.out.println("Retrieval successful");

		while (resultSet.next()) {
			System.out.println(
					"--------------------------------------------------------------------------------------------------");
			System.out.printf("|%-30s|%-30s|%-30s|%n", resultSet.getLong("accountid"), resultSet.getString("name"),
					resultSet.getString("Account_Balance"));
		}
		System.out.println(
				"--------------------------------------------------------------------------------------------------");

	}

	public static void deleteAccount(String id) throws SQLException {
		Connection connect = getConnection();
		String query = "delete from accountstable where AccountId = ?";
		PreparedStatement statement = connect.prepareStatement(query);
		statement.setNString(1, id);
		statement.execute();

		System.out.println("sucessFully deleted account");

	}

	public static void updateAccount(String AccountId, String newAccountId) throws SQLException {

		Connection connect = getConnection();
		String query = "update accountstable set name = ? where accountid = ?;";

		PreparedStatement statement = connect.prepareStatement(query);
		statement.setString(1, newAccountId);
		statement.setString(2, AccountId);

		boolean execute = statement.execute();
		System.out.println("SucessFully updated");

	}

}
