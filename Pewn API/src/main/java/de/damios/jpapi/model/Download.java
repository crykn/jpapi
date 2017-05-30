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
 * <i>Java-Modell des JSON-Download-Objekts.</i>
 * <p>
 * Ein Download kann heruntergeladen werden unter folgender Adresse:
 * '/api/v1/analytics/downloads/{id}'
 * 
 * @author damios
 * @since 0.6.0
 */
public class Download implements Serializable {

	private static final long serialVersionUID = 110L;
	private long id;
	private String title;
	private Timestamp creationDate;
	private long count;

	
	/**
	 * @return Liefert die individuelle ID des Downloads.
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return Liefert den Titel des Downloads.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return Liefert das Erstellungsdatum des Downloads.
	 */
	public Timestamp getCreationDate() {
		return creationDate;
	}

	/**
	 * @return Liefert die Anzahl der Downloads.
	 */
	public long getCount() {
		return count;
	}

}
