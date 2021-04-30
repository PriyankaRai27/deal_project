package com.target.targetcasestudy.ui.deal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.entities.DealItem
import com.target.targetcasestudy.model.DealViewModel
import com.target.targetcasestudy.utils.DealResource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DealListFragment : Fragment(), DealItemAdapter.DealItemListener {
    private var dealAdapter: DealItemAdapter? = null
    private var rv: RecyclerView? = null
    private val dealsViewModel: DealViewModel by viewModels()
    var dealArrayList: ArrayList<DealItem> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View? = null
        if (view == null) {
            Log.d("Fragment", "onCreateView: ")
            view = inflater.inflate(R.layout.fragment_deal_list, container, false)
        }
        return view
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Fragment", "onViewCreated: ")
        rv = view.findViewById(R.id.recycler_view);
        setupRecyclerView()
        setupObservers()
    }


    private fun setupObservers() {
        dealsViewModel?.deals?.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                DealResource.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()) {
                        dealArrayList.clear()
                        dealArrayList.addAll(it.data)
                        dealAdapter?.notifyDataSetChanged()
                    }
                }
                DealResource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

            }
        })
    }

    private fun setupRecyclerView() {
        dealAdapter = context?.let { DealItemAdapter(it, dealArrayList, this) }
        rv?.setHasFixedSize(true);
        rv?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        rv?.setLayoutManager(LinearLayoutManager(getActivity()));
        rv!!.adapter = dealAdapter
        rv!!.itemAnimator = DefaultItemAnimator()
        rv!!.isNestedScrollingEnabled = true
    }

    override fun onClickedDeal(dealId: Int?) {
        val bundle = Bundle()
        dealId?.let { bundle.putInt("id", it) }
        val bookFragment = DealDetailsFragment()
        bookFragment.arguments = bundle
        findNavController().navigate(
            R.id.action_dealFragment_to_dealDetailFragment,
            bundleOf("id" to dealId)
        )
    /*    requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, bookFragment, "dealfragment").addToBackStack("dealfragment")
            .commit()*/
    }

}
