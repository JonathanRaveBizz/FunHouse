package com.example.funhouse.WeatherApp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.funhouse.WallpaperSpawner.WallpaperAdapter
import com.example.funhouse.WeatherApp.models.circleResponce.Cities
import com.example.funhouse.WeatherApp.models.weather.ZeWeatherHead
import com.example.funhouse.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() , WeatherAdapterListener{
    private lateinit var binding: ActivityWeatherBinding
    private lateinit var weatherHead: ZeWeatherHead
    private lateinit var viewModel: ZeWeatherViewModel
    private lateinit var mAdapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ZeWeatherViewModel()



        val linearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        mAdapter = WeatherAdapter()

        binding.weatherRv.apply {
            layoutManager = linearLayoutManager
            adapter = mAdapter.apply {
                listener = this@WeatherActivity
            }
        }
        binding.searchBtn.apply {
            setOnClickListener {
                UpdateCity()
            }
        }
        createObservers()
        viewModel.loadWeather()


    }

    private fun updateWeather(weatherHead: ZeWeatherHead) {
        Log.d("_INFO", "We Got the Info for ${weatherHead.name}")
        //weatherHead = mWeatherHead

        with(binding) {
            currentWeatherIco.apply {
                Glide.with(this)
                    .load("http://openweathermap.org/img/wn/${weatherHead.weather[0].icon}@4x.png")
                    .into(currentWeatherIco)

            }
            tempTxt.text = weatherHead.main.temp.toString()
            weatherDescrip.text = weatherHead.weather[0].main
            cityTxt.text = weatherHead.name
        }
        viewModel.loadListofCities(lat = weatherHead.coord.lat, lon = weatherHead.coord.lon)

    }

    private fun UpdateCity() {
        Log.d("_INFO", "We are updating the city")
        viewModel.loadWeatherByCity(binding.searchEdtxt.text.toString())

    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, WeatherActivity::class.java)
        }
    }

    override fun onItemClick(city: Cities) {
        Log.d("DEBUG", "trying to load ${city.name}")
        viewModel.loadWeatherByCity(city.name)
    }
    fun createObservers()
    {
        viewModel.getWeather()
            .observe(this, Observer { newWeather ->
                updateWeather(newWeather)
            })
        viewModel.getListOfCities()
            .observe(this, Observer { newWeather ->
                Log.d("DEBUG", "we have ${newWeather.size} cities")
                mAdapter.updateList(newWeather)

            })
    }
}