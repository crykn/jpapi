package de.damios.jpapi.model;

import java.io.IOException;
import java.io.Serializable;

import de.damios.jpapi.core.Api;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * <i>Java-Modell des JSON-Benutzer-Daten-Objekts.</i>
 * 
 * @author damios
 * @since 0.5.0
 */
public class UserData implements Serializable {

	/**
	 * Der Service, der die Verbindung zu den benötigten API-Endpunkten
	 * beinhaltet.
	 */
	private static UserDataService service = Api.createService(UserDataService.class);

	private static final long serialVersionUID = 100L;
	private long id;
	private String username;
	private String email;

	/**
	 * @return Liefert die individuelle ID des Nutzers.
	 * @see User#getId()
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return Liefert den Namen des Nutzers.
	 * @see User#getName()
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return Liefert die Email-Adresse des Nutzers.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Liefert die Daten des authentifizierten Nutzers.
	 * <p>
	 * Erfordert vorige Authentifizierung und die Zugriffsberechtigung
	 * 'PROFILE_DATA'.
	 * 
	 * @return Die Nutzerdaten.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static UserData get() throws IOException {
		Token token = Token.getRefreshedToken();
		return Api.executeCall(service.get( "Bearer " + token.getAccessToken()));
	}

	/**
	 * Das Service-Interface für die Verbindung zur Pewn-API, das für
	 * Benutzer-Daten zuständig ist.
	 * 
	 * @author damios
	 * @since 0.5.0
	 */
	interface UserDataService {

		@GET("v1/self/details?format=json")
		Call<UserData> get(@Header("Authorization") String token);

	}

}
