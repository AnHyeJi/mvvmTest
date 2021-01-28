package com.and.mvvmtest.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.and.mvvmtest.adapter.SearchUiAdapter

@BindingAdapter("adapterData")
fun setAdapterData(rcView :RecyclerView?, item :ArrayList<String>?) {
    item?.let {
        (rcView?.adapter as SearchUiAdapter).setData(item)
    }
}