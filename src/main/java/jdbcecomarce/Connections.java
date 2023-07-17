package jdbcecomarce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mysql.cj.Query;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.a.ResultsetFactory;

public class Connections {

	final static String DB_URL = "jdbc:mysql://localhost:3306/employee";
	final static String USER = "root";
	final static String PASSWORD = "Phanirama@1997";

	public static Connection getConnetion1() throws SQLException {

		Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);

		System.out.println("connection sucessfull");
		return con;
	}

	public static void createTables() throws SQLException {
		Connection connection = getConnetion1();

		Statement statement = connection.createStatement();

		String dropConstraintQuery = "ALTER TABLE purchases DROP FOREIGN KEY purchases_ibfk_1";
		statement.execute(dropConstraintQuery);

		String dropCustomersQuery = "DROP TABLE IF EXISTS customers";
		String dropPurchasesQuery = "DROP TABLE IF EXISTS purchases";
		statement.execute(dropCustomersQuery);
		statement.execute(dropPurchasesQuery);

		StringBuffer stringBufferCustomer = new StringBuffer("create table Customers(");
		stringBufferCustomer.append("CUSTOMERID INT PRIMARY KEY,");
		stringBufferCustomer.append("NAME VARCHAR(100) NOT NULL, ");
		stringBufferCustomer.append("AGE INT NOT NULL,");
		stringBufferCustomer.append("GENDER VARCHAR(20) NOT NULL,");
		stringBufferCustomer.append("ADDRESS VARCHAR(200) NOT NULL");
		stringBufferCustomer.append(")");

		statement.execute(stringBufferCustomer.toString());

		StringBuffer stringBufferPurchase = new StringBuffer("create table Purchases(");
		stringBufferPurchase.append("CUSTOMERID INT NOT NULL,");
		stringBufferPurchase.append("PURCHASEID INT PRIMARY KEY,");
		stringBufferPurchase.append("QUANTITY INT NOT NULL,");
		stringBufferPurchase.append("ITEMPURCHASED VARCHAR(100) NOT NULL,");
		stringBufferPurchase.append("PRICE DECIMAL(10,2) NOT NULL,");
		stringBufferPurchase.append("DATEOFPURCHASE DATE,");
		stringBufferPurchase.append("FOREIGN KEY (customerId) REFERENCES Customers(customerId)");
		stringBufferPurchase.append(")");

		statement.execute(stringBufferPurchase.toString());

		System.out.println("Tables Scussefully Created");
	}

	public static void insertdataIntoTables(ArrayList<LaptopCustomers> laptopcustomers,
			ArrayList<LaptopPurchases> laptopPurchases) throws SQLException {

		Connection connection = getConnetion1();
		String sql = "INSERT INTO customers (customerId, name,  age, gender, address) VALUES (?, ?, ?, ?, ?)";
		String sql1 = "INSERT INTO PURCHASES (customerId,purchaseId,quantity,itemPurchased,price,dateOfPurchase) VALUES (?, ?, ?, ?, ?,?)";
		PreparedStatement statementCustomer = connection.prepareStatement(sql);
		PreparedStatement statementPurchase = connection.prepareStatement(sql1);
		for (LaptopCustomers customer : laptopcustomers) {
			statementCustomer.setInt(1, customer.getCustomerId());
			statementCustomer.setString(2, customer.getName());
			statementCustomer.setInt(3, customer.getAge());
			statementCustomer.setString(4, customer.getGender());
			statementCustomer.setString(5, customer.getAddress());

			statementCustomer.executeUpdate();
		}

		for (LaptopPurchases purchase : laptopPurchases) {
			statementPurchase.setInt(1, purchase.getCustomerId());
			statementPurchase.setInt(2, purchase.getPurchaseId());
			statementPurchase.setInt(3, purchase.getQuantity());
			statementPurchase.setString(4, purchase.getItemPurchased());
			statementPurchase.setDouble(5, purchase.getPrice());
			statementPurchase.setDate(6, purchase.getDateOfPurchase());

			statementPurchase.executeUpdate();
		}

		System.out.println(" Data Inserted successfully");
	}

	public static void readTables(int customerId) throws SQLException {

		Connection connection = getConnetion1();
		String sql = "select PURCHASEID,NAME,PRICE,ITEMPURCHASED,DATEOFPURCHASE,ADDRESS from customers c,purchases p where c.CUSTOMERID = p.CUSTOMERID and c.CUSTOMERID  = ?;";
		PreparedStatement preparedstatement = connection.prepareStatement(sql);
		preparedstatement.setInt(1, customerId);
		ResultSet resultSet = preparedstatement.executeQuery();
		System.out.println(
				"----------------------------------------------------------------------------------------------------------");
		System.out.printf("| %-15s | %-15s | %-10s | %-17s | %-15s | %-15s |%n", "PurchaserId", "Name", "price",
				"ItemPurchased", "DateOfPurchased", "Address");
		while (resultSet.next()) {
			System.out.println(
					"----------------------------------------------------------------------------------------------------------");
			System.out.printf("| %-15s | %-15s | %-10s | %-17s | %-15s | %-15s |%n", resultSet.getInt(1),
					resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getDate(5),
					resultSet.getString(6));
		}
		System.out.println(
				"----------------------------------------------------------------------------------------------------------");
		System.out.println("Data reading is sucessfully completed");
	}

	public static void update(int customerId, int age) throws SQLException {

		Connection connection = getConnetion1();
		String query = "update employee.customers SET AGE = ? WHERE CUSTOMERID = ? ;";
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setInt(1, customerId);
		preparedStatement.setInt(2, age);

		preparedStatement.execute();

		System.out.println("Sucessfully Updated");
	}

	public static void delete(int customerId) throws SQLException {

		Connection connection = getConnetion1();

		String query = "delete from purchases where CUSTOMERID = ? ;";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, customerId);
		preparedStatement.execute();

		System.out.println("sucessfully deleted");

	}

	public static void customersNamesinAssending() throws SQLException {

		Connection connection = getConnetion1();
		System.out.println("-------------------");
		System.out.printf("| %-15s |%n", "CustomerID");
		System.out.println("-------------------");
		String query = "SELECT NAME FROM  CUSTOMERS ORDER BY NAME ASC;";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			System.out.printf("| %-15s |%n", resultSet.getString("name"));
			System.out.println("-------------------");

		}

		System.out.println("sucessfully names sorted in asscending order");

	}

	public static void weeklyPurchases(ArrayList<LaptopPurchases> laptopPurchases) throws SQLException {

		Connection connection = getConnetion1();
		String query = "select * from purchases where week(DateOfPurchase) = week(CURDATE());";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultset = preparedStatement.executeQuery();
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------");
		System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |%n", "CutostomerID", "PurchaseID",
				"Quantity", "ItemPurchased", "Price", "DateOfPurchase");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------");
		while (resultset.next()) {
			System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |%n", resultset.getInt("customerId"),
					resultset.getInt("purchaseId"), resultset.getInt("quantity"), resultset.getString("itemPurchased"),
					resultset.getDouble("price"), resultset.getString("dateOfPurchase"));
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------");
		}

	}

	public static void totalPurchases(int customerId) throws SQLException {
		Connection connection = getConnetion1();
		String query = "SELECT SUM(price) AS total_customerpurchase FROM purchases WHERE customerid = ?;";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, customerId);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			System.out.println(rs.getInt("total_customerpurchase"));
		}
	}

}
