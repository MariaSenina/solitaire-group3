package app;

public class Score {
	private int score;
	
	public void addScore(int addAmount) {
		int newScore;
		newScore = score + addAmount;
		score = newScore;
	}
	
	public void subScore(int subAmount) {
		int newScore;
		newScore = score - subAmount;
		score = newScore;
	}
	
	public int getScore() {
		return score;
	}
	
	public Score(String mode) {
		if (mode.equals("standard")) {
			score = 0;
		} else if (mode.equals("vegas")) {
			score = 52;
		}
	}
}