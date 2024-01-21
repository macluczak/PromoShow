package com.example.promoshow.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.promoshow.databinding.FragmentDashboardBinding
import com.example.promoshow.feature.category.CategoryPreviewFragment
import com.example.promoshow.feature.deals.previews.DealsPreviewFragment
import com.example.promoshow.feature.stores.previews.StoresPreviewFragment

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

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