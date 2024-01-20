package com.example.promoshow.feature.stores
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.promoshow.R

class StoresListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var storesAdapter: StoresListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stores_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        val stores = listOf("Empik", "KFC", "MediaMarket", "Sephora", "Rituals", "xKom", "McDonald's", "Starbucks", "Lego")

        storesAdapter = StoresListAdapter(stores, ::handleOfferClick)
        recyclerView.adapter = storesAdapter

        return view
    }

    private fun handleOfferClick(int: Int) {
        findNavController().navigate(StoresListFragmentDirections.actionStoresListFragmentToStoresDetailsActivity(int))
    }
}