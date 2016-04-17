package de.damios.jpapi.object;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;

/**
 * Java Repräsentierung des JSON-Benutzer-Objekts
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

	public String getAboutText() {
		return about;
	}

	public int getLevel() {
		return level;
	}

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
