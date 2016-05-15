package de.damios.jpapi.core;

import java.io.IOException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;

import de.damios.jpapi.exception.JpapiInternalException;

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
	 */
	private static final TypeAdapter<URL> URL = new TypeAdapter<URL>() {
		@Override
		public URL read(JsonReader in) throws IOException {
			if (in.peek() == JsonToken.NULL) {
				in.nextNull();
				return null;
			}
			String nextString = in.nextString();
			return ("".equals(nextString) || "null".equals(nextString)) ? null
					: new URL(nextString);
		}

		@Override
		public void write(JsonWriter out, URL value) throws IOException {
			out.value(value == null ? null : value.toExternalForm());
		}
	};

	private static GsonBuilder builder = new GsonBuilder().setDateFormat(
			"yyyy-MM-dd HH:mm:ss").registerTypeAdapter(URL.class, URL);
	/**
	 * Gson-Parser.
	 */
	private static Gson gson = builder.create();

	/**
	 * REST-Adapter.
	 */
	private static Retrofit restAdapter = new Retrofit.Builder()
			.baseUrl(Constants.HOST + Constants.API_ROOT)
			.addConverterFactory(GsonConverterFactory.create(gson)).build();

	/**
	 * Erstellt eine Implementation der API-Endpunkte, die im übergebenen
	 * Interface definiert werden.
	 * 
	 * @param service
	 *            Die Klasse des Service-Interfaces.
	 * @return Der Service.
	 * @see #restAdapter
	 */
	public static <T> T createService(Class<T> service) {
		return restAdapter.create(service);
	}

	/**
	 * Führt eine Anfrage an die Pewn-API aus und liefert die Antwort.
	 * 
	 * @param T
	 *            Der Typ der Antwort.
	 * @param call
	 *            Die Anfrage.
	 * @return Die Antwort der Pewn-API.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 */
	public static <T> T executeCall(Call<T> call) throws IOException {
		try {
			Response<T> response = call.execute();
			return response.body();
		} catch (IOException e) {
			if (e instanceof MalformedJsonException)
				throw new JpapiInternalException(e);
			else
				throw e;
		}
	}

}
