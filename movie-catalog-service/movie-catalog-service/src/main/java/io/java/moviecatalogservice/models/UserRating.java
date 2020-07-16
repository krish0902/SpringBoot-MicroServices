package io.java.moviecatalogservice.models;

import java.util.List;

public class UserRating {

	private List<Rating> userRating;
	private String userid;

	public List<Rating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<Rating> userRating) {
		this.userRating = userRating;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	
	
	
}
