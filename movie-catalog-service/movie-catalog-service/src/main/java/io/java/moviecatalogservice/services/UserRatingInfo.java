package io.java.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.java.moviecatalogservice.models.Rating;
import io.java.moviecatalogservice.models.UserRating;

@Service
public class UserRatingInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@HystrixCommand(fallbackMethod = "getFallbackUserItem")
	public UserRating getUserRating(String userId) {
		return restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId,UserRating.class);
	}
	
	public UserRating getFallbackUserItem(String userId) {
		UserRating userRating =new UserRating();
		userRating.setUserid(userId);
		userRating.setUserRating(Arrays.asList(
				new Rating("0",0)));
		return userRating;
	}

}
