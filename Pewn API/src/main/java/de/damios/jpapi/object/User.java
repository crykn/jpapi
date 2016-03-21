package de.damios.jpapi.object;

import java.io.Serializable;
import java.sql.Timestamp;

import com.google.gson.annotations.SerializedName;

/**
 * Java Repräsentierung des JSON-Benutzer-Objekts
 * 
 * @author damios
 * @version 1.0
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

	public String getUsername() {
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
	
}
