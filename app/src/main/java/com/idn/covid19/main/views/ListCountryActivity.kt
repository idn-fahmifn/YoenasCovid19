package com.idn.covid19.main.views

import android.os.Bundle
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
import kotlin.math.abs

class ListCountryActivity : AppCompatActivity() {
    private lateinit var countryBinding: ActivityListNegaraBinding
    private lateinit var vmCountry: CountryViewModel

    private lateinit var adapter: CountryAdapter
    private var listCountries: MutableList<CountriesItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initToolbar()

        initRecyclerView()
        initSwipeRefresh()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar_list_country)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        toolbar_list_country.setNavigationIcon(R.drawable.ic_arrow_left)

        var isShow = true
        var scrollRange = -1
        appbar_list_country.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->

            // fade out content heading when scroll up
            container_head_title.translationY =
                -verticalOffset.toFloat() // Un-slide the image or container from views

            val percent =
                (abs(verticalOffset)).toFloat() / appBarLayout?.totalScrollRange!! // 0F to 1F

            container_head_title.alpha = 1F - percent


            container_head_title.scaleY = (1F - percent) + percent / 1.199F
            container_head_title.scaleX = (1F - percent) + percent / 1.199F

            // show text toolbar
            if (scrollRange == -1) {
                scrollRange = appBarLayout.totalScrollRange
            }
            if (scrollRange + verticalOffset == 0) {
                collapsing_list_country.title = getString(R.string.title_list_country)

                isShow = true
            } else if (isShow) {
                collapsing_list_country.title = " "
                isShow = false
            }
        })
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

        adapter = CountryAdapter(this, listCountries) {
            // TODO: 30/10/20 send id to detail page
            Toast.makeText(this, it.countryCode, Toast.LENGTH_LONG).show()
        }
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