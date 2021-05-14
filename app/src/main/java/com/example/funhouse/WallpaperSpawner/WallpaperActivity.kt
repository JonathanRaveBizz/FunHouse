package com.example.funhouse.WallpaperSpawner

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.funhouse.WallpaperSpawner.ViewModels.WallpaperViewModel
import androidx.lifecycle.Observer
import com.example.funhouse.WeatherApp.WeatherActivity
import com.example.funhouse.databinding.WallpaperActivityBinding

class WallpaperActivity : AppCompatActivity(){

    private lateinit var viewmodel: WallpaperViewModel
    private lateinit var binding : WallpaperActivityBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WallpaperActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val linearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.wallpaperRv.apply {
            layoutManager = linearLayoutManager
            adapter = WallpaperAdapter().apply {
                //listener = this@MainActivity
            }
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        viewmodel.getWallpapers()
                    }
                    if(!recyclerView.canScrollVertically(-1))
                        viewmodel.addWallpapers()
                }

            })

        }
//

        viewmodel = ViewModelProvider(this).get(WallpaperViewModel::class.java)
        //viewmodel.getWallpapers()
        viewmodel.getWallpapers().observe(this, Observer { wallpaperList ->
            //Log.d("_TAG:", "Got ${wallpaperList.size} wallpapers")
            (binding.wallpaperRv.adapter as WallpaperAdapter).updateList(wallpaperList)
        })
        viewmodel.addWallpapers().observe(this, Observer { wallpaperList ->
            //Log.d("_TAG:", "Got ${wallpaperList.size} wallpapers")
            (binding.wallpaperRv.adapter as WallpaperAdapter).updateList(wallpaperList)
        })


    }
    companion object
    {
        fun newIntent(context: Context) : Intent
        {
            return Intent(context, WallpaperActivity::class.java)

        }
    }


}