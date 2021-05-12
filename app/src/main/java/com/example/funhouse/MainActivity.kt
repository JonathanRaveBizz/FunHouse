package com.example.funhouse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.funhouse.JailHouseRock
import com.example.funhouse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var jailRecords_btn: Button
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jailRecords_btn = findViewById(R.id.jail_records)
        jailRecords_btn.setOnClickListener{
            goToJail()
        }


    }

    private fun goToJail() {
        startActivity(JailHouseRock.newIntent(this))

    }
}