package com.example.technical.challenge.presentation.productsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.technical.challenge.R
import com.example.technical.challenge.data.network.response.productlist.SearchResults
import com.example.technical.challenge.databinding.AdapterProductItemRowBinding
import com.example.technical.challenge.utils.OnItemClickListener
import com.example.technical.pinglib.PingStream
import com.example.technical.pinglib.ResponseWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class ProductItemAdapter(private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ProductItemAdapter.ProductItemViewHolder>() {

    var productsList = emptyList<SearchResults>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val binding = AdapterProductItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProductItemViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if(payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else{
            setViewHolderData(holder, position, false)
        }
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        setViewHolderData(holder, position, true)
    }

    private fun setViewHolderData(holder: ProductItemViewHolder, position: Int, doCaching: Boolean){
        with(holder){
            binding.btnRefreshLatency.tag = productsList[position]
            binding.btnRefreshLatency.setOnClickListener {
                onItemClickListener.onItemClickListener(binding.btnRefreshLatency, position)
            }
            with(productsList[position]){
                binding.txtName.text = this.name
                Glide.with(binding.root.context).load(this.icon).placeholder(
                    R.drawable.ic_launcher_foreground).into(binding.imgCompany)

                CoroutineScope(Main).launch {
                    when(val latency = productsList[position].url?.let {
                        PingStream.Builder(binding.root.context, it).pings(5).doCaching(doCaching)
                            .port(80).build().getHostHealth()
                    }){
                        is ResponseWrapper.Success ->{
                            binding.txtTitle.text = "Latency = ${latency.value.avgLatency} Milli Sec"
                        }
                        is ResponseWrapper.NetworkError ->{
                            binding.txtTitle.text = binding.root.context.getText(com.example.technical.challenge.R.string.error_no_internet)
                        }
                        is ResponseWrapper.GenericError ->{
                            binding.txtTitle.text = latency.error
                        }
                    }

                }
            }
        }
    }

    override fun getItemCount(): Int = productsList.size

    inner class ProductItemViewHolder(val binding: AdapterProductItemRowBinding) : RecyclerView.ViewHolder(binding.root)

}