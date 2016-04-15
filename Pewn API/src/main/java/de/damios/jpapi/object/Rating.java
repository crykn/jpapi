package de.damios.jpapi.object;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import de.damios.jpapi.core.ApiRequest;

/**
 * Java Repräsentierung des JSON-Bewertungs-Objekts
 * 
 * @author damios
 * @version 0.4.0
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

	public int getId() {
		return id;
	}

	public int getRating() {
		return rating;
	}

	public Timestamp getDate() {
		return date;
	}

	public User getAuthor() {
		return author;
	}

	public String getText() {
		return text;
	}

	public RatingComment getComment() {
		return comment;
	}

	/**
	 * Liefert ein Array aller Bewertungen eines Spiels
	 * 
	 * @param gameid
	 *            Die Spieleid
	 * @return Rating-Array
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Rating[] get(int gameid) throws JsonSyntaxException,
			IOException {
		return ApiRequest.execute("v1/game/id/" + gameid + "/ratings",
				Rating[].class);
	}

	/**
	 * Java Repräsentierung des JSON-Bewertungskommentar-Objekts
	 * 
	 * @author damios
	 * @version 0.4.0
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

		public int getId() {
			return id;
		}

		public String getText() {
			return text;
		}

		public Timestamp getDate() {
			return date;
		}

		public User getAuthor() {
			return author;
		}

	}

}