package com.target.targetcasestudy.ui.deal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.entities.DealItem


class DealItemAdapter(
    private var context: Context,
    var dealsArrayList: ArrayList<DealItem>,
    var listener: DealItemListener
) : RecyclerView.Adapter<DealItemViewHolder>() {

    interface DealItemListener {
        fun onClickedDeal(dealId: Int?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.deal_list_item, null)
        return DealItemViewHolder(view, listener)

    }

    override fun getItemCount(): Int {
        return dealsArrayList.size
    }

    override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
        viewHolder.bindDeal(dealsArrayList[position])
    }
}

class DealItemViewHolder(
    itemView: View,
    private val listener: DealItemAdapter.DealItemListener
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    var id: Int? = 0
    fun bindDeal(product: DealItem) {
        id = product.id
        itemView.findViewById<TextView>(R.id.dealTitle).text = product.title
        itemView.findViewById<TextView>(R.id.dealPrice).text = product.regular_price.display_string
        itemView.findViewById<TextView>(R.id.dealAisle).text = product.aisle
        val dealImageView: ImageView = itemView.findViewById(R.id.dealImage)
        Picasso.get().load(product.image_url).into(dealImageView)
        itemView.setOnClickListener(this);

    }

    override fun onClick(v: View?) {
        listener.onClickedDeal(id)
    }
}





