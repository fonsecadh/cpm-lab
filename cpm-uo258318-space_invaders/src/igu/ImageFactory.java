package igu;

import javax.swing.ImageIcon;

import logic.Cell;
import logic.Space;
import logic.Invader;

public class ImageFactory {

	private static final String IMAGEN_INVADER = "/img/invader.jpg";
	private static final String IMAGEN_SPACE = "/img/space.jpg";
	private static final String IMAGEN_SHOOT = "/img/shoot.png";

	public static ImageIcon getImagen(Cell cell) {
		if (cell instanceof Invader)
			return loadImage(IMAGEN_INVADER);
		else if (cell instanceof Space)
			return loadImage(IMAGEN_SPACE);
		return null;
	}

	public static ImageIcon getImage()
	{
		return loadImage(IMAGEN_SHOOT);
	}
	
	private static ImageIcon loadImage(String fqImageName) {
		return new ImageIcon(ImageFactory.class.getResource(fqImageName));
	}
}
