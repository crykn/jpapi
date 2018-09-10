package de.damios.jpapi.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gson.annotations.SerializedName;

/**
 * <i>Java-Modell des JSON-Neuigkeiten-Kommentar-Objekts.</i>
 * 
 * @author damios
 * @since 0.5.0
 */
public class ForumTopic implements Serializable {

	private long id;
	@SerializedName("content")
	private String text;
	@SerializedName("headline")
	private String title;
	private long forumSectionId;
	private Hashtag[] hashtags;
	private boolean sticky;
	private boolean locked;
	private Timestamp creationDate;
	@SerializedName("lastUpdate")
	private Timestamp lastUpdateDate;
	@SerializedName("customer")
	private User author;

	/**
	 * @return Liefert die individuelle ID des Topics.
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return Liefert den Nutzer, der den Kommentar erstellt hat.
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @return Liefert den Inhalt des Kommentars.
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return Liefert das Erstellungsdatum des Kommentars.
	 */
	public Timestamp getCreationDate() {
		return creationDate;
	}

	/**
	 * @return Liefert das Datum des letzten Updates.
	 */
	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}

	/**
	 * @return Liefert den Titel.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return Liefert alle Hashtags, die dem Topic zugeordnet sind.
	 */
	public Hashtag[] getHashtags() {
		return hashtags;
	}

	/**
	 * @return Liefert, ob das Topic 'sticky' ist.
	 */
	public boolean isSticky() {
		return sticky;
	}

	/**
	 * @return Liefert, ob das Topic 'locked' ist.
	 */
	public boolean isLocked() {
		return locked;
	}

	/*
	 * public long getForumSectionId() { return forumSectionId; }
	 */

	/**
	 * Liefert alle Posts im Topic.
	 * 
	 * @return Die Posts als ForumPost-Array; wenn ein Topic noch keine Posts
	 *         enthält, ein leeres Array.
	 * @throws IOException
	 *             wenn ein Fehler bei der Kommunikation mit Pewn auftritt.
	 * @see ForumPost#getByTopicId(int)
	 */
	/*
	 * public ForumPost[] getPosts() throws IOException { return
	 * Api.executeCall(service.getByTopicId(id)); }
	 */

}
