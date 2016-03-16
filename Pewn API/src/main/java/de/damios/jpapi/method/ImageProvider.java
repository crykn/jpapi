package de.damios.jpapi.method;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

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
	 * <i>Von Breite und Höhe wird nur der Wert verwendet, dessen relative
	 * Veränderung zum Ursprungswert am kleinsten ist (auch negativ Werte werden
	 * beachtet!), um das ursprüngliche Seitenverhältnis des Bildes
	 * beizubehalten.</i>
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
					+ URLEncoder.encode(filename, "UTF-8")
					+ ((width < 0 || height < 0) ? "" : ("?width=" + width
							+ "&height=" + height)));
		} catch (MalformedURLException e) {
			throw new JpapiInternalException(e);
		}
		return ImageIO.read(url);
	}

	/**
	 * Liefert ein Bild
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
