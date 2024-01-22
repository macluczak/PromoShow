package com.example.promoshow.feature.category.preview
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.promoshow.R
import com.example.promoshow.model.Category
import kotlin.reflect.KFunction1

class CategoryPreviewAdapter(private val categories: List<Category>, private val clickListener: KFunction1<Category, Unit>) :
    RecyclerView.Adapter<CategoryPreviewAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_preview, parent, false)
        return CategoryViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class CategoryViewHolder(itemView: View, private val clickListener: KFunction1<Category, Unit>) : RecyclerView.ViewHolder(itemView) {
        private val categoryTextView: TextView = itemView.findViewById(R.id.categoryTitleTextView)
        private val categoryIcon: ImageView = itemView.findViewById(R.id.category_icon)
        private val tile: CardView = itemView.findViewById(R.id.category_cv)

        fun bind(category: Category) {
            categoryTextView.text = category.name
            categoryIcon.background = ContextCompat.getDrawable(itemView.context, category.icon)
            tile.setOnClickListener { clickListener(category)}
        }
    }
}