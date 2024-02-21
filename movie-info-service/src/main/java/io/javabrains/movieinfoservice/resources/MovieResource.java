package io.javabrains.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.javabrains.movieinfoservice.models.Movie;
import io.javabrains.movieinfoservice.models.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieResource {
	
	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		//String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
		//String url = "https://api.themoviedb.org/3/movie/100?api_key=9aa69c8cef3b402d9ae536d561b0d015";
		MovieSummary movieSummary = restTemplate.getForObject(
				"https://api.themoviedb.org/3/movie/100?api_key=9aa69c8cef3b402d9ae536d561b0d015", 
				MovieSummary.class
			);
		return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
		
	}

}
