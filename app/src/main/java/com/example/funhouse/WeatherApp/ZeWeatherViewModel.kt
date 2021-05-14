package com.example.funhouse.WeatherApp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.funhouse.WallpaperSpawner.utils.WallpaperListener
import com.example.funhouse.WeatherApp.models.ZeWeatherHead
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ZeWeatherViewModel {
    private val disposables : CompositeDisposable = CompositeDisposable()
    var weather: MutableLiveData<ZeWeatherHead> = MutableLiveData()
    lateinit var listener : WallpaperListener

    @Inject
    lateinit var weatherRepo : WeatherRepository
    init {
        WeatherApp.inject(this)
    }

    fun getWeather() : LiveData<ZeWeatherHead>
    {
        disposables.add(
            weatherRepo.getSpaghettiWeatherObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(this::weatherSuccess, this::onError)
        )
        return weather
    }
    private fun weatherSuccess(weatherHead: ZeWeatherHead)
    {
        weather.postValue(weatherHead)
    }
    private fun onError(t: Throwable)
    {
        Log.e("_NETWORK", "Failed To Load Wallpapers $t")
    }
}