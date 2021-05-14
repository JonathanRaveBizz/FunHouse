package com.example.funhouse.WeatherApp.di.modules

import com.example.funhouse.WeatherApp.WeatherAPI
import com.example.funhouse.WeatherApp.WeatherRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NewtworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(baseURL: String) : Retrofit
    {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideSpegettiAndMeatBalls() : WeatherAPI
    {
        return provideRetrofit("https://api.openweathermap.org/data/2.5/")
            .create(WeatherAPI::class.java)
    }
    @Singleton
    @Provides
    fun provideWeatherRepository() :WeatherRepository
    {
        return WeatherRepository(provideSpegettiAndMeatBalls())
    }



}