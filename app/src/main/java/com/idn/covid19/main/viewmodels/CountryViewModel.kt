package com.idn.covid19.main.viewmodels

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.idn.covid19.main.models.CountriesItem
import com.idn.covid19.main.models.CovidModel
import com.idn.covid19.networks.repositories.CovidRepository

class CountryViewModel (application: Application) : AndroidViewModel(application) {
    var isLoading: ObservableField<Boolean> = ObservableField()
    var cekCountryResponse: MutableLiveData<CovidModel> = MutableLiveData()
    var error: MutableLiveData<Throwable> = MutableLiveData()

    private var mainRepository = CovidRepository()

    fun getEachCountry() {
        isLoading.set(true)
        mainRepository.getWorld({
            isLoading.set(false)
            cekCountryResponse.postValue(it)
        }, {
            isLoading.set(false)
            error.postValue(it)
        }
        )
    }


    override fun onCleared() {
        super.onCleared()
        mainRepository.cleared()
    }
}