package com.fajar.moviedb.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajar.moviedb.R
import com.fajar.moviedb.core.data.source.Resource
import com.fajar.moviedb.core.ui.MovieAdapter
import com.fajar.moviedb.core.ui.ViewModelFactory
import com.fajar.moviedb.databinding.FragmentSearchBinding
import com.fajar.moviedb.ui.detail.DetailActivity



class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchAdapter = MovieAdapter()
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //ViewModelProvider(this)[SearchViewModel::class.java]

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
       // binding.rvResult.layoutManager = LinearLayoutManager(context)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = obtainViewModel(context as AppCompatActivity)
        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        val searchAdapter = MovieAdapter()

        binding.apply {
            rvResult.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = searchAdapter
            }

            searchAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }
        }

      //  getSearchFilm()
        getDataFilm()
    }

    private fun obtainViewModel(activity: AppCompatActivity): SearchViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[SearchViewModel::class.java]

    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
        val search = menu.findItem(R.id.search)
        search.expandActionView()

        val searchView = search.actionView as SearchView

        searchView.apply {
            onActionViewExpanded()
            setIconifiedByDefault(false)
            isFocusable = true
            isIconified = false
            requestFocusFromTouch()
            requestFocus()
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    clearFocus()
                    binding.rvResult.scrollToPosition(0)
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    if (query != null && query.isNotEmpty()) {
                        binding.apply {
                            progressBar.visibility = View.VISIBLE
                        //    onEmptyStateMessage.visibility = View.GONE
                       //     onFailMsg.visibility = View.GONE
                       //     onInitialSearchStateMessage.visibility = View.GONE
                            rvResult.scrollToPosition(0)
                        }
                        viewModel.setSearchQuery(query)
                        getDataFilm()
                    } else if (query.equals("")) {
//                        searchAdapter.clearList()
                    }
                    return true
                }
            })
        }

    }

    private fun getDataFilm() {
        binding.apply {
            viewModel.searchResult.observe(viewLifecycleOwner) { results ->
                if (results != null) {
                    when (results) {
                        is Resource.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            val searchResult = results.data
                            progressBar.visibility = View.GONE
                            finalState()
                            if (searchResult != null) {
                                searchAdapter.setData(searchResult)
                                rvResult.scrollToPosition(0)
                                //swipeToRefresh.isRefreshing = false
                                if (searchResult.isEmpty()){
                                    emptyState()
                                  //  onEmptyStateMessage.visibility = View.VISIBLE
                                  //  onInitialSearchStateMessage.visibility = View.GONE
                                }
                            }
                        }
                        is Resource.Error -> {
                            progressBar.visibility = View.GONE
                          //  onFailMsg.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun emptyState() {
        binding.apply {
            rvResult.visibility = View.GONE
            layoutEmptyData.root.visibility = View.VISIBLE
        }
    }

    private fun finalState() {
        binding.apply {
            rvResult.visibility = View.VISIBLE
            layoutEmptyData.root.visibility = View.GONE
        }
    }


    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    //            tvLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

