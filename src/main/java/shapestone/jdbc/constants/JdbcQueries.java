package shapestone.jdbc.constants;

public class JdbcQueries {
	public static String getCreateStudentTableQuery() {
		StringBuffer sb = new StringBuffer("create table bc2_jdbc.student (");
		sb.append("id int primary key, ");
		sb.append("name varchar(50),");
		sb.append("lastname varchar(50),");
		sb.append("age int)");
		return sb.toString();
	}
}
