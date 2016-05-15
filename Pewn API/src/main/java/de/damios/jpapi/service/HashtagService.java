package de.damios.jpapi.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import de.damios.jpapi.model.Hashtag;

/**
 * Beinhaltet die Verbindung zu allen Endpunkten der Pewn-API, die mit Hashtags
 * zu tun haben.
 * 
 * @author damios
 * @since 0.5.0
 */
public interface HashtagService {

	@GET("v1/game/id/{id}/hashtags?format=json")
	Call<Hashtag[]> get(@Path("id") int id);

}
