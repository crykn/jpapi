package de.damios.jpapi.object;

import java.io.IOException;
import java.io.Serializable;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import de.damios.jpapi.core.ApiRequest;

/**
 * <i>Java Repr�sentierung des JSON-Hashtag-Objekts.</i>
 * <p>
 * Wenn ein Hashtag einen {@linkplain HashtagMetatag Metatag} besitzt, sollte
 * dieser anstelle des Hashtags verwendet werden.
 * 
 * @author damios
 * @since 0.4.0
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

	/**
	 * @return Liefert die individuelle ID des Hashtags.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Liefert den Namen des Hashtags.
	 * <p>
	 * Jeder Name ist einzigartig und stellt das Hauptmerkmal des Hashtags dar.
	 * 
	 * @return Name des Hashtags.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Liefert die Anzahl der Projekte, in denen der Hashtag benutzt
	 *         wurde.
	 */
	public int getUsageCount() {
		return usedInProjectsCount;
	}

	/**
	 * @return Liefert die Kategorie, der der Hashtag zugeordnet wurde.
	 */
	public HashtagCategory getCategory() {
		return category;
	}

	/**
	 * Liefert den Metatag des Hashtags, der dazu dient, synonyme Hashtags unter
	 * einem �berbegriff zusammenzufassen. Wenn ein Metatag vorhanden ist,
	 * sollte der Metatag anstelle des eigentlichen Hashtags verwendet werden.
	 * 
	 * @return Der Metatag des Hashtags; null wenn keiner vorhanden ist.
	 */
	public HashtagMetatag getMetatag() {
		return metatag;
	}

	/**
	 * Liefert ein Array aller Hashtags eines Spiels.
	 * 
	 * @param gameid
	 *            Die Spiele-ID.
	 * @return Die Hashtags als Hashtag-Array; wenn eine Projekt mit keinerlei
	 *         Hashtags versehen ist, ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler beim Ausf�hren der Anfrage auftritt.
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt.
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Hashtag[] get(int gameid) throws JsonSyntaxException,
			IOException {
		return ApiRequest.execute("v1/game/id/" + gameid + "/hashtags",
				Hashtag[].class);
	}

	/**
	 * <i>Java Repr�sentierung des JSON-Hashtag-Kategorie-Objekts.</i>
	 * <p>
	 * Dient dazu, Hashtags, die �hnliche Merkmale beschreiben, in Kategorien
	 * zusammenzufassen (z.B.: Genres, Betriebssysteme).
	 * 
	 * @author damios
	 * @since 0.4.0
	 */
	public class HashtagCategory implements Serializable {

		private static final long serialVersionUID = 100L;
		private int id;
		private String name;

		/**
		 * @return Liefert die individuelle ID der Hashtag-Kategorie.
		 */
		public int getId() {
			return id;
		}

		/**
		 * Liefert den Namen der Hashtag-Kategorie.
		 * <p>
		 * Jeder Name ist einzigartig und stellt das Hauptmerkmal der Kategorie
		 * dar.
		 * 
		 * @return Name der Kategorie.
		 */
		public String getName() {
			return name;
		}
	}

	/**
	 * <i>Java Repr�sentierung des JSON-Hashtag-Metatag-Objekts.</i>
	 * <p>
	 * Dient dazu, synonyme Hashtags unter einem �berbegriff zusammenzufassen.
	 * <p>
	 * Verh�lt sich genauso wie ein Hashtag, liefert allerdings bei
	 * {@linkplain Hashtag#getCategory getCategory()},
	 * {@linkplain Hashtag#getUsageCount getUsageCount()} und
	 * {@linkplain Hashtag#getMetatag getMetatag()} jeweils null.
	 * 
	 * @author damios
	 * @since 0.4.2
	 */
	public class HashtagMetatag extends Hashtag implements Serializable {

		private static final long serialVersionUID = 100L;

	}

}