package logic;

import logic.gmelement.GameElement;

public class Game {
	
	// Attributes
	int score;
	int shots;
	private Board board;
	private boolean gameOver;
	private GameState state;
	private int maxShots;
	private Dice dice;
	
	

	public Board getBoard() {
		return board;
	}

	public Game(GameElement... gameElements) {
		initialize(gameElements);
	}

	public void initialize(GameElement... gameElements) {
		board = new Board(gameElements);
		score = 800;
		shots = 0;
		maxShots = 4;
		gameOver = false;
		state = GameState.DEFAULT;
		dice = new Dice(this);
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
		setShots(dice.launch());
	}

	public int getShots() {
		return shots;
	}
	
	public GameState getState() {
		return state;
	}
	
	public int getMaxShots() {
		return maxShots;
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

	public void setState(GameState state) {
		this.state = state;
	}
	
	public void setMaxShots(int shots) {
		this.maxShots = shots;
	}
}
