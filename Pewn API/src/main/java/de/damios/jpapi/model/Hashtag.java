package de.damios.jpapi.model;

import java.io.IOException;
import java.io.Serializable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.google.gson.annotations.SerializedName;

import de.damios.jpapi.core.Api;

/**
 * <i>Java Modell des JSON-Hashtag-Objekts.</i>
 * <p>
 * Wenn ein Hashtag einen {@linkplain HashtagMetatag Metatag} besitzt, sollte
 * dieser anstelle des Hashtags verwendet werden.
 * 
 * @author damios
 * @since 0.4.0
 */
public class Hashtag implements Serializable {

	/**
	 * Der Service, der die Verbindung zu den benötigten API-Endpunkten beinhaltet.
	 */
	private static HashtagService service = Api
			.createService(HashtagService.class);

	private static final long serialVersionUID = 100L;
	private long id;
	private String name;
	private int usedInProjectsCount;
	@SerializedName("hashtagCategory")
	private HashtagCategory category;
	@SerializedName("hashtagMetaTag")
	private HashtagMetatag metatag;

	/**
	 * @return Liefert die individuelle ID des Hashtags.
	 */
	public long getId() {
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
	 * einem Überbegriff zusammenzufassen. Wenn ein Metatag vorhanden ist,
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
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Hashtag[] getProjectHashtags(long gameid) throws IOException {
		return Api.executeCall(service.get(gameid));
	}

	/**
	 * <i>Java-Modell des JSON-Hashtag-Kategorie-Objekts.</i>
	 * <p>
	 * Dient dazu, Hashtags, die ähnliche Merkmale beschreiben, in Kategorien
	 * zusammenzufassen (z.B.: Genres, Betriebssysteme).
	 * 
	 * @author damios
	 * @since 0.4.0
	 */
	public class HashtagCategory implements Serializable {

		private static final long serialVersionUID = 100L;
		private long id;
		private String name;

		/**
		 * @return Liefert die individuelle ID der Hashtag-Kategorie.
		 */
		public long getId() {
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
	 * <i>Java-Modell des JSON-Hashtag-Metatag-Objekts.</i>
	 * <p>
	 * Dient dazu, synonyme Hashtags unter einem Überbegriff zusammenzufassen.
	 * <p>
	 * Verhält sich genauso wie ein Hashtag, liefert allerdings bei
	 * {@linkplain Hashtag#getCategory getCategory()},
	 * {@linkplain Hashtag#getUsageCount getUsageCount()} und
	 * {@linkplain Hashtag#getMetatag getMetatag()} jeweils null.
	 * 
	 * @author damios
	 * @since 0.4.2
	 */
	public class HashtagMetatag extends Hashtag {

		private static final long serialVersionUID = 100L;

	}

	/**
	 * Das Service-Interface für die Verbindung zur Pewn-API, das für Hashtags
	 * zuständig ist.
	 * 
	 * @author damios
	 * @since 0.5.0
	 */
	interface HashtagService {

		@GET("v1/games/id/{id}/hashtags?format=json")
		Call<Hashtag[]> get(@Path("id") long id);

	}

}