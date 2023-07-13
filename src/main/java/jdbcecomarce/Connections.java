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
	final static String PASSWord = "Phanirama@1997";

	public static Connection getConnetion1() throws SQLException {

		Connection con = DriverManager.getConnection(DB_URL, USER, PASSWord);

		System.out.println("connection sucessfull");
		return con;
	}

	public static void createTables() throws SQLException {
		Connection connection = getConnetion1();

		Statement statement = connection.createStatement();

		String query = "DROP TABLE IF EXISTS passengers";
		String query1 = "DROP TABLE IF EXISTS bookingss";
		statement.execute(query);
		statement.execute(query1);

		StringBuffer stringBuffer = new StringBuffer("create table Customers(");
		stringBuffer.append("CUSTOMERID INT PRIMARY KEY,");
		stringBuffer.append("NAME VARCHAR(100) NOT NULL, ");
		stringBuffer.append("AGE INT NOT NULL,");
		stringBuffer.append("GENDER VARCHAR(20) NOT NULL,");
		stringBuffer.append("ADDRESS VARCHAR(200) NOT NULL");
		stringBuffer.append(")");

		statement.execute(stringBuffer.toString());

		StringBuffer stringBuffer1 = new StringBuffer("create table Purchases(");
		stringBuffer1.append("CUSTOMERID INT NOT NULL,");
		stringBuffer1.append("PURCHASEID INT PRIMARY KEY,");
		stringBuffer1.append("QUANTITY INT NOT NULL,");
		stringBuffer1.append("ITEMPURCHASED VARCHAR(100) NOT NULL,");
		stringBuffer1.append("PRICE DECIMAL(10,2) NOT NULL,");
		stringBuffer1.append("DATEOFPURCHASE DATE,");
		stringBuffer1.append("FOREIGN KEY (customerId) REFERENCES Customers(customerId)");
		stringBuffer1.append(")");

		statement.execute(stringBuffer1.toString());

		System.out.println("Tables Scussefully Created");
	}

	public static void insertdataIntoTables(ArrayList<LaptopCustomers> laptopcustomers,
			ArrayList<LaptopPurchases> laptopPurchases) throws SQLException {

		Connection connection = getConnetion1();
		String sql = "INSERT INTO customers (customerId, name,  age, gender, address) VALUES (?, ?, ?, ?, ?)";
		String sql1 = "INSERT INTO PURCHASES (customerId,purchaseId,quantity,itemPurchased,price,dateOfPurchase) VALUES (?, ?, ?, ?, ?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		PreparedStatement statement1 = connection.prepareStatement(sql1);
		for (LaptopCustomers customer : laptopcustomers) {
			statement.setInt(1, customer.getCustomerId());
			statement.setString(2, customer.getName());
			statement.setInt(3, customer.getAge());
			statement.setString(4, customer.getGender());
			statement.setString(5, customer.getAddress());

			statement.executeUpdate();
		}

		for (LaptopPurchases purchase : laptopPurchases) {
			statement1.setInt(1, purchase.getCustomerId());
			statement1.setInt(2, purchase.getPurchaseId());
			statement1.setInt(3, purchase.getQuantity());
			statement1.setString(4, purchase.getItemPurchased());
			statement1.setDouble(5, purchase.getPrice());
			statement1.setString(6, purchase.getDateOfPurchase());

			statement1.executeUpdate();
		}

		System.out.println(" Data Inserted successfully");
	}

	public static void readTables(int customerId) throws SQLException {

		Connection connection = getConnetion1();
		String sql = "select * from customers where customerId=?";
		PreparedStatement preparedstatement = connection.prepareStatement(sql);
		preparedstatement.setInt(1, customerId);
		ResultSet resultSet = preparedstatement.executeQuery();
		System.out.println("-----------------------------------------------------------------------");
		System.out.printf("| %-10s | %-10s | %-10s | %-10s | %-15s |%n", "CustomerId", "Name", "Age", "Gender",
				"Address");
		while (resultSet.next()) {
			System.out.println("-----------------------------------------------------------------------");
			System.out.printf("| %-10s | %-10s | %-10s | %-10s | %-15s |%n", resultSet.getInt("customerId"),
					resultSet.getString("name"), resultSet.getInt("age"), resultSet.getString("gender"),
					resultSet.getString("address"));
		}
		System.out.println("-----------------------------------------------------------------------");
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
