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
 * <i>Java-Modell des JSON-Bewertungs-Objekts.</i>
 * 
 * @author damios
 * @since 0.4.0
 */
public class Rating implements Serializable {

	/**
	 * Der Service, der die Verbindung zu den benötigten API-Endpunkten
	 * beinhaltet.
	 */
	private static RatingService service = Api
			.createService(RatingService.class);

	private static final long serialVersionUID = 100L;
	private long id;
	private int rating;
	@SerializedName("ratingDate")
	private Timestamp date;
	@SerializedName("customer")
	private User author;
	@SerializedName("ratingReason")
	private String text;
	@SerializedName("ratingComment")
	private RatingComment comment;
	private String ratedVersion;
	private boolean curated;

	/**
	 * @return Liefert die individuelle ID der Bewertung.
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return Liefert den Zahlenwert der Bewertung; dabei sind Werte von 0 bis
	 *         10 möglich.
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @return Liefert das Datum des letzten Veränderns der Bewertung.
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * @return Liefert den Verfasser der Bewertung.
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @return Liefert den Text der Bewertung.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Liefert den Kommentar des Entwicklers.
	 * 
	 * @return Der Kommentar des Entwicklers; null wenn keiner vorhanden ist.
	 */
	public RatingComment getComment() {
		return comment;
	}

	/**
	 * Liefert die Version des Spiels, die bewertet wurde.
	 * 
	 * @return Die bewertete Version; bei alten Bewertungen null;
	 *         sonderbarerweise manchmal einen leeren String.
	 * @see Project#version
	 */
	public String getRatedVersion() {
		return ratedVersion;
	}

	/**
	 * Gibt an, ob eine Bewertung als kuriert markiert wurde.
	 * 
	 * @return Ob die Bewertung ausgezeichnet wurde.
	 */
	public boolean isCurated() {
		return curated;
	}

	/**
	 * Liefert ein Array aller Bewertungen eines Spiels.
	 * 
	 * @param gameid
	 *            Die Spiele-ID.
	 * @return Die Bewertungen als Rating-Array; wenn eine Projekt noch keine
	 *         Bewertung erhalten hat, ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Rating[] getByProjectId(long gameid) throws IOException {
		return Api.executeCall(service.getByProjectId(gameid));
	}

	/**
	 * Liefert ein Array aller Bewertungen eines Spiels.
	 * 
	 * @param userId
	 *            Die ID des Entwicklers, dessen Bewertungen abgerufen werden
	 *            sollen.
	 * @return Die Bewertungen als Rating-Array; wenn eine Benutzer noch keine
	 *         Bewertungen verfasst hat, ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Rating[] getByUserId(long userId) throws IOException {
		return Api.executeCall(service.getByUserId(userId));
	}

	/**
	 * <i>Java-Modell des JSON-Bewertungskommentar-Objekts.</i>
	 * <p>
	 * Stellt die Antwort eines Entwicklers auf eine {@linkplain Rating
	 * Bewertung} da.
	 * 
	 * @author damios
	 * @since 0.4.0
	 * @see Rating
	 */
	public class RatingComment implements Serializable {

		private static final long serialVersionUID = 100L;
		private long id;
		@SerializedName("content")
		private String text;
		@SerializedName("creationDate")
		private Timestamp date;
		@SerializedName("customer")
		private User author;

		/**
		 * @return Liefert die individuelle ID des Bewertungskommentars.
		 */
		public long getId() {
			return id;
		}

		/**
		 * @return Liefert den Inhalt des Bewertungskommentars.
		 */
		public String getText() {
			return text;
		}

		/**
		 * @return Liefert das Verfassungsdatum des Bewertungskommentars.
		 */
		public Timestamp getDate() {
			return date;
		}

		/**
		 * @return Liefert den Verfasser des Bewertungskommentars.
		 */
		public User getAuthor() {
			return author;
		}

	}

	/**
	 * Das Service-Interface für die Verbindung zur Pewn-API, das für Ratings
	 * zuständig ist.
	 * 
	 * @author damios
	 * @since 0.5.0
	 */
	interface RatingService {

		@GET("v1/games/id/{id}/ratings?format=json")
		Call<Rating[]> getByProjectId(@Path("id") long id);

		@GET("v1/users/id/{id}/ratings?format=json")
		Call<Rating[]> getByUserId(@Path("id") long id);

	}

}