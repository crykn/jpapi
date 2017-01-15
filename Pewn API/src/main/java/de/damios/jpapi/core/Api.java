package de.damios.jpapi.core;

import java.io.IOException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;

import de.damios.jpapi.exception.JpapiInternalException;
import de.damios.jpapi.model.Token;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Diese Klasse besteht ausschließlich aus statischen Methoden, die zum
 * Ausführen der Anfragen an die <a href="http://pewn.de/papi/">Pewn-API</a>
 * notwendig sind.
 * 
 * @author damios
 * @since 0.1.0
 */
public class Api {

	private Api() {
	}

	/**
	 * URL-Type-Adapter.
	 * 
	 * @see #gson
	 */
	private static final TypeAdapter<URL> URL = new TypeAdapter<URL>() {
		@Override
		public URL read(JsonReader in) throws IOException {
			if (in.peek() == JsonToken.NULL) {
				in.nextNull();
				return null;
			}
			String nextString = in.nextString();
			return ("".equals(nextString) || "null".equals(nextString)) ? null : new URL(nextString);
		}

		@Override
		public void write(JsonWriter out, URL value) throws IOException {
			out.value(value == null ? null : value.toExternalForm());
		}
	};

	/**
	 * Gson-Parser.
	 */
	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
			.registerTypeAdapter(URL.class, URL).create();

	/**
	 * REST-Adapter.
	 */
	private static Retrofit restAdapter = new Retrofit.Builder().baseUrl(Constants.HOST + Constants.API_ROOT)
			.addConverterFactory(GsonConverterFactory.create(gson)).build();

	private static String clientId, clientSecret, refreshToken;

	/**
	 * Initialisiert die API für die Authentifizierung.
	 * <p>
	 * Die Einrichtung über diese Methode erlaubt auch den Zugriff auf
	 * geschützte Ressourcen.
	 * 
	 * @param clientId
	 *            Die ID der API-Anwendung.
	 * @param clientSecret
	 *            Das Secret der Anwendung.
	 * @param refreshToken
	 *            Der nutzer- und anwendungsspezifische Refresh-Token.
	 * 
	 * @see #authForFirstTime(String, String, String, String)
	 */
	public static void initAuth(String clientId, String clientSecret, String refreshToken) {
		Api.clientId = clientId;
		Api.clientSecret = clientSecret;
		Api.refreshToken = refreshToken;
	}

	public static String getClientId() {
		return clientId;
	}

	public static String getClientSecret() {
		return clientSecret;
	}

	public static String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * Authentifiziert den Nutzer das erste Mal.
	 * <p>
	 * Hier wird ein temporärer Auth-Code gegen einen dauerhaften Refresh-Token
	 * ausgetauscht.
	 * 
	 * @param clientId
	 *            Die ID der API-Anwendung.
	 * @param clientSecret
	 *            Das Secret der Anwendung.
	 * @param authCode
	 *            Der nutzer- und anwendungsspezifische Authorization-Code.
	 * @param redirectUri
	 *            Die für die Anwendung hinterlegte Weiterleitungs-URI.
	 * @return Der nutzer- und anwendungsspezifische Refresh-Token.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * 
	 * @see Token#getFirstToken(String, String, String, String)
	 */
	public static String authForFirstTime(String clientId, String clientSecret, String authCode, String redirectUri)
			throws IOException {
		Token t = Token.getFirstToken(clientId, clientSecret, authCode, redirectUri);
		return t.getRefreshToken();
	}

	/**
	 * Erstellt eine Implementation der API-Endpunkte, die im übergebenen
	 * Interface definiert werden.
	 * 
	 * @param <T>
	 *            Der Typ des Services.
	 * @param service
	 *            Die Klasse des Service-Interfaces.
	 * @return Der Service.
	 * @see #restAdapter
	 */
	public static <T> T createService(Class<T> service) {
		if (restAdapter == null)
			throw new IllegalStateException("Die API wurde nicht initialisiert.");
		return restAdapter.create(service);
	}

	/**
	 * Führt eine Anfrage an die Pewn-API aus und liefert die Antwort.
	 * 
	 * @param <T>
	 *            Der Typ der Antwort.
	 * @param call
	 *            Die Anfrage.
	 * @return Die Antwort der Pewn-API.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 */
	public static <T> T executeCall(Call<T> call) throws IOException {
		try {
			retrofit2.Response<T> response = call.execute();
			return response.body();
		} catch (IOException e) {
			if (e instanceof MalformedJsonException)
				throw new JpapiInternalException(e);
			else
				throw e;
		}
	}

}
