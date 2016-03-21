package de.damios.jpapi.object;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import de.damios.jpapi.core.ApiRequest;

/**
 * Java Repräsentierung des JSON-Projekt-Objekts
 * 
 * @author damios
 * @version 0.1.0
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
	 * Der Rückgabetyp wird noch zu float geändert, sobald die Pewn-API auch die
	 * Nachkommastellen der Bewertungen liefert.
	 * 
	 * @return Die Durchschnitts-Bewertung des Spiels (Nachkommastellen
	 *         weggelassen)
	 */
	public int getRating() {
		return rating;
	}

	public URL getWindowsDownloadUrl() {
		return downloadWindows;
	}

	public URL getLinuxDownloadUrl() {
		return downloadLinux;
	}

	public URL getMacOSDownloadUrl() {
		return downloadMacOs;
	}

	public URL getAndroidDownloadUrl() {
		return downloadAndroid;
	}

	public URL getIosDownloadUrl() {
		return downloadIos;
	}

	public URL getWindowsPhoneDownloadUrl() {
		return downloadWindowsPhone;
	}

	/**
	 * Liefert den Link zum Ausführen des Spiels im Browser
	 * 
	 * @return
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
	 * Liefert alle Hashtags in der {@linkplain description Spielebeschreibung}
	 * 
	 * @return Alle Hashtags als HashSet{@literal<String>}
	 */
	public Set<String> getHashtags() {
		Set<String> tags = new HashSet<String>();
		Pattern pattern = Pattern.compile("(#\\w+)");
		Matcher matcher = pattern.matcher(description);
		while (matcher.find()) {
			String hashtag = matcher.group(1);
			boolean alreadyAdded = false;
			for (String tag : tags) {
				if (hashtag.equals(tag))
					alreadyAdded = true;
			}
			if (!alreadyAdded)
				tags.add(hashtag);
		}
		return tags;
	}

	/**
	 * Liefert alle Projekte eines bestimmten Nutzers
	 * 
	 * @param username
	 *            Nutzername
	 * @return Die Projekte als Project-Array
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project[] get(String username) throws JsonSyntaxException,
			IOException {
		return ApiRequest.execute("v1/game/user/" + username, Project[].class);
	}

	/**
	 * Liefert ein bestimmtes Spiel anhand seiner Id
	 * 
	 * @param gameid
	 *            Die Spieleid
	 * @return Das Projekt
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project get(int gameid) throws JsonSyntaxException,
			IOException {
		return ApiRequest.execute("v1/game/id/" + gameid, Project.class);
	}

	/**
	 * Liefert das neueste Spiel
	 * 
	 * @return Das Projekt
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project getLatest() throws JsonSyntaxException, IOException {
		return ApiRequest.execute("v1/game/last", Project.class);
	}

	/**
	 * Liefert ein zufälliges Spiel
	 * 
	 * @return Das Projekt
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project getRandom() throws JsonSyntaxException, IOException {
		return ApiRequest.execute("v1/game/random", Project.class);
	}

	/**
	 * Liefert alle Spiele {@link OrderedBy sortiert}
	 * 
	 * @param ord
	 *            Reihenfolge
	 * @return Alle Projekte als Project-Array
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project[] getAll(OrderedBy ord) throws JsonSyntaxException,
			IOException {
		return ApiRequest.execute("v1/game/all/" + ord.parameter,
				Project[].class);
	}

	/**
	 * Gibt die Reihenfolge an, in der Spiele sortiert sein sollen
	 * 
	 * @author damios
	 * @see Project#getAll(OrderedBy)
	 */
	public static enum OrderedBy implements Serializable {
		/**
		 * Nach Erstellungsdatum sortieren (Neuestes zuerst)
		 */
		CREATION_DATE("creation"),
		/**
		 * Nach Datum des letzten Updates sortieren (Neuestes zuerst)
		 */
		UPDATE_DATE("update"),
		/**
		 * Nach Bewertung sortieren (Höhere Bewertung zuerst)
		 */
		RATING("rating");

		String parameter;

		OrderedBy(String parameter) {
			this.parameter = parameter;
		}
	}
}
