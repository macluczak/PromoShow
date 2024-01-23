package com.example.promoshow.feature.stores.previews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api.shop.model.Shop
import com.example.promoshow.databinding.FragmentStoresPreviewBinding
import com.example.promoshow.feature.stores.viewmodel.StoresViewModel
import com.example.promoshow.ui.dashboard.DashboardFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoresPreviewFragment : Fragment() {

    private var _binding: FragmentStoresPreviewBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var storesAdapter: StoresPreviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val storesViewModel: StoresViewModel by viewModels({ requireActivity() })

        _binding = FragmentStoresPreviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        storesViewModel.shops.observe(viewLifecycleOwner) { stores ->

            recyclerView = binding.recyclerView
            recyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

            storesAdapter = StoresPreviewAdapter(
                stores.sortedByDescending { it.products.count() }
                    .take(5),
                ::handleOfferClick
            )
            recyclerView.adapter = storesAdapter
        }

        return root
    }
    private fun handleOfferClick(shop: Shop) {
        findNavController().navigate(
            DashboardFragmentDirections.actionStoresPreviewFragmentToStoresDetailsActivity(
                shop
            )
        )
    }
}