package com.idn.covid19.main.models

import com.google.gson.annotations.SerializedName

data class CovidModel(

	@field:SerializedName("Message")
	val message: String? = null,

	@field:SerializedName("Countries")
	val countries: List<CountriesItem>,

	@field:SerializedName("Global")
	val global: Global? = null,

	@field:SerializedName("Date")
	val date: String? = null
)

data class Global(

	@field:SerializedName("NewRecovered")
	val newRecovered: Int? = null,

	@field:SerializedName("NewDeaths")
	val newDeaths: Int? = null,

	@field:SerializedName("TotalRecovered")
	val totalRecovered: Int? = null,

	@field:SerializedName("TotalConfirmed")
	val totalConfirmed: Int? = null,

	@field:SerializedName("NewConfirmed")
	val newConfirmed: Int? = null,

	@field:SerializedName("TotalDeaths")
	val totalDeaths: Int? = null
)

data class Premium(
	val any: Any? = null
)

data class CountriesItem(

	@field:SerializedName("NewRecovered")
	val newRecovered: Int? = null,

	@field:SerializedName("NewDeaths")
	val newDeaths: Int? = null,

	@field:SerializedName("TotalRecovered")
	val totalRecovered: Int? = null,

	@field:SerializedName("TotalConfirmed")
	val totalConfirmed: Int? = null,

	@field:SerializedName("Country")
	val country: String? = null,

	@field:SerializedName("Premium")
	val premium: Premium? = null,

	@field:SerializedName("CountryCode")
	val countryCode: String? = null,

	@field:SerializedName("Slug")
	val slug: String? = null,

	@field:SerializedName("NewConfirmed")
	val newConfirmed: Int? = null,

	@field:SerializedName("TotalDeaths")
	val totalDeaths: Int? = null,

	@field:SerializedName("Date")
	val date: String? = null
)
