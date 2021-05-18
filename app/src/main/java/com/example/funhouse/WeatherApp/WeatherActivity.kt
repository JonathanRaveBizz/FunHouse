package com.example.funhouse.WeatherApp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.funhouse.WallpaperSpawner.WallpaperAdapter
import com.example.funhouse.WeatherApp.models.circleResponce.Cities
import com.example.funhouse.WeatherApp.models.weather.ZeWeatherHead
import com.example.funhouse.databinding.ActivityWeatherBinding
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

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
            itemAnimator = SlideInUpAnimator()
            itemAnimator?.apply {
                addDuration = 1000
                removeDuration = 100
                moveDuration = 1000
                changeDuration = 100
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
        viewModel.loadWeatherByLatLon(city.coord.lat, city.coord.lon)
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
                newWeather.forEach {
                    Log.d("DEBUG", it.name)
                }
                mAdapter.updateList(newWeather)

            })
    }
}