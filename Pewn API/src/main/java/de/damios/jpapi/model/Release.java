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
 * <i>Java-Modell des JSON-Release-Objekts.</i>
 * <p>
 * Die zentrale Klasse um die Releases von Spielen zu erhalten.
 * 
 * @author damios
 * @since 0.6.0
 */
public class Release implements Serializable {

	/**
	 * Der Service, der die Verbindung zu den benötigten API-Endpunkten
	 * beinhaltet.
	 */
	private static ReleaseService service = Api
			.createService(ReleaseService.class);

	private static final long serialVersionUID = 110L;
	private long id;
	@SerializedName("customer")
	private User author;
	private String title;
	private String description;
	private Timestamp releaseDate;
	@SerializedName("lastUpdate")
	private Timestamp lastUpdateDate;
	private long projectId = -1;
	private Download[] downloads;

	/**
	 * @return Liefert die individuelle ID des Releases.
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return Liefert den Ersteller des Releases.
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @return Liefert den Titel des Releases.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return Liefert die Beschreibung des Releases.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return Liefert das Datum, an welchem das Release veröffentlicht wurde.
	 */
	public Timestamp getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @return Liefert das Datum, an welchem das Release zuletzt geupdatet
	 *         wurde.
	 */
	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}

	/**
	 * Liefert alle Downloads, die zu einem Release gehören.
	 * 
	 * @return Download-Array
	 */
	public Download[] getDownloads() {
		return downloads;
	}

	@Override
	public int hashCode() {
		return 31 + (int) (id ^ (id >>> 32));
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return this.id == ((Release) obj).id;
	}

	/**
	 * Liefert das Spiel des Releases.
	 * 
	 * @return Das Spiel.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Project#getByProjectId(long)
	 */
	public Project getProject() throws IOException {
		if (projectId == -1)
			return null;
		return Project.getByProjectId(projectId);
	}

	/**
	 * Liefert ein Array aller Empfehlungen zu einem Spiel.
	 * 
	 * @param gameid
	 *            Die Spiele-ID.
	 * @return Recommendation-Array
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Release[] getByProjectId(int gameid) throws IOException {
		return Api.executeCall(service.getByProjectId(gameid));
	}

	/**
	 * Das Service-Interface für die Verbindung zur Pewn-API, das für Releases
	 * zuständig ist.
	 * 
	 * @author damios
	 * @since 0.6.0
	 */
	interface ReleaseService {

		@GET("v1/games/id/{id}/releases?format=json")
		Call<Release[]> getByProjectId(@Path("id") long id);

	}

}
