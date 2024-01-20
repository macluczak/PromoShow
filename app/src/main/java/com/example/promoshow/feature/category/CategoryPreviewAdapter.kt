package com.example.promoshow.feature.category
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.promoshow.R
import com.example.promoshow.model.Category
import org.w3c.dom.Text

class CategoryPreviewAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<CategoryPreviewAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_preview, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryTextView: TextView = itemView.findViewById(R.id.categoryTitleTextView)
        private val categoryIcon: ImageView = itemView.findViewById(R.id.category_icon)

        fun bind(category: Category) {
            categoryTextView.text = category.name
            categoryIcon.background = ContextCompat.getDrawable(itemView.context, category.icon)

        }
    }
}