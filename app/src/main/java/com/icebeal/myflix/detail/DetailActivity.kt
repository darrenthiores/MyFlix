package com.icebeal.myflix.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.icebeal.core.model.presenter.Movie
import com.icebeal.myflix.R
import com.icebeal.myflix.databinding.ActivityDetailBinding
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding

    private val detailViewModel: DetailViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding?.toolbar)

        supportActionBar?.title = ""

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val movie = DetailActivityArgs.fromBundle(intent.extras as Bundle).movie
        populateMovie(movie)

        binding?.btUp?.setOnClickListener { finish() }

    }

    private fun populateMovie(movie: Movie?) {

        binding?.apply {

            loading.visibility = View.GONE

            movie?.let {

                tvDetailName.text = movie.title
                tvOverview.text = movie.overview
                tvDate.text = movie.releaseDate
                tvRate.text = movie.rating.toString()
                tvVoterCount.text = movie.voter.toString()

                Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/original/${movie.poster}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(detailPoster)

                Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/original/${movie.poster}")
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(collapsingImg)

                var isFav = movie.isFavorite
                setFavoriteButton(isFav)

                fab.setOnClickListener {

                    isFav = !isFav
                    detailViewModel.setMovieFavorite(movie, isFav)
                    setFavoriteButton(isFav)

                    if(isFav){
                        Toast.makeText(this@DetailActivity, "${movie.title} Added To Favorite!!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@DetailActivity, "${movie.title} Removed From Favorite!!", Toast.LENGTH_SHORT).show()
                    }

                }

            }

        }

    }

    private fun setFavoriteButton(state: Boolean) {

        if (state) {
            binding?.fab?.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding?.fab?.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}