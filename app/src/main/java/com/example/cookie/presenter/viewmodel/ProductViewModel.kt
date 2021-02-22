package com.example.cookie.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cookie.data.model.Product
import com.example.cookie.data.repository.GetProductDetailRepository
import com.example.cookie.data.repository.GetProductRepository

class ProductViewModel : ViewModel() {

    private val getProduct = GetProductRepository()
    private val getProductDetailRepository = GetProductDetailRepository()

    val showProductListProgress: LiveData<Boolean>
    val showProgress: LiveData<Boolean>
    val onGetProductList: LiveData<List<Product>>
    val onGetProductDetail: LiveData<Product>

    init {
        this.onGetProductList = getProduct.onGetCookiesList
        this.showProductListProgress = getProduct.showProductListProgress
        this.onGetProductDetail = getProductDetailRepository.onGetCookiesDetail
        this.showProgress = getProductDetailRepository.showProgress

    }

    fun getGetProductList() {
        getProduct.getGetProduct()
    }

    fun getCookiesDetail(productId: Int) {
        getProductDetailRepository.getProductDetail(productId)
    }
}