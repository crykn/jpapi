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
 * <i>Java Repräsentierung des JSON-Projekt-Objekts.</i>
 * <p>
 * Die zentrale Klasse zum Umgang mit den Spielen auf Pewn.
 * 
 * @author damios
 * @since 0.1.0
 */
public class Project implements Serializable {

	private static final long serialVersionUID = 110L;
	/**
	 * Die individuelle ID eines jeden Spiels. Ist in der URL des Spiels auf
	 * Pewn zu finden (Bsp.: "http://pewn.de/game/12345-test/" -&gt; ID: 12345).
	 * 
	 * @see #getId()
	 */
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

	/**
	 * @return Liefert die individuelle ID des Spiels.
	 * @see #id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return Liefert die Beschreibung des Spiels auf Pewn.
	 */
	public String getDescriptionText() {
		return description;
	}

	/**
	 * @return Liefert den Namen des Spiels.
	 */
	public String getName() {
		return title;
	}

	/**
	 * @return Liefert das Erstellungsdatum des Spiels.
	 */
	public Timestamp getCreationDate() {
		return creationDate;
	}

	/**
	 * @return Liefert den Zeitpunkt des letzten Updates.
	 */
	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}

	/**
	 * @return Liefert den Entwickler des Spiels.
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * Liefert die Durchschnitts-Bewertung des Spiels.
	 * <p>
	 * <i>Der Rückgabetyp wird eventuell noch zu float geändert, sobald die
	 * Pewn-API auch die Nachkommastellen der Bewertung liefert. </i>
	 * 
	 * @return Die Durchschnitts-Bewertung des Spiels (Nachkommastellen
	 *         weggelassen).
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Liefert, ob überhaupt ein Downloadlink hinterlegt ist.
	 * 
	 * @return true, wenn ein Download vorhanden ist.
	 */
	public boolean hasDownload() {
		return downloadWindows != null || downloadLinux != null
				|| downloadMacOs != null || downloadWeb != null
				|| downloadAndroid != null || downloadIos != null
				|| downloadWindowsPhone != null;
	}

	/**
	 * Liefert den Link zum Download einer Version für <i>Windows</i>.
	 * 
	 * @return Die Download-URL; wenn eine entsprechende Version nicht zum
	 *         Download steht, null.
	 */
	public URL getDownloadUrlWindows() {
		return downloadWindows;
	}

	/**
	 * Liefert den Link zum Download einer Version für <i>Linux</i>.
	 * 
	 * @return Die Download-URL; wenn eine entsprechende Version nicht zum
	 *         Download steht, null.
	 */
	public URL getDownloadUrlLinux() {
		return downloadLinux;
	}

	/**
	 * Liefert den Link zum Download einer Version für <i>Mac OS X</i>.
	 * 
	 * @return Die Download-URL; wenn eine entsprechende Version nicht zum
	 *         Download steht, null.
	 */
	public URL getDownloadUrlMacOS() {
		return downloadMacOs;
	}

	/**
	 * Liefert den Link zum Download einer Version für <i>Android</i>.
	 * 
	 * @return Die Download-URL; wenn eine entsprechende Version nicht zum
	 *         Download steht, null.
	 */
	public URL getDownloadUrlAndroid() {
		return downloadAndroid;
	}

	/**
	 * Liefert den Link zum Download einer Version für <i>iOS</i>.
	 * 
	 * @return Die Download-URL; wenn eine entsprechende Version nicht zum
	 *         Download steht, null.
	 */
	public URL getDownloadUrlIos() {
		return downloadIos;
	}

	/**
	 * Liefert den Link zum Download einer Version für <i>Windows Phone</i>.
	 * 
	 * @return Die Download-URL; wenn eine entsprechende Version nicht zum
	 *         Download steht, null.
	 */
	public URL getDownloadUrlWindowsPhone() {
		return downloadWindowsPhone;
	}

	/**
	 * Liefert den Link zum Ausführen des Spiels im Browser.
	 * 
	 * @return Die URL; wenn eine entsprechende Version nicht verfügbar ist,
	 *         null.
	 */
	public URL getWebDownloadUrl() {
		return downloadWeb;
	}

	/**
	 * Liefert die Version des Spiels.
	 * <p>
	 * Es gibt keinerlei Vorgaben zum Format der Versionierung.
	 * 
	 * @return Die Version.
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @return Liefert alle Bilder, die dem Spiel zugeordnet sind.
	 */
	public Image[] getImages() {
		return images;
	}

	/**
	 * Liefert alle Bewertungen eines Spiels.
	 * 
	 * @return Die Bewertungen als Rating-Array; wenn eine Projekt noch keine
	 *         Bewertung erhalten hat, ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt.
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
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt.
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt.
	 * @see Hashtag#get(int)
	 */
	public Hashtag[] getHashtags() throws JsonSyntaxException, IOException {
		return Hashtag.get(id);
	}

	/**
	 * Liefert alle Spiele eines bestimmten Nutzers, aufsteigend nach
	 * Erstellungsdatum sortiert.
	 * 
	 * @param username
	 *            Nutzername
	 * @return Die Spiele als Project-Array; wenn ein Nutzer keine Spiele hat,
	 *         ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt.
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt.
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project[] get(String username) throws JsonSyntaxException,
			IOException {
		return ApiRequest.execute("v1/game/user/" + username, Project[].class);
	}

	/**
	 * Liefert ein bestimmtes Spiel anhand dessen individueller {@linkplain #id
	 * ID}.
	 * 
	 * @param gameid
	 *            Die Spiele-ID.
	 * @return Das Spiel.
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt; speziell
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
	 * @return Das Spiel.
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt.
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt.
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project getLatest() throws JsonSyntaxException, IOException {
		return ApiRequest.execute("v1/game/last", Project.class);
	}

	/**
	 * Liefert ein zufälliges Spiel.
	 * 
	 * @return Das Spiel.
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt.
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
	 * @return Alle Spiele als Project-Array.
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt.
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
	 * Gibt die Reihenfolge an, in der Spiele sortiert sein sollen.
	 * <ul>
	 * <li>{@link #CREATION_DATE Erstellungsdatum}
	 * <li>{@link #UPDATE_DATE Letztes Update}
	 * <li>{@link #RATING Bewertung}
	 * </ul>
	 * 
	 * @author damios
	 * @since 0.1.0
	 * @see Project#getAll(OrderedBy)
	 */
	public static enum OrderedBy implements Serializable {
		/**
		 * Nach Erstellungsdatum sortieren (Neuestes zuerst).
		 * 
		 * @see Project#creationDate
		 */
		CREATION_DATE("creation"),
		/**
		 * Nach Datum des letzten Updates sortieren (Neuestes zuerst).
		 * 
		 * @see Project#lastUpdateDate
		 */
		UPDATE_DATE("update"),
		/**
		 * Nach Bewertung sortieren (Höhere Bewertung zuerst).
		 * 
		 * @see Project#rating
		 */
		RATING("rating");

		String parameter;

		OrderedBy(String parameter) {
			this.parameter = parameter;
		}
	}
}
