package de.damios.jpapi.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import de.damios.jpapi.exception.JpapiInternalException;
import de.damios.jpapi.object.Project;
import de.damios.util.UrlReader;

/**
 * Diese Klasse führt die Anfragen an die <a
 * href="http://pewn.de/papi/">Pewn-API</a> aus
 * 
 * @author damios
 * @since 1.0
 */
public class ApiRequest {

	private ApiRequest() {
	}

	private static Gson gson = new GsonBuilder().setDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssX").create();

	/**
	 * Liest den Inhalt einer Seite aus und parst ihn zur gegebenen Java-Klasse
	 * 
	 * @param <T>
	 *            Typ des Rückgabewerts
	 * @param url
	 *            URL des JSON-Dokuments
	 * @param clazz
	 *            Die Klasse des Rückgabewerts
	 * @return Geparstes Objekt
	 * @throws JsonSyntaxException
	 *             wenn ein Problem beim Verarbeiten der JSON-Elemente auftritt
	 * @throws IOException
	 *             wenn ein Fehler beim Lesen der Seite auftritt
	 * @see UrlReader#read(URL)
	 */
	private static <T> T readJson(URL url, Class<T> clazz)
			throws JsonSyntaxException, IOException {
		return (T) gson.fromJson(UrlReader.read(url), clazz);
	}

	/**
	 * Führt einen API-Request aus
	 * 
	 * @param <T>
	 *            Typ des Rückgabewerts
	 * @param request
	 *            Der Teil der Request URL nach "api/" im Format "v123/x/y"
	 *            (Beispiel: "{@linkplain Project#getRandom() v1/game/random}") <br>
	 * @param clazz
	 *            Die Klasse des Rückgabewerts
	 * @return Ergebnis der Anfrage
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt
	 * @see #readJson(URL, Class)
	 */
	public static <T> T execute(String request, Class<T> clazz)
			throws IOException, JsonSyntaxException {
		if (request == null || request.equalsIgnoreCase("")
				|| request.startsWith("/") || request.endsWith("/"))
			throw new IllegalArgumentException(
					"Der Paramter darf weder 'null' noch leer sein und darf nicht mit '/' anfangen oder enden");

		URL url;
		try {
			url = new URL(Constants.HOST + "api/" + request + "?format=json");
		} catch (MalformedURLException e) {
			throw new JpapiInternalException(e);
		}

		return (T) readJson(url, clazz);
	}
}
