package com.example.funhouse.WeatherApp

import android.app.Application
import android.content.Context
import com.example.funhouse.WeatherApp.di.component.AppComponent
import com.example.funhouse.WeatherApp.di.component.DaggerAppComponent
import javax.inject.Singleton

@Singleton
class WeatherApp : Application(){
    override fun onCreate()
    {
        super.onCreate()
        buildAppComponent(applicationContext)
    }

    companion object {
        lateinit var appComponent : AppComponent
        fun buildAppComponent(context: Context) {
            appComponent = DaggerAppComponent
                .builder()
                .build()
        }
        fun inject(viewModel: ZeWeatherViewModel)
        {
            appComponent.inject(viewModel)
        }
    }
}