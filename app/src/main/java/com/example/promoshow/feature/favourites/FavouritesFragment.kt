package com.example.promoshow.feature.favourites
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api.product.model.Product
import com.example.promoshow.databinding.FragmentFavouritesBinding
import com.example.promoshow.feature.deals.previews.DealsViewModel
import com.example.promoshow.feature.stores.details.StoresDealsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var storesDealsAdapter: StoresDealsAdapter

    private val dealsViewModel: DealsViewModel by viewModels({ requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onResume() {
        super.onResume()
        val favourites = dealsViewModel.getFavourites()

        dealsViewModel.products.observe(viewLifecycleOwner) { product ->

            recyclerView = binding.recyclerView
            recyclerView.layoutManager =
                GridLayoutManager(activity, 3,  GridLayoutManager.VERTICAL, false)

            val deals = product.filter { favourites.contains(it.id.toString())}
            storesDealsAdapter = StoresDealsAdapter(deals, ::handleOfferClick)
            recyclerView.adapter = storesDealsAdapter

        }
    }

    private fun handleOfferClick(deal: Product) {
        findNavController().navigate(
            FavouritesFragmentDirections.actionFavouritesFragmentToDealsDetailsActivity(deal)
        )
    }

}