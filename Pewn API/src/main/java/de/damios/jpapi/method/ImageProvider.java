package de.damios.jpapi.method;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.imageio.ImageIO;

import de.damios.jpapi.core.Constants;
import de.damios.jpapi.exception.JpapiInternalException;
import de.damios.jpapi.object.Image;
import de.damios.jpapi.object.Project;

/**
 * Beinhaltet Methoden, um auf der Seite gespeicherte Bilder zu erhalten.
 * 
 * @author damios
 * @since 0.1.0
 */
public class ImageProvider {

	private ImageProvider() {
	}

	/**
	 * Liefert ein Bild, dass auf Pewn hochgeladen wurde.<br>
	 * <i>Von Breite und H�he wird nur der Wert verwendet, dessen relative
	 * Ver�nderung zum Ursprungswert am kleinsten ist (auch negativ Werte werden
	 * beachtet!), um das urspr�ngliche Seitenverh�ltnis des Bildes
	 * beizubehalten.</i>
	 * 
	 * @param gameid
	 *            Die Spieleid.
	 * @param filename
	 *            Der Dateiname des Bildes.
	 * @param width
	 *            Die Bildbreite.
	 * @param height
	 *            Die Bildh�he.
	 * @return Das Bild.
	 * @throws IOException
	 *             wenn ein Fehler beim Lesen des Bilds auftritt.
	 * @see #get(int, String)
	 * @see #get(Project, Image)
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
	 * Liefert ein Bild, dass auf Pewn hochgeladen wurde.
	 * 
	 * @param gameid
	 *            Die Spieleid.
	 * @param filename
	 *            Der Dateiname des Bildes.
	 * @return Das Bild.
	 * @throws IOException
	 *             wenn ein Fehler beim Lesen des Bilds auftritt.
	 * @see #get(int, String, int, int)
	 * @deprecated Stattdessen {@link #get(Project, Image)} benutzen.
	 */
	@Deprecated
	public static BufferedImage get(int gameid, String filename)
			throws IOException {
		return get(gameid, filename, -1, -1);
	}
	
	/**
	 * Liefert ein Bild, dass auf Pewn hochgeladen wurde.
	 * 
	 * @param project
	 *            Das Projekt zu dem das Bild geh�rt.
	 * @param filename
	 *            Das Image-Objekt des Bildes.
	 * @return Das Bild.
	 * @throws IOException
	 *             wenn ein Fehler beim Lesen des Bilds auftritt.
	 * @see #get(int, String, int, int)
	 */
	public static BufferedImage get(Project project, Image image)
			throws IOException {
		return get(project.getId(), image.getFileName());
	}
}
