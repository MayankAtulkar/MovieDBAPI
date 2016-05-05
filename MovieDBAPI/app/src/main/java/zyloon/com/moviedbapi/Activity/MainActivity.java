package zyloon.com.moviedbapi.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import zyloon.com.moviedbapi.Adapter.FeedAdapter;
import zyloon.com.moviedbapi.retrofit.Movie;
import zyloon.com.moviedbapi.retrofit.MoviesApiService;

public class MainActivity extends AppCompatActivity {

	private RecyclerView recyclerView;
	private LinearLayoutManager layoutManager;
	public FeedAdapter adapter;
	Activity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		activity = this;
		recyclerView = (RecyclerView) findViewById(R.id.feed_recycler_view);
		recyclerView.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(activity);
		recyclerView.setLayoutManager(layoutManager);
		adapter = new FeedAdapter(getApplicationContext());
		recyclerView.setAdapter(adapter);
		List<Movie> movies = new ArrayList<>();

		for (int i = 0; i < 25; i++) {
			movies.add(new Movie());
		}
		adapter.setMovieList(movies);

		RestAdapter restAdapter = new RestAdapter.Builder()
				.setEndpoint("https://api.themoviedb.org/3")
				.setRequestInterceptor(new RequestInterceptor() {
					@Override
					public void intercept(RequestFacade request) {
						request.addEncodedQueryParam("api_key", "2f5187a4f608e83ffa7fa15f5dd82be0");
					}
				})
				.setLogLevel(RestAdapter.LogLevel.FULL)
				.build();
		MoviesApiService service = restAdapter.create(MoviesApiService.class);
		service.getPopularMovies(new Callback<Movie.MovieResult>() {
			@Override
			public void success(Movie.MovieResult movieResult, Response response) {
				adapter.setMovieList(movieResult.getResults());
			}

			@Override
			public void failure(RetrofitError error) {
				error.printStackTrace();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.date) {
			RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint("https://api.themoviedb.org/3")
					.setRequestInterceptor(new RequestInterceptor() {
						@Override
						public void intercept(RequestFacade request) {
							request.addEncodedQueryParam("api_key", "2f5187a4f608e83ffa7fa15f5dd82be0");
							request.addEncodedQueryParam("sort_by", "release_date.desc");
						}
					})
					.setLogLevel(RestAdapter.LogLevel.FULL)
					.build();
			MoviesApiService service = restAdapter.create(MoviesApiService.class);
			service.getPopularMovies(new Callback<Movie.MovieResult>() {
				@Override
				public void success(Movie.MovieResult movieResult, Response response) {
					adapter.setMovieList(movieResult.getResults());
				}

				@Override
				public void failure(RetrofitError error) {
					error.printStackTrace();
				}
			});
			return true;

		} else if (id == R.id.rating) {
			RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint("https://api.themoviedb.org/3")
					.setRequestInterceptor(new RequestInterceptor() {
						@Override
						public void intercept(RequestFacade request) {
							request.addEncodedQueryParam("api_key", "2f5187a4f608e83ffa7fa15f5dd82be0");
							request.addEncodedQueryParam("sort_by", "vote_average.desc");
						}
					})
					.setLogLevel(RestAdapter.LogLevel.FULL)
					.build();
			MoviesApiService service = restAdapter.create(MoviesApiService.class);
			service.getPopularMovies(new Callback<Movie.MovieResult>() {
				@Override
				public void success(Movie.MovieResult movieResult, Response response) {
					adapter.setMovieList(movieResult.getResults());
				}

				@Override
				public void failure(RetrofitError error) {
					error.printStackTrace();
				}
			});
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
