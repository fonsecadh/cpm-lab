package logic.gmelement;

import logic.Cell;
import logic.Game;

public abstract class GameElement extends Cell {
	
	// Attributes
	boolean erased;

	public boolean isErased() {
		return erased;
	}

	public void setErased(boolean erased) {
		this.erased = erased;
	}
	
	public abstract void action(Game game);
	public abstract String getImageURL();
	
}
