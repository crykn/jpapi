package de.damios.jpapi.model;

import java.io.IOException;
import java.io.Serializable;

import de.damios.jpapi.core.Api;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * <i>Java-Modell eines API-Tokens.</i>
 * <p>
 * Die zentrale Klasse für die Authentifizierung.
 * 
 * @author damios
 * @since 0.5.0
 */
public class Token implements Serializable {

	/**
	 * Der Service, der die Verbindung zu den benötigten API-Endpunkten
	 * beinhaltet.
	 */
	private static TokenService service = Api.createService(TokenService.class);

	private static final long serialVersionUID = 110L;
	private String access_token;
	private String refresh_token;
	private String token_type;
	private String expires_in;
	private String username;

	public String getAccessToken() {
		return access_token;
	}

	public String getRefreshToken() {
		return refresh_token;
	}

	public String getUsername() {
		return username;
	}

	/**
	 * Liefert einen vollständigen Token.
	 * <p>
	 * Muss vom Nutzer normalerweise nie selbst aufgerufen werden.
	 * 
	 * @param clientId
	 *            Die ID der API-Anwendung.
	 * @param clientSecret
	 *            Das Secret der Anwendung.
	 * @param code
	 *            Der auth_code.
	 * @param redirectUri
	 *            Die redirect_uri, die die Anwendung auf Pewn hinterlegt hat.
	 * @return Ein vollständiger Token (mit access_token und refresh_token)
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 * @see Api#authForFirstTime(String, String, String, String)
	 */
	public static Token getFirstToken(String clientId, String clientSecret,
			String code, String redirectUri) throws IOException {
		return Api.executeCall(service.getFirstToken(clientId, clientSecret,
				"authorization_code", code, redirectUri));
	}

	/**
	 * Liefert einen Token ohne refresh_token.
	 * <p>
	 * Muss vom Nutzer normalerweise nie selbst aufgerufen werden.
	 * 
	 * @return Ein Token mit access_token, ohne refresh_token)
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @throws IllegalStateException
	 *             wenn die API vorher nicht initialisiert wurde.
	 * @see Api#executeCall(Call)
	 * @see Api#initAuth(String, String, String)
	 */
	public static Token getRefreshedToken()
			throws IOException, IllegalStateException {
		if (Api.getClientId() == null || Api.getClientSecret() == null
				|| Api.getRefreshToken() == null) {
			throw new IllegalStateException("API nicht richtig initialisiert");
		}
		return Api.executeCall(service.refreshToken(Api.getClientId(),
				Api.getClientSecret(), "refresh_token", Api.getRefreshToken()));
	}

	/**
	 * Das Service-Interface für die Verbindung zur Pewn-API, das für Tokens
	 * zuständig ist.
	 * 
	 * @author damios
	 * @since 0.5.0
	 */
	interface TokenService {

		@FormUrlEncoded
		@POST("v1/oauth/token")
		Call<Token> getFirstToken(@Field("client_id") String client_id,
				@Field("client_secret") String client_secret,
				@Field("grant_type") String grant_type,
				@Field("code") String code,
				@Field("redirect_uri") String redirect_uri);

		@FormUrlEncoded
		@POST("v1/oauth/token")
		Call<Token> refreshToken(@Field("client_id") String client_id,
				@Field("client_secret") String client_secret,
				@Field("grant_type") String grant_type,
				@Field("refresh_token") String refresh_token);

	}

}
