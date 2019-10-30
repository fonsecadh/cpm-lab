package logic.gmelement.factory;

import logic.gmelement.GameElement;

public interface GameElementFactory {
	
	GameElement createMeteorite();
	GameElement createInvader();
	GameElement createShield();

}
