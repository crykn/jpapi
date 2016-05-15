package de.damios.jpapi.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import de.damios.jpapi.model.Image;

/**
 * Beinhaltet die Verbindung zu allen Endpunkten der Pewn-API, die mit den
 * Metadaten von Bildern zu tun haben.
 * 
 * @author damios
 * @since 0.5.0
 */
public interface ImageService {

	@GET("v1/game/id/{id}/images?format=json")
	Call<Image[]> get(@Path("id") int id);

}
