package com.example.kotlintest_lib.presentation.components

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kotlintest_lib.R
import com.example.kotlintest_lib.databinding.ItemCardProductBinding
import com.example.kotlintest_lib.domain.model.Product

class ProductAdapter(private val items: MutableList<Product>) :
    RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemCardProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = items[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateData(newItems: List<Product>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}

class ProductViewHolder(private val binding: ItemCardProductBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val titleTextView: TextView = binding.titleText
    private val priceTextView: ImageView = binding.imageView
    private val descriptionTextView: TextView = binding.descriptionText


    fun bind(product: Product) {
        titleTextView.text = product.title
        priceTextView.load(product.images[0]) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_background)
        }
        descriptionTextView.text = product.description
    }
}