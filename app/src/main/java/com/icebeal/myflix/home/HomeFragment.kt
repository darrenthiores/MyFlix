package com.icebeal.myflix.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.icebeal.core.model.presenter.Movie
import com.icebeal.core.ui.MovieAdapter
import com.icebeal.core.utils.DataMapper
import com.icebeal.core.utils.OnClickCallback
import com.icebeal.core.vo.Resource
import com.icebeal.myflix.databinding.FragmentHomeBinding
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(), OnClickCallback {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    private val homeViewModel: HomeViewModel by inject()
    private var movieAdapter: MovieAdapter? = MovieAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvMovie?.let {
            it.adapter = movieAdapter
            it.layoutManager = GridLayoutManager(activity, 2)
            it.setHasFixedSize(true)
        }

        homeViewModel.movies.observe(viewLifecycleOwner, { movies ->
            when (movies) {

                is Resource.Success -> {
                    val movie = DataMapper.mapDomainToPresenter(movies.data!!)
                    movieAdapter?.setMovie(movie)
                    movieAdapter?.notifyDataSetChanged()
                    showImage(movie)
                    showLoading(false)
                }

                is Resource.Loading -> {
                    binding?.movie?.visibility = View.GONE
                    showLoading(true)
                }

                is Resource.Error -> {
                    showLoading(false)
                    Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                }

            }
        })

        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchMovie(newText.toString())
                return true
            }

        })

    }

    private fun searchMovie(searchQuery: String) {

        val query = "%$searchQuery%"

        homeViewModel.searchMovie(query).observe(viewLifecycleOwner, {

            movieAdapter?.setMovie(it)
            movieAdapter?.notifyDataSetChanged()

            showAnimation(it)

        })

    }

    private fun showLoading(state: Boolean) {

        if (state) {

            binding?.loading?.visibility = View.VISIBLE

        } else {

            binding?.loading?.visibility = View.GONE

        }

    }

    private fun showImage(movie: List<Movie>) {

        if (movie.isEmpty()) {

            binding?.movie?.visibility = View.VISIBLE

        } else {

            binding?.movie?.visibility = View.GONE

        }

    }

    private fun showAnimation(movie: List<Movie>) {

        if (movie.isEmpty()) {

            binding?.noResultView?.visibility = View.VISIBLE

        } else {

            binding?.noResultView?.visibility = View.GONE

        }

    }

    override fun onClick(movie: Movie) {

        val toDetailActivity = HomeFragmentDirections.actionHomeFragmentToDetailActivity()
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
        movieAdapter = null
    }

}