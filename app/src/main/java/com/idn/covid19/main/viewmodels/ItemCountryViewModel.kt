package com.idn.covid19.main.viewmodels

import android.content.Context
import androidx.databinding.ObservableField
import com.idn.covid19.databinding.ListCountry2Binding
import com.idn.covid19.main.models.CountriesItem

class ItemCountryViewModel (
    private val context: Context,
    model: CountriesItem,
    binding: ListCountry2Binding
    ) {
        var country: ObservableField<String?> = ObservableField(model.country)
        var cases: ObservableField<String?> = ObservableField(model.totalConfirmed.toString())
        var recovered: ObservableField<String?> = ObservableField(model.totalRecovered.toString())
        var death: ObservableField<String?> = ObservableField(model.totalDeaths.toString())
    }