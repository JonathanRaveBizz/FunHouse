package com.example.funhouse.WeatherApp

import ZeGeocodeHead
import com.example.funhouse.WeatherApp.models.circleResponce.CircleHead
import com.example.funhouse.WeatherApp.models.weather.ZeWeatherHead
import io.reactivex.Single

class WeatherRepository (private val weatherAPI: WeatherAPI){

    fun getSpaghettiWeatherObservable() : Single<ZeWeatherHead>
    {
        return weatherAPI.getWeather()
    }
    fun getMeatballTargetsObservable(lat : Double, lon : Double) : Single<CircleHead>
    {
        return weatherAPI.getCities(lat=lat, lon=lon)
    }
    fun getSpaghettiWeatherObservable(city : String) : Single<ZeWeatherHead>
    {
        return weatherAPI.getWeather(city=city)
    }

}