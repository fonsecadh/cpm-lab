package logic;

public class Game {

	// Constants
	public static final int maxShots = 4;
	
	// Attributes
	int score;
	int shots;
	private Board board;
	private boolean gameOver;
	
	

	public Board getBoard() {
		return board;
	}

	public Game() {
		initialize();
	}

	public void initialize() {
		board = new Board();
		score = 800;
		shots = 0;
	}

	public void shoot(int i) {
		shots--;
		score = score + board.getCells()[i].getScore();
		
		if (board.getCells()[i] instanceof GameElement) {
			((GameElement) board.getCells()[i]).setErased(true);
			((GameElement) board.getCells()[i]).action(this);
		}
	}

	public boolean isGameOver() {
		return (gameOver || shots == 0);
	}

	public int getScore() {
		return score;
	}

	public void launch() {
		setShots(Dice.launch());
	}

	public int getShots() {
		return shots;
	}

	private void setShots(int shots) {
		this.shots = shots;
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void increaseScore(int amount) {
		this.score += amount;
	}
}
