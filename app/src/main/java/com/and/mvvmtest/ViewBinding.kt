package com.and.mvvmtest

import android.util.Log
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.and.mvvmtest.adapter.SearchUiAdapter

object ViewBinding {

    @BindingAdapter("adapterData")
    @JvmStatic
    fun setAdapterData(rcView :RecyclerView?, item :ArrayList<String>?) {
        Log.d("##ahj adapterData : ", rcView.toString())
        rcView?.adapter?.let {

            item?.let {
                val layoutManager = GridLayoutManager(rcView.context, 2)
                val adapter = SearchUiAdapter(item)
                rcView.layoutManager = layoutManager
                rcView.adapter = adapter

                (it as SearchUiAdapter).setData(item)
                rcView.adapter?.notifyDataSetChanged()

                Toast.makeText(SearchActivity().applicationContext,"",Toast.LENGTH_LONG).show()
            }
            Toast.makeText(SearchActivity().applicationContext,"",Toast.LENGTH_LONG).show()
        }
}

}