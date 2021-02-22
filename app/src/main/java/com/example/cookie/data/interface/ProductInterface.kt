package com.example.cookie.data.`interface`

import com.example.cookie.data.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://ecommerce-product-app.herokuapp.com/"

interface GetCookiesInterface {

    @GET("products")
    fun getCookiesItemList(): Call<List<Product>>

    @GET("products/{productid}")
    fun getCookiesDetail(@Path("productid") productId: Int): Call<Product>
}