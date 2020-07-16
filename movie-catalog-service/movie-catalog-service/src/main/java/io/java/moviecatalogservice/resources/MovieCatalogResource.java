package io.java.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.java.moviecatalogservice.models.CatalogItem;
import io.java.moviecatalogservice.models.Movie;
import io.java.moviecatalogservice.models.Rating;
import io.java.moviecatalogservice.models.UserRating;
import io.java.moviecatalogservice.services.MovieInfo;
import io.java.moviecatalogservice.services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingInfo userRatingInfo;
	
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
	
		
		// get all rated movies IDs
		
	UserRating ratings = userRatingInfo.getUserRating(userId);
				
		
		return ratings.getUserRating().stream().map(rating ->movieInfo.getCatalogItem(rating))
					.collect(Collectors.toList());
		
	}

	

	
	
	
}
