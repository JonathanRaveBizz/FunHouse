package com.example.funhouse.WallpaperSpawner

import com.example.funhouse.WallpaperSpawner.utils.WallpaperItem
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.funhouse.databinding.WallpaperItemBinding
import com.example.funhouse.WallpaperSpawner.utils.WallpaperListener

class WallpaperAdapter :RecyclerView.Adapter<WallpaperAdapter.ViewHolder>() {
    lateinit var listener: WallpaperListener
    lateinit var binding: WallpaperItemBinding

    private var wallpaperList: List<com.example.funhouse.WallpaperSpawner.utils.WallpaperItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = WallpaperItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = wallpaperList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(wallpaperList[position])
    }
    fun updateList(newList: List<com.example.funhouse.WallpaperSpawner.utils.WallpaperItem>){
        this.wallpaperList= newList
        notifyDataSetChanged()
    }

    fun addToList(newList:List<com.example.funhouse.WallpaperSpawner.utils.WallpaperItem>) {
        wallpaperList = wallpaperList+newList
        notifyDataSetChanged()
    }


    inner class  ViewHolder(binding: WallpaperItemBinding): RecyclerView.ViewHolder(binding.root){
        private var wallpaperImageView: ImageView = binding.wallpaperIv
        private lateinit var wallpaperItem: com.example.funhouse.WallpaperSpawner.utils.WallpaperItem

        fun bind(paper: com.example.funhouse.WallpaperSpawner.utils.WallpaperItem) {
            wallpaperItem = paper
            wallpaperImageView.apply {
                Glide.with(itemView)
                    .load(paper.url_image)
                    .into(wallpaperImageView)
            }
        }

    }
}

