package com.and.mvvmtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.and.mvvmtest.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_gif.view.*
import javax.inject.Inject

class SearchUiAdapter @Inject constructor() : RecyclerView.Adapter<ViewHolder>() {

    private var arrData: ArrayList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_gif, parent, false))
    }

    override fun getItemCount(): Int {
        return arrData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(arrData[position])
    }

    fun setData(arrData: ArrayList<String>) {
        this.arrData = arrData
        notifyDataSetChanged()
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val img: ImageView = view.iv

    fun bindView(data: String) {
        Glide.with(itemView.context).load(data).into(img)
    }
}