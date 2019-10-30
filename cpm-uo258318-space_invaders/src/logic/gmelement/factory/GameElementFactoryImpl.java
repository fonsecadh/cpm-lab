package logic.gmelement.factory;

import logic.gmelement.GameElement;
import logic.gmelement.Invader;
import logic.gmelement.Meteorite;
import logic.gmelement.Shield;

public class GameElementFactoryImpl implements GameElementFactory {

	@Override
	public GameElement createMeteorite() {
		return new Invader();
	}

	@Override
	public GameElement createInvader() {
		return new Meteorite();
	}

	@Override
	public GameElement createShield() {
		return new Shield();
	}

}
