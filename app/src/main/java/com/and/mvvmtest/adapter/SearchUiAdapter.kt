package com.and.mvvmtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.and.mvvmtest.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_gif.view.*

class SearchUiAdapter(var arrData: ArrayList<String>) : RecyclerView.Adapter<SearchUiAdapter.viewHolder>() {

    class viewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.iv


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_gif, parent, false))
    }

    override fun getItemCount(): Int {
        return arrData.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        Glide.with(holder.img.context).load(arrData.get(position)).into(holder.img)
    }

    fun setData(arrData: ArrayList<String>) {
        this.arrData = arrData
        notifyDataSetChanged()
    }

}