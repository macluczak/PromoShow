package com.example.promoshow.feature.deals.previews
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.api.product.model.Product
import com.example.promoshow.R
import com.example.promoshow.util.loadImageWithGlide
import com.example.promoshow.util.toPLN

class DealsPreviewAdapter(private val deals: List<Product>, private val offerClickListener: (Product) -> Unit,) :
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

    class DealsViewHolder(itemView: View, private val offerClickListener: (Product) -> Unit,) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.productNameTextView)
        private val maker: TextView = itemView.findViewById(R.id.makerNameTextView)
        private val discount: TextView = itemView.findViewById(R.id.promoPriceTextView)
        private val price: TextView = itemView.findViewById(R.id.regularPriceTextView)
        private  val image: ImageView = itemView.findViewById(R.id.productImageView)

        fun bind(product: Product) {
            title.text = product.name
            discount.text = product.discountPrice?.toPLN()
            maker.text = product.maker
            price.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                text = product.price.toPLN()
            }
            loadImageWithGlide(image, product.image)
            itemView.setOnClickListener { offerClickListener(product)}
        }
    }
}