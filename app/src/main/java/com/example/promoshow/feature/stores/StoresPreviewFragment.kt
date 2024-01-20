package com.example.promoshow.feature.stores
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.promoshow.R

class StoresPreviewFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var storesAdapter: StoresPreviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stores_preview, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        val stores = listOf("Empik", "KFC", "MediaMarket", "Sephora", "Rituals")

        storesAdapter = StoresPreviewAdapter(stores)
        recyclerView.adapter = storesAdapter

        return view
    }
}