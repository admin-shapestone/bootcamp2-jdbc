package shapestone.jdbc.constants;

public class JdbcQueries {
	public static String getCreateStudentTableQuery() {
		StringBuffer sb = new StringBuffer("create table student (");
		sb.append("id int primary key, ");
		sb.append("name varchar(50),");
		sb.append("lastname varchar(50),");
		sb.append("age int)");
		return sb.toString();
		
	}

	public static String getInsertQueryForStudentTable() {
		String insertQuery = new String("insert into student(id,name,age,lastname) values(?,?,?,?)");
		return insertQuery;
	}
}
