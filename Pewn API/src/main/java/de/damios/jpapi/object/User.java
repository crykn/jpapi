package de.damios.jpapi.object;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

/**
 * <i>Java Repräsentierung des JSON-Benutzer-Objekts</i>
 * 
 * @author damios
 * @version 0.1.0
 */
public class User implements Serializable {

	private static final long serialVersionUID = 100L;
	private int id;
	private String username;
	private String about;
	@SerializedName("gamificationLevel")
	private int level;
	private int xp;
	private Timestamp registrationDate;

	public int getId() {
		return id;
	}

	public String getName() {
		return username;
	}

	/**
	 * @return Liefert die Profilbeschreibung des Nutzers
	 */
	public String getAboutText() {
		return about;
	}

	/**
	 * Liefert das Level eines Nutzers <br>
	 * Neue Nutzer starten mit Level 0; alle 1000 erreichte XP erhöht sich das
	 * Level
	 * 
	 * @return Das Level
	 * @see #getXp()
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Liefert die Anzahl der XP eines Nutzers
	 * <p>
	 * Erfahrung erhält man für die folgenden Aktionen:
	 * <ul>
	 * <li>Spiele hochladen (500 XP)
	 * <li>Blogeinträge verfassen (100 XP)
	 * <li>Designs einreichen (100 XP)
	 * <li>Spiele bewerten (75 XP)
	 * <li>Blogeinträge kommentieren (50 XP)
	 * <li>Forenbeiträge verfassen (10 XP)
	 * </ul>
	 * <p>
	 * 
	 * @return Die Anzahl der XP
	 * @see #getLevel()
	 */
	public int getXp() {
		return xp;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * Liefert alle Spiele des Nutzers
	 * 
	 * @return Die Projekte als Project-Array; wenn ein Nutzer keine Projekte
	 *         hat, ein leeres Array
	 * @throws IOException
	 *             wenn ein Fehler beim Ausführen der Anfrage auftritt
	 * @throws JsonSyntaxException
	 *             wenn ein Fehler beim Parsen auftritt
	 * @see Project#get(String)
	 */
	public Project[] getProjects() throws JsonSyntaxException, IOException {
		return Project.get(username);
	}

}
