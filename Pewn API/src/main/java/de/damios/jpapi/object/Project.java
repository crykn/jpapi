package de.damios.jpapi.object;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.sql.Timestamp;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import de.damios.jpapi.core.ApiRequest;

/**
 * <i>Java Repr�sentierung des JSON-Projekt-Objekts.</i>
 * 
 * @author damios
 * @since 0.1.0
 */
public class Project implements Serializable {

	private static final long serialVersionUID = 110L;
	private int id;
	@SerializedName("content")
	private String description;
	@SerializedName("headline")
	private String title;
	private Timestamp creationDate;
	@SerializedName("lastUpdate")
	private Timestamp lastUpdateDate;
	@SerializedName("customer")
	private User author;
	private int rating;
	private URL downloadWindows;
	private URL downloadLinux;
	private URL downloadMacOs;
	private URL downloadAndroid;
	private URL downloadIos;
	private URL downloadWindowsPhone;
	private URL downloadWeb;
	private String version;
	@SerializedName("fileContainers")
	private Image[] images;

	public int getId() {
		return id;
	}

	public String getDescriptionText() {
		return description;
	}

	public String getName() {
		return title;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}

	public User getAuthor() {
		return author;
	}

	/**
	 * Der R�ckgabetyp wird eventuell noch zu float ge�ndert, sobald die
	 * Pewn-API auch die Nachkommastellen der Bewertung liefert.
	 * 
	 * @return Die Durchschnitts-Bewertung des Spiels (Nachkommastellen
	 *         weggelassen).
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Liefert den Link zum Download einer Version f�r <i>Windows</i>.
	 * 
	 * @return Die Download-URL; wenn eine entsprechende Version nicht zum
	 *         Download steht, null.
	 */
	public URL getDownloadUrlWindows() {
		return downloadWindows;
	}

	/**
	 * Liefert den Link zum Download einer Version f�r <i>Linux</i>.
	 * 
	 * @return Die Download-URL; wenn eine entsprechende Version nicht zum
	 *         Download steht, null.
	 */
	public URL getDownloadUrlLinux() {
		return downloadLinux;
	}

	/**
	 * Liefert den Link zum Download einer Version f�r <i>Mac OS X</i>.
	 * 
	 * @return Die Download-URL; wenn eine entsprechende Version nicht zum
	 *         Download steht, null.
	 */
	public URL getDownloadUrlMacOS() {
		return downloadMacOs;
	}

	/**
	 * Liefert den Link zum Download einer Version f�r <i>Android</i>.
	 * 
	 * @return Die Download-URL; wenn eine entsprechende Version nicht zum
	 *         Download steht, null.
	 */
	public URL getDownloadUrlAndroid() {
		return downloadAndroid;
	}

	/**
	 * Liefert den Link zum Download einer Version f�r <i>iOS</i>.
	 * 
	 * @return Die Download-URL; wenn eine entsprechende Version nicht zum
	 *         Download steht, null.
	 */
	public URL getDownloadUrlIos() {
		return downloadIos;
	}

	/**
	 * Liefert den Link zum Download einer Version f�r <i>Windows Phone</i>.
	 * 
	 * @return Die Download-URL; wenn eine entsprechende Version nicht zum
	 *         Download steht, null.
	 */
	public URL getDownloadUrlWindowsPhone() {
		return downloadWindowsPhone;
	}

	/**
	 * Liefert den Link zum Ausf�hren des Spiels im Browser.
	 * 
	 * @return Die URL; wenn eine entsprechende Version nicht verf�gbar ist,
	 *         null.
	 */
	public URL getWebDownloadUrl() {
		return downloadWeb;
	}

	public String getVersion() {
		return version;
	}

	public Image[] getImages() {
		return images;
	}

	/**
	 * Liefert alle Bewertungen eines Spiels.
	 * 
	 * @return Die Bewertungen als Rating-Array; wenn eine Projekt noch keine
	 *         Bewertung erhalten hat, ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler beim Ausf�hren der Anfrage auftritt.
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt.
	 * @see Rating#get(int)
	 */
	public Rating[] getRatings() throws JsonSyntaxException, IOException {
		return Rating.get(id);
	}

