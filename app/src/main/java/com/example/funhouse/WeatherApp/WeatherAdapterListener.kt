package com.example.funhouse.WeatherApp

import com.example.funhouse.WeatherApp.models.circleResponce.Cities

interface WeatherAdapterListener {
    fun onItemClick(city: Cities)
}