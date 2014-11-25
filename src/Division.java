import java.util.ArrayList;
public class Division {
	public Location tp;
	public WeightClass div;
	public ArrayList<Player> players;
	public ArrayList<Player> matchMaker;
	public ArrayList<Match> matches;
	private Location id;
	public Division(Location loc, Location toc, WeightClass wc) {
		id = loc;
		tp = toc;
		div = wc;
	}
	public Location getID() {
		return id;
	}
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
	public void makeMatches() {
		matches = new ArrayList<Match>();
		if (players.size() == 0) {
			players = matchMaker;
			matchMaker = new ArrayList<Player>();
		}
		if (matchMaker.size() == 0) {
			for (int i = 0; i < players.size(); i += 2)
				matches.add(new Match(
						players.get(i),
						players.get(i + 1)));
		} else {
			for (int i = 0; i < matchMaker.size(); i++)
				matches.add(new Match(
						players.get(i),
						matchMaker.get(players.size() - i - 1)));
		}
	}
	public void playMatches() {
		ArrayList<MatchResult> mr = new ArrayList<MatchResult>();
		for (int i = 0; i < matches.size(); i++)
			mr.add(
				matches.get(i).resolveMatch());
		div.handle(this, mr);
	}
}
