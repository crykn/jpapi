package de.damios.jpapi.object;

import java.io.IOException;
import java.io.Serializable;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import de.damios.jpapi.core.ApiRequest;

/**
 * Java Repräsentierung des JSON-Hashtag-Objekts
 * 
 * @author damios
 * @version 0.4.0
 */
public class Hashtag implements Serializable {

	private static final long serialVersionUID = 100L;
	private int id;
	private String name;
	private int usedInProjectsCount;
	@SerializedName("hashtagCategory")
	private HashtagCategory category;
	@SerializedName("hashtagMetaTag")
	private HashtagMetatag metatag;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	/**
	 * Liefert Anzahl der Projekte, in denen der Hashtag benutzt wurde
	 * 
	 * @return Anzahl der Benutzungen
	 */
	public int getUsageCount() {
		return usedInProjectsCount;
	}

	public HashtagCategory getCategory() {
		return category;
	}

	public HashtagMetatag getMetatag() {
		return metatag;
	}

	/**
	 * Liefert ein Array aller Hashtags eines Spiels
	 * 
	 * @param gameid
	 *            Die Spieleid
	 * @return Die Hashtags als Hashtag-Array; wenn eine Projekt mit keinerlei
	 *         Hashtags versehen ist, ein leeres Array
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Hashtag[] get(int gameid) throws JsonSyntaxException,
			IOException {
		return ApiRequest.execute("v1/game/id/" + gameid + "/hashtags",
				Hashtag[].class);
	}

	/**
	 * Java Repräsentierung des JSON-Hashtag-Kategorie-Objekts
	 * 
	 * @author damios
	 * @version 0.4.0
	 */
	public class HashtagCategory implements Serializable {

		private static final long serialVersionUID = 100L;
		private int id;
		private String name;

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}
	}

	/**
	 * Java Repräsentierung des JSON-Hashtag-Metatag-Objekts
	 * 
	 * @author damios
	 * @version 0.4.2
	 */
	public class HashtagMetatag implements Serializable {

		private static final long serialVersionUID = 100L;
		private int id;
		private String name;

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}
	}

}
