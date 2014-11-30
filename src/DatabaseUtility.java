import java.sql.*;

public class DatabaseUtility {
	public static Connection getConnection() throws SQLException {
		String USERNAME = "raghav985788";
		String PASSWORD = "rm.121898";
		String CONN_STR = "jdbc:mysql:///p3nlmysql101plsk.secureserver.net/mycomplabtest";
		return DriverManager.getConnection(USERNAME, PASSWORD, CONN_STR);
	}
	
}