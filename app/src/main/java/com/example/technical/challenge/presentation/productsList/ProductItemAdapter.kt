package com.example.technical.challenge.presentation.productsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technical.challenge.data.network.response.productlist.SearchResults
import com.example.technical.challenge.databinding.AdapterProductItemRowBinding

class ProductItemAdapter() : RecyclerView.Adapter<ProductItemAdapter.ProductItemViewHolder>() {

    var productsList = emptyList<SearchResults>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val binding = AdapterProductItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        with(holder){
            with(productsList[position]){
                binding.txtTitle.text = this.title
                binding.txtMaker.text = this.make
                binding.txtName.text = this.name
                binding.txtModel.text = this.model
                binding.txtYear.text = this.year
                binding.txtPrice.text = this.price
            }
        }
    }

    override fun getItemCount(): Int = productsList.size

    inner class ProductItemViewHolder(val binding: AdapterProductItemRowBinding) : RecyclerView.ViewHolder(binding.root)

}