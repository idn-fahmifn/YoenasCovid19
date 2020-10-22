package com.idn.covid19.main.views

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.idn.covid19.R
import com.idn.covid19.databinding.ActivityListNegaraBinding
import com.idn.covid19.main.adapter.CountryAdapter
import com.idn.covid19.main.models.CountriesItem
import com.idn.covid19.main.models.CovidModel
import com.idn.covid19.main.viewmodels.CountryViewModel
import kotlinx.android.synthetic.main.activity_list_negara.*

class ListCountryActivity : AppCompatActivity() {
    private lateinit var countryBinding: ActivityListNegaraBinding
    private lateinit var vmCountry: CountryViewModel

    private lateinit var adapter: CountryAdapter
    private var listCountries: MutableList<CountriesItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        initBinding()
        initRecyclerView()
        initSwipeRefresh()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar_list_country)

//        var isShow = true
//        var scrollRange = -1
//        appbar_list_country.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
//            if (scrollRange == -1) {
//                scrollRange = barLayout?.totalScrollRange!!
//            }
//            if (scrollRange + verticalOffset == 0) {
//                collapsing_list_country.title = getString(R.string.title_list_country)
//                supportActionBar?.setDisplayHomeAsUpEnabled(true)
//                toolbar_list_country.setNavigationIcon(R.drawable.ic_arrow_left)
//                isShow = true
//            } else if (isShow) {
//                collapsing_list_country.title = " "
//                supportActionBar?.setDisplayHomeAsUpEnabled(false)
//                isShow = false
//            }
//        })
    }

    private fun initBinding() {
        countryBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_negara)
        vmCountry = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        countryBinding.allCountryData = vmCountry

        vmCountry.getEachCountry()

        vmCountry.cekCountryResponse.observe(this, Observer {
            showDataUI(it)
        })

        vmCountry.error.observe(this, Observer {
            handlingError(it)
        })
    }

    private fun initRecyclerView() {
        countryBinding.swipeCountry.isRefreshing = true
        val lManager = LinearLayoutManager(this)
        countryBinding.rvCountry.layoutManager = lManager
        countryBinding.rvCountry.setHasFixedSize(true)

        adapter = CountryAdapter(
            this,
            listCountries
        )
        countryBinding.rvCountry.adapter = adapter
    }

    private fun initSwipeRefresh() {
        countryBinding.swipeCountry.setOnRefreshListener {
            countryBinding.swipeCountry.isRefreshing = false
            vmCountry.getEachCountry()
        }
    }

    private fun showDataUI(it: CovidModel?) {
        countryBinding.swipeCountry.isRefreshing = false
        listCountries.clear()
        listCountries.addAll(it!!.countries)
        countryBinding.rvCountry.post {
            adapter.notifyDataSetChanged()
        }
    }

    private fun handlingError(it: Throwable?) {
        countryBinding.swipeCountry.isRefreshing = false
        Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
    }

}