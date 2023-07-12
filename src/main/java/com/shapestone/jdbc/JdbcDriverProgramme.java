package com.shapestone.jdbc;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JdbcDriverProgramme {

	public static void main(String[] args) throws IOException, SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter option 1 for getConnection");
		System.out.println("enter option 2 for Create");
		System.out.println("enter option 3 for insert");
		System.out.println("enter option 4 for read");
		System.out.println("enter option 5 for update");
		System.out.println("enter option 6 for delete");
		System.out.println("enter option 7 for all employee details in ascending order based on names");
		System.out.println("enter option 8 employees who joined current week");
		System.out.println("enter option 9 get total ctc withdrawn as on today");

		int option = sc.nextInt();

		ObjectMapper mapper = new ObjectMapper();

		List<EmployeePojo> employee = mapper.readValue(new File("employeejdc.json"),
				new TypeReference<List<EmployeePojo>>() {
				});
		List<CompanyPojo> company = mapper.readValue(new File("companyjdc.json"),
				new TypeReference<List<CompanyPojo>>() {
				});

		if (option == 1) {
			Connections.getConnection();
		} else if (option == 2) {
			Connections.createTable();
		} else if (option == 3) {
			Connections.insertData(employee, company);
		} else if (option == 4) {
			System.out.println("enter the empId");
			int empId = sc.nextInt();
			Connections.readData(empId);

			List<EmployeePojo> employees = Connections.readData(empId);

			System.out.println("Employee Data:");
			System.out.println(
					"---------------------------------------------------------------------------------------------");
			System.out.format("|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n", "Name", "EmpID", "Age", "Gender", "Address",
					"Phone Number");
			System.out.println(
					"-----------------------------------------------------------------------------------------------");
			for (EmployeePojo employee1 : employees) {
				System.out.format("|%-15s|%-15d|%-15d|%-15s|%-15s|%-15d|\n", employee1.getName(), employee1.getEmpId(),
						employee1.getAge(), employee1.getGender(), employee1.getAddress(), employee1.getPhoneNumber());
			}
			System.out.println(
					"------------------------------------------------------------------------------------------------");
		} else if (option == 5) {
			System.out.println("enter the age for update");
			int age = sc.nextInt();
			System.out.println("enter the empid number");
			int empid = sc.nextInt();
			Connections.update(age, empid);
		} else if (option == 6) {
			System.out.println("enter the empid for delete");
			int empid = sc.nextInt();
			Connections.deleteData(empid);
		} else if (option == 7) {
			Connections.employeeDetailsInAscendingOrder();
		}

		else if (option == 8) {
			Connections.currentWeek();

		} else if (option == 9) {
			System.out.println("enter the empid");
			int empid = sc.nextInt();

			Connections.totalCost(empid);
		}
	}
}
