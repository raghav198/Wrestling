import java.sql.*;

public class DatabaseUtility {
	private static final String USERNAME = "raghav985788";
	private static final String PASSWORD = "rm.121898";
	private static final String CONN_STRING = "jdbc:mysql://p3nlmysql101plsk.secureserver.net/mycomplabtest";
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
	}
	
}
