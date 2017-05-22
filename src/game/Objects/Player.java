package game.Objects;

/**
 * Representa o player, contem campos para registro do score(name e score).
 */

public class Player implements Comparable<Player>{
	private String name;
	private int score = 0; 
	
	public Player() {
		
	}

	public Player(String key, int val) {
		this.setName(key);
		this.setScore(val);
	}
	
	@Override
	public int compareTo(Player p) {
		return p.getScore() - this.getScore();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
