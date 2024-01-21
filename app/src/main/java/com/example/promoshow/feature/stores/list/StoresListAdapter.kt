package com.example.promoshow.feature.stores.list
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.api.shop.model.Shop
import com.example.promoshow.R

class StoresListAdapter(private val deals: List<Shop>,  private val offerClickListener: (Shop) -> Unit) :
    RecyclerView.Adapter<StoresListAdapter.StoresViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoresViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stores_list, parent, false)
        return StoresViewHolder(view, offerClickListener)
    }

    override fun onBindViewHolder(holder: StoresViewHolder, position: Int) {
        holder.bind(deals[position])
    }

    override fun getItemCount(): Int {
        return deals.size
    }

    class StoresViewHolder(itemView: View,  private val offerClickListener: (Shop) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.storesTitleTextView)

        fun bind(shop: Shop) {
            title.text = shop.name
            itemView.setOnClickListener { offerClickListener(shop)}
        }
    }
}