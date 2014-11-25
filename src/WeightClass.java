import java.util.ArrayList;
public class WeightClass {
	public WeightClass(String[] weightClass) {
		allPlayers = new ArrayList<Player>();
		for (String s : weightClass){
			Player p = new Player(s);
			p.bracket = Location.WINNERS;
			allPlayers.add(p);
		}
	}
	public ArrayList<Player> allPlayers;
	public Division winnersBracket = new Division(
			Location.WINNERS,
			Location.WINNER_,
			this);
	public Division losersBracket = new Division(
			Location.LOSERS,
			Location.LOSER_,
			this);
	public void runRoundWith(Division b) {
		b.loadPlayersFrom(allPlayers);
		b.makeMatches();
		b.playMatches();
		winnersBracket.loadPlayersFrom(allPlayers);
		losersBracket.loadPlayersFrom(allPlayers);
	}
	public void handle(Division bracket, ArrayList<MatchResult> results) {
		Location id = bracket.getID();
		for (int i = 0; i < results.size(); i++) {
			MatchResult mr = results.get(i);
			if (id == Location.WINNERS) mr.getLoser().bracket = Location.LOSER_;
			else if (id == Location.LOSERS) mr.getLoser().bracket = Location.DISQ;
		}
	}
	public void main() {
		runRoundWith(winnersBracket);
		runRoundWith(losersBracket);
		while (winnersBracket.players.size() > 4) {
			runRoundWith(winnersBracket);
			runRoundWith(losersBracket);
			runRoundWith(losersBracket);
		}
		Match semiFinalsA = new Match(
				winnersBracket.players.get(0),
				winnersBracket.players.get(1));
		Match semiFinalsB = new Match(
				winnersBracket.players.get(2),
				winnersBracket.players.get(3));
		MatchResult semiFinalsAResults = semiFinalsA.resolveMatch();
		MatchResult semiFinalsBResults = semiFinalsB.resolveMatch();
		Match primaryFinals = new Match(
				semiFinalsAResults.getWinner(),
				semiFinalsBResults.getWinner());
		Match secondaryFinals = new Match(
				semiFinalsAResults.getLoser(),
				semiFinalsBResults.getLoser());
		MatchResult primaryFinalResult = primaryFinals.resolveMatch();
		MatchResult secondaryFinalResult = secondaryFinals.resolveMatch();
		Match tertiaryFinals = new Match(
				losersBracket.players.get(0),
				losersBracket.players.get(1));
		MatchResult tertiaryFinalResult = tertiaryFinals.resolveMatch();
		System.out.println("The tournament is over!!!!");
		System.out.println("5 players have been selected to move on");
		System.out.println("In first place: " + primaryFinalResult.getWinner());
		System.out.println("In second place: " + primaryFinalResult.getLoser());
		System.out.println("In third place: " + secondaryFinalResult.getWinner());
		System.out.println("In fourth place: " + secondaryFinalResult.getLoser());
		System.out.println("In fifth place: " + tertiaryFinalResult.getWinner());
		return;
		
	}
}
