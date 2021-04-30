package com.target.targetcasestudy.ui.deal

import android.R.attr.defaultValue
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.target.targetcasestudy.utils.DealResource
import com.squareup.picasso.Picasso
import com.target.targetcasestudy.model.DealViewModel
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.entities.DealItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealDetailsFragment : Fragment() {
    lateinit var tvPrice: TextView
    lateinit var tvTitle: TextView
    private lateinit var tvDesription: TextView
    lateinit var tvImage: ImageView
    private val dealsViewModel: DealViewModel by viewModels()
    var dealId: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = this.arguments
        if (bundle != null) {
            dealId = bundle.getInt("id", defaultValue)
        }
        return inflater.inflate(R.layout.fragment_deal_item, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvPrice = view.findViewById(R.id.dealPrice) as TextView
        tvTitle = view.findViewById(R.id.dealTitle) as TextView
        tvDesription = view.findViewById(R.id.dealDiscription) as TextView
        tvImage = view.findViewById(R.id.dealImage) as ImageView
        dealsViewModel.start(dealId)
        setupObservers()
    }

    private fun updateData(deal: DealItem) {
        // this logic is because the particular id description value is too long to set on textview and not want to trim all other deal description
        tvTitle.text = deal.title
        if (dealId == 24) {
            tvDesription.text = deal.description?.substring(0, 500)
        } else {
            tvDesription.text = deal.description

        }
        tvPrice.text = deal.regular_price.display_string
        Picasso.get().load(deal.image_url).into(tvImage)
    }

    private fun setupObservers() {
        dealsViewModel?.dealDetail?.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                DealResource.Status.SUCCESS -> {
                    updateData(it.data!!)
                }

                DealResource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                DealResource.Status.LOADING -> {
                  //  Toast.makeText(activity, "loading", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
