package zyloon.com.moviedbapi.retrofit;

import retrofit.Callback;
import retrofit.http.GET;

public interface MoviesApiService {
	@GET("/discover/movie")
	void getPopularMovies(Callback<Movie.MovieResult> cb);
}