package de.damios.jpapi.model;

import java.io.IOException;
import java.io.Serializable;

import de.damios.jpapi.core.Api;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * <i>Java-Modell des JSON-Spiele-Empfehlungs-Objekts.</i>
 * <p>
 * Die zentrale Klasse um die Spiele-Empfelungen eines Spiels zu erhalten.
 * 
 * @author damios
 * @since 0.6.0
 */
public class Recommendation implements Serializable {

	/**
	 * Der Service, der die Verbindung zu den benötigten API-Endpunkten
	 * beinhaltet.
	 */
	private static RecommendationService service = Api.createService(RecommendationService.class);

	private static final long serialVersionUID = 110L;
	private long id;
	private int sort;
	private Project recommendation;

	/**
	 * @return Liefert die individuelle ID der Empfehlung.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Liefert die Gewichtung des Vorschlags.
	 * 
	 * @return Ein ganzzahliger Wert von 1 bis 6.
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * @return Liefert das empfohlene Spiel.
	 */
	public Project getRecommendation() {
		return recommendation;
	}

	/**
	 * Liefert ein Array aller Empfehlungen zu einem Spiel.
	 * 
	 * @param gameid
	 *            Die Spiele-ID.
	 * @return Recommendation-Array
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Recommendation[] getByGameId(int id) throws IOException {
		return Api.executeCall(service.getByGameId(id));
	}

	/**
	 * Das Service-Interface für die Verbindung zur Pewn-API, das für
	 * Spieleempfehlungen zuständig ist.
	 * 
	 * @author damios
	 * @since 0.6.0
	 */
	interface RecommendationService {

		@GET("v1/games/id/{id}/recommendations?format=json")
		Call<Recommendation[]> getByGameId(@Path("id") long id);

	}

}
