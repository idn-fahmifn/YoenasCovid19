package com.idn.covid19.main.viewmodels

import android.content.Context
import androidx.databinding.ObservableField
import com.idn.covid19.databinding.ListCountry2Binding
import com.idn.covid19.main.models.CountriesItem
import java.text.DecimalFormat

class ItemCountryViewModel(
    private val context: Context,
    model: CountriesItem,
    binding: ListCountry2Binding
) {
    val formatter = DecimalFormat("#,###,###")

    var country: ObservableField<String?> = ObservableField(model.country)
    var cases: ObservableField<String?> =
        ObservableField(formatter.format(model.totalConfirmed))
    var recovered: ObservableField<String?> =
        ObservableField(formatter.format(model.totalRecovered))
    var death: ObservableField<String?> =
        ObservableField(formatter.format(model.totalDeaths))
}