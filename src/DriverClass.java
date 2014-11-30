import java.sql.*;

//Runs the program
public class DriverClass {
	public static void main(String[] args) throws SQLException {
		//This will eventually be loaded from a database connection
		/*String[] players = new String[] {
				"First Player",
				"Second Player",
				"Third Player",
				"Fourth Player",
				"Fifth Player",
				"Sixth Player",
				"Seventh Player",
				"Eighth Player"
		};*/
		//More weight classes will eventually be added
		//WeightClass wc = new WeightClass(players);
		//wc.main();
		Connection c = DatabaseUtility.getConnection();
		Statement s = c.createStatement();
		ResultSet r = s.executeQuery("SELECT * FROM players");
		while (r.next()) {
			System.out.printf("%s %s, ", r.getString("fName"), r.getString("lName"));
		}
	}
}
