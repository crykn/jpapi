package de.damios.jpapi.model;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;

import de.damios.jpapi.core.Constants;

/**
 * <i>Java-Modell des JSON-Download-Objekts.</i>
 * <p>
 * Ein Download kann heruntergeladen werden unter folgender Adresse:
 * '/api/v1/analytics/downloads/{id}'
 * 
 * @author damios
 * @since 0.6.0
 */
public class Download implements Serializable {

	private static final long serialVersionUID = 110L;
	private long id;
	private String title;
	private Timestamp creationDate;
	private String icon;
	private long count;

	/**
	 * @return Liefert die individuelle ID des Downloads.
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return Liefert den Titel des Downloads.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return Liefert das Erstellungsdatum des Downloads.
	 */
	public Timestamp getCreationDate() {
		return creationDate;
	}

	/**
	 * @return Liefert die Anzahl der Downloads.
	 */
	public long getCount() {
		return count;
	}

	/**
	 * Liefert das Icon des Downloads. Das ist hilfreich zur Bestimmmung, um
	 * welche Art von Download es sich handelt und für welches Betriebssystem
	 * dieser bestimmt ist.
	 * <p>
	 * Mögliche Icons sind:
	 * <ul>
	 * <li><code>fa-windows</code>
	 * <li><code>fa-linux</code>
	 * <li><code>fa-apple</code>
	 * <li><code>fa-android</code>
	 * <li><code>fa-mobile</code>
	 * <li><code>fa-globe</code>
	 * <li><code>fa-code</code>
	 * <li><code>fa-file-archive-o</code>
	 * </ul>
	 * 
	 * @return Das Icon des Downloads.
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * Liefert die URL, unter der der Download aufgerufen werden kann. Meist
	 * erfolgt dabei zunächst noch eine Weiterleitung.
	 * 
	 * @return Die URL des Downloads.
	 * @throws MalformedURLException
	 *             wenn die angegebene URL nicht gültig ist.
	 * 
	 * @see #getId()
	 */
	public URL getDownloadUrl() throws MalformedURLException {
		return new URL(Constants.DOWNLOAD_URL + id);
	}

	@Override
	public int hashCode() {
		return 31 + (int) (id ^ (id >>> 32));
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return this.id == ((Download) obj).id;
	}

}
