import java.util.Scanner;

//Handles a match between two players
public class Match {
	private Player first, second;
	public Match(Player a, Player b) {
		first = a;
		second = b;
	}
	//Display prompt and gather information from the console
	public MatchResult resolveMatch() {
		//This will eventually be put into a graphics class
		System.out.printf("%s(1) vs %s(2): ", first.toString(), second.toString());
		@SuppressWarnings("resource")
		int winner = new Scanner(System.in).nextInt();
		if (winner == 1) return new MatchResult(first, second);
		else return new MatchResult(second, first);
	}
}
