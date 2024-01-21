package com.example.promoshow.feature.deals.previews
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.api.product.model.Product
import com.example.promoshow.R

class DealsPreviewAdapter(private val deals: List<Product>, private val offerClickListener: (Int) -> Unit,) :
    RecyclerView.Adapter<DealsPreviewAdapter.DealsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_deals_preview, parent, false)
        return DealsViewHolder(view, offerClickListener)
    }

    override fun onBindViewHolder(holder: DealsViewHolder, position: Int) {
        holder.bind(deals[position])
    }

    override fun getItemCount(): Int {
        return deals.size
    }

    class DealsViewHolder(itemView: View, private val offerClickListener: (Int) -> Unit,) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.dealsTitleTextView)

        fun bind(deal: Product) {
            title.text = deal.name
            itemView.setOnClickListener { offerClickListener(adapterPosition)}
        }

    }
}