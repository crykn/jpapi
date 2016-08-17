package de.damios.jpapi.model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.google.gson.annotations.SerializedName;

import de.damios.jpapi.core.Api;

/**
 * <i>Java-Modell des JSON-Blog-Post-Objekts.</i>
 * 
 * @author damios
 * @since 0.5.0
 */
public class BlogPost implements Serializable {

	/**
	 * Der Service, der die Verbindung zu den benötigten API-Endpunkten
	 * beinhaltet.
	 */
	private static BlogPostService service = Api
			.createService(BlogPostService.class);

	private static final long serialVersionUID = 100L;
	private long id;
	@SerializedName("content")
	private String text;
	@SerializedName("headline")
	private String title;
	private Timestamp creationDate;
	@SerializedName("lastUpdate")
	private Timestamp lastUpdateDate;
	@SerializedName("customer")
	private User author;
	private Hashtag[] hashtags;

	/**
	 * @return Liefert die individuelle ID des Blog-Posts.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Liefert den Inhalt des Blog-Posts.
	 * 
	 * @return Der Text.
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return Liefert den Titel des Posts.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return Liefert das Erstellungsdatum des Blog-Posts.
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
	 * @return Liefert den Nutzer, der den Blog erstellt hat.
	 */
	public User getAuthor() {
		return author;
	}
	
	/**
	 * Liefert alle Hashtags, die mit dem Blog-Post verknüpft sind.
	 * 
	 * @return Die Hashtags als Hashtag-Array; wenn ein Blog mit keinerlei
	 *         Hashtags versehen ist, ein leeres Array.
	 */
	public Hashtag[] getHashtags() {
		return hashtags;
	}

	/**
	 * Liefert ein Array aller Blog-Posts eines Nutzers.
	 * 
	 * @param id
	 *            Die ID des Nutzers.
	 * @return BlogPost-Array.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static BlogPost[] get(long id) throws IOException {
		return Api.executeCall(service.get(id));
	}

	/**
	 * Das Service-Interface für die Verbindung zur Pewn-API, das für Blog Posts
	 * zuständig ist.
	 * 
	 * @author damios
	 * @since 0.5.0
	 */
	interface BlogPostService {

		@GET("v1/users/id/{id}/blogs?format=json")
		Call<BlogPost[]> get(@Path("id") long id);

	}

}
