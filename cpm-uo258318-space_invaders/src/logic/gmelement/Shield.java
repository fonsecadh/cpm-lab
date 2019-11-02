package logic.gmelement;

import logic.Game;
import logic.GameState;

public class Shield extends GameElement {

	// Constants
	private static final String IMAGEN_SHIELD = "/img/shield.jpg";
	
	
	
	public Shield() {
		// Empty constructor
		setScore(500);
		setErased(false);
	}
	
	public Shield(int position) {
		setPosition(position);
		System.out.println("Shield position: " + getPosition());
		setScore(500);
		setErased(false);
	}

	@Override
	public void action(Game game) {
		game.setState(GameState.SHIELDED);
	}

	@Override
	public String getImageURL() {
		return IMAGEN_SHIELD;
	}

}
