package de.damios.jpapi.method;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import de.damios.jpapi.core.ApiRequest;
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
	 * @return BufferedImage
	 * @throws IOException
	 *             wenn ein Fehler beim Lesen des Bilds auftritt
	 * @see ImageIO#read(URL)
	 * 
	 */
	public static BufferedImage get(int gameid, String filename, int width,
			int height) throws IOException {
		URL url;
		try {
			url = new URL(ApiRequest.HOST + "image/projects/" + gameid
					+ "/files/" + filename + "?width=" + width + "&height="
					+ height);
		} catch (MalformedURLException e) {
			throw new JpapiInternalException(e);
		}
		return ImageIO.read(url);
	}
}
