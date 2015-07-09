package de.damios.jpapi.object;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * Java Repräsentierung des Json-Benutzer-Objekts
 * 
 * @author damios
 * @version 1.0
 */
public class User implements Serializable {

	private static final long serialVersionUID = 100L;
	public int id;
	public String username;
	public String about;
	@SerializedName("gamificationLevel")
	public int level;
	public int xp;
}
