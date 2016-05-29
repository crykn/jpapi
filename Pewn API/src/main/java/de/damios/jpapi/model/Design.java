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
 * <i>Java-Modell des JSON-Design-Objekts.</i>
 * 
 * @author damios
 * @since 0.5.0
 */
public class Design implements Serializable {

	/**
	 * Der Service, der die Verbindung zu den benötigten API-Endpunkten
	 * beinhaltet.
	 */
	private static DesignService service = Api
			.createService(DesignService.class);

	private static final long serialVersionUID = 100L;
	private int id;
	private String description;
	private String title;
	private Timestamp creationDate;
	@SerializedName("lastUpdate")
	private Timestamp lastUpdateDate;
	@SerializedName("customer")
	private User author;
	private Hashtag[] hashtags;
	@SerializedName("fileContainer")
	private Image image;

	/**
	 * @return Liefert die individuelle ID des Designs.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Liefert die Beschreibung des Designs.
	 * 
	 * @return Die Beschreibung.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return Liefert den Titel des Designs.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return Liefert das Erstellungsdatum des Designs.
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
	 * @return Liefert den Nutzer, der das Design erstellt hat.
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * Liefert alle Hashtags, die mit dem Design verknüpft sind.
	 * 
	 * @return Die Hashtags als Hashtag-Array; wenn ein Design mit keinerlei
	 *         Hashtags versehen ist, ein leeres Array.
	 */
	public Hashtag[] getHashtags() {
		return hashtags;
	}

	/**
	 * Liefert ein Array aller Designs eines Nutzers.
	 * 
	 * @param username
	 *            Der Nutzername.
	 * @return BlogPost-Array
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Design[] get(String username) throws IOException {
		return Api.executeCall(service.get(username));
	}

	/**
	 * Das Service-Interface für die Verbindung zur Pewn-API, das für Designs
	 * zuständig ist.
	 * 
	 * @author damios
	 * @since 0.5.0
	 */
	interface DesignService {

		@GET("v1/user/name/{name}/designs?format=json")
		Call<Design[]> get(@Path("name") String name);

	}

}
