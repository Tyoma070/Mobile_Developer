package com.example.retrofitforecaster_lab10

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ContactItemDiffCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem) = oldItem == newItem
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem) = oldItem == newItem
}

class ViewHolderCold(view: View) : RecyclerView.ViewHolder(view) {
    private val datetime: TextView = view.findViewById(R.id.datetime_text)
    private val temperature: TextView = view.findViewById(R.id.temperature_text)
    private val description: TextView = view.findViewById(R.id.desc_text)
    private val icon: ImageView = view.findViewById(R.id.imageCOLD)
    private val icon2: ImageView = view.findViewById(R.id.imageHOT)



    fun bindTo(weather: ListItem) {
        icon.visibility = View.VISIBLE
        icon2.visibility = View.GONE
        temperature.text = weather.main.temp.toString()
        datetime.text = weather.dt_txt
        description.text = weather.weather?.get(0)?.description
    }
}

class ViewHolderHot(view: View) : RecyclerView.ViewHolder(view) {
    private val datetime: TextView = view.findViewById(R.id.datetime_text)
    private val temperature: TextView = view.findViewById(R.id.temperature_text)
    private val description: TextView = view.findViewById(R.id.desc_text)
    private val icon: ImageView = view.findViewById(R.id.imageHOT)
    private val icon2: ImageView = view.findViewById(R.id.imageCOLD)

    fun bindTo(weather: ListItem) {
        icon.visibility = View.VISIBLE
        icon2.visibility = View.GONE
        temperature.text = weather.main.temp.toString()
        datetime.text = weather.dt_txt
        description.text = weather.weather?.get(0)?.description
    }
}

class Adapter : ListAdapter<ListItem, RecyclerView.ViewHolder>(ContactItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            WEATHER_STATE_HOT -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.rview_item, parent, false)
                view.setBackgroundColor(Color.rgb(255, 203, 219))
                ViewHolderHot(view)
            }
            else -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.rview_item, parent, false)
                view.setBackgroundColor(Color.rgb(171, 205, 239))
                ViewHolderCold(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].main.temp > WEATHER_STATE_CHANGE_VALUE) WEATHER_STATE_HOT else WEATHER_STATE_COLD
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = currentList[position]

        if (data.main.temp > WEATHER_STATE_CHANGE_VALUE) {
            val viewHolderHot: ViewHolderHot = holder as ViewHolderHot
            viewHolderHot.bindTo(data)
        } else {
            val viewHolderCold: ViewHolderCold = holder as ViewHolderCold
            viewHolderCold.bindTo(data)
        }
    }
}

private val WEATHER_STATE_CHANGE_VALUE: Int = 5
private val WEATHER_STATE_HOT: Int = 11222333
private val WEATHER_STATE_COLD: Int = 34433626