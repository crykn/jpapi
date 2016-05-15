package de.damios.jpapi.model;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.google.gson.annotations.SerializedName;

import de.damios.jpapi.core.Api;

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
	 * Der Service, der die Verbindung zu den ben�tigten API-Endpunkten beinhaltet.
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
	private int id;
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
	private URL downloadWindows;
	private URL downloadLinux;
	private URL downloadMacOs;
	private URL downloadAndroid;
	private URL downloadIos;
	private URL downloadWindowsPhone;
	private URL downloadWeb;
	private String version;
	@SerializedName("fileContainers")
	private Image[] images;
	private Rating[] ratings;
	private Hashtag[] hashtags;

	/**
	 * @return Liefert die individuelle ID des Spiels.
	 * @see #id
	 */
	public int getId() {
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
	 * Der Name kann jederzeit ge�ndert werden und sollte daher nicht als
	 * Identifikationsmerkmal f�r Spiele verwendet werden.
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
	 * Sofern das Spiel nicht einem Entwickler-Team zugewiesen ist, ist der
	 * Ersteller auch der Entwickler. Der Ersteller hat umfangreichere
	 * Befugnisse als andere Entwickler des Spiels.
	 * 
	 * @return Ersteller des Spiels.
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * Liefert die Durchschnitts-Bewertung des Spiels.
	 * <p>
	 * <i>Der R�ckgabetyp wird eventuell noch zu float ge�ndert, sobald die
	 * Pewn-API auch die Nachkommastellen der Bewertung liefert. </i>
	 * 
	 * @return Die Durchschnitts-Bewertung des Spiels (Nachkommastellen
	 *         weggelassen).
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Liefert, ob �berhaupt ein Downloadlink hinterlegt ist.
	 * 
	 * @return true, wenn ein Download vorhanden ist.
	 */
	public boolean hasDownload() {
		return downloadWindows != null || downloadLinux != null
				|| downloadMacOs != null || downloadWeb != null
				|| downloadAndroid != null || downloadIos != null
				|| downloadWindowsPhone != null;
	}

	/**
	 * Liefert den Link zum Download einer Version f�r <i>Windows</i>.
	 * 
	 * @return Die Download-URL; wenn eine entsprechende Version nicht zum
	 *         Download steht, null.
	 */
	public URL getDownloadUrlWindows() {
		return downloadWindows;
	}

	/**
	 * Liefert den Link zum Download einer Version f�r <i>Linux</i>.
	 * 
	 * @return Die Download-URL; wenn eine entsprechende Version nicht zum
	 *         Download steht, null.
	 */
	public URL getDownloadUrlLinux() {
		return downloadLinux;
	}

	/**
	 * Liefert den Link zum Download einer Version f�r <i>Mac OS X</i>.
	 * 
	 * @return Die Download-URL; wenn eine entsprechende Version nicht zum
	 *         Download steht, null.
	 */
	public URL getDownloadUrlMacOS() {
		return downloadMacOs;
	}

	/**
	 * Liefert den Link zum Download einer Version f�r <i>Android</i>.
	 * 
	 * @return Die Download-URL; wenn eine entsprechende Version nicht zum
	 *         Download steht, null.
	 */
	public URL getDownloadUrlAndroid() {
		return downloadAndroid;
	}

	/**
	 * Liefert den Link zum Download einer Version f�r <i>iOS</i>.
	 * 
	 * @return Die Download-URL; wenn eine entsprechende Version nicht zum
	 *         Download steht, null.
	 */
	public URL getDownloadUrlIos() {
		return downloadIos;
	}

	/**
	 * Liefert den Link zum Download einer Version f�r <i>Windows Phone</i>.
	 * 
	 * @return Die Download-URL; wenn eine entsprechende Version nicht zum
	 *         Download steht, null.
	 */
	public URL getDownloadUrlWindowsPhone() {
		return downloadWindowsPhone;
	}

	/**
	 * Liefert den Link zum Ausf�hren des Spiels im Browser.
	 * 
	 * @return Die URL; wenn eine entsprechende Version nicht verf�gbar ist,
	 *         null.
	 */
	public URL getWebDownloadUrl() {
		return downloadWeb;
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
	 * @return Liefert alle Bilder, die dem Spiel zugeordnet sind.
	 */
	public Image[] getImages() {
		return images;
	}

	/**
	 * Liefert alle Bewertungen eines Spiels.
	 * 
	 * @return Die Bewertungen als Rating-Array; wenn ein Projekt noch keine
	 *         Bewertung erhalten hat, ein leeres Array.
	 * @see Rating#get(int)
	 */
	public Rating[] getRatings() {
		return ratings;
	}

	/**
	 * Liefert alle Hashtags, die mit dem Spiel verkn�pft sind.
	 * 
	 * @return Die Hashtags als Hashtag-Array; wenn ein Projekt mit keinerlei
	 *         Hashtags versehen ist, ein leeres Array.
	 * @see Hashtag#get(int)
	 */
	public Hashtag[] getHashtags() {
		return hashtags;
	}

	/**
	 * Liefert alle Spiele eines bestimmten Nutzers, aufsteigend nach
	 * Erstellungsdatum sortiert.
	 * 
	 * @param username
	 *            Der Name des Entwicklers, dessen Spiele abgerufen werden
	 *            sollen.
	 * @return Die Spiele als Project-Array; wenn ein Nutzer keine Spiele hat,
	 *         ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Project[] get(String username) throws IOException {
		return loadProject(Api.executeCall(service.get(username)));
	}

	/**
	 * Liefert ein bestimmtes Spiel anhand dessen individueller {@linkplain #id
	 * ID}.
	 * 
	 * @param gameid
	 *            Die Spiele-ID.
	 * @return Das Spiel; null, wenn das Spiel nicht existiert.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Project get(int gameid) throws IOException {
		Api.executeCall(service.get(gameid)).author = new User();
		return loadProject(Api.executeCall(service.get(gameid)));
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
		return loadProject(Api.executeCall(service.getLatest()));
	}

	/**
	 * Liefert ein zuf�lliges Spiel.
	 * 
	 * @return Das Spiel.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Project getRandom() throws IOException {
		return loadProject(Api.executeCall(service.getRandom()));
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
		return loadProject(Api.executeCall(service.getAll(ord.parameter)));
	}

	/**
	 * Sendet Requests an die Pewn-API um Hashtags und Ratings zu erhalten.
	 * <p>
	 * Sollten in Zukunft obsolet werden.
	 * 
	 * @param p
	 *            Zu ladendes Projekt.
	 * @return Vollst�ndiges Projekt.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 */
	private static Project loadProject(Project p) throws IOException {
		p.ratings = Rating.get(p.getId());
		p.hashtags = Hashtag.get(p.getId());
		return p;
	}

	/**
	 * Sendet Requests an die Pewn-API um Hashtags und Ratings zu erhalten.
	 * <p>
	 * Sollten in Zukunft obsolet werden.
	 * 
	 * @param ps
	 *            Zu ladende Projekte.
	 * @return Vollst�ndige Projekte.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see {@link #loadProject(Project[])}
	 */
	private static Project[] loadProject(Project[] ps) throws IOException {
		for (Project p : ps) {
			loadProject(p);
		}
		return ps;
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
		 * Nach Bewertung sortieren (H�here Bewertung zuerst).
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
	 * Das Service-Interface f�r die Verbindung zur Pewn-API, das f�r Projekte
	 * zust�ndig ist.
	 * 
	 * @author damios
	 * @since 0.5.0
	 */
	interface ProjectService {

		@GET("v1/game/id/{id}?format=json")
		Call<Project> get(@Path("id") int id);

		@GET("v1/game/user/{username}?format=json")
		Call<Project[]> get(@Path("username") String username);

		@GET("v1/game/last?format=json")
		Call<Project> getLatest();

		@GET("v1/game/random?format=json")
		Call<Project> getRandom();

		@GET("v1/game/all/{order}?format=json")
		Call<Project[]> getAll(@Path("order") String order);

	}
}
