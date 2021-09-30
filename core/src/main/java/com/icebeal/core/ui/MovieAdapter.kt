package com.icebeal.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.icebeal.core.R
import com.icebeal.core.databinding.MovieLayoutBinding
import com.icebeal.core.model.presenter.Movie
import com.icebeal.core.utils.OnClickCallback

class MovieAdapter(private val onClickCallback: OnClickCallback) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movieList = ArrayList<Movie>()

    fun setMovie(items: List<Movie>) {

        movieList.clear()
        movieList.addAll(items)

        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_layout, parent, false)

        return MovieViewHolder(view)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.bind(movieList[position])

    }

    override fun getItemCount(): Int = movieList.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = MovieLayoutBinding.bind(itemView)

        fun bind(movie: Movie) {

            with(binding) {

                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/original/${movie.poster}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(poster)

                tvTitle.text = movie.title

            }

            itemView.setOnClickListener { onClickCallback.onClick(movie) }

        }

    }

}