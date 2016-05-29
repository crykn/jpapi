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
 * <i>Java-Modell des JSON-Team-Objekts.</i>
 * 
 * @author damios
 * @since 0.5.0
 */
public class Team implements Serializable {

	/**
	 * Der Service, der die Verbindung zu den benötigten API-Endpunkten
	 * beinhaltet.
	 */
	private static TeamPostService service = Api
			.createService(TeamPostService.class);

	private static final long serialVersionUID = 100L;
	private int id;
	@SerializedName("teamName")
	private String name;
	private String description;
	private Timestamp creationDate;
	@SerializedName("lastUpdate")
	private Timestamp lastUpdateDate;
	private User teamFounder;
	@SerializedName("customers")
	private User[] members;
	private Project[] projects;

	/**
	 * @return Liefert die individuelle ID des Teams.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Liefert die Beschreibung des Teams.
	 * 
	 * @return Die Beschreibung.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return Liefert den Namen des Teams.
	 */
	public String getName() {
		return name;
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
	 * @return Liefert den Teamgründer.
	 */
	public User getFounder() {
		return teamFounder;
	}

	/**
	 * @return Liefert alle Teammitglieder.
	 */
	public User[] getMembers() {
		return members;
	}

	/**
	 * @return Liefert alle Spiele des Teams.
	 */
	public Project[] getProjects() {
		return projects;
	}

	/**
	 * Liefert ein Team anhand seiner ID.
	 * 
	 * @param id
	 *            Die Team-ID.
	 * @return Das Team.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Team[] get(int id) throws IOException {
		return Api.executeCall(service.get(id));
	}

	/**
	 * Das Service-Interface für die Verbindung zur Pewn-API, das für Designs
	 * zuständig ist.
	 * 
	 * @author damios
	 * @since 0.5.0
	 */
	interface TeamPostService {

		@GET("v1/team/id/{id}?format=json")
		Call<Team[]> get(@Path("id") int id);

	}

}
