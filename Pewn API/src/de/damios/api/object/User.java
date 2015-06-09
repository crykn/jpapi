package de.damios.api.object;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;
	public int id;
	public String username;
	public String about;
	@SerializedName("gamificationLevel")
	public int level;
	public int xp;
}
