package com.example.promoshow.feature.stores.previews
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.api.shop.model.Shop
import com.example.promoshow.R

class StoresPreviewAdapter(private val shops: List<Shop>, private val offerClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<StoresPreviewAdapter.StoresViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoresViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stores_preview, parent, false)
        return StoresViewHolder(view, offerClickListener)
    }

    override fun onBindViewHolder(holder: StoresViewHolder, position: Int) {
        holder.bind(shops[position])
    }

    override fun getItemCount(): Int {
        return shops.size
    }

    class StoresViewHolder(itemView: View, private val offerClickListener: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.storesTitleTextView)

        fun bind(shop: Shop) {
            title.text = shop.name
            itemView.setOnClickListener { offerClickListener(adapterPosition)}
        }
    }
}