package com.idn.covid19.network

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import java.util.concurrent.TimeUnit

interface ApiService {
    @GET("summary")
    fun getAllCountry(): Call<AllCountries>
}

interface InfoService {
    @GET
    fun getInfoService(@Url url: String?): Call<List<InfoCountry>>
}

class Network {
    val okkhtp = OkHttpClient().newBuilder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.covid19api.com/")
            .client(okkhtp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInfo(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.covid19api.com/dayone/country/")
            .client(okkhtp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getCountries() = getRetrofit().create(ApiService::class.java)
    fun getInfoCountry() = getInfo().create(InfoService::class.java)

}