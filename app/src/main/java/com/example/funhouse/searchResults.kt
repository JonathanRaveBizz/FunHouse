package com.example.funhouse

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class searchResults : Fragment() , BookingListener {

    companion object {
        fun newInstance() = searchResults()
    }

    private lateinit var viewModel: SearchResultsViewModel
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_results_fragment, container, false)
        val gridLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerView = view.findViewById(R.id.jail_records)
        recyclerView.apply {
            layoutManager = gridLayoutManager
            adapter = BookingAdapter().apply {
                listener = this@searchResults
            }
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchResultsViewModel::class.java)


    }
    public fun search(first_name: String?, last_name :String)
    {
        viewModel.search(first_name, last_name)

    }

}