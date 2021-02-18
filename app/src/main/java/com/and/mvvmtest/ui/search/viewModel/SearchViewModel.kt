package com.and.mvvmtest.ui.search.viewModel

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.and.mvvmtest.adapter.SearchUiAdapter
import com.and.mvvmtest.ui.search.SearchRepository
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {
    private var _searchData: MutableLiveData<ArrayList<String>> = MutableLiveData()

    val searchData: MutableLiveData<ArrayList<String>>
        get() = _searchData

    fun setSearchData(s: String) {
        _searchData = repository.getApi(s)
    }

    override fun onCleared() {
        repository.onCleared()
        super.onCleared()
    }
// test

}