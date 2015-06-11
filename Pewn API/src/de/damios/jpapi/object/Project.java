package de.damios.jpapi.object;

import java.io.Serializable;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.annotations.SerializedName;

import de.damios.util.ApiRequest;

/**
 * Java Repr�sentierung des Json-Projekt-Objekts
 * 
 * @author damios
 * @version 1.0
 *
 */
public class Project implements Serializable {
	private static final long serialVersionUID = 100L;
	public int id;
	@SerializedName("content")
	public String description;
	public String headline;
	// TODO convert date
	public String creationDate;
	@SerializedName("lastUpdate")
	public String lastUpdateDate;
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
		Set<String> tmp = new HashSet<String>();
		String regexPattern = "(#\\w+)";
		Pattern p = Pattern.compile(regexPattern);
		Matcher m = p.matcher(description);
		while (m.find()) {
			String hashtag = m.group(1);
			boolean alreadyUsed = true;
			for (String tag : tmp) {
				if (hashtag.equals(tag))
					alreadyUsed = false;
			}
			if (alreadyUsed)
				tmp.add(hashtag);
		}
		return tmp;
	}

	/**
	 * Liefert alle Projekte eines bestimmten Nutzers
	 * 
	 * @param username
	 *            Nutzername
	 * @return Project-Array
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project[] get(String username) {
		return ApiRequest.execute("game/user/" + username, Project[].class);
	}

	/**
	 * Liefert ein bestimmtes Spiel anhand seiner Id
	 * 
	 * @param gameid
	 *            Die Spieleid
	 * @return Project-Objekt
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project get(int gameid) {
		return ApiRequest.execute("game/id/" + gameid, Project.class);
	}

	/**
	 * Liefert das neueste Spiel
	 * 
	 * @return Project-Objekt
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project getLatest() {
		return ApiRequest.execute("game/last", Project.class);
	}

	/**
	 * Liefert ein zuf�lliges Spiel
	 * 
	 * @return Project-Object
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project getRandom() {
		return ApiRequest.execute("game/random", Project.class);
	}

	/**
	 * Liefert alle Spiele {@link OrderedBy sortiert}
	 * 
	 * @param ord
	 *            Reihenfolge
	 * @return Project-Array
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Project[] getAll(OrderedBy ord) {
		return ApiRequest.execute("game/all/" + ord.parameter, Project[].class);
	}

	/**
	 * Gibt die Reihenfolge an, in der Spiele sortiert sein sollen
	 * 
	 * @author damios
	 * @see Project#getAll(OrderedBy)
	 *
	 */
	public static enum OrderedBy {
		CREATION_DATE("creation"), UPDATE_DATE("update"), RATING("rating");

		String parameter;

		OrderedBy(String parameter) {
			this.parameter = parameter;
		}
	}
}
