package logic;

public class Dice {
	
	// Attributes
	private Game game;

	public Dice(Game game) {
		this.game = game;
	}

	public int launch() {
		return ((int) (Math.random() * game.getMaxShots()) + 1);
	}

}
