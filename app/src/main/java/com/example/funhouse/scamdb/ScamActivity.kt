package com.example.funhouse.scamdb

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.funhouse.JailHouse.JailHouseRock
import com.example.funhouse.databinding.ActivityScamBinding

class ScamActivity : AppCompatActivity() {
    private lateinit var binding : ActivityScamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScamBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
    companion object
    {
        fun newIntent(context: Context): Intent
        {

            return Intent(context, JailHouseRock::class.java)
        }
    }
}