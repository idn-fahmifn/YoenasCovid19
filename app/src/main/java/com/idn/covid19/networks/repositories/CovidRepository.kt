package com.idn.covid19.networks.repositories

import com.idn.covid19.main.models.*
import com.idn.covid19.networks.ApiObserver
import com.idn.covid19.networks.ServiceFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

class CovidRepository {
    private val compositeDisposable = CompositeDisposable()
    private val apiService = ServiceFactory.create()

    fun getWorld(
        onResult: (CovidModel) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        apiService.getAllCountry()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<CovidModel>(compositeDisposable) {
                override fun onApiSuccess(data: CovidModel) {
                    onResult(data)
                }

                override fun onApiError(er: Throwable) {
                    onError(er)
                }
            })
    }

    fun getEachCountries(
        onResult: (CountriesItem) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        apiService.getEachCountry()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<CountriesItem>(compositeDisposable) {
                override fun onApiSuccess(data: CountriesItem) {
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