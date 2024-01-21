package com.example.promoshow.feature.stores.previews
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.promoshow.databinding.FragmentStoresPreviewBinding
import com.example.promoshow.feature.deals.previews.StoresPreviewViewModel
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
        val storesViewModel =
            ViewModelProvider(this)[StoresPreviewViewModel::class.java]

        _binding = FragmentStoresPreviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        storesViewModel.shops.observe(viewLifecycleOwner) {
            Log.d("API_CALL", "LIST_OF_SHOPS: $it")

            recyclerView = binding.recyclerView
            recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)


            storesAdapter = StoresPreviewAdapter(it, ::handleOfferClick)
            recyclerView.adapter = storesAdapter

        }

        return root
    }
    private fun handleOfferClick(int: Int) {
        findNavController().navigate(DashboardFragmentDirections.actionStoresPreviewFragmentToStoresDetailsActivity(int))
    }
}