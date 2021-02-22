package com.example.cookie.data.model


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("content")
    val content: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("isNewProduct")
    val isNewProduct: Boolean,
    @SerializedName("price")
    val price: Double,
    @SerializedName("title")
    val title: String
)