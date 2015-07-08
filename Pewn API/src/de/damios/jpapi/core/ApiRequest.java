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
 * Diese Klasse f�hrt die Anfragen an die <a
 * href="http://pewn.de/papi/">Pewn-API</a> aus
 * 
 * @author damios
 * @since 1.0
 */
public class ApiRequest {
	
	private ApiRequest(){
	}

	/**
	 * Enth�lt die Adresse des Hosts, an den alle Anfragen gehen ({@value} )
	 */
	public static final String HOST = "http://pewn.de/";
	private static final String API_REQUEST = "api/v1/";
	private static final String PARAMETER = "?format=json";
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
	 * @param <T>
	 *            Typ des R�ckgabewerts
	 * @param url
	 *            Request-URL
	 * @param clazz
	 *            Die Klasse des R�ckgabewerts
	 * @return Geparstes Objekt
	 * @throws JsonSyntaxException
	 *             wenn ein Problem beim Verarbeiten der Json-Elemente auftritt
	 * @throws IOException
	 *             wenn ein Fehler beim Lesen der Seite auftritt
	 * @see UrlReader#read(URL)
	 */
	private static <T> T readJson(URL url, Class<T> clazz)
			throws JsonSyntaxException, IOException {
		return (T) gson.fromJson(UrlReader.read(url), clazz);
	}

	/**
	 * F�hrt einen API-Request aus
	 * 
	 * @param <T>
	 *            Typ des R�ckgabewerts
	 * @param request
	 *            Der Teil der Request URL nach {@value #API_REQUEST} im Format
	 *            "x/y/z" (Beispiel: "{@linkplain Project#getRandom()
	 *            game/random/}") <br>
	 * @param clazz
	 *            Die Klasse des R�ckgabewerts
	 * @return Ergebnis der Anfrage
	 * @throws IOException
	 *             wenn ein Fehler beim Ausf�hren der Anfrage auftritt
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt
	 * @see #readJson(URL, Class)
	 */
	public static <T> T execute(String request, Class<T> clazz) throws IOException, JsonSyntaxException{
		if (request == null || request.equalsIgnoreCase("")
				|| request.startsWith("/") || request.endsWith("/"))
			throw new IllegalArgumentException(
					"Der Paramter darf weder 'null' und noch leer sein und darf nicht mit '/' anfangen oder enden");

		URL url;
		try {
			url = new URL(HOST + API_REQUEST + request + PARAMETER);
		} catch (MalformedURLException e) {
			throw new JpapiInternalException(e);
		}

		return (T) readJson(url, clazz);
	}
}
