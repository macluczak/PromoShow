package com.example.promoshow.feature.deals
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.promoshow.R
import kotlin.reflect.KFunction0

class DealsPreviewAdapter(private val deals: List<String>, private val offerClickListener: (Int) -> Unit,) :
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
//        private val categoryTextView: TextView = itemView.findViewById(R.id.dealsTitleTextView)

        fun bind(category: String) {
//            categoryTextView.text = category
            itemView.setOnClickListener { offerClickListener(adapterPosition)}
        }

    }
}