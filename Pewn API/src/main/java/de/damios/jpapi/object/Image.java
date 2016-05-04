package de.damios.jpapi.object;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import de.damios.jpapi.core.ApiRequest;

/**
 * <i>Java Repräsentierung des JSON-Bild-Objekts.</i>
 * <p>
 * Zum Herunterladen des eigentlichen Bildes muss die
 * {@linkplain de.damios.jpapi.method.ImageProvider ImageProvider}-Klasse
 * verwendet werden. Das sieht im Normalfall folgendermaßen aus:
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
	 * @return Liefert den Namen der Datei.
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
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt.
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt.
	 * @see ApiRequest#execute(String, Class)
	 */
	public static Image[] get(int gameid) throws JsonSyntaxException,
			IOException {
		return ApiRequest.execute("v1/game/id/" + gameid + "/images",
				Image[].class);
	}

}
