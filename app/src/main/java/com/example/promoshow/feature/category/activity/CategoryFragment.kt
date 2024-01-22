package com.example.promoshow.feature.category.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api.product.model.Product
import com.example.promoshow.databinding.FragmentCategoryBinding
import com.example.promoshow.feature.deals.previews.DealsViewModel
import com.example.promoshow.feature.stores.details.StoresDealsAdapter
import com.example.promoshow.model.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    val args by navArgs<CategoryFragmentArgs>()
    private var _binding: FragmentCategoryBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var storesDealsAdapter: StoresDealsAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dealsViewModel: DealsViewModel by viewModels({ requireActivity() })

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val selectedCategory = args.categoryObject

        binding.headerSubtitle.text = selectedCategory.name
        binding.categoryImage.background = ContextCompat.getDrawable(requireActivity(), args.categoryObject.icon)

        dealsViewModel.products.observe(viewLifecycleOwner) { it ->

            recyclerView = binding.categoryItemsRc
            recyclerView.layoutManager =
                GridLayoutManager(activity, 3,  GridLayoutManager.VERTICAL, false)

            val categoryItems = it.filter { if(selectedCategory != Category.All)
                it.category == args.categoryObject.name else true  }
            storesDealsAdapter = StoresDealsAdapter(categoryItems, ::handleOfferClick)
            recyclerView.adapter = storesDealsAdapter

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleOfferClick(deal: Product) {
        findNavController().navigate(
            CategoryFragmentDirections.actionSearchDealsFragmentToDealsDetailsActivity(deal)
        )
    }
}