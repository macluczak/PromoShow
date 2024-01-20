package com.example.promoshow.feature.stores.details
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.promoshow.R

class StoresDealsAdapter(private val deals: List<String>,  private val offerClickListener: (Int) -> Unit) :
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

    class StoresDealsViewHolder(itemView: View,  private val offerClickListener: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val categoryTextView: TextView = itemView.findViewById(R.id.storesTitleTextView)

        fun bind(category: String) {
            categoryTextView.text = category
            itemView.setOnClickListener { offerClickListener(adapterPosition)}
        }
    }
}