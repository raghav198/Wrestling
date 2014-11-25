//Container class to hold 2 players as the result of a match
public class MatchResult {
	public Player winner, loser;
	public MatchResult(Player w, Player l) {
		winner = w;
		loser = l;
	}
	public Player getWinner() {
		return winner;
	}
	public Player getLoser() {
		return loser;
	}
}
