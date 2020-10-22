package com.idn.covid19.networks

import com.idn.covid19.main.models.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface RestApi {
    @GET("summary")
    fun getAllCountry(): Observable<CovidModel>

    @GET("summary")
    fun getEachCountry(): Observable<CountriesItem>
}