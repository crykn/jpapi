package de.damios.jpapi.object;

import java.io.IOException;
import java.io.Serializable;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import de.damios.jpapi.core.ApiRequest;

/**
 * Java Repr�sentierung des JSON-Hashtag-Objekts
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

	/**
	 * Liefert die Kategorie, der der Hashtags zugeordnet wurde
	 * 
	 * @return Kategorie des Hashtags
	 */
	public HashtagCategory getCategory() {
		return category;
	}

	/**
	 * Liefert den Metatag des Hashtags, der dazu dient synonyme Hashtags unter
	 * einem �berbegriff zusammenzufassen. Wenn ein Metatag vorhanden ist,
	 * sollte der Metatag anstelle des eigentlichen Hashtags verwendet werden.
	 * 
	 * @return Der Metatag des Hashtags; null wenn keiner vorhanden ist
	 */
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
	 *             wenn ein Fehler beim Ausf�hren der Anfrage auftritt
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
	 * <i>Java Repr�sentierung des JSON-Hashtag-Kategorie-Objekts</i>
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
	 * <i>Java Repr�sentierung des JSON-Hashtag-Metatag-Objekts</i><br>
	 * Dient dazu, synonyme Hashtags unter einem �berbegriff zusammenzufassen.
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
