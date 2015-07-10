package de.damios.jpapi.method;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import de.damios.jpapi.core.Constants;
import de.damios.jpapi.exception.JpapiInternalException;

/**
 * Liefert Methoden um auf der Seite gespeicherte Bilder zu erhalten
 * 
 * @author damios
 * @version 1.0
 */
public class ImageProvider {

	private ImageProvider() {
	}

	/**
	 * Liefert ein Bild<br>
	 * <i>Das Seitenverhältnis bleibt immer erhalten</i>
	 * 
	 * @param gameid
	 *            Die Spieleid
	 * @param filename
	 *            Der Dateiname des Bildes
	 * @param width
	 *            Die Bildbreite
	 * @param height
	 *            Die Bildhöhe
	 * @return Das Bild
	 * @throws IOException
	 *             wenn ein Fehler beim Lesen des Bilds auftritt
	 * @see #get(int, String)
	 * @see ImageIO#read(URL)
	 */
	public static BufferedImage get(int gameid, String filename, int width,
			int height) throws IOException {
		URL url;
		try {
			url = new URL(Constants.HOST
					+ "image/projects/"
					+ gameid
					+ "/files/"
					+ filename
					+ ((width < 0 || height < 0) ? "" : ("?width=" + width
							+ "&height=" + height)));
		} catch (MalformedURLException e) {
			throw new JpapiInternalException(e);
		}
		return ImageIO.read(url);
	}

	/**
	 * Liefert ein Bild<br>
	 * <i>Das Seitenverhältnis bleibt immer erhalten</i>
	 * 
	 * @param gameid
	 *            Die Spieleid
	 * @param filename
	 *            Der Dateiname des Bildes
	 * @return Das Bild
	 * @throws IOException
	 *             wenn ein Fehler beim Lesen des Bilds auftritt
	 * @see #get(int, String, int, int)
	 */
	public static BufferedImage get(int gameid, String filename)
			throws IOException {
		return get(gameid, filename, -1, -1);
	}
}
