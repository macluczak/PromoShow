package com.example.promoshow.feature.deals.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import androidx.navigation.fragment.navArgs

import com.example.promoshow.databinding.FragmentDealsDetailsBinding

class DealsDetailsFragment : Fragment() {

    val args by navArgs<DealsDetailsFragmentArgs>()
    private var _binding: FragmentDealsDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDealsDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dealId.text = args.dealObject.id.toString()
        binding.dealName.text = args.dealObject.name
        binding.dealDescription.text = args.dealObject.description
        binding.dealOutlet.text = "Producent"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}