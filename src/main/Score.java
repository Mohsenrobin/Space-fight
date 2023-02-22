package main;
public class Score {
	
	
	private int  playerScore ;
	private int highScore;
	private static Score score= new Score();
	
	public Score (){
		this.playerScore = 0;
		this.highScore = 0;
	}
	
	public int getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(int score) {
		this.playerScore = score;
	}

	public static Score getScore() {
		return score;
	}

	public static void setScore(Score score) {
		Score.score = score;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
		
}
