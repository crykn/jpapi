package de.damios.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Bietet Methoden zum Auslesen des Inhalts einer Seite.
 * 
 * @author damios
 * @since 0.1.0
 */
public class UrlReader {

	private UrlReader() {
	}

	/**
	 * Liefert den Inhalt einer Seite als String.
	 * 
	 * @param url
	 *            Die URL.
	 * @return Inhalt der Seite als Text.
	 * @throws IOException
	 *             wenn ein Fehler beim Öffnen des Streams oder beim Lesen der
	 *             Seite auftritt.
	 */
	public static String read(URL url) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				url.openStream(), "UTF-8"))) {
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		}
	}
}
