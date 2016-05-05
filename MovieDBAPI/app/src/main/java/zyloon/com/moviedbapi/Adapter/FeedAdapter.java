package zyloon.com.moviedbapi.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import zyloon.com.moviedbapi.Activity.R;
import zyloon.com.moviedbapi.retrofit.Movie;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {


	private Context context;
	private List<Movie> feedList;

	public FeedAdapter(Context context) {
		this.feedList = new ArrayList<>();
		this.context = context;
	}

	@Override
	public FeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_items, parent, false);
		return new ViewHolder(view,0);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, final int position) {
		Movie movie = feedList.get(position);

		Picasso.with(context)
				.load(movie.getPoster())
				.placeholder(R.color.colorAccent)
				.into(holder.imageView);

		holder.filmTitle.setText(movie.getTitle());
		holder.releaseDate.setText(movie.getRelease_date());
		holder.rating.setText(movie.getVote_average());

	}

	@Override
	public int getItemCount() {
		if (feedList == null) {
			return 0;
		}
		return feedList.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		public ImageView imageView;
		public TextView filmTitle;
		public TextView releaseDate;
		public TextView rating;

		public ViewHolder(View itemView, int pos) {
			super(itemView);
			filmTitle = (TextView) itemView.findViewById(R.id.movie_name);
			releaseDate = (TextView) itemView.findViewById(R.id.movie_release_date_text);
			imageView = (ImageView) itemView.findViewById(R.id.imageView);
			rating = (TextView) itemView.findViewById(R.id.rating_text);
			}
	}

	public void setMovieList(List<Movie> movieList)
	{
		this.feedList.clear();
		this.feedList.addAll(movieList);
		notifyDataSetChanged();
	}
}

