package com.example.dipenrana.flicks.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.media.Rating;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dipenrana.flicks.R;
import com.example.dipenrana.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by dipenrana on 2/10/18.
 */



public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<Movie> mMovies;
    private Context mContext;
    private String movieImageURL;


    public MovieAdapter(Context context, ArrayList<Movie> movieList) {
        mMovies = movieList;
        mContext = context;
    }

    private Context getContext(){
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View mView = inflater.inflate(R.layout.rv_movie_item,parent,false);
        ViewHolder mViewHolder = new ViewHolder(mView);

        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {

        Movie movie = mMovies.get(position);

        TextView movieName = holder.movieTitle;
        movieName.setText(movie.getOriginalTitle());

        RatingBar movieVotes = holder.movieVotes;
        Double stars = movie.getVoteAverage();
        movieVotes.setRating((float) (stars/2));

        TextView movieInfo = holder.movieOverview;
        movieInfo.setText(movie.getOverview());

        ImageView movieImage = holder.moviePoster;
        movieImage.setImageResource(0);

        int orientation = mContext.getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE)
            movieImageURL =  movie.getBackdropPath();
        else
            movieImageURL = movie.getPosterPath();

        Picasso.with(mContext)
                .load(movieImageURL)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.posterplaceholder)
                .error(R.drawable.postererror)
                .noFade()
                .transform(new RoundedCornersTransformation(10,10))
                .into(holder.moviePoster);



    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView movieTitle;
        TextView movieOverview;
        ImageView moviePoster;
        RatingBar movieVotes;

        public ViewHolder(View itemView) {
            super(itemView);

            movieTitle = (TextView) itemView.findViewById(R.id.tvMovieTitle);
            movieOverview = (TextView) itemView.findViewById(R.id.tvMovieOverview);
            moviePoster = (ImageView) itemView.findViewById(R.id.ivMoviePoster);
            movieVotes = (RatingBar) itemView.findViewById(R.id.rbMovieVotes);
        }
    }
}
