package de.damios.jpapi.object;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import de.damios.jpapi.core.ApiRequest;

/**
 * <i>Java Repräsentierung des JSON-Bewertungs-Objekts.</i>
 * 
 * @author damios
 * @since 0.4.0
 */
public class Rating implements Serializable {

	private static final long serialVersionUID = 100L;
	private int id;
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

	/**
	 * @return Liefert die individuelle ID der Bewertung.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return Liefert den Zahlenwert der Bewertung; Dabei sind Werte von 0 bis
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
	 * @return Liefert den Kommentar des Entwicklers; null wenn keiner vorhanden
	 *         ist.
	 */
	public RatingComment getComment() {
		return comment;
	}

	/**
	 * Liefert die Version des Spiels, die bewertet wurde.
	 * 
	 * @return Die bewertete Version; bei alten Spielen null; sonderbarerweise
	 *         manchmal einen leeren String.
	 * @see Project#version
	 */
	public String getRatedVersion() {
		return ratedVersion;
	}

	/**
	 * Liefert ein Array aller Bewertungen eines Spiels.
	 * 
	 * @param gameid
	 *            Die Spiele-ID
	 * @return Die Bewertungen als Rating-Array; wenn eine Projekt noch keine
	 *         Bewertung erhalten hat, ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt.
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt.
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Rating[] get(int gameid) throws JsonSyntaxException,
			IOException {
		return ApiRequest.execute("v1/game/id/" + gameid + "/ratings",
				Rating[].class);
	}

	/**
	 * <i>Java Repräsentierung des JSON-Bewertungskommentar-Objekts.</i>
	 * <p>
	 * Stellt die Antwort eines Entwicklers auf eine {@linkplain Rating Bewertung} da.
	 * 
	 * @author damios
	 * @since 0.4.0
	 * @see Rating
	 */
	public class RatingComment implements Serializable {

		private static final long serialVersionUID = 100L;
		private int id;
		@SerializedName("content")
		private String text;
		@SerializedName("creationDate")
		private Timestamp date;
		@SerializedName("customer")
		private User author;

		/**
		 * @return Liefert die individuelle ID des Bewertungskommentars.
		 */
		public int getId() {
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

}