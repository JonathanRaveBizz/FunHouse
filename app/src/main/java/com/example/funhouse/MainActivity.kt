package com.example.funhouse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.funhouse.JailHouseRock

class MainActivity : AppCompatActivity() {
    lateinit var jailRecords_btn: Button

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