package com.example.promoshow.feature.stores
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.promoshow.R

class StoresListAdapter(private val deals: List<String>,  private val offerClickListener: (Int) -> Unit) :
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

    class StoresViewHolder(itemView: View,  private val offerClickListener: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val categoryTextView: TextView = itemView.findViewById(R.id.storesTitleTextView)

        fun bind(category: String) {
            categoryTextView.text = category
            itemView.setOnClickListener { offerClickListener(adapterPosition)}
        }
    }
}