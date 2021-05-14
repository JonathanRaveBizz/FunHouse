package com.example.funhouse.WallpaperSpawner

import com.example.funhouse.WallpaperSpawner.utils.WallpaperItem
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homework2butinkotlin.utils.WallpaperRepository

class WallpaperRecyclerView: ViewModel()
{
    val wallRepo = WallpaperRepository()
    var wallpapers: MutableLiveData<List<com.example.funhouse.WallpaperSpawner.utils.WallpaperItem>>? = null
    get()
    {
        if (wallpapers == null)
        {
            wallpapers = MutableLiveData()
            loadWallpapers()
        }
        return wallpapers
    }
    fun loadWallpapers()
    {
      /* wallRepo.wallpaperCall.enque(object : callback<WallpaperRequest?>)
        {
            call: call
        }*/
    }


}