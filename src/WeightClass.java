import java.util.ArrayList;

//All the fun happens here!!!
public class WeightClass {
	//Add a string of names to list of unverse players
	public WeightClass(String[] weightClass) {
		allPlayers = new ArrayList<Player>();
		for (String s : weightClass){
			Player p = new Player(s);
			p.bracket = Location.WINNERS;
			allPlayers.add(p);
		}
	}
	public ArrayList<Player> allPlayers;
	//Create winner and loser brackets
	public Division winnersBracket = new Division(
			Location.WINNERS,
			Location.WINNERS,
			this);
	public Division losersBracket = new Division(
			Location.LOSERS,
			Location.LOSER_,
			this);
	//Utility function to run a particular bracket
	public void runRoundWith(Division b) {
		b.loadPlayersFrom(allPlayers);
		b.makeMatches();
		b.playMatches();
		//This is to update the sizes
		winnersBracket.loadPlayersFrom(allPlayers);
		losersBracket.loadPlayersFrom(allPlayers);
	}
	//Callback method: moves players around based on match results
	public void handle(Division bracket, ArrayList<MatchResult> results) {
		Location id = bracket.getID();
		for (int i = 0; i < results.size(); i++) {
			MatchResult mr = results.get(i);
			if (id == Location.WINNERS) mr.getLoser().bracket = Location.LOSER_;
			else if (id == Location.LOSERS) mr.getLoser().bracket = Location.DISQ;
		}
	}
	public void main() {
		//Start with running both
		runRoundWith(winnersBracket);
		runRoundWith(losersBracket);
		//Run succession until we reach the finals
		while (winnersBracket.players.size() > 4) {
			runRoundWith(winnersBracket);
			runRoundWith(losersBracket);
			runRoundWith(losersBracket);
		}
		//Play out to get top 5 players
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
		//Display results
		System.out.println("The tournament is over!!!!");
		System.out.println("5 players have been selected to move on");
		System.out.println("In first place: " + primaryFinalResult.getWinner());
		System.out.println("In second place: " + primaryFinalResult.getLoser());
		System.out.println("In third place: " + secondaryFinalResult.getWinner());
		System.out.println("In fourth place: " + secondaryFinalResult.getLoser());
		System.out.println("In fifth place: " + tertiaryFinalResult.getWinner());
		return; //OK, so it's not *strictly* necessary but whatever
	}
}
