package logic.gmelement;

import logic.Game;
import logic.GameState;

public class Meteorite extends GameElement {
	
	// Constants
	private static final String IMAGEN_METEORITE = "/img/meteorite.jpg";
	
	

	public Meteorite() {
		// Empty constructor
		setErased(false);
	}
	
	public Meteorite(int position) {
		setPosition(position);
		System.out.println("Meteorite position: " + getPosition());
		setErased(false);
	}

	@Override
	public void action(Game game) {
		if (!game.getState().equals(GameState.SHIELDED)) {
			game.setScore(0);
			game.setGameOver(true);
		}
	}

	@Override
	public String getImageURL() {
		return IMAGEN_METEORITE;
	}

}
