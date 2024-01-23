package com.example.promoshow.feature.stores.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api.product.model.Product
import com.example.promoshow.R
import com.example.promoshow.databinding.FragmentStoreDetailsBinding
import com.example.promoshow.util.loadImageWithGlide

class StoreDetailsFragment : Fragment() {

    val args by navArgs<StoreDetailsFragmentArgs>()
    private var _binding: FragmentStoreDetailsBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var storesDealsAdapter: StoresDealsAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoreDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.store_items_rc)
        recyclerView.layoutManager =
            GridLayoutManager(activity, 2,  GridLayoutManager.VERTICAL, false)

        storesDealsAdapter = StoresDealsAdapter(args.shopObject.products, ::handleOfferClick)
        recyclerView.adapter = storesDealsAdapter

        binding.storeName.text = args.shopObject.name
        binding.storeDescription.text = args.shopObject.description

        loadImageWithGlide(binding.storeLogo, args.shopObject.image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleOfferClick(deal: Product) {
        findNavController().navigate(StoreDetailsFragmentDirections.actionStoresDetailsFragmentToDealsDetailsActivity(deal))
    }
}