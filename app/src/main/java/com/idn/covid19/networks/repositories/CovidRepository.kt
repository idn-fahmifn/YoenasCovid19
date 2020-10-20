package com.idn.covid19.networks.repositories

import com.idn.covid19.main.models.AllCountries
import com.idn.covid19.networks.ApiObserver
import com.idn.covid19.main.models.InfoCountry
import com.idn.covid19.networks.ServiceFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

class CovidRepository {
    private val compositeDisposable = CompositeDisposable()
    private val apiService = ServiceFactory.create()

    fun getWorld(
        onResult: (AllCountries) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        apiService.getAllCountry()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<AllCountries>(compositeDisposable) {
                override fun onApiSuccess(data: AllCountries) {
                    onResult(data)
                }

                override fun onApiError(er: Throwable) {
                    onError(er)
                }
            })
    }

    fun getInfoCountry(
        onResult: (MutableList<InfoCountry>) -> Unit,
        onError: (Throwable) -> Unit,
        country: String
    ) {
        apiService.getInfoService(country)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<MutableList<InfoCountry>>(compositeDisposable) {
                override fun onApiSuccess(data: MutableList<InfoCountry>) {
                    onResult(data)
                }

                override fun onApiError(er: Throwable) {
                    onError(er)
                }
            })
    }

    fun cleared() {
        compositeDisposable.clear()
    }
}