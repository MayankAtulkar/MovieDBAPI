package zyloon.com.moviedbapi.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {
	private static String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

	private String title;

	@SerializedName("poster_path")
	private String poster;

	@SerializedName("vote_average")
	private String vote_average;

	@SerializedName("release_date")
	private String release_date;

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getVote_average() {
		return vote_average;
	}

	public void setVote_average(String vote_average) {
		this.vote_average = vote_average;
	}


	public Movie() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return TMDB_IMAGE_PATH + poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public static class MovieResult {
		private List<Movie> results;

		public List<Movie> getResults() {
			return results;
		}
	}

}