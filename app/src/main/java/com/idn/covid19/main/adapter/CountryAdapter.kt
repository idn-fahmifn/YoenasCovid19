package com.idn.covid19.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.idn.covid19.R
import com.idn.covid19.databinding.ListCountry2Binding
import com.idn.covid19.main.models.CountriesItem
import com.idn.covid19.main.viewmodels.ItemCountryViewModel

class CountryAdapter(
    private val context: Context,
    private val listCountry: MutableList<CountriesItem>
) : Adapter<CountryAdapter.CountryViewHolder>() {

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
        holder.bindBinding(context, listCountry[fixPosition])
    }

    override fun getItemCount(): Int {
        return listCountry.size
    }

    class CountryViewHolder(val binding: ListCountry2Binding)
        : RecyclerView.ViewHolder(binding.root){
        private lateinit var viewModel: ItemCountryViewModel

        fun bindBinding(context: Context, model: CountriesItem) {
            viewModel = ItemCountryViewModel(
                context,
                model,
                binding
            )
            binding.itemCountry = viewModel
            binding.executePendingBindings()
        }
    }
}