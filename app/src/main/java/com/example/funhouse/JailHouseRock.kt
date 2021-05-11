package com.example.funhouse

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class JailHouseRock : AppCompatActivity() {
    lateinit var searchButton: Button
    lateinit var firstname: EditText
    lateinit var lastName: EditText
    lateinit var mSearchResults: searchResults



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jail_house_rock)

        mSearchResults = searchResults()
        searchButton = findViewById(R.id.getRecords)
        firstname = findViewById(R.id.first_name_etxt)
        lastName = findViewById(R.id.last_name_etxt)
        searchButton.apply{
            setOnClickListener{
                search(firstname.text.toString(), lastName.text.toString())}
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.jail_house_fragement,mSearchResults)
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
    private fun search(first_name :String?, last_name: String)
    {
        mSearchResults.search(first_name, last_name)
    }
}