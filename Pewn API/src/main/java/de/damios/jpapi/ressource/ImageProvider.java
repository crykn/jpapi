package de.damios.jpapi.ressource;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import de.damios.jpapi.core.Api;
import de.damios.jpapi.model.Image;
import de.damios.jpapi.model.Project;
import de.damios.jpapi.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Beinhaltet ausschließlich statische Methoden zum Herunterladen der auf Pewn
 * gespeicherten Bilder.
 * 
 * @author damios
 * @since 0.1.0
 */
public class ImageProvider {

	private ImageProvider() {
	}

	/**
	 * Der Service, der die Verbindung zu den benötigten API-Endpunkten
	 * beinhaltet.
	 */
	private static ImageProviderService service = Api
			.createService(ImageProviderService.class);

	/**
	 * Liefert ein Bild, das auf Pewn hochgeladen wurde.
	 * <p>
	 * Von Breite und Höhe wird nur der Wert verwendet, dessen relative
	 * Veränderung zum Ursprungswert am kleinsten ist (auch negative Werte
	 * werden beachtet!), um das ursprüngliche Seitenverhältnis des Bildes
	 * beizubehalten. Wenn einer oder beide der Parameter Breite und Höhe
	 * kleiner als 0 sind, wird die ursprüngliche Größe des Bildes beibehalten.
	 * 
	 * @param gameid
	 *            Die Spiele-ID.
	 * @param filename
	 *            Der Dateiname des Bildes.
	 * @param width
	 *            Die Bildbreite.
	 * @param height
	 *            Die Bildhöhe.
	 * @return Das Bild; null, wenn das Bild nicht vorhanden ist.
	 * @throws IOException
	 *             wenn ein Fehler bei der Anfrage oder beim Lesen des Bilds
	 *             auftritt.
	 * @see #get(Project, Image)
	 * @see #get(Project, Image, int, int)
	 * @see Api#executeCall(Call)
	 * @see ImageIO#read(URL)
	 */
	public static BufferedImage get(long gameid, String filename, int width,
			int height) throws IOException {
		ResponseBody tmp = Api.executeCall(service.downloadImage(gameid,
				filename, (width < 0 || height < 0) ? null : width,
				(width < 0 || height < 0) ? null : height));

		return tmp == null ? null : ImageIO.read(tmp.byteStream());
	}

	/**
	 * Liefert ein Bild, das auf Pewn hochgeladen wurde.
	 * 
	 * @param gameid
	 *            Die Spiele-ID.
	 * @param filename
	 *            Der Dateiname des Bildes.
	 * @return Das Bild.
	 * @throws IOException
	 *             wenn ein Fehler beim Lesen des Bilds auftritt.
	 * @see #get(long, String, int, int)
	 * @deprecated Stattdessen {@link #get(Project, Image)} benutzen.
	 */
	@Deprecated
	public static BufferedImage get(long gameid, String filename)
			throws IOException {
		return get(gameid, filename, -1, -1);
	}

	/**
	 * Liefert ein Bild, das auf Pewn hochgeladen wurde.
	 * <p>
	 * Von Breite und Höhe wird nur der Wert verwendet, dessen relative
	 * Veränderung zum Ursprungswert am kleinsten ist (auch negative Werte
	 * werden beachtet!), um das ursprüngliche Seitenverhältnis des Bildes
	 * beizubehalten. Wenn einer oder beide der Parameter Breite und Höhe
	 * kleiner als 0 sind, wird die ursprüngliche Größe des Bildes beibehalten.
	 * <p>
	 * <i>Der Project-Parameter wird in absehbarer Zeit entfernt werden!</i>
	 * 
	 * @param project
	 *            Das Spiel, zu dem das Bild gehört.
	 * @param image
	 *            Das Image-Objekt des Bildes.
	 * @param width
	 *            Die Bildbreite.
	 * @param height
	 *            Die Bildhöhe.
	 * @return Das Bild.
	 * @throws IOException
	 *             wenn ein Fehler beim Lesen des Bilds auftritt.
	 * @see #get(long, String, int, int)
	 * @see #get(Project, Image)
	 * @see ImageIO#read(URL)
	 */
	public static BufferedImage get(Project project, Image image, int width,
			int height) throws IOException {
		return get(project.getId(), image.getFileName(), width, height);
	}

	/**
	 * Liefert ein Bild, das auf Pewn hochgeladen wurde.
	 * <p>
	 * <i>Der Project-Parameter wird in absehbarer Zeit entfernt werden!</i>
	 * 
	 * @param project
	 *            Das Spiel, zu dem das Bild gehört.
	 * @param image
	 *            Das Image-Objekt des Bildes.
	 * @return Das Bild.
	 * @throws IOException
	 *             wenn ein Fehler beim Lesen des Bilds auftritt.
	 * @see #get(Project, Image, int, int)
	 */
	public static BufferedImage get(Project project, Image image)
			throws IOException {
		return get(project, image, -1, -1);
	}

	/**
	 * Liefert den Avatar eines Nutzers.
	 * 
	 * @param user
	 *            Der jeweilige Nutzer. Der Avatar des Nutzers darf nicht null
	 *            sein!
	 * @return Das Bild; null, wenn das Bild nicht vorhanden ist.
	 * @throws IOException
	 *             wenn ein Fehler bei der Anfrage oder beim Lesen des Bilds
	 *             auftritt.
	 * @see Api#executeCall(Call)
	 * @see ImageIO#read(URL)
	 */
	public static BufferedImage getAvatar(User user) throws IOException {
		if (user.getProfile().getAvatar() == null)
			throw new IllegalArgumentException(
					"Der Avatar des Nutzers darf nicht null sein");
		ResponseBody tmp = Api.executeCall(service
				.downloadAvatar(user.getName(), user.getProfile().getAvatar()));

		return tmp == null ? null : ImageIO.read(tmp.byteStream());
	}

	/**
	 * Das Service-Interface für die Verbindung zur Pewn-API, das für
	 * Bilder-Daten zuständig ist.
	 * 
	 * @author damios
	 * @since 0.5.0
	 */
	interface ImageProviderService {

		@GET("/image/projects/{gameid}/files/{filename}")
		Call<ResponseBody> downloadImage(@Path("gameid") long gameid,
				@Path("filename") String filename,
				@Query("width") Integer width, @Query("height") Integer height);

		@GET("/download/users/{username}/avatar/{filename}")
		Call<ResponseBody> downloadAvatar(@Path("username") String username,
				@Path("filename") String filename);

	}
}
