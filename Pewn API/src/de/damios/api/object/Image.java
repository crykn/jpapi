package de.damios.api.object;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import de.damios.util.ApiRequest;

public class Image implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;
	public int id;
	public String fileName;
	public int fileSize;
	public String uploadDate;
	@SerializedName("customer")
	public User author;
	
	public static Image[] get(int gameid){
		return ApiRequest.execute("game/id/"+gameid+"/images", Image[].class);
	}

}
