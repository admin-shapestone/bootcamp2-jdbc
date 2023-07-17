package shapestone.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import shapestone.jdbc.constants.JdbcQueries;
import shapestone.jdbc.constants.JdbcUtil;

public class JdbcDriverProgram {

	public static void main(String[] args) {
		System.out.println("Do you want to create tables. ???");
		Scanner scanner = new Scanner(System.in);
		boolean creatTables = scanner.nextBoolean();
		if (creatTables) {
			createTables();

		}

		System.out.println("Done with execution. Exiting out...");

	}

	private static void createTables() {
		Connection connnection = null;
		System.out.println("Creating requred tables");
		try {
			connnection = JdbcUtil.getConnnection();
			if (null != connnection) {
				Statement createStatement = connnection.createStatement();
				String createStudentTableQuery = JdbcQueries.getCreateStudentTableQuery();
				createStatement.execute(createStudentTableQuery);
				System.out.println("Done with table creation...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
