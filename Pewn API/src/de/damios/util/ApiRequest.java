package de.damios.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * @author damios
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
	}

	private static <T> T readJson(String url, Class<T> clazz)
			throws JsonSyntaxException, Exception {
		return (T) gson.fromJson(UrlReader.read(url), clazz);
	}

	/**
	 * Führt einen API-Request aus
	 * 
	 * @param request Der Request Parameter
	 * @param clazz Die Klasse des Rückgabewerts 
	 * @return
	 * @throws JsonSyntaxException
	 * @throws Exception
	 */
	public static <A> A execute(String request, Class<A> clazz) {
		try {
			return (A) readJson(URL_PREFIX + VERSION + "/" + request
					+ URL_POSTFIX, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * GsonBuilder gson = new GsonBuilder();
	 * gson.registerTypeAdapter(MyType2.class, new MyTypeAdapter());
	 * gson.registerTypeAdapter(MyType.class, new MySerializer());
	 * gson.registerTypeAdapter(MyType.class, new MyDeserializer());
	 * gson.registerTypeAdapter(MyType.class, new MyInstanceCreator());
	 * 
	 * private class DateTimeDeserializer implements JsonDeserializer<DateTime>
	 * { public DateTime deserialize(JsonElement json, Type typeOfT,
	 * JsonDeserializationContext context) throws JsonParseException { return
	 * new DateTime(json.getAsJsonPrimitive().getAsString()); } }
	 */

}
