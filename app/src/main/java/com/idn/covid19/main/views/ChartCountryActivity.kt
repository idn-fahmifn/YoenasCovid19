//package com.idn.covid19
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.content.SharedPreferences
//import android.graphics.Color
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Toast
//import com.bumptech.glide.Glide
//import com.github.mikephil.charting.components.XAxis
//import com.github.mikephil.charting.data.BarData
//import com.github.mikephil.charting.data.BarDataSet
//import com.github.mikephil.charting.data.BarEntry
//import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
//import com.idn.covid19.main.models.InfoCountry
//import com.idn.covid19.networks.Network
//import kotlinx.android.synthetic.main.activity_chart_country.*
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import java.text.DecimalFormat
//import java.text.NumberFormat
//import java.text.SimpleDateFormat
//import java.util.*
//import kotlin.collections.ArrayList
//
//class ChartCountryActivity : AppCompatActivity() {
//
//    companion object {
//        const val EXTRA_COUNTRY = "extra_country"
//        const val EXTRA_LATESTUPDATE = "latest_update"
//        const val EXTRA_NEWDEATH = "extra_newdeath"
//        const val EXTRA_NEWCONFIRMED = "extra_newconfirmed"
//        const val EXTRA_NEWRECOVERED = "extra_newrecovered"
//        const val EXTRA_TOTALDEATH = "extra_totaldeath"
//        const val EXTRA_TOTALCONFIRMED = "extra_totalconfirmed"
//        const val EXTRA_TOTALRECOVERED = "extra_totalrecovered"
//        const val EXTRA_COUNTRYID = "extra_countryid"
//        lateinit var simpanDataNegara: String
//        lateinit var simpanDataFlag: String
//    }
//
//    private val sharedPrefFile = "kotlinsharedpreference"
//    private lateinit var sharedPreferences: SharedPreferences
//    private var dayCases = ArrayList<String>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_chart_country)
//
//        sharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
//
//        val formatter: NumberFormat = DecimalFormat("#,###")
//        val intentNamaNegara: String? = intent.getStringExtra(EXTRA_COUNTRY)
//        val lastUdate: String? = intent.getStringExtra(EXTRA_LATESTUPDATE)
//        val newDeath: String? = intent.getStringExtra(EXTRA_NEWDEATH)
//        val totalDeath: String? = intent.getStringExtra(EXTRA_TOTALDEATH)
//        val newConfirmed: String? = intent.getStringExtra(EXTRA_NEWCONFIRMED)
//        val totalConfirmed: String? = intent.getStringExtra(EXTRA_TOTALCONFIRMED)
//        val newRecovery: String? = intent.getStringExtra(EXTRA_NEWRECOVERED)
//        val totalRecovery: String? = intent.getStringExtra(EXTRA_TOTALRECOVERED)
//        val idCountry: String? = intent.getStringExtra(EXTRA_COUNTRYID)
//        val editor: SharedPreferences.Editor = sharedPreferences.edit()
//
//        txt_country_chart.text = intentNamaNegara.toString()
//        txt_current.text = lastUdate.toString()
//        txt_total_deaths_current.text = formatter.format(totalDeath.toString().toDouble())
//        txt_new_deaths_current.text = formatter.format(newDeath.toString().toDouble())
//        txt_total_confirmed_current.text = formatter.format(totalConfirmed.toString().toDouble())
//        txt_new_confirmed_current.text = formatter.format(newConfirmed.toString().toDouble())
//        txt_total_recovered_current.text = formatter.format(totalRecovery.toString().toDouble())
//        txt_new_recovered_current.text = formatter.format(newRecovery.toString().toDouble())
//        editor.putString(intentNamaNegara, intentNamaNegara)
//        editor.apply()
//        editor.commit()
//
//        val simpanNegara = sharedPreferences.getString(intentNamaNegara, intentNamaNegara)
//        val simpanFlag = sharedPreferences.getString(idCountry, idCountry)
//        simpanDataNegara = simpanNegara.toString()
//        simpanDataFlag = simpanFlag.toString() + "/flat/64.png"
//
//        if (simpanFlag != null) {
//            Glide.with(this).load("https://www.countryflags.io/$simpanDataFlag")
//                .into(img_flag_chart)
//        } else {
//            Toast.makeText(this, "Image Not Found", Toast.LENGTH_SHORT).show()
//        }
//
//        getInfoCountry()
//
//    }
//
//    private fun getInfoCountry() {
//        Network().getInfoCountry().getInfoService(simpanDataNegara).enqueue(object :
//            Callback<List<InfoCountry>> {
//            override fun onFailure(call: Call<List<InfoCountry>>, t: Throwable) {
//                Toast.makeText(
//                    this@ChartCountryActivity,
//                    "error, please re-enter to this Country",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//
//            @SuppressLint("SimpleDateFormat")
//            override fun onResponse(
//                call: Call<List<InfoCountry>>,
//                response: Response<List<InfoCountry>>
//            ) {
//                val getListDataCorona: List<InfoCountry> = response.body()!!
//                if (response.isSuccessful) {
//                    val barEntries: ArrayList<BarEntry> = ArrayList()
//                    val barEntries2: ArrayList<BarEntry> = ArrayList()
//                    val barEntries3: ArrayList<BarEntry> = ArrayList()
//                    val barEntries4: ArrayList<BarEntry> = ArrayList()
//                    var i = 0
//
//                    while (i < getListDataCorona.size) {
//                        for (s in getListDataCorona) {
//                            val barEntry = BarEntry(i.toFloat(), s.Confirmed.toFloat())
//                            val barEntry2 = BarEntry(i.toFloat(), s.Deaths.toFloat())
//                            val barEntry3 = BarEntry(i.toFloat(), s.Recovered.toFloat())
//                            val barEntry4 = BarEntry(i.toFloat(), s.Active.toFloat())
//
//                            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SS'Z'")
//                            val outputFormat = SimpleDateFormat("dd-MM-yyyy")
//                            val date: Date? = inputFormat.parse(s.Date)
//                            val formattedDate: String = outputFormat.format(date!!)
//                            dayCases.add(formattedDate)
//
//                            barEntries.add(barEntry)
//                            barEntries2.add(barEntry2)
//                            barEntries3.add(barEntry3)
//                            barEntries4.add(barEntry4)
//
//                            i++
//                        }
//                    }
//
//                    val xAxis: XAxis = chart_view.xAxis
//                    xAxis.valueFormatter = IndexAxisValueFormatter(dayCases)
//                    chart_view.axisLeft.axisMinimum = 0f
//                    xAxis.position = XAxis.XAxisPosition.BOTTOM
//                    xAxis.granularity = 1f
//                    xAxis.setCenterAxisLabels(true)
//                    xAxis.isGranularityEnabled = true
//
//                    val barDataSet = BarDataSet(barEntries, "Confirmed")
//                    val barDataSet2 = BarDataSet(barEntries2, "Deaths")
//                    val barDataSet3 = BarDataSet(barEntries3, "Recovered")
//                    val barDataSet4 = BarDataSet(barEntries4, "Active")
//                    barDataSet.setColors(Color.parseColor("#F44336"))
//                    barDataSet2.setColors(Color.parseColor("#FFEB3B"))
//                    barDataSet3.setColors(Color.parseColor("#03DAC5"))
//                    barDataSet4.setColors(Color.parseColor("#2196F3"))
//
//                    val data = BarData(barDataSet, barDataSet2, barDataSet3, barDataSet4)
//                    chart_view.data = data
//
//                    val barSpace = 0.02f
//                    val groupSpace = 0.3f
//                    val groupCount = 4f
//
//                    data.barWidth = 0.15f
//                    chart_view.invalidate()
//                    chart_view.setNoDataTextColor(android.R.color.black)
//                    chart_view.setTouchEnabled(true)
//                    chart_view.description.isEnabled = false
//                    chart_view.xAxis.axisMinimum = 0f
//                    chart_view.setVisibleXRangeMaximum(
//                        0f + chart_view.barData.getGroupWidth(
//                            groupSpace,
//                            barSpace
//                        ) * groupCount
//                    )
//                    chart_view.groupBars(0f, groupSpace, barSpace)
//                }
//            }
//        })
//    }
//}