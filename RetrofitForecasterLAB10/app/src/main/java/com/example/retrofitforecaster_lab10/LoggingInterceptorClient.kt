package com.example.retrofitforecaster_lab10

import okhttp3.OkHttpClient

object LoggingInterceptorClient {
    val client: OkHttpClient
        get() = OkHttpClient.Builder().addInterceptor(LoggingInterceptor()).build()
}