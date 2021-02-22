package com.example.cookie.presenter

import com.example.cookie.data.model.Product


interface OnItemClickListener {
    fun onItemClick(position: Int,list: List<Product>)
}