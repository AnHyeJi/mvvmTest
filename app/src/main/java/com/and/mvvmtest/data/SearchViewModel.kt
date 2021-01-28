package com.and.mvvmtest.data

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.and.mvvmtest.adapter.SearchUiAdapter

class SearchViewModel :ViewModel() {
      val _searchData : MutableLiveData<ArrayList<String>> = MutableLiveData()

    fun setData(data: ArrayList<String>){
        _searchData.postValue(data)
    }

    fun getData():MutableLiveData<ArrayList<String>> {
        return _searchData
    }
    val searchData: LiveData<ArrayList<String>> = _searchData

    @BindingAdapter("setSearchData")
    fun setSearch(listview: RecyclerView, data: ArrayList<String>) {
        listview?.adapter?.let {
            (it as SearchUiAdapter).setData(data)
        }
    }
}