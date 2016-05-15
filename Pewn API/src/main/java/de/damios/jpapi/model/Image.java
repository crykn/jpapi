package de.damios.jpapi.model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;

import retrofit2.Call;

import com.google.gson.annotations.SerializedName;

import de.damios.jpapi.core.Api;
import de.damios.jpapi.service.ImageService;

/**
 * <i>Java-Modell des JSON-Bild-Objekts.</i>
 * <p>
 * Stellt nur die Metadaten eines Bildes dar; zum Herunterladen des eigentlichen
 * Bildes muss die {@linkplain de.damios.jpapi.method.ImageProvider
 * ImageProvider}-Klasse verwendet werden. Das sieht im Normalfall
 * folgendermaßen aus:
 * 
 * <pre>
 * {@code  
 * BufferedImage bufferedImage = ImageProvider.get(project, image)  
 * }
 * </pre>
 * 
 * @author damios
 * @since 0.1.0
 */
public class Image implements Serializable {

	/**
	 * Der Service, der die Verbindung zu den API-Endpunkten beinhaltet.
	 */
	public static ImageService service = Api.createService(ImageService.class);

	private static final long serialVersionUID = 100L;
	private int id;
	private String fileName;
	private int fileSize;
	private Timestamp uploadDate;
	@SerializedName("customer")
	private User author;

	/**
	 * @return Liefert die individuelle ID des Bildes.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Liefert den Namen der Bild-Datei.
	 * 
	 * @return Der Name mit Dateinamenserweiterung.
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return Liefert die Größe des Bildes in Kilobyte.
	 */
	public int getFileSize() {
		return fileSize;
	}

	/**
	 * @return Liefert den Zeitpunkt des Hochladens des Bildes.
	 */
	public Timestamp getUploadDate() {
		return uploadDate;
	}

	/**
	 * @return Liefert den Nutzer, der das Bild hochgeladen hat.
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * Liefert ein Array aller Bilder eines Spiels.
	 * 
	 * @param gameid
	 *            Die Spiele-ID.
	 * @return Image-Array
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see Api#executeCall(Call)
	 */
	public static Image[] get(int gameid) throws IOException {
		return Api.executeCall(service.get(gameid));
	}

}
