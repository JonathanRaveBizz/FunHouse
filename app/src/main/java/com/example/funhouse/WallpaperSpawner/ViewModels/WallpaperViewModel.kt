package com.example.funhouse.WallpaperSpawner.ViewModels


import com.example.funhouse.WallpaperSpawner.utils.WallpaperHead
import com.example.funhouse.WallpaperSpawner.utils.WallpaperItem
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.funhouse.WallpaperSpawner.utils.WallpaperListener
import com.example.homework2butinkotlin.utils.WallpaperRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WallpaperViewModel : ViewModel() {
    private val disposables : CompositeDisposable= CompositeDisposable()
    private val wallRepo = WallpaperRepository()
    private var wallpapers: MutableLiveData<List<com.example.funhouse.WallpaperSpawner.utils.WallpaperItem>> = MutableLiveData()
    lateinit var listener : WallpaperListener


    fun getWallpapers() : LiveData<List<com.example.funhouse.WallpaperSpawner.utils.WallpaperItem>>
    {
        loadWallpapers()
        return wallpapers
    }

    private fun loadWallpapers()
    {
        disposables.add(
            wallRepo.getWallpaperObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(this::wallpaperSuccess, this::onError))
    }
    private fun loadReverseWallpapers()
    {
        disposables.add(
            wallRepo.getReverseWallpaperObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(this::wallpaperSuccess, this::onError))
    }
    private fun wallpaperSuccess(wallPaperItemList: WallpaperHead)
    {
        if(wallPaperItemList==null) {
            Log.d("DEBUG", "root is null")
            return
        }
        if(wallPaperItemList.wallpapers==null)
        {
            Log.d("DEBUG", "List is null")
            return
        }

        Log.d("DEBUG", wallPaperItemList.wallpapers.get(0).url_image)
        wallpapers.postValue(wallPaperItemList.wallpapers)

    }
    private fun onError(throwable: Throwable)
    {
        Log.e("API_ERROR", "something broke: $throwable")

    }

    fun addWallpapers() : LiveData<List<com.example.funhouse.WallpaperSpawner.utils.WallpaperItem>>{
        loadReverseWallpapers()
        return wallpapers

    }
}