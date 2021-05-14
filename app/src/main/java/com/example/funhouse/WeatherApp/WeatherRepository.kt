package com.example.funhouse.WeatherApp

import com.example.funhouse.WeatherApp.models.ZeWeatherHead
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository (private val weatherAPI: WeatherAPI){


    fun getSpaghettiWeatherObservable() : Single<ZeWeatherHead>
    {
        return weatherAPI.getWeather()
    }

}