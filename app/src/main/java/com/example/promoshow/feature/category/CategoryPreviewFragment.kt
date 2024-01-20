package com.example.promoshow.feature.category
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.promoshow.R
import com.example.promoshow.model.Category

class CategoryPreviewFragment : Fragment() {

     private lateinit var recyclerView: RecyclerView
     private lateinit var categoryAdapter: CategoryPreviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category_preview, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(activity, 2,  GridLayoutManager.HORIZONTAL, false)

        val categories = Category.values().toList()
//            listOf("Food", "Electronics", "Accessories", "Clothing", "Books", "Beauty", "Home", "Sports", "Health", "Kids")


        categoryAdapter = CategoryPreviewAdapter(categories)
        recyclerView.adapter = categoryAdapter

        return view
    }
}