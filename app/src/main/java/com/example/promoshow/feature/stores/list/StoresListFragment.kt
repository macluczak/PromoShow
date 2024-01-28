package com.example.promoshow.feature.stores.list
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
import com.example.promoshow.databinding.FragmentStoresListBinding
import com.example.promoshow.feature.stores.viewmodel.StoresViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoresListFragment : Fragment() {

    private var _binding: FragmentStoresListBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var storesAdapter: StoresListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val storesViewModel: StoresViewModel by viewModels({ requireActivity() })

        _binding = FragmentStoresListBinding.inflate(inflater, container, false)
        val root: View = binding.root


        storesViewModel.shops.observe(viewLifecycleOwner) { shops ->

            recyclerView = binding.recyclerView
            recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

            storesAdapter = StoresListAdapter(shops.sortedBy { it.name }, ::handleOfferClick)
            recyclerView.adapter = storesAdapter
        }

        return root
    }

    private fun handleOfferClick(shop: Shop) {
        findNavController().navigate(
            StoresListFragmentDirections.actionStoresListFragmentToStoresDetailsActivity(
                shop
            )
        )
    }
}