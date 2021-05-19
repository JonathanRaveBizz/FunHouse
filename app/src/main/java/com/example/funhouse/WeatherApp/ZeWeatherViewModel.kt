package com.example.funhouse.WeatherApp

import com.example.funhouse.WeatherApp.models.circleResponce.Cities
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.funhouse.WallpaperSpawner.utils.WallpaperListener
import com.example.funhouse.WeatherApp.models.circleResponce.CircleHead
import com.example.funhouse.WeatherApp.models.weather.ZeWeatherHead
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ZeWeatherViewModel {
    private val disposables : CompositeDisposable = CompositeDisposable()
    var weather: MutableLiveData<ZeWeatherHead> = MutableLiveData()
    var city : MutableLiveData<List<Cities>> = MutableLiveData()
    lateinit var listener : WallpaperListener

    @Inject
    lateinit var weatherRepo : WeatherRepository
    init {
        WeatherApp.inject(this)
    }

    fun getWeather() : LiveData<ZeWeatherHead> = weather
    fun getListOfCities() : LiveData<List<Cities>> = city

    fun loadWeather() {
        disposables.add(
            weatherRepo.getSpaghettiWeatherObservable()
            .subscribeOn(Schedulers.io())
            .subscribe(this::weatherSuccess, this::onError))
    }

    fun loadListofCities(lat : Double, lon : Double)
    {
        disposables.add(
            weatherRepo.getMeatballTargetsObservable(lat=lat, lon=lon)
                .subscribeOn(Schedulers.io())
                .subscribe(this::citiesSuccess, this::onError)
        )
    }

    fun loadWeatherByCity(citytxt: String){
        disposables.add(
            weatherRepo.getSpaghettiWeatherObservable(citytxt)
                .subscribeOn(Schedulers.io())
                .subscribe(this::weatherSuccess, this::onError))
    }

    private fun citiesSuccess(lost : CircleHead) {
        for (cities in lost.list) {
            //Log.d("DEBUG", "city ${cities.name}")
        }
        city.postValue(lost.list)
    }

    private fun weatherSuccess(weatherHead: ZeWeatherHead)
    {
        weather.postValue(weatherHead)
    }
    private fun onError(t: Throwable)
    {
        Log.e("_NETWORK", "Failed To Load Weather: $t")
    }

    fun loadWeatherByLatLon(lat: Double, lon: Double) {
        disposables.add(
            weatherRepo.getSpaghettiWeatherByLatLonObservable(lat=lat, lon=lon)
                .subscribeOn(Schedulers.io())
                .subscribe(this::weatherSuccess, this::onError))

    }


}