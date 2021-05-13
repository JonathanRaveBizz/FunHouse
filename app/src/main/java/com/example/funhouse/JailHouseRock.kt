package com.example.funhouse

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.funhouse.databinding.JailHouseRockBinding

class JailHouseRock : AppCompatActivity() {
    lateinit var mSearchResults: searchResults
    private lateinit var viewModel: SearchResultsViewModel
    lateinit var binding : JailHouseRockBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = JailHouseRockBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mSearchResults = searchResults()
        viewModel = ViewModelProvider(this).get(SearchResultsViewModel::class.java)
        with(binding) {
            Log.d("_FUCK", "we got to the binding")
            searchBtn.apply{
                Log.d("_FUCK", "we got to the apply")
                setOnClickListener {
                    Log.d("_FUCK", "Have Hit the Click")
                    search(firstNameEtxt.text.toString(), lastNameEtxt.text.toString())
                }
            }
            recentBtn.apply{
                setOnClickListener{
                    recent()
                }
            }
        }
        supportFragmentManager.beginTransaction()
                .add(binding.jailHouseFragement.id,mSearchResults)
                .setReorderingAllowed(true)
                .commit()



    }

    companion object
    {
        fun newIntent(context: Context): Intent
        {

            return Intent(context, JailHouseRock::class.java)
        }
    }
    private fun search(firstName :String, lastName: String)
    {
        Log.d("_FUCK", "we hit the onclick listener")
        if (lastName.isEmpty()) {
            Log.d("_DEBUG", "LastName is Empty")
            Toast.makeText(applicationContext, "Last Name Required", Toast.LENGTH_LONG).show()
            return
        }
        viewModel.search(firstName, lastName).observe( this, Observer { RecordList ->
            mSearchResults.bAdapter.updateList(RecordList)
        })
    }
    private fun recent()
    {
        viewModel.recent().observe(this, Observer { RecordList ->
            mSearchResults.bAdapter.updateList(RecordList)
        })
    }
}