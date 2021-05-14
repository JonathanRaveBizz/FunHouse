package com.example.funhouse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.funhouse.JailHouse.JailHouseRock
import com.example.funhouse.WallpaperSpawner.WallpaperActivity
import com.example.funhouse.WeatherApp.WeatherActivity
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
        binding.weatherApp.setOnClickListener(){
            weatherWeatherSTORMs()
        }
        binding.wallpaperBtn.setOnClickListener(){
            AnimeTheAnimeWeeb()
        }


    }

    private fun goToJail() {
        startActivity(JailHouseRock.newIntent(this))
    }
    private fun weatherWeatherSTORMs() {
        startActivity(WeatherActivity.newIntent(this))
    }
    private fun AnimeTheAnimeWeeb()
    {
        startActivity(WallpaperActivity.newIntent(this))
    }

}