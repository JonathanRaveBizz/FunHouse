package com.example.funhouse.WeatherApp

import android.util.Log
import com.example.funhouse.WeatherApp.models.circleResponce.Cities
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.funhouse.databinding.WeatherItemBinding

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    lateinit var listener: WeatherAdapterListener
    //lateinit var binding: WeatherItemBinding

    private var weatherList: ArrayList<Cities> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = WeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = weatherList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Log.d("DEBUG", "Binding for ${weatherList[position].name}" )
        holder.bind(weatherList[position])

    }

    fun updateList(newList: List<Cities>) {
        newList.forEach {
            weatherList.add(it)
            notifyItemInserted(weatherList.size-1)
        }
    }

    fun ClearList() {
        if (weatherList.isNotEmpty()) {
            for (i in weatherList.size-1 downTo 0)
            {
                weatherList.removeAt(i)
                notifyItemRemoved(i)
            }
        }
    }


    inner class ViewHolder(val binding: WeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {
        //private lateinit var weatherItem: Cities
        fun bind(paper: Cities) {
            val weatherItem = paper
            with(binding)
            {
                    Glide.with(itemView)
                        .load("http://openweathermap.org/img/wn/${weatherItem.weather[0].icon}@4x.png")
                        .into(currentWeatherIco)
                    weatherDescrip.text = weatherItem.weather[0].description
                    cityTxt.text = weatherItem.name
                    tempTxt.text = weatherItem.main.temp.toString()
                weatherItemContainer.setOnClickListener {
                    listener.onItemClick(weatherItem)
                }

            }


        }

    }
}

