package de.damios.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import de.damios.jpapi.exception.JpapiRequestException;
import de.damios.jpapi.object.Project;

/**
 * Diese Klasse führt die Anfragen an die <a
 * href="http://pewn.de/papi/">Pewn-API</a> aus
 * 
 * @author damios
 * @version 1.0
 *
 */
public class ApiRequest {
	private static final String URL_PREFIX = "http://pewn.de/api/";
	private static final String URL_POSTFIX = "?format=json";
	private static final String VERSION = "v1";
	private static Gson gson;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();

		// gson.registerTypeAdapter(MyType2.class, new MyTypeAdapter());
		/*
		 * private class DateTimeDeserializer implements
		 * JsonDeserializer<DateTime> { public DateTime deserialize(JsonElement
		 * json, Type typeOfT, JsonDeserializationContext context) throws
		 * JsonParseException { return new
		 * DateTime(json.getAsJsonPrimitive().getAsString()); } }
		 */
	}

	/**
	 * Liest den Inhalt einer Seite aus und parst ihn zur gegebenen Java-Klasse
	 * 
	 * @throws JsonSyntaxException
	 *             Wenn ein Problem beim Verarbeiten der Json-Elemente auftritt
	 * @throws IOException
	 *             Wenn ein Fehler beim Lesen der Seite auftritt
	 * @see UrlReader#read(URL)
	 */
	private static <T> T readJson(URL url, Class<T> clazz)
			throws JsonSyntaxException, IOException {
		return (T) gson.fromJson(UrlReader.read(url), clazz);
	}

	/**
	 * Führt einen API-Request aus
	 * 
	 * @param request
	 *            Der Teil der Request URL nach {@value #URL_PREFIX} im Format
	 *            "x/y/z" (Beispiel: "{@linkplain Project#getRandom()
	 *            game/random/}") <br>
	 * 
	 * @param clazz
	 *            Die Klasse des Rückgabewerts
	 * @return
	 * @throws JpapiRequestException
	 * @throws JsonSyntaxException
	 * @see #readJson(String, Class)
	 */
	public static <A> A execute(String request, Class<A> clazz) {
		if (request == null || request.equalsIgnoreCase("")
				|| request.startsWith("/") || request.endsWith("/"))
			throw new IllegalArgumentException(
					"Der Paramter darf weder 'null' und noch leer sein und darf nicht mit '/' anfangen und enden");

		URL url;
		try {
			url = new URL(URL_PREFIX + VERSION + "/" + request + URL_POSTFIX);
		} catch (MalformedURLException e) {
			throw new JpapiRequestException(e);
		}

		try {
			return (A) readJson(url, clazz);
		} catch (IOException e) {
			// nicht doch lieber schmeißen?
			throw new JpapiRequestException(e);
		}
	}
}
