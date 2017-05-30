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
 * <i>Java-Modell des JSON-Design-Kommentar-Objekts.</i>
 * 
 * @author damios
 * @since 0.5.0
 */
public class DesignComment implements Serializable {

	private static final long serialVersionUID = 110L;
	@SerializedName("content")
	private String text;
	private Timestamp creationDate;
	@SerializedName("lastUpdate")
	private Timestamp lastUpdateDate;
	@SerializedName("customer")
	private User author;
	private Design design;

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
	 * @return Liefert das Design, das kommentiert wurde.
	 */
	public Design getDesign() {
		return design;
	}

	/**
	 * Liefert ein Array aller Kommentare eines bestimmten Designs.
	 * 
	 * @param designId
	 *            Die ID des Designs.
	 * @return DesignComment-Array
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	/*public static DesignComment[] get(int designId) throws IOException {
		return Api.executeCall(service.get(designId));
	}*/

}
