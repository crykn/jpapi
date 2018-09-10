package de.damios.jpapi.model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gson.annotations.SerializedName;

import de.damios.jpapi.core.Api;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * <i>Java-Modell des JSON-Neuigkeit-Objekts.</i>
 * 
 * @author damios
 * @since 0.5.0
 */
public class News implements Serializable {

	/**
	 * Der Service, der die Verbindung zu den benötigten API-Endpunkten
	 * beinhaltet.
	 */
	private static NewsService service = Api.createService(NewsService.class);

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
	@SerializedName("fileContainer")
	private Image[] images;

	/**
	 * @return Liefert die individuelle ID der Neugikeit.
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return Liefert den Text der Neuigkeit.
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return Liefert den Titel.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return Liefert das Erstellungsdatum.
	 */
	public Timestamp getCreationDate() {
		return creationDate;
	}

	/**
	 * @return Liefert das Datum des letzten Updates.
	 */
	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}

	/**
	 * @return Liefert den Autor der Neuigkeit.
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @return Liefert alle Hashtags, die der Neugikeit zugeordnet sind.
	 */
	public Hashtag[] getHashtags() {
		return hashtags;
	}

	/**
	 * @return Liefert alle Bilder, die zur Neuigkeit gehören.
	 */
	public Image[] getImages() {
		return images;
	}

	/**
	 * Liefert ein Array aller News eines Nutzers.
	 * 
	 * @param id
	 *            Die ID des Nutzers.
	 * @return News-Array
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static News[] getByUserId(long id) throws IOException {
		return Api.executeCall(service.getByUserId(id));
	}

	/**
	 * Das Service-Interface für die Verbindung zur Pewn-API, das für News
	 * zuständig ist.
	 * 
	 * @author damios
	 * @since 0.5.0
	 */
	interface NewsService {

		@GET("v1/users/id/{id}/news?format=json")
		Call<News[]> getByUserId(@Path("id") long id);

	}

}
