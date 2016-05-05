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
 * Beinhaltet ausschließlich statische Methoden zum Erhalten der auf Pewn
 * gespeicherten Bilder.
 * 
 * @author damios
 * @since 0.1.0
 */
public class ImageProvider {

	private ImageProvider() {
	}

	/**
	 * Liefert ein Bild, das auf Pewn hochgeladen wurde.
	 * <p>
	 * Von Breite und Höhe wird nur der Wert verwendet, dessen relative
	 * Veränderung zum Ursprungswert am kleinsten ist (auch negative Werte
	 * werden beachtet!), um das ursprüngliche Seitenverhältnis des Bildes
	 * beizubehalten. Wenn einer oder beide der Parameter Breite und Höhe
	 * kleiner als 0 sind, wird die ursprüngliche Größe des Bildes beibehalten.
	 * 
	 * @param gameid
	 *            Die Spiele-ID.
	 * @param filename
	 *            Der Dateiname des Bildes.
	 * @param width
	 *            Die Bildbreite.
	 * @param height
	 *            Die Bildhöhe.
	 * @return Das Bild.
	 * @throws IOException
	 *             wenn ein Fehler beim Lesen des Bilds auftritt.
	 * @see #get(Project, Image)
	 * @see #get(Project, Image, int, int)
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
	 * Liefert ein Bild, das auf Pewn hochgeladen wurde.
	 * 
	 * @param gameid
	 *            Die Spiele-ID.
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
	 * Liefert ein Bild, das auf Pewn hochgeladen wurde.
	 * <p>
	 * Von Breite und Höhe wird nur der Wert verwendet, dessen relative
	 * Veränderung zum Ursprungswert am kleinsten ist (auch negative Werte
	 * werden beachtet!), um das ursprüngliche Seitenverhältnis des Bildes
	 * beizubehalten. Wenn einer oder beide der Parameter Breite und Höhe
	 * kleiner als 0 sind, wird die ursprüngliche Größe des Bildes beibehalten.
	 * <p>
	 * <i>Der Project-Parameter wird in absehbarer Zeit entfernt werden!</i>
	 * 
	 * @param project
	 *            Das Spiel, zu dem das Bild gehört.
	 * @param image
	 *            Das Image-Objekt des Bildes.
	 * @param width
	 *            Die Bildbreite.
	 * @param height
	 *            Die Bildhöhe.
	 * @return Das Bild.
	 * @throws IOException
	 *             wenn ein Fehler beim Lesen des Bilds auftritt.
	 * @see #get(int, String, int, int)
	 * @see #get(Project, Image)
	 * @see ImageIO#read(URL)
	 */
	public static BufferedImage get(Project project, Image image, int width,
			int height) throws IOException {
		return get(project.getId(), image.getFileName(), width, height);
	}

	/**
	 * Liefert ein Bild, das auf Pewn hochgeladen wurde.
	 * <p>
	 * <i>Der Project-Parameter wird in absehbarer Zeit entfernt werden!</i>
	 * 
	 * @param project
	 *            Das Spiel, zu dem das Bild gehört.
	 * @param image
	 *            Das Image-Objekt des Bildes.
	 * @return Das Bild.
	 * @throws IOException
	 *             wenn ein Fehler beim Lesen des Bilds auftritt.
	 * @see #get(Project, Image, int, int)
	 */
	public static BufferedImage get(Project project, Image image)
			throws IOException {
		return get(project, image, -1, -1);
	}
}
