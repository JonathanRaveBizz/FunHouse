package com.example.funhouse.WeatherApp.di.component

import com.example.funhouse.WeatherApp.ZeWeatherViewModel
import com.example.funhouse.WeatherApp.di.modules.NewtworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NewtworkModule::class]
)
interface AppComponent{
    fun inject(viewModel: ZeWeatherViewModel)

}