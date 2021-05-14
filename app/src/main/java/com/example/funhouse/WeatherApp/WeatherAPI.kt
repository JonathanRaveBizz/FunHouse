package com.example.funhouse.WeatherApp

import com.example.funhouse.WeatherApp.models.ZeWeatherHead
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherAPI {
    @GET("weather")
    fun getWeather(
        @Query("q") city :String = "Atlanta",
        @Query("appid") appid : String = "d6316a51fce25dc4afac9c5ef8de0406",
        @Query("units") units : String= "imperial"
    ) : Single<ZeWeatherHead>
}
