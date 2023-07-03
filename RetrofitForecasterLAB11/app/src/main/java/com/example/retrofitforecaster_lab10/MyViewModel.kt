package com.example.retrofitforecaster_lab10

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {

    private val mService: RetrofitServices = Common.retrofitService

    fun loadData(callback: (List<ListItem>) -> Unit) {
        return runBlocking {
            mService.getWeatherList().enqueue(object : Callback<DataWeather> {
                override fun onResponse(call: Call<DataWeather>, response: Response<DataWeather>) {
                    callback((response.body() as DataWeather).list)
                }

                override fun onFailure(call: Call<DataWeather>, t: Throwable) {
                }
            })
        }

    }
}