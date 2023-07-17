package jdbcecomarce;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.sym.Name;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JdbcCrudOperations {
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException, SQLException {

		Scanner scanner = new Scanner(System.in);
		System.out.println("press option 1 for connection");
		System.out.println("press option 2 for create  tables");
		System.out.println("press option 3 for insert data into tables");
		System.out.println("press option 4 for read");
		System.out.println("press option 5 for update");
		System.out.println("press option 6 for delete");
		System.out.println("press option 7 for assending order");
		System.out.println("Press option 8 for total week purchases");
		System.out.println("press option 9 for total purchase of one customer");
		int option = scanner.nextInt();
		ObjectMapper objectMapper = new ObjectMapper();

		ArrayList<LaptopCustomers> laptopcustomers = objectMapper.readValue(new java.io.File("laptopcustomer.json"),
				new TypeReference<ArrayList<LaptopCustomers>>() {
				});
		ArrayList<LaptopPurchases> laptopPurchases = objectMapper.readValue(new java.io.File("laptopPurchase.json"),
				new TypeReference<ArrayList<LaptopPurchases>>() {
				});

		if (option == 1) {
			Connections.getConnetion1();

		} else if (option == 2) {
			Connections.createTables();

		} else if (option == 3) {
			Connections.insertdataIntoTables(laptopcustomers, laptopPurchases);

		} else if (option == 4) {
			System.out.println("enter the customerId");
			int customerId1 = scanner.nextInt();
			Connections.readTables(customerId1);
		} else if (option == 5) {
			System.out.println("enter the customerId to update");
			int customerId2 = scanner.nextInt();
			System.out.println("enter the Age");
			int age1 = scanner.nextInt();
			Connections.update(age1, customerId2);

		} else if (option == 6) {
			System.out.println("enter customerId to delete");
			int customerId3 = scanner.nextInt();
			Connections.delete(customerId3);

		} else if (option == 7) {
			Connections.customersNamesinAssending();

		} else if (option == 8) {
			Connections.weeklyPurchases(laptopPurchases);

		} else if (option == 9) {
			System.out.println("enter customerId");
			int customerId1 = scanner.nextInt();
			Connections.totalPurchases(customerId1);
		 } else {
	            System.out.println("Invalid option");
	        }

		}
	}

