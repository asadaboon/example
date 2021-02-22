package com.example.cookie.presenter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cookie.R
import com.example.cookie.databinding.FragmentGameBinding
import com.example.cookie.presenter.viewmodel.ProductViewModel
import com.squareup.picasso.Picasso

class ProductDetailFragment : Fragment() {

    private lateinit var viewModel: ProductViewModel
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding
    private var productId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            productId = arguments?.getInt("productId", 0)
        }

        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productId?.let { viewModel.getCookiesDetail(it) }
        viewModel.onGetProductDetail.observe(viewLifecycleOwner, Observer { product ->

            binding?.apply {
                itemTitleDetailTextView.text = product.title
                itemProductDetailTextView.text = product.content
                itemPriceDetailTextView.text = product.price.toString()

                if (product.isNewProduct) {
                    isNewProductDetailTextView.visibility = View.VISIBLE
                } else {
                    isNewProductDetailTextView.visibility = View.GONE
                }

                Picasso.get()
                    .load(product.image)
                    .error(R.drawable.ic_launcher_background)
                    .into(productDetailImageView)
            }
        })

        viewModel.showProgress.observe(viewLifecycleOwner, Observer {
            binding?.apply {
                if (it) {
                    productDetailProgressBar.visibility = View.VISIBLE
                } else {
                    productDetailProgressBar.visibility = View.GONE
                }
            }
        })
    }
}