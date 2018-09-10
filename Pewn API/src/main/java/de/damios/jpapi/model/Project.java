package de.damios.jpapi.model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gson.annotations.SerializedName;

import de.damios.jpapi.core.Api;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * <i>Java-Modell des JSON-Projekt-Objekts.</i>
 * <p>
 * Die zentrale Klasse zum Umgang mit den Spielen auf Pewn.
 * 
 * @author damios
 * @since 0.1.0
 */
public class Project implements Serializable {

	/**
	 * Der Service, der die Verbindung zu den benötigten API-Endpunkten
	 * beinhaltet.
	 */
	private static ProjectService service = Api
			.createService(ProjectService.class);

	private static final long serialVersionUID = 110L;
	/**
	 * Die individuelle ID eines jeden Spiels. Ist in der URL des Spiels auf
	 * Pewn zu finden (Bsp.: "http://pewn.de/game/12345-test/" -&gt; ID: 12345).
	 * 
	 * @see #getId()
	 */
	private long id;
	@SerializedName("content")
	private String description;
	@SerializedName("headline")
	private String title;
	private Timestamp creationDate;
	@SerializedName("lastUpdate")
	private Timestamp lastUpdateDate;
	@SerializedName("customer")
	private User author;
	private int rating;
	private String version;
	@SerializedName("fileContainers")
	private Image[] images;
	private Hashtag[] hashtags;
	private Team team;
	// private String website;
	private String advertisement;

	/**
	 * @return Liefert die individuelle ID des Spiels.
	 * @see #id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return Liefert die Beschreibung des Spiels auf Pewn.
	 * @see #getHashtags
	 */
	public String getDescriptionText() {
		return description;
	}

	/**
	 * Liefert den Namen des Spiels.
	 * <p>
	 * Der Name kann jederzeit geändert werden und sollte daher nicht als
	 * Identifikationsmerkmal für Spiele verwendet werden.
	 * 
	 * @return Der Projektname.
	 */
	public String getName() {
		return title;
	}

	/**
	 * @return Liefert das Erstellungsdatum des Spiels.
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
	 * Liefert den Ersteller des Spiels.
	 * <p>
	 * Sofern das Spiel nicht einem {@linkplain #getTeam() Entwickler-Team}
	 * zugewiesen ist, ist der Ersteller auch der Entwickler. Der Ersteller hat
	 * umfangreichere Befugnisse als andere Entwickler des Spiels.
	 * 
	 * @return Ersteller des Spiels.
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * Liefert das Entwickler-Team, dem das Projekt gehört.
	 * 
	 * @return Das Entwickler-Team.
	 * @see #getAuthor
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * Liefert die Durchschnitts-Bewertung des Spiels.
	 * 
	 * @return Die Durchschnitts-Bewertung des Spiels (Nachkommastellen
	 *         weggelassen).
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Liefert die Version des Spiels.
	 * <p>
	 * Es gibt keinerlei Vorgaben zum Format der Versionierung.
	 * 
	 * @return Die Version.
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Liefert alle Bilder, die dem Spiel zugeordnet sind.
	 * 
	 * @return Alle zugeordneten Bilder; wenn ein Projekt keine Bilder besitzt,
	 *         ein leeres Array.
	 */
	public Image[] getImages() {
		return images;
	}

	/**
	 * Liefert alle Bewertungen eines Spiels.
	 * 
	 * @return Die Bewertungen als Rating-Array; wenn ein Projekt noch keine
	 *         Bewertung erhalten hat, ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Rating#getByProjectId(long)
	 */
	public Rating[] getRatings() throws IOException {
		return Rating.getByProjectId(id);
	}

	/**
	 * Liefert alle Hashtags, die mit dem Spiel verknüpft sind.
	 * 
	 * @return Die Hashtags als Hashtag-Array; wenn ein Projekt mit keinerlei
	 *         Hashtags versehen ist, ein leeres Array.
	 * @see Hashtag#getByProjectId(long)
	 */
	public Hashtag[] getHashtags() {
		return hashtags;
	}

	/**
	 * Liefert die Kurzbeschreibung des Spiels.
	 * 
	 * @return Die Kurzbeschreibung; zwischen 45 und 250 Zeichen lang.
	 */
	public String getAdvertisement() {
		return advertisement;
	}

	/**
	 * Liefert ein bestimmtes Spiel anhand dessen individueller {@linkplain #id
	 * ID}.
	 * 
	 * @param gameid
	 *            Die Spiele-ID.
	 * @return Das Spiel; <code>null</code>, wenn das Spiel nicht existiert.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Project getByProjectId(long gameid) throws IOException {
		return Api.executeCall(service.getByProjectId(gameid));
	}

	/**
	 * Liefert alle Spiele eines bestimmten Nutzers, aufsteigend nach
	 * Erstellungsdatum sortiert.
	 * 
	 * @param userId
	 *            Die ID des Entwicklers, dessen Spiele abgerufen werden sollen.
	 * @return Die Spiele als Project-Array; wenn ein Nutzer keine Spiele hat,
	 *         ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Project[] getByUserId(long userId) throws IOException {
		return Api.executeCall(service.getByUserId(userId));
	}

	/**
	 * Liefert alle Spiele, die ein bestimmter Benutzer gelikt hat.
	 * 
	 * @param userId
	 *            Die ID des Entwicklers, dessen gelikte Spiele abgerufen werden
	 *            sollen.
	 * @return Die Spiele als Project-Array; wenn ein Nutzer keine Spiele
	 *         geliket hat, ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Project[] getLikedProjectsByUserId(long userId)
			throws IOException {
		return Api.executeCall(service.getLikedProjectsByUserId(userId));
	}

	/**
	 * Liefert das neueste Spiel.
	 * 
	 * @return Das Spiel.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Project getLatest() throws IOException {
		return Api.executeCall(service.getLatest());
	}

	/**
	 * Liefert ein zufälliges Spiel.
	 * 
	 * @return Das Spiel.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Project getRandom() throws IOException {
		return Api.executeCall(service.getRandom());
	}

	/**
	 * Liefert alle Spiele auf Pewn in einer {@link OrderedBy bestimmten
	 * Reihenfolge}.
	 * 
	 * @param ord
	 *            Reihenfolge, in der die Spiele sortiert sein sollen.
	 * @return Alle Spiele als Project-Array.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Project[] getAll(OrderedBy ord) throws IOException {
		return Api.executeCall(service.getAll(ord.parameter));
	}

	/**
	 * Liefert alle Spiele in der 'Neueste Spiele'-Box auf der Startseite.
	 * 
	 * @return Alle Spiele als Project-Array.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Project[] getInLatestBox() throws IOException {
		return Api.executeCall(service.getLastUpdateBox());
	}

	/**
	 * Liefert alle Spiele in der 'Am meisten gesehen'-Box auf der Startseite.
	 * 
	 * @return Alle Spiele als Project-Array.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Project[] getInMostViewedBox() throws IOException {
		return Api.executeCall(service.getMostViewedBox());
	}

	/**
	 * Liefert ein Array aller Empfehlungen zu einem Spiel.
	 * 
	 * @param gameid
	 *            Die Spiele-ID.
	 * @return Alle empfohlenen Spiele als Project-Array.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Project[] getRecommendedProjectsByProjectId(int gameid)
			throws IOException {
		return Api
				.executeCall(service.getRecommendedProjectsByProjectId(gameid));
	}

	/**
	 * Gibt die Reihenfolge an, in der Spiele sortiert sein sollen.
	 * <ul>
	 * <li>{@link #CREATION_DATE Erstellungsdatum}
	 * <li>{@link #UPDATE_DATE Letztes Update}
	 * <li>{@link #RATING Bewertung}
	 * </ul>
	 * 
	 * @author damios
	 * @since 0.1.0
	 * @see Project#getAll(OrderedBy)
	 */
	public static enum OrderedBy implements Serializable {
		/**
		 * Nach Erstellungsdatum sortieren (Neuestes zuerst).
		 * 
		 * @see Project#creationDate
		 */
		CREATION_DATE("creation"),
		/**
		 * Nach Datum des letzten Updates sortieren (Neuestes zuerst).
		 * 
		 * @see Project#lastUpdateDate
		 */
		UPDATE_DATE("update"),
		/**
		 * Nach Bewertung sortieren (Höhere Bewertung zuerst).
		 * 
		 * @see Project#rating
		 */
		RATING("rating");

		String parameter;

		OrderedBy(String parameter) {
			this.parameter = parameter;
		}
	}

	/**
	 * Das Service-Interface für die Verbindung zur Pewn-API, das für Projekte
	 * zuständig ist.
	 * 
	 * @author damios
	 * @since 0.5.0
	 */
	interface ProjectService {

		@GET("v1/contents/games/views?format=json")
		Call<Project[]> getMostViewedBox();

		@GET("v1/contents/games/update?format=json")
		Call<Project[]> getLastUpdateBox();

		@GET("v1/games/id/{id}?format=json")
		Call<Project> getByProjectId(@Path("id") long id);

		@GET("v1/games/last?format=json")
		Call<Project> getLatest();

		@GET("v1/games/random?format=json")
		Call<Project> getRandom();

		@GET("v1/games/all?format=json")
		Call<Project[]> getAll(@Query("order") String order);

		@GET("v1/users/id/{id}/games?format=json")
		Call<Project[]> getByUserId(@Path("id") long userId);

		@GET("v1/users/id/{id}/likes?format=json")
		Call<Project[]> getLikedProjectsByUserId(@Path("id") long userId);

		@GET("v1/games/id/{id}/recommendations?format=json")
		Call<Project[]> getRecommendedProjectsByProjectId(@Path("id") long id);

	}
}
