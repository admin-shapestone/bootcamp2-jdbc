package shapestone.jdbc.mysql;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.Statement;

public class JdbcDriver {
	public static void main(String[] args) throws SQLException, StreamReadException, DatabindException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter option 1 for getconnection ");
		System.out.println("enter option 2 for create ");
		System.out.println("enter option 3 for insert ");
		System.out.println("enter option 4 for read ");
		System.out.println("enter option 5 for ubdate ");
		System.out.println("enter option 6 for delete ");
		System.out.println("enter option 7 for print patinet names by assending order");
		System.out.println("enter option 8 for print total cost of each patient");
		System.out.println("enter option 9 display current week");
		int choice = sc.nextInt();
		ObjectMapper mapper = new ObjectMapper();

		List<PatientPojo> patientList = mapper.readValue(new File("patients.json"),
				new TypeReference<List<PatientPojo>>() {
				});
		if (choice == 1) {
			Connections.getconnection();
		} else if (choice == 2) {
			Connections.createTable();
		} else if (choice == 3) {
			Connections.insertData(patientList);
		} else if (choice == 4) 
		{
			Connections.readData();
			
		} else if (choice == 5) {
			System.out.println("enter the age for update");
			int age = sc.nextInt();
			System.out.println("enter the id for update");
			int id = sc.nextInt();
			Connections.update(age, id);
		} else if (choice == 6) {
			System.out.println("enter the id number");
			int id = sc.nextInt();
			Connections.deleteData(id);
		} else if (choice == 7) {
			Connections.patientNamesInAscending();
		} else if (choice == 8) {
			System.out.println("enter the patient name");
			String name=sc.next();
            Connections.totalCost(name);
		} else if (choice == 9) {
      Connections.currentWeek();
		} else {
			System.out.println("enter valid option");
		}
	}
}
