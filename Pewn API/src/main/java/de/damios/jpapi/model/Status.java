package de.damios.jpapi.model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;

import de.damios.jpapi.core.Api;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * <i>Java-Modell des JSON-Status-Objekts.</i>
 * 
 * @author damios
 * @since 0.5.0
 */
public class Status implements Serializable {

	/**
	 * Der Service, der die Verbindung zu den benötigten API-Endpunkten
	 * beinhaltet.
	 */
	private static StatusService service = Api
			.createService(StatusService.class);

	private static final long serialVersionUID = 100L;
	private String status;
	private Timestamp lastUpdated;

	/**
	 * @return Liefert den API-Status als String.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return Liefert den Zeitpunkt des letzten Updates des Status.
	 */
	public Timestamp getLastUpdateDate() {
		return lastUpdated;
	}

	/**
	 * Liefert den momentanen API-Status.
	 * 
	 * @return Der API-Status.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Status get() throws IOException {
		return Api.executeCall(service.get());
	}

	/**
	 * Das Service-Interface für die Verbindung zur Pewn-API, das für den
	 * API-Status zuständig ist.
	 * 
	 * @author damios
	 * @since 0.5.0
	 */
	interface StatusService {

		@GET("status?format=json")
		Call<Status> get();

	}

}
