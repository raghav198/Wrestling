//Runs the program
public class DriverClass {
	public static void main(String[] args) {
		//This will eventually be loaded from a database connection
		String[] players = new String[] {
				"First Player",
				"Second Player",
				"Third Player",
				"Fourth Player",
				"Fifth Player",
				"Sixth Player",
				"Seventh Player",
				"Eighth Player"
		};
		//More weight classes will eventually be added
		WeightClass wc = new WeightClass(players);
		wc.main();
	}
}
