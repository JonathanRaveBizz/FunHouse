package com.example.funhouse.WeatherApp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.funhouse.WeatherApp.models.ZeWeatherHead
import com.example.funhouse.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWeatherBinding
    private lateinit var weatherHead: ZeWeatherHead
    private lateinit var viewModel: ZeWeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ZeWeatherViewModel()
        viewModel.getWeather().observe(this, Observer { newWeather ->
            updateWeather(newWeather)
        })

    }
    private fun updateWeather(weatherHead: ZeWeatherHead)
    {
        this.weatherHead=weatherHead

        with(binding){
            currentWeatherIco.apply {
                Glide.with(this)
                    .load("http://openweathermap.org/img/wn/" + weatherHead.weather[0].icon + "@4x.png")
                    .into(currentWeatherIco)
            }
            tempTxt.text = weatherHead.main.temp.toString()
            weatherDescrip.text = weatherHead.weather[0].main
        }
    }
    companion object
    {
        fun newIntent(context: Context): Intent
        {
            return Intent(context, WeatherActivity::class.java)
        }
    }
}