package shapestone.jdbc.constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
	
	public static Connection getConnnection() throws SQLException {
		Connection connection = DriverManager.getConnection(JDBCConstants.DB_URL, JDBCConstants.USER, JDBCConstants.PASS);
		return connection;

	}

}
