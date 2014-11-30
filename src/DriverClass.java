import java.sql.*;
import java.util.ArrayList;
//Runs the program
public class DriverClass {
	public static void main(String[] args) throws SQLException {
		//This code will be expanded to work on several weight classes
		ArrayList<String> playerNames = new ArrayList<String>();
		Connection c = DatabaseUtility.getConnection();
		Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet r = s.executeQuery("SELECT * FROM player");
		while (r.next())
			playerNames.add(String.format("%s %s", r.getString("fName"), r.getString("lName")));
		WeightClass wc = new WeightClass(playerNames);
		wc.main();
	}
}
