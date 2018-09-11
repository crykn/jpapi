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
	private static TeamService service = Api.createService(TeamService.class);

	private static final long serialVersionUID = 100L;
	private long id;
	@SerializedName("teamName")
	private String name;
	private String description;
	private Timestamp creationDate;
	@SerializedName("lastUpdate")
	private Timestamp lastUpdateDate;
	@SerializedName("customerFounderId")
	private long founderId;
	@SerializedName("customerMemberId")
	private long[] memberIds;
	@SerializedName("projectId")
	private long[] projectIds;

	/**
	 * @return Liefert die individuelle ID des Teams.
	 */
	public long getId() {
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
	 * @return Liefert den Namen des Teams; Länge zwischen 3 und 30 Zeichen.
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
	 * Liefert den Teamgründer.
	 * 
	 * @return Der Gründer des Teams.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see User#getByUserId(long)
	 */
	public User getFounder() throws IOException {
		return User.getByUserId(founderId);
	}

	/**
	 * Liefert alle Teammitglieder.
	 * 
	 * @return Die Mitlgieder des Teams.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see User#getByUserId(long)
	 */
	public User[] getMembers() throws IOException {
		User[] members = new User[memberIds.length];

		for (int i = 0; i < memberIds.length; i++)
			members[i] = User.getByUserId(memberIds[i]);

		return members;
	}

	/**
	 * Liefert Spiele des Teams.
	 * 
	 * @return Die Spiele als Project-Array; wenn ein Team noch keine Projekte
	 *         besitzt, ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Project#getByProjectId(long)
	 */
	public Project[] getProjects() throws IOException {
		if (projectIds == null || projectIds.length == 0)
			return new Project[0];

		Project[] projects = new Project[projectIds.length];

		for (int i = 0; i < projectIds.length; i++)
			projects[i] = Project.getByProjectId(projectIds[i]);

		return projects;
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
		return this.id == ((Team) obj).id;
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
	public static Team getByTeamId(long id) throws IOException {
		return Api.executeCall(service.getByTeamId(id));
	}

	/**
	 * Liefert alle Teams eines bestimmten Nutzers.
	 * 
	 * @param userId
	 *            Die ID des Nutzers, dessen Teams abgerufen werden sollen.
	 * @return Die Teams als Team-Array; wenn ein Nutzer keinem Team angehört,
	 *         ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Team[] getByUserId(long userId) throws IOException {
		return Api.executeCall(service.getByUserId(userId));
	}

	/**
	 * Das Service-Interface für die Verbindung zur Pewn-API, das für Designs
	 * zuständig ist.
	 * 
	 * @author damios
	 * @since 0.5.0
	 */
	interface TeamService {

		@GET("v1/teams/id/{id}?format=json")
		Call<Team> getByTeamId(@Path("id") long id);

		@GET("v1/users/id/{id}/teams?format=json")
		Call<Team[]> getByUserId(@Path("id") long userId);

	}

}
