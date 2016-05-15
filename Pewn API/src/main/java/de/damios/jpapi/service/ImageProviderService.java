package de.damios.jpapi.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Beinhaltet die Verbindung zu allen Endpunkten der Pewn-API, die mit echten
 * Bilder-Daten zu tun haben.
 * 
 * @author damios
 * @since 0.5.0
 */
public interface ImageProviderService {

	@GET("/image/projects/{gameid}/files/{filename}")
	Call<ResponseBody> downloadImage(@Path("gameid") int gameid,
			@Path("filename") String filename, @Query("width") Integer width,
			@Query("height") Integer height);

}
