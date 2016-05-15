package de.damios.jpapi.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import de.damios.jpapi.model.Project;

/**
 * Beinhaltet die Verbindung zu allen Endpunkten der Pewn-API, die mit Projekten
 * zu tun haben.
 * 
 * @author damios
 * @since 0.5.0
 */
public interface ProjectService {

	@GET("v1/game/id/{id}?format=json")
	Call<Project> get(@Path("id") int id);

	@GET("v1/game/user/{username}?format=json")
	Call<Project[]> get(@Path("username") String username);

	@GET("v1/game/last?format=json")
	Call<Project> getLatest();

	@GET("v1/game/random?format=json")
	Call<Project> getRandom();

	@GET("v1/game/all/{order}?format=json")
	Call<Project[]> getAll(@Path("order") String order);

}
