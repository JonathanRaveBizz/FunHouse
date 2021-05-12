package com.example.funhouse

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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
        setContentView(R.layout.jail_house_rock)
        binding = JailHouseRockBinding.inflate(layoutInflater)

        mSearchResults = searchResults()
        viewModel = ViewModelProvider(this).get(SearchResultsViewModel::class.java)
        with(binding)
        {
            searchBtn.apply{
            setOnClickListener{
                search(firstNameEtxt.text.toString(), lastNameEtxt.text.toString())}
        }
            supportFragmentManager.beginTransaction()
                .add(binding.jailHouseFragement.id,mSearchResults)
                .setReorderingAllowed(true)
                .commit()
        }



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
        if (lastName.isEmpty()) {
            Log.d("_DEBUG", "LastName is Empty")
            Toast.makeText(applicationContext, "Last Name Required", Toast.LENGTH_LONG).show()
            return
        }
        viewModel.search(firstName, lastName)
            .observe(this, Observer { resultsList ->
                searchResults.bAdapter.updateList(resultsList)
            }
    }
}