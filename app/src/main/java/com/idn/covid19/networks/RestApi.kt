package com.idn.covid19.networks

import com.idn.covid19.main.models.AllCountries
import com.idn.covid19.main.models.InfoCountry
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface RestApi {
    @GET("summary")
    fun getAllCountry(): Observable<AllCountries>

    @GET("dayone/country/")
    fun getInfoService(@Url url: String?): Observable<MutableList<InfoCountry>>
}