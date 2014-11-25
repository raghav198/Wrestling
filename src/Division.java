import java.util.ArrayList;

//Handles Winner's Bracket and Loser's Bracket
public class Division {
	public Location tp;
	public WeightClass div;
	public ArrayList<Player> players;
	public ArrayList<Player> matchMaker;
	public ArrayList<Match> matches;
	private Location id;
	//Location ID, Temporary location ID, and dependency injection
	public Division(Location loc, Location toc, WeightClass wc) {
		id = loc;
		tp = toc;
		div = wc;
	}
	public Location getID() {
		return id;
	}
	//Parse universe for relevant players
	public void loadPlayersFrom(ArrayList<Player> universe) {
		players = new ArrayList<Player>();
		matchMaker = new ArrayList<Player>();
		for (int i = 0; i < universe.size(); i++) {
			if (universe.get(i).bracket == id) players.add(universe.get(i));
			else if (universe.get(i).bracket == tp) {
				matchMaker.add(universe.get(i));
				universe.get(i).bracket = id;
			}
		}
	}
	//Pair up players using reverse-sort algorithm
	public void makeMatches() {
		matches = new ArrayList<Match>();
		//All the players are new, flip new & old
		if (players.size() == 0) {
			players = matchMaker;
			matchMaker = new ArrayList<Player>();
		}
		//All the players are old, pair up normally
		if (matchMaker.size() == 0) {
			for (int i = 0; i < players.size(); i += 2)
				matches.add(new Match(
						players.get(i),
						players.get(i + 1)));
		} else {
			//Invert new list and zip-pair
			for (int i = 0; i < matchMaker.size(); i++)
				matches.add(new Match(
						players.get(i),
						matchMaker.get(players.size() - i - 1)));
		}
	}
	//Play each generated match
	public void playMatches() {
		ArrayList<MatchResult> mr = new ArrayList<MatchResult>();
		for (int i = 0; i < matches.size(); i++)
			mr.add(
				matches.get(i).resolveMatch());
		div.handle(this, mr);
	}
}
