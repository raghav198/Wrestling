//Framework class to represent a player
//This will be expanded as per the database
public class Player {
	public String firstName, lastName;
	public Location bracket;
	public Player(String name) {
		firstName = name.split(" ")[0];
		lastName = name.split(" ")[1];
	}
	public String toString() {
		return firstName;
	}
}
