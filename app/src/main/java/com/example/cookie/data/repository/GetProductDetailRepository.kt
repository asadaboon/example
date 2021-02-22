package com.example.cookie.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookie.data.`interface`.BASE_URL
import com.example.cookie.data.`interface`.GetCookiesInterface
import com.example.cookie.data.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetProductDetailRepository : ViewModel() {

    val showProgress = MutableLiveData<Boolean>()
    val onGetCookiesDetail = MutableLiveData<Product>()

    fun getProductDetail(productId: Int) {
        showProgress.value = true
        val retrofit = Retrofit
            .Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GetCookiesInterface::class.java)
        service.getCookiesDetail(productId).enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                showProgress.value = false
                onGetCookiesDetail.value = response.body()
                Log.d("GetProductDetailRepository", "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                showProgress.value = false
                Log.d("GetProductDetailRepository", "onFailure: ${t.message}")
            }
        })
    }
}