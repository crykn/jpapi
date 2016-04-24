package de.damios.jpapi.object;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

import de.damios.jpapi.core.ApiRequest;

/**
 * <i>Java Repr�sentierung des JSON-Bild-Objekts.</i>
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
	 * @return Liefert die Gr��e des Bildes in Kilobyte.
	 */
	public int getFileSize() {
		return fileSize;
	}

	public Timestamp getUploadDate() {
		return uploadDate;
	}

	public User getAuthor() {
		return author;
	}

	/**
	 * Liefert ein Array aller Bilder eines Spiels.
	 * 
	 * @param gameid
	 *            Die Spieleid.
	 * @return Image-Array
	 * @throws IOException
	 *             wenn ein Fehler beim Ausf�hren der Anfrage auftritt.
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
