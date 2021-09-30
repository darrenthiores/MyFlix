package com.icebeal.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.icebeal.core.model.presenter.Movie
import com.icebeal.core.ui.MovieAdapter
import com.icebeal.core.utils.OnClickCallback
import com.icebeal.favorite.databinding.FragmentFavoriteBinding
import com.icebeal.favorite.di.favoriteModule
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment(), OnClickCallback {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    private val favoriteViewModel: FavoriteViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loadKoinModules(favoriteModule)
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieAdapter(this)

        binding?.rvMovie?.let {
            it.adapter = movieAdapter
            it.layoutManager = GridLayoutManager(activity, 2)
            it.setHasFixedSize(true)
        }

        showLoading(true)

        favoriteViewModel.favoriteMovies.observe(viewLifecycleOwner, {
            if (it != null) {
                movieAdapter.setMovie(it)
                movieAdapter.notifyDataSetChanged()
                showLoading(false)
                showAnimation(it)
            }
        })

    }

    private fun showLoading(state: Boolean) {

        if (state) {

            binding?.loading?.visibility = View.VISIBLE

        } else {

            binding?.loading?.visibility = View.GONE

        }

    }

    private fun showAnimation(movie: List<Movie>) {

        if (movie.isEmpty()) {

            binding?.emptyView?.visibility = View.VISIBLE

        } else {

            binding?.emptyView?.visibility = View.GONE

        }

    }

    override fun onClick(movie: Movie) {
        val toDetailActivity = FavoriteFragmentDirections.actionFavoriteFragmentToDetailActivity()
        toDetailActivity.movie = movie

        findNavController().navigate(toDetailActivity)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}