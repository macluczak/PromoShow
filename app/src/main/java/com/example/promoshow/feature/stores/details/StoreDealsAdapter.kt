package com.example.promoshow.feature.stores.details
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.api.product.model.Product
import com.example.promoshow.R
import kotlin.reflect.KFunction1

class StoresDealsAdapter(private val deals: List<Product>, private val offerClickListener: KFunction1<Product, Unit>) :
    RecyclerView.Adapter<StoresDealsAdapter.StoresDealsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoresDealsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_store_deals, parent, false)
        return StoresDealsViewHolder(view, offerClickListener)
    }

    override fun onBindViewHolder(holder: StoresDealsViewHolder, position: Int) {
        holder.bind(deals[position])
    }

    override fun getItemCount(): Int {
        return deals.size
    }

    class StoresDealsViewHolder(itemView: View,  private val offerClickListener: KFunction1<Product, Unit>) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.storesTitleTextView)

        fun bind(product: Product) {
            title.text = product.name
            itemView.setOnClickListener { offerClickListener(product)}
        }
    }
}