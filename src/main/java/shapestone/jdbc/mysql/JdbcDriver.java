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
import com.mysql.cj.xdevapi.Table;

public class JdbcDriver {
	public static void main(String[] args) throws SQLException, StreamReadException, DatabindException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter option 1 for table creation ");
		System.out.println("enter option 2 for read ");
		System.out.println("enter option 3 for ubdate ");
		System.out.println("enter option 4 for delete ");
		System.out.println("enter option 5for print patinet names by assending order");
		System.out.println("enter option 6 for print total cost of each patient");
		System.out.println("enter option 7 display current week");
		int choice = sc.nextInt();
		ObjectMapper mapper = new ObjectMapper();

		List<PatientPojo> patientList = mapper.readValue(new File("patients.json"),
				new TypeReference<List<PatientPojo>>() {
				});
		List<TreatmentPojo> treatmentList = mapper.readValue(new File("treatments.json"),
				new TypeReference<List<TreatmentPojo>>() {
				});

		if (choice == 1) {
			Connections.table(patientList, treatmentList);
		} else if (choice == 2) {
			Connections.readData();

		} else if (choice == 3) {
			System.out.println("enter the address for update");
			String address = sc.next();
			System.out.println("enter the patientid for update");
			int patientid = sc.nextInt();
			Connections.update(address, patientid);
		} else if (choice == 4) {
			System.out.println("enter the id number");
			int id = sc.nextInt();
			Connections.deleteData(id);
		} else if (choice == 5) {
			Connections.patientNamesInAscending();
		} else if (choice == 6) {
			System.out.println("Enter the patient ID:");
			int patientId = sc.nextInt();
			boolean patientIdExists = false;

			for (TreatmentPojo patient : treatmentList) {
				if (patient.getPatientId() == patientId) {
					patientIdExists = true;
					break;
				}
			}

			if (patientIdExists) {
				try {
					Connections.totalCost(patientId);
				} catch (SQLException e) {
					System.out.println("Error occurred while retrieving total cost: " + e.getMessage());
				}
			} else {
				System.out.println("Invalid patient ID: " + patientId);
			}

		} else if (choice == 7) {
			Connections.currentWeek();
		} else {
			System.out.println("enter valid option");
		}
	}
}
