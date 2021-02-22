package com.example.cookie.presenter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cookie.R
import com.example.cookie.data.model.Product
import com.example.cookie.databinding.FragmentTitleBinding
import com.example.cookie.presenter.OnItemClickListener
import com.example.cookie.presenter.ProductListAdapter
import com.example.cookie.presenter.viewmodel.ProductViewModel

class ProductListFragment : Fragment(), OnItemClickListener {

    private lateinit var viewModel: ProductViewModel
    private lateinit var productListAdapter: ProductListAdapter
    private var _binding: FragmentTitleBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTitleBinding.inflate(inflater, container, false)
        initViewModel()
        return binding?.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        viewModel.getGetProductList()

        binding?.let { binding ->
            binding.recyclerview.layoutManager =
                GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            binding.recyclerview.hasFixedSize()

            viewModel.onGetProductList.observe(viewLifecycleOwner, Observer { it ->
                productListAdapter = ProductListAdapter(it, this)
                binding.recyclerview.adapter = productListAdapter
            })
        }

        viewModel.showProductListProgress.observe(viewLifecycleOwner, Observer {
            binding?.apply {
                if (it) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    override fun onItemClick(position: Int, list: List<Product>) {
        val bundle = bundleOf(
            "productId" to list[position].id,
        )
        view?.findNavController()?.navigate(R.id.action_titleFragment_to_gameFragment, bundle)
    }
}