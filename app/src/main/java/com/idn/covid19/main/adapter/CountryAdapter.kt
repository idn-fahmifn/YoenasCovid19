package com.idn.covid19.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.idn.covid19.R
import com.idn.covid19.databinding.ListCountry2Binding
import com.idn.covid19.main.models.CountriesItem
import com.idn.covid19.main.viewmodels.ItemCountryViewModel
import java.util.*
import kotlin.collections.ArrayList

class CountryAdapter(
    private val context: Context,
    private val listCountry: MutableList<CountriesItem>,
    private val listener: (CountriesItem) -> Unit
) : Adapter<CountryAdapter.CountryViewHolder>(), Filterable {

    private var countryFilterList = ArrayList<CountriesItem>()

    init {
        countryFilterList = listCountry as ArrayList<CountriesItem>
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        val binding: ListCountry2Binding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.list_country2,
                parent,
                false
            )
        return CountryViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val fixPosition = holder.adapterPosition
        holder.bindBinding(context, countryFilterList[fixPosition], listener)
    }

    override fun getItemCount(): Int {
        return countryFilterList.size
    }

    class CountryViewHolder(val binding: ListCountry2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var viewModel: ItemCountryViewModel

        fun bindBinding(context: Context, model: CountriesItem, listener: (CountriesItem) -> Unit) {
            viewModel = ItemCountryViewModel(
                context,
                model,
                binding
            )
            binding.itemCountry = viewModel
            binding.cvListcountries.setOnClickListener { listener(model) }
            binding.executePendingBindings()
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                countryFilterList = if (charSearch.isEmpty()) {
                     listCountry as ArrayList<CountriesItem>
                } else {
                    val resultList = ArrayList<CountriesItem>()
                    for (row in listCountry) {
                        if (row.country!!.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as ArrayList<CountriesItem>
                notifyDataSetChanged()
            }

        }
    }
}