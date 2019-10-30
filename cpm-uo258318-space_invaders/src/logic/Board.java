package logic;

import java.util.HashMap;
import java.util.Map;

public class Board {

	// Constants
	public static final int DIM = 8;
	public static final int INVADERS = 1;
	public static final int METEORITES = 1;
	
	// Attributes
	private Cell[] cells;
	private Map<Integer, GameElement> posGameElements = new HashMap<Integer, GameElement>();
	
	

	public Board() {
		cells = new Cell[DIM];
		for (int i = 0; i < DIM; i++) {
			cells[i] = new Space(i);	
		}
		createGameElements();
	}

	private void createGameElements() {
		createInvaders();		
		createMeteorites();
	}

	private void createMeteorites() {
		int meteoCounter = METEORITES;
		
		while (meteoCounter > 0) {
			int meteoritePosition = (int) (Math.random() * DIM);
			while (posGameElements.get(meteoritePosition) != null) {
				meteoritePosition = (int) (Math.random() * DIM);
			}
			cells[meteoritePosition] = new Meteorite(meteoritePosition);
			meteoCounter--;
		}
	}

	private void createInvaders() {
		int invCounter = INVADERS;		
		
		while (invCounter > 0) {
			int invaderPosition = (int) (Math.random() * DIM);
			while (posGameElements.get(invaderPosition) != null) {
				invaderPosition = (int) (Math.random() * DIM);
			}
			cells[invaderPosition] = new Invader(invaderPosition);
			invCounter--;
		}
	}

	public Cell[] getCells() {
		return cells;
	}

	public void setCells(Cell[] cells) {
		this.cells = cells;
	}

}
