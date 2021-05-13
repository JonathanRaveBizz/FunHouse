package com.example.funhouse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.funhouse.databinding.SearchResultsFragmentBinding

class searchResults : Fragment() , BookingListener {

    companion object {
        fun newInstance() = searchResults()
    }
    private lateinit var binding : SearchResultsFragmentBinding
    lateinit var bAdapter : BookingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchResultsFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        val gridLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        bAdapter = BookingAdapter()
        binding.jailRecords.apply {
            layoutManager = gridLayoutManager
            adapter = bAdapter.apply {
                listener = this@searchResults
            }
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(SearchResultsViewModel::class.java)


    }


}