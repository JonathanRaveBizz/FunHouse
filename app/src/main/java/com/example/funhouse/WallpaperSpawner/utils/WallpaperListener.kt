package com.example.funhouse.WallpaperSpawner.utils

import androidx.recyclerview.widget.RecyclerView

interface AdapterListener{

}

interface WallpaperListener {
    fun onScroll(recyclerView: RecyclerView, newState : Int)
}