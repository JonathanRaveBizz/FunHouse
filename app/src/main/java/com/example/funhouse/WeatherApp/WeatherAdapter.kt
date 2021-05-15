package com.example.funhouse.WeatherApp

import com.example.funhouse.WeatherApp.models.circleResponce.Cities
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.funhouse.databinding.WeatherItemBinding

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    lateinit var listener: WeatherAdapterListener
    lateinit var binding: WeatherItemBinding

    private var weatherList: List<Cities> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = WeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = weatherList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    fun updateList(newList: List<Cities>) {
        weatherList = newList
        notifyDataSetChanged()
    }


    inner class ViewHolder(binding: WeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var weatherItem: Cities

        fun bind(paper: Cities) {
            weatherItem = paper
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

