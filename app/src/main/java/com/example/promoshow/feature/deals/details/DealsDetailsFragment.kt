package com.example.promoshow.feature.deals.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.promoshow.R
import com.example.promoshow.databinding.FragmentDealsDetailsBinding
import com.example.promoshow.feature.deals.previews.DealsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealsDetailsFragment : Fragment() {

    private val args by navArgs<DealsDetailsFragmentArgs>()
    private var _binding: FragmentDealsDetailsBinding? = null

    private val dealsViewModel: DealsViewModel by viewModels({ requireActivity() })

    private var isFavourite: Boolean = false

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDealsDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.dealId.text = args.dealObject.id.toString()
        binding.dealName.text = args.dealObject.name
        binding.dealDescription.text = args.dealObject.description
        binding.dealOutlet.text = "Producent"
        isFavourite = dealsViewModel.isFavourite(args.dealObject.id.toString())
        updateFavouriteIcon()

        binding.favouriteButton.setOnClickListener {
            isFavourite = !isFavourite
            dealsViewModel.updateFavourite(args.dealObject.id.toString(), isFavourite)
            updateFavouriteIcon()
        }
        return root
    }

    private fun updateFavouriteIcon() {
        val icon =
            if (isFavourite)
                ContextCompat.getDrawable(requireActivity(), R.drawable.baseline_favorite_24)
            else
                ContextCompat.getDrawable(requireActivity(), R.drawable.baseline_favorite_border_24)
        binding.favouriteButton.setImageDrawable(icon)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}