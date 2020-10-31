package com.idn.covid19.main.views

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.idn.covid19.R
import com.idn.covid19.databinding.ActivityMain2Binding
import com.idn.covid19.main.models.CovidModel
import com.idn.covid19.main.viewmodels.WorldViewModel
import java.text.DecimalFormat
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {
    private lateinit var worldBinding: ActivityMain2Binding
    private lateinit var vmWorld: WorldViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
    }

    private fun initBinding() {
        worldBinding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
        vmWorld = ViewModelProviders.of(this).get(WorldViewModel::class.java)
        worldBinding.worldData = vmWorld

        vmWorld.getWorld()

        vmWorld.cekWorldResponse.observe(this, Observer {
            showDataUI(it)
        })

        vmWorld.error.observe(this, Observer {
            handlingError(it)
        })
    }

    private fun showDataUI(it: CovidModel?) {
        val formatter: NumberFormat = DecimalFormat("#,###")

        worldBinding.txtTotalConfirm.text = formatter.format(it?.global?.totalConfirmed?.toDouble())
        worldBinding.txtTotalRecovered.text = formatter.format(it?.global?.totalRecovered?.toDouble())
        worldBinding.txtTotalDeaths.text = formatter.format(it?.global?.totalDeaths?.toDouble())
    }

    private fun handlingError(it: Throwable?) {
        Log.d("debug ", "handlingError: " + it.toString())
        Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
    }

    fun moveToCountry(view: View){
        startActivity(Intent(this, ListCountryActivity::class.java))
    }
}