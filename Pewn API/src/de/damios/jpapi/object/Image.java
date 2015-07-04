package de.damios.jpapi.object;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import de.damios.jpapi.core.ApiRequest;

/**
 * Java Repr�sentierung des Json-Bild-Objekts
 * 
 * @author damios
 * @version 1.0
 *
 */
public class Image implements Serializable {

	private static final long serialVersionUID = 100L;
	public int id;
	public String fileName;
	public int fileSize;
	public String uploadDate;
	@SerializedName("customer")
	public User author;

	/**
	 * Liefert ein Array aller Bilder eines Spiels
	 * 
	 * @param gameid
	 *            Die Spieleid
	 * @return Image-Array
	 * @see ApiRequest#execute(String, Class)
	 * 
	 */
	public static Image[] get(int gameid) {
		return ApiRequest.execute("game/id/" + gameid + "/images",
				Image[].class);
	}

}
