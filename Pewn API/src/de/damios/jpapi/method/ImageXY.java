package de.damios.jpapi.method;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * <b>Klasse wird noch umbenannt!</b> <br>
 * Liefert Methoden um auf der Seite gespeicherte Bilder zu erhalten
 * 
 * @author damios
 * @version 1.0
 */
public class ImageXY {
	private ImageXY() {
	}

	/**
	 * Liefert ein Bild
	 * 
	 * @param gameid
	 *            Die Spieleid
	 * @param filename
	 *            Der Dateiname des Bildes
	 * @param width
	 *            Die Bildbreite
	 * @param height
	 *            Die Bildhöhe
	 * @return BufferedImage oder null, wenn ein Fehler auftritt
	 * 
	 */
	public static BufferedImage get(int gameid, String filename, int width,
			int height) {
		try {
			return ImageIO.read(new URL("http://pewn.de/image/projects/"
					+ gameid + "/files/" + filename + "?width=" + width
					+ "&height=" + height));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
