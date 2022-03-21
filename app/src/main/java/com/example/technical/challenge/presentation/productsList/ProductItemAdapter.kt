package com.example.technical.challenge.presentation.productsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.technical.challenge.R
import com.example.technical.challenge.data.network.response.productlist.SearchResults

class ProductItemAdapter() : RecyclerView.Adapter<ProductItemAdapter.ProductItemViewHolder>() {

    var productsList = emptyList<SearchResults>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_product_item_row, parent, false)
        return ProductItemViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val product = productsList[position]
        holder.txtTitle.text = product.title
        holder.txtMaker.text = product.make
        holder.txtName.text = product.name
        holder.txtModel.text = product.model
        holder.txtYear.text = product.year
        holder.txtPrice.text = product.price
    }

    override fun getItemCount(): Int = productsList.size

    inner class ProductItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtTitle: AppCompatTextView = itemView.findViewById(R.id.txt_title)
        val txtName: AppCompatTextView = itemView.findViewById(R.id.txt_name)
        val txtMaker: AppCompatTextView = itemView.findViewById(R.id.txt_maker)
        val txtModel: AppCompatTextView = itemView.findViewById(R.id.txt_model)
        val txtYear: AppCompatTextView = itemView.findViewById(R.id.txt_year)
        val txtPrice: AppCompatTextView = itemView.findViewById(R.id.txt_price)
    }

}