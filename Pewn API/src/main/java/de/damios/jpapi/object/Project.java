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
 * Java Repräsentierung des Json-Projekt-Objekts
 * 
 * @author damios
 * @version 1.0
 */
public class Project implements Serializable {

	private static final long serialVersionUID = 100L;
	public int id;
	@SerializedName("content")
	public String description;
	@SerializedName("headline")
	public String title;
	public Timestamp creationDate;
	@SerializedName("lastUpdate")
	public Timestamp lastUpdateDate;
	@SerializedName("customer")
	public User author;
	public int rating;
	public URL downloadWindows;
	public URL downloadLinux;
	public URL downloadMacOs;
	public URL downloadAndroid;
	public URL downloadIos;
	public URL downloadWindowsPhone;
	public URL downloadWeb;
	public String version;
	@SerializedName("fileContainers")
	public Image[] images;

	/**
	 * Liefert alle Hashtags in der {@linkplain description Spielebeschreibung}
	 * 
	 * @return HashSet{@literal<String>}
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
	 * @return Project-Array
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project[] get(String username) throws JsonSyntaxException,
			IOException {
		return ApiRequest.execute("game/user/" + username, Project[].class);
	}

	/**
	 * Liefert ein bestimmtes Spiel anhand seiner Id
	 * 
	 * @param gameid
	 *            Die Spieleid
	 * @return Project-Objekt
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project get(int gameid) throws JsonSyntaxException,
			IOException {
		return ApiRequest.execute("game/id/" + gameid, Project.class);
	}

	/**
	 * Liefert das neueste Spiel
	 * 
	 * @return Project-Objekt
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project getLatest() throws JsonSyntaxException, IOException {
		return ApiRequest.execute("game/last", Project.class);
	}

	/**
	 * Liefert ein zufälliges Spiel
	 * 
	 * @return Project-Object
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project getRandom() throws JsonSyntaxException, IOException {
		return ApiRequest.execute("game/random", Project.class);
	}

	/**
	 * Liefert alle Spiele {@link OrderedBy sortiert}
	 * 
	 * @param ord
	 *            Reihenfolge
	 * @return Project-Array
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project[] getAll(OrderedBy ord) throws JsonSyntaxException,
			IOException {
		return ApiRequest.execute("game/all/" + ord.parameter, Project[].class);
	}

	/**
	 * Gibt die Reihenfolge an, in der Spiele sortiert sein sollen
	 * 
	 * @author damios
	 * @see Project#getAll(OrderedBy)
	 */
	public static enum OrderedBy {
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
