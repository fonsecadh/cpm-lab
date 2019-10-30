package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import logic.gmelement.GameElement;

public class Board {

	// Constants
	public static final int DIM = 8;
	
	// Attributes
	private Cell[] cells;
	private Map<Integer, GameElement> posGameElements = new HashMap<Integer, GameElement>();
	
	

	public Board(GameElement... gameElements) { 
		if (gameElements.length > DIM) {
			throw new IllegalArgumentException("Too many game elements. Board is not big enough.");
		}
		
		cells = new Cell[DIM];
		for (int i = 0; i < DIM; i++) {
			cells[i] = new Space(i);	
		}
		createGameElements(gameElements);
	}

	private void createGameElements(GameElement... gameElements) {
		new ArrayList<GameElement>(Arrays.asList(gameElements)).forEach(ge -> createGameElement(ge));
	}

	private void createGameElement(GameElement ge) {
		int pos = (int) (Math.random() * DIM);
		while (posGameElements.get(pos) != null) {
			pos = (int) (Math.random() * DIM);
		}
		ge.setPosition(pos);
		cells[pos] = ge;
	}

	public Cell[] getCells() {
		return cells;
	}

	public void setCells(Cell[] cells) {
		this.cells = cells;
	}

}
