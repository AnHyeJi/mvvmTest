package com.and.mvvmtest

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.and.mvvmtest.adapter.SearchUiAdapter

object ViewBinding {

    @BindingAdapter("adapterData")
    @JvmStatic
    fun setAdapterData(rcView :RecyclerView?, item :ArrayList<String>?) {
        item?.let {
            (rcView?.adapter as SearchUiAdapter).setData(item)
        }
    }
}

