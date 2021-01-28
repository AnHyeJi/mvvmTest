package com.and.mvvmtest.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.and.mvvmtest.adapter.SearchUiAdapter

@BindingAdapter("setSearchData")
fun setSearch(listview: RecyclerView, data: ArrayList<String>) {
    listview?.adapter?.let {
        (it as SearchUiAdapter).setData(data)
    }
}