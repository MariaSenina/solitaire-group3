package app;

public class Score {
	private int score;
	private String mode;
	
	public Score(String mode) {
		this.mode = mode;
		
		if (mode.equals("standard")) {
			score = 0;
		} else if (mode.equals("vegas")) {
			score = -52;
		}
	}
	
	private void addScore(int addAmount) {
		int newScore;
		newScore = score + addAmount;
		score = newScore;
	}
	
	private void subScore(int subAmount) {
		int newScore;
		newScore = score - subAmount;
		score = newScore;
	}
	
	public int getScore() {
		return score;
	}
	
	public void changeScore(String type) {
		switch (type) {
		case "toFoundation":
			toFoundation();
			break;
		case "toTableau":
			toTableau();
			break;
		case "moveTabPile":
			moveTabPile();
			break;
		}
	}
	
	private void toFoundation() {
		if (mode.equals("standard")) {
			addScore(10);
		} else if (mode.equals("vegas")) {
			addScore(5);
		}
	}
	
	private void toTableau() {
		if (mode.equals("standard")) {
			addScore(5);
		} else if (mode.equals("vegas")) { /* do nothing */ }
	}
	
	private void moveTabPile() {
		if (mode.equals("standard")) {
			addScore(3);
		} else if (mode.equals("vegas")) { /* do nothing */ }
	}
}