package com.example.promoshow.feature.deals
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.promoshow.R
import com.example.promoshow.feature.deals.details.DealsDetailsActivity

class DealsPreviewFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dealsAdapter: DealsPreviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_deals_preview, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        val deals = listOf("Iphone 12", "Air Jordan's", "Galaxy 22", "BigMac", "Eyeliner")

        dealsAdapter = DealsPreviewAdapter(deals, ::handleOfferClick)
        recyclerView.adapter = dealsAdapter

        return view
    }

    private fun handleOfferClick(int: Int) {
        Log.d("TEST_DEAL", "product: $int")
        val intent = Intent(requireContext(), DealsDetailsActivity::class.java)
        intent.putExtra("selectedProduct", int)
        startActivity(intent)
//        val bundle = Bundle()
//        bundle.putInt("selectedProduct", int)
//        findNavController().navigate(R.id.action_dealsPreviewFragment_to_dealsDetailsFragment, bundle)
    }
}