package com.idn.covid19.main.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.idn.covid19.main.adapter.CountryAdapter
import com.idn.covid19.R
//import com.idn.covid19.networks.Network

class MainActivity : AppCompatActivity() {

    private lateinit var adapters: CountryAdapter
    private var ascending = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

//        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                adapters.filter.filter(newText)
//                return false
//            }
//        })

//        swipe_refresh.setOnRefreshListener {
//            getCountry()
//            swipe_refresh.isRefreshing = false
//        }
//
        getCountry()
//        initializeViews()

    }

    private fun getCountry() {
//        Network().getCountries().getAllCountry().enqueue(object : Callback<AllCountries> {
//            override fun onFailure(call: Call<AllCountries>, t: Throwable) {
//
//            }
//
//            override fun onResponse(call: Call<AllCountries>, response: Response<AllCountries>) {
//                if (response.isSuccessful) {
//                    val getListDataCorona = response.body()!!.Global
//                    val formatter: NumberFormat = DecimalFormat("#,###")
//                    txt_total_confirm.text =
//                        formatter.format(getListDataCorona.TotalConfirmed.toDouble())
//                    txt_total_recovered.text =
//                        formatter.format(getListDataCorona.TotalRecovered.toDouble())
//                    txt_total_deaths.text =
//                        formatter.format(getListDataCorona.TotalDeaths.toDouble())
//                    rv_country.apply {
//                        setHasFixedSize(true)
//                        layoutManager = LinearLayoutManager(this@MainActivity)
//                        progress_bar.visibility = View.GONE
//                        adapters = CountryAdapter(
//                            response.body()!!.Countries as ArrayList<Countries>
//                        ) { negara ->
//                            itemClicked(negara)
//                        }
//                        adapter = adapters
//                    }
//                } else {
////                    progress_bar.visibility = View.GONE
////                    handleError(this@MainActivity)
////                }
////            }
////        })
    }

    fun moveToCountry(){
        startActivity(Intent(this, ListCountryActivity::class.java))
    }

//    private fun itemClicked(countries: Countries) {
//        val moveWithData = Intent(this@MainActivity, ChartCountryActivity::class.java)
//        moveWithData.putExtra(ChartCountryActivity.EXTRA_COUNTRY, countries.Country)
//        moveWithData.putExtra(ChartCountryActivity.EXTRA_LATESTUPDATE, countries.Date)
//        moveWithData.putExtra(ChartCountryActivity.EXTRA_NEWDEATH, countries.NewDeaths)
//        moveWithData.putExtra(ChartCountryActivity.EXTRA_NEWCONFIRMED, countries.NewConfirmed)
//        moveWithData.putExtra(ChartCountryActivity.EXTRA_NEWRECOVERED, countries.NewRecovered)
//        moveWithData.putExtra(ChartCountryActivity.EXTRA_TOTALDEATH, countries.TotalDeaths)
//        moveWithData.putExtra(ChartCountryActivity.EXTRA_TOTALCONFIRMED, countries.TotalConfirmed)
//        moveWithData.putExtra(ChartCountryActivity.EXTRA_TOTALRECOVERED, countries.TotalRecovered)
//        moveWithData.putExtra(ChartCountryActivity.EXTRA_COUNTRYID, countries.CountryCode)
//        startActivity(moveWithData)
//    }

    private fun handleError(context: Context) {
        AlertDialog.Builder(context)
            .setTitle("Network Error!")
            .setCancelable(false)
            .setPositiveButton("REFRESH") { _, _ ->
                super.onRestart()
                val ripres = Intent(this@MainActivity, MainActivity::class.java)
                startActivity(ripres)
                finish()
            }
            .setNegativeButton("EXIT") { _, _ ->
                finish()
            }
            .create()
            .show()
    }

//    private fun sequenceWithoutInternet(asc: Boolean) {
//        rv_country.apply {
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(this@MainActivity)
//            if (asc) {
//                (layoutManager as LinearLayoutManager).reverseLayout = true
//                (layoutManager as LinearLayoutManager).stackFromEnd = true
//                Toast.makeText(this@MainActivity, "Z - A", Toast.LENGTH_SHORT).show()
//            } else {
//                (layoutManager as LinearLayoutManager).reverseLayout = false
//                (layoutManager as LinearLayoutManager).stackFromEnd = false
//                Toast.makeText(this@MainActivity, "A - Z", Toast.LENGTH_SHORT).show()
//            }
//            adapter = adapters
//        }
//    }

//    private fun initializeViews() {
//        btn_sequence.setOnClickListener {
//            sequenceWithoutInternet(ascending)
//            ascending = !ascending
//        }
//    }

}
