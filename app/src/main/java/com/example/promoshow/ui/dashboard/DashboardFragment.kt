package com.example.promoshow.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.promoshow.databinding.FragmentDashboardBinding
import com.example.promoshow.feature.category.CategoryPreviewFragment
import com.example.promoshow.feature.deals.DealsPreviewFragment
import com.example.promoshow.feature.stores.StoresPreviewFragment

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val dashboardViewModel =
//            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        childFragmentManager.beginTransaction()
            .replace(binding.categoryFragmentContainer.id, CategoryPreviewFragment())
            .replace(binding.dealsFragmentContainer.id, DealsPreviewFragment())
            .replace(binding.storesFragmentContainer.id, StoresPreviewFragment())
            .commit()


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}