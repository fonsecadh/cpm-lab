package logic.gmelement;

import logic.Game;

public class Invader extends GameElement {
	
	// Constants
	private static final String IMAGEN_INVADER = "/img/invader.jpg";
	
	

	public Invader() {
		// Empty constructor
	}
	
	public Invader(int position) {
		setPosition(position);
		System.out.println("Invader position: " + getPosition());
		setScore(3000);
		setErased(false);
	}

	@Override
	public void action(Game game) {
		game.setGameOver(true);
	}

	@Override
	public String getImageURL() {
		return IMAGEN_INVADER;
	}
}