	/**
	 * Liefert alle Hashtags in der Spielebeschreibung.
	 * 
	 * @return Die Hashtags als Hashtag-Array; wenn eine Projekt mit keinerlei
	 *         Hashtags versehen ist, ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler beim Ausf�hren der Anfrage auftritt.
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt.
	 * @see Hashtag#get(int)
	 */
	public Hashtag[] getHashtags() throws JsonSyntaxException, IOException {
		return Hashtag.get(id);
	}

	/**
	 * Liefert alle Projekte eines bestimmten Nutzers, aufsteigend nach
	 * Erstellungsdatum sortiert.
	 * 
	 * @param username
	 *            Nutzername
	 * @return Die Projekte als Project-Array; wenn ein Nutzer keine Projekte
	 *         hat, ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler beim Ausf�hren der Anfrage auftritt.
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt.
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project[] get(String username) throws JsonSyntaxException,
			IOException {
		return ApiRequest.execute("v1/game/user/" + username, Project[].class);
	}

	/**
	 * Liefert ein bestimmtes Spiel anhand seiner Id.
	 * 
	 * @param gameid
	 *            Die Spieleid.
	 * @return Das Projekt.
	 * @throws IOException
	 *             wenn ein Fehler beim Ausf�hren der Anfrage auftritt; speziell
	 *             eine {@linkplain FileNotFoundException}, wenn das Spiel nicht
	 *             existiert.
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt.
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project get(int gameid) throws JsonSyntaxException,
			IOException {
		return ApiRequest.execute("v1/game/id/" + gameid, Project.class);
	}

	/**
	 * Liefert das neueste Spiel.
	 * 
	 * @return Das Projekt.
	 * @throws IOException
	 *             wenn ein Fehler beim Ausf�hren der Anfrage auftritt.
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt.
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project getLatest() throws JsonSyntaxException, IOException {
		return ApiRequest.execute("v1/game/last", Project.class);
	}

	/**
	 * Liefert ein zuf�lliges Spiel.
	 * 
	 * @return Das Projekt
	 * @throws IOException
	 *             wenn ein Fehler beim Ausf�hren der Anfrage auftritt.
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt.
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project getRandom() throws JsonSyntaxException, IOException {
		return ApiRequest.execute("v1/game/random", Project.class);
	}

	/**
	 * Liefert alle Spiele auf Pewn in einer {@link OrderedBy bestimmten
	 * Reihenfolge}.
	 * 
	 * @param ord
	 *            Reihenfolge, in der die Spiele sortiert sein sollen.
	 * @return Alle Projekte als Project-Array.
	 * @throws IOException
	 *             wenn ein Fehler beim Ausf�hren der Anfrage auftritt.
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt.
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project[] getAll(OrderedBy ord) throws JsonSyntaxException,
			IOException {
		return ApiRequest.execute("v1/game/all/" + ord.parameter,
				Project[].class);
	}

	/**
	 * Gibt die Reihenfolge an, in der Spiele sortiert sein sollen. <li>
	 * {@link #CREATION_DATE Erstellungsdatum}</li> <li>{@link #UPDATE_DATE
	 * Letztes Update}</li> <li>{@link #RATING Bewertung}</li>
	 * 
	 * @author damios
	 * @since 0.1.0
	 * @see Project#getAll(OrderedBy)
	 */
	public static enum OrderedBy implements Serializable {
		/**
		 * Nach Erstellungsdatum sortieren (Neuestes zuerst).
		 */
		CREATION_DATE("creation"),
		/**
		 * Nach Datum des letzten Updates sortieren (Neuestes zuerst).
		 */
		UPDATE_DATE("update"),
		/**
		 * Nach Bewertung sortieren (H�here Bewertung zuerst).
		 */
		RATING("rating");

		String parameter;

		OrderedBy(String parameter) {
			this.parameter = parameter;
		}
	}
}
