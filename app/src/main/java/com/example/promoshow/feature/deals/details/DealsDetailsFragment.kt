package com.example.promoshow.feature.deals.details

import android.graphics.Paint
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
import com.example.promoshow.util.loadImageWithGlide
import com.example.promoshow.util.toPLN
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
        val deal = args.dealObject

        isFavourite = dealsViewModel.isFavourite(deal.id.toString())
        updateFavouriteIcon()

        binding.apply {
            dealLabelId.text = "id: ${deal.id.toString()}"
            dealName.text = deal.name
            dealDescription.text = deal.description
            dealOutlet.text = deal.maker

            loadImageWithGlide(dealImage, deal.image)

            dealPrice.text = deal.discountPrice?.toPLN()

            dealRegularPrice.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                text = deal.price.toPLN()
            }

            favouriteButton.setOnClickListener {
                isFavourite = !isFavourite
                dealsViewModel.updateFavourite(deal.id.toString(), isFavourite)
                updateFavouriteIcon()
            }
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