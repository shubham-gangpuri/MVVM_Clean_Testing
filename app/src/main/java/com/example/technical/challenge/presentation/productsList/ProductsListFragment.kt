package com.example.technical.challenge.presentation.productsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technical.challenge.R
import com.example.technical.challenge.databinding.FragmentProductListBinding
import com.example.technical.challenge.utils.closeKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsListFragment : Fragment() {

    lateinit var viewModel: ProductsFragmentViewModel
    private lateinit var binding: FragmentProductListBinding
    private val productItemAdapter by lazy {
        ProductItemAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ProductsFragmentViewModel::class.java]
        binding.viewModel = viewModel
        binding.item = viewModel.searchItems
        binding.rcyViewProductsList.layoutManager = LinearLayoutManager(requireContext())
        binding.rcyViewProductsList.adapter = productItemAdapter
        init()
    }

    private fun init() {
        viewModel.productListResponse.observe(viewLifecycleOwner) {
            closeKeyboard(binding.root)
            productItemAdapter.productsList = it?:emptyList()
            productItemAdapter.notifyDataSetChanged()
        }
    }

}