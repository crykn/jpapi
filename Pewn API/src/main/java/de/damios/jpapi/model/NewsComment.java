package de.damios.jpapi.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gson.annotations.SerializedName;

/**
 * <i>Java-Modell des JSON-Neuigkeiten-Kommentar-Objekts.</i>
 * 
 * @author damios
 * @since 0.5.0
 */
public class NewsComment implements Serializable {

	@SerializedName("content")
	private String text;
	private Timestamp creationDate;
	@SerializedName("lastUpdate")
	private Timestamp lastUpdateDate;
	@SerializedName("customer")
	private User author;
	private News news;

	/**
	 * @return Liefert den Nutzer, der den Kommentar erstellt hat.
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @return Liefert den Inhalt des Kommentars.
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return Liefert das Erstellungsdatum des Kommentars.
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
	 * @return Liefert die Neuigkeit, das kommentiert wurde.
	 */
	public News getNews() {
		return news;
	}

}
