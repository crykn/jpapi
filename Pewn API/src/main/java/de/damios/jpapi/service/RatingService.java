package de.damios.jpapi.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import de.damios.jpapi.model.Rating;

/**
 * Beinhaltet die Verbindung zu allen Endpunkten der Pewn-API, die mit
 * Bewertungen zu tun haben.
 * 
 * @author damios
 * @since 0.5.0
 */
public interface RatingService {

	@GET("v1/game/id/{id}/ratings?format=json")
	Call<Rating[]> get(@Path("id") int id);

}
