package io.java.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.java.movieinfoservice.models.Movie;
import io.java.movieinfoservice.models.MovieSummary;




@RestController
@RequestMapping("/movies")
public class MovieResource {

	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId)
	{
		
		MovieSummary movieSummary =restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+ movieId +"?api_key=" +apiKey, MovieSummary.class);
		
		
		return new Movie(movieId,movieSummary.getTitle(),movieSummary.getOverview());
	}
	
	
	
}
