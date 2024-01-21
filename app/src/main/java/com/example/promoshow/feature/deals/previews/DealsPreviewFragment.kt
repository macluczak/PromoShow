package com.example.promoshow.feature.deals.previews
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
import com.example.promoshow.databinding.FragmentDealsPreviewBinding
import com.example.promoshow.ui.dashboard.DashboardFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealsPreviewFragment : Fragment() {


    private var _binding: FragmentDealsPreviewBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var dealsAdapter: DealsPreviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dealsViewModel =
            ViewModelProvider(this)[DealsPreviewViewModel::class.java]

        _binding = FragmentDealsPreviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dealsViewModel.products.observe(viewLifecycleOwner) {
            Log.d("API_CALL", "LIST_OF_PRODUCTS: $it")

            recyclerView = binding.recyclerView
            recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

            dealsAdapter = DealsPreviewAdapter(it, ::handleOfferClick)
            recyclerView.adapter = dealsAdapter

        }

        return root
    }

    private fun handleOfferClick(int: Int) {
        findNavController().navigate(DashboardFragmentDirections.actionDealsPreviewFragmentToDealsDetailsActivity(int))
    }
}