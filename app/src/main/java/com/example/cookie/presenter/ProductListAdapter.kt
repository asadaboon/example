package com.example.cookie.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cookie.R
import com.example.cookie.data.model.Product
import com.example.cookie.databinding.ItemRecyclerviewBinding
import com.squareup.picasso.Picasso

class ProductListAdapter(
    private val item: List<Product>,
    private val itemClick: OnItemClickListener
) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item[position])
        holder.itemView.setOnClickListener {
            itemClick.onItemClick(position, item)
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRecyclerviewBinding.bind(itemView)
        fun bind(item: Product) {
            with(binding) {
                itemTitleTextView.text = item.title
                itemPriceTextView.text = item.price.toString()

                if (item.isNewProduct)
                    isNewProductTextView.visibility = VISIBLE
                else
                    isNewProductTextView.visibility = GONE

                Picasso.get()
                    .load(item.image)
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView)

            }
        }
    }
}