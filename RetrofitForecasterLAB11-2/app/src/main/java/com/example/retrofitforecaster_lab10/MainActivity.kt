package com.example.retrofitforecaster_lab10

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var mService: RetrofitServices
    private val adapter = Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.rView)

        val model: MyViewModel by viewModels()
        model.getWeatherData().observe(this) { weatherData ->
            adapter.submitList(weatherData)
        }

        mService = Common.retrofitService
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }


}