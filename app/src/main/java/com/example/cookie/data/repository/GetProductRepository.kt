package com.example.cookie.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cookie.data.`interface`.BASE_URL
import com.example.cookie.data.`interface`.GetCookiesInterface
import com.example.cookie.data.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetProductRepository {

    val showProductListProgress = MutableLiveData<Boolean>()
    val onGetCookiesList = MutableLiveData<List<Product>>()

    fun getGetProduct() {
        showProductListProgress.value = true
        val retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(GetCookiesInterface::class.java)
        service.getCookiesItemList().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                showProductListProgress.value  = false
                onGetCookiesList.value = response.body()
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                showProductListProgress.value = false
                Log.d("GetProductRepository", "onFailure : ${t.message}")
            }
        })
    }
}