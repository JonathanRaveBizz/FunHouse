package com.example.funhouse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.funhouse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.jailRecords.setOnClickListener(){
            goToJail()
        }


    }

    private fun goToJail() {
        startActivity(JailHouseRock.newIntent(this))
    }
}