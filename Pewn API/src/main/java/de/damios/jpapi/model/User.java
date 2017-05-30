package de.damios.jpapi.model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gson.annotations.SerializedName;

import de.damios.jpapi.core.Api;
import de.damios.jpapi.ressource.ImageProvider;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * <i>Java-Modell des JSON-Benutzer-Objekts.</i>
 * 
 * @author damios
 * @since 0.1.0
 */
public class User implements Serializable {

	/**
	 * Der Service, der die Verbindung zu den benötigten API-Endpunkten
	 * beinhaltet.
	 */
	private static UserService service = Api.createService(UserService.class);

	private static final long serialVersionUID = 100L;
	private long id;
	private String username;
	private Profile profile;
	@SerializedName("gamificationLevel")
	private int level;
	private int xp;
	private Timestamp registrationDate;

	/**
	 * @return Liefert die individuelle ID des Nutzers.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Liefert den Namen des Nutzers.
	 * <p>
	 * Jeder Nutzername ist einzigartig; Länge zwischen 3 und 15 Zeichen; nur
	 * folgende Zeichen sind erlaubt: a-z, 0-9, '.', '_', '-'.
	 * 
	 * @return Der Nutzername.
	 */
	public String getName() {
		return username;
	}

	/**
	 * Liefert das Level eines Nutzers. <br>
	 * Neue Nutzer starten mit Level 0; alle 1000 erreichte XP erhöht sich das
	 * Level.
	 * 
	 * @return Das Level.
	 * @see #getXp()
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Liefert die Anzahl der XP eines Nutzers.
	 * <p>
	 * Erfahrung erhält man für die folgenden Aktionen:
	 * <ul>
	 * <li>Spiele hochladen (500 XP)
	 * <li>Blogeinträge verfassen (100 XP)
	 * <li>Designs einreichen (100 XP)
	 * <li>Spiele bewerten (75 XP)
	 * <li>Blogeinträge kommentieren (50 XP)
	 * <li>Forenbeiträge verfassen (10 XP)
	 * </ul>
	 * <p>
	 * 
	 * @return Die Anzahl der XP.
	 * @see #getLevel()
	 */
	public int getXp() {
		return xp;
	}

	/**
	 * @return Liefert das Registrierungsdatum des Nutzers.
	 */
	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @return Liefert das Profil des Nutzers.
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * Liefert alle Spiele des Nutzers.
	 * 
	 * @return Die Spiele als Project-Array; wenn ein Nutzer keine Spiele hat,
	 *         ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt.
	 * @see Project#getByUserId(long)
	 */
	public Project[] getProjects() throws IOException {
		return Project.getByUserId(id);
	}

	/**
	 * Liefert einen bestimmten Nutzer anhand dessen individueller ID.
	 * 
	 * @param userid
	 *            Die Nutzer-ID.
	 * @return Der Nutzer; null, wenn der Nutzer nicht existiert.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static User getByUserId(long userid) throws IOException {
		return Api.executeCall(service.getByUserId(userid));
	}

	/**
	 * Liefert einen bestimmten Nutzer anhand dessen individuellem Namen.
	 * 
	 * @param username
	 *            Der Name des Nutzers.
	 * @return Der Nutzer; null, wenn der Nutzer nicht existiert.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static User getByUsername(String username) throws IOException {
		return Api.executeCall(service.getByUsername(username));
	}

	/**
	 * <i>Java-Modell des JSON-User-Profil-Objekts.</i>
	 * <p>
	 * Dient dazu, die Nutzerprofile zu kapseln.
	 * 
	 * @author damios
	 * @since 0.5.0
	 */
	public class Profile implements Serializable {

		private static final long serialVersionUID = 100L;
		private String about;
		private String website;
		private String avatar;

		/**
		 * @return Liefert die Profilbeschreibung des Nutzers.
		 */
		public String getAboutText() {
			return about;
		}
		
		/**
		 * Liefert die Webseite des Nutzers.
		 * 
		 * @return Die Website-URL als String.
		 */
		public String getWebsite() {
			return website;
		}
		
		/**
		 * Liefert den Dateinamen des auf Pewn gespeicherten Avatars.
		 * 
		 * @return Der Dateiname des Avatars.
		 * @see ImageProvider#getAvatar(User)
		 */
		public String getAvatar() {
			return avatar;
		}
	}

	/**
	 * Das Service-Interface für die Verbindung zur Pewn-API, das für Benutzer
	 * zuständig ist.
	 * 
	 * @author damios
	 * @since 0.5.0
	 */
	interface UserService {

		@GET("v1/users/id/{id}?format=json")
		Call<User> getByUserId(@Path("id") long id);

		@GET("v1/users/name/{name}?format=json")
		Call<User> getByUsername(@Path("name") String name);

	}

}
