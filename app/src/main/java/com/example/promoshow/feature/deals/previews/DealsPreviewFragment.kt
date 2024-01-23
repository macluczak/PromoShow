package com.example.promoshow.feature.deals.previews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api.product.model.Product
import com.example.promoshow.databinding.FragmentDealsPreviewBinding
import com.example.promoshow.model.Category
import com.example.promoshow.ui.dashboard.DashboardFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealsPreviewFragment : Fragment() {


    private var _binding: FragmentDealsPreviewBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var dealsAdapter: DealsPreviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dealsViewModel: DealsViewModel by viewModels({ requireActivity() })

        _binding = FragmentDealsPreviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.dealViewAll.setOnClickListener { handleViewAll() }

        dealsViewModel.products.observe(viewLifecycleOwner) { deals ->

            recyclerView = binding.recyclerView
            recyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

            dealsAdapter = DealsPreviewAdapter(
                deals.filter { it.discountPrice != null }
                    .sortedByDescending { it.price - it.discountPrice!! }
                    .take(5),
                ::handleOfferClick)
            recyclerView.adapter = dealsAdapter
        }
        return root
    }

    private fun handleOfferClick(deal: Product) =
        findNavController().navigate(
            DashboardFragmentDirections.actionDealsPreviewFragmentToDealsDetailsActivity(
                deal
            )
        )

    private fun handleViewAll() =
        findNavController().navigate(
            DashboardFragmentDirections.actionDealsPreviewFragmentToCategoryActivity(
                Category.All
            )
        )
}