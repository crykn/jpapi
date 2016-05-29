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
 * <i>Java-Modell des JSON-Benutzer-Objekts.</i>
 * 
 * @author damios
 * @since 0.1.0
 */
public class User implements Serializable {

	/**
	 * Der Service, der die Verbindung zu den ben�tigten API-Endpunkten
	 * beinhaltet.
	 */
	private static UserService service = Api.createService(UserService.class);

	private static final long serialVersionUID = 100L;
	private int id;
	private String username;
	private String about;
	@SerializedName("gamificationLevel")
	private int level;
	private int xp;
	private Timestamp registrationDate;

	/**
	 * @return Liefert die individuelle ID des Nutzers.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Liefert den Namen des Nutzers.
	 * <p>
	 * Jeder Nutzername ist einzigartig; L�nge zwischen 3 und 15 Zeichen; nur
	 * folgende Zeichen sind erlaubt: a-z, 0-9, '.', '_', '-'.
	 * 
	 * @return Der Nutzername.
	 */
	public String getName() {
		return username;
	}

	/**
	 * @return Liefert die Profilbeschreibung des Nutzers.
	 */
	public String getAboutText() {
		return about;
	}

	/**
	 * Liefert das Level eines Nutzers. <br>
	 * Neue Nutzer starten mit Level 0; alle 1000 erreichte XP erh�ht sich das
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
	 * Erfahrung erh�lt man f�r die folgenden Aktionen:
	 * <ul>
	 * <li>Spiele hochladen (500 XP)
	 * <li>Blogeintr�ge verfassen (100 XP)
	 * <li>Designs einreichen (100 XP)
	 * <li>Spiele bewerten (75 XP)
	 * <li>Blogeintr�ge kommentieren (50 XP)
	 * <li>Forenbeitr�ge verfassen (10 XP)
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
	 * Liefert alle Spiele des Nutzers.
	 * 
	 * @return Die Spiele als Project-Array; wenn ein Nutzer keine Spiele hat,
	 *         ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler beim Ausf�hren der Anfrage auftritt.
	 * @see Project#get(String)
	 */
	public Project[] getProjects() throws IOException {
		return Project.get(username);
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
	public static User get(int userid) throws IOException {
		return Api.executeCall(service.get(userid));
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
	public static User get(String username) throws IOException {
		return Api.executeCall(service.get(username));
	}

	/**
	 * Das Service-Interface f�r die Verbindung zur Pewn-API, das f�r Benutzer
	 * zust�ndig ist.
	 * 
	 * @author damios
	 * @since 0.5.0
	 */
	interface UserService {

		@GET("v1/user/id/{id}?format=json")
		Call<User> get(@Path("id") int id);

		@GET("v1/user/name/{name}?format=json")
		Call<User> get(@Path("name") String name);

	}

}
