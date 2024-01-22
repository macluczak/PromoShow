package com.example.promoshow.feature.category.preview
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.promoshow.databinding.FragmentCategoryPreviewBinding
import com.example.promoshow.model.Category
import com.example.promoshow.ui.dashboard.DashboardFragmentDirections

class CategoryPreviewFragment : Fragment() {

    private var _binding: FragmentCategoryPreviewBinding? = null
    private val binding get() = _binding!!

     private lateinit var recyclerView: RecyclerView
     private lateinit var categoryAdapter: CategoryPreviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCategoryPreviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.categoryViewAll.setOnClickListener {
            handleViewAllClick(Category.All)
        }

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(activity, 2,  GridLayoutManager.HORIZONTAL, false)

        val categories = Category.values().toList()
            .filter { it != Category.All }

        categoryAdapter = CategoryPreviewAdapter(categories, ::handleViewAllClick)
        recyclerView.adapter = categoryAdapter

        return root
    }
    private fun handleViewAllClick(category: Category) {
        findNavController().navigate(DashboardFragmentDirections.actionDealsPreviewFragmentToCategoryActivity(category))
    }
}