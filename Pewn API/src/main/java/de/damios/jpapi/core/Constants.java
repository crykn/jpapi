package de.damios.jpapi.core;

/**
 * Enthält Konstanten, die im Wrapper verwendet werden.
 *
 * @author damios
 * @since 0.1.0
 */
public final class Constants {

	private Constants() {
	}

	/**
	 * Adresse des Hosts, an den alle Anfragen gehen ({@value}).
	 */
	public static final String HOST = "https://pewn.de/";
	/**
	 * Root-Verzeichnis der API ({@value}).
	 */
	public static final String API_ROOT = "api/";

	public static final String DOWNLOAD_URL = HOST + API_ROOT + "v1/analytics/downloads/";

}
