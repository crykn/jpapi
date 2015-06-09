package de.damios.api.object;

import java.io.Serializable;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.annotations.SerializedName;

import de.damios.util.ApiRequest;

public class Project implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;
	public int id;
	@SerializedName("content")
	public String description;
	public String headline;
	public String creationDate;
	public String lastUpdate;
	@SerializedName("customer")
	public User author;
	public int rating;
	public URL downloadWindows;
	public URL downloadLinux;
	public URL downloadMacOs;
	public URL downloadAndroid;
	public URL downloadIos;
	public URL downloadWindowsPhone;
	public URL downloadWeb;
	public String version;
	@SerializedName("fileContainers")
	public Image[] images;

	// TODO convert date

	public Set<String> getHashtags() {
		Set<String> tmp = new HashSet<String>();
		String regexPattern = "(#\\w+)";
		Pattern p = Pattern.compile(regexPattern);
		Matcher m = p.matcher(description);
		while (m.find()) {
			String hashtag = m.group(1);
			boolean alreadyUsed = true;
			for (String tag : tmp) {
				if (hashtag.equals(tag))
					alreadyUsed = false;
			}
			if (alreadyUsed)
				tmp.add(hashtag);
		}
		return tmp;
	}

	public static Project[] get(String username) {
		return ApiRequest.execute("game/user/" + username, Project[].class);
	}

	public static Project get(int gameid) {
		return ApiRequest.execute("game/id/" + gameid, Project.class);
	}

	public static Project getLatest() {
		return ApiRequest.execute("game/last/", Project.class);
	}

	public static Project getRandom() {
		return ApiRequest.execute("game/random/", Project.class);
	}

	public static Project[] getAll(OrderedBy arg) {
		return ApiRequest.execute("game/all/" + arg.parameter, Project[].class);
	}

	public static enum OrderedBy {
		CREATION_DATE("creation"), UPDATE_DATE("update"), RATING("rating");

		String parameter;

		OrderedBy(String parameter) {
			this.parameter = parameter;
		}
	}
}
