package com.and.mvvmtest.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.and.mvvmtest.R
import com.and.mvvmtest.adapter.SearchUiAdapter
import com.and.mvvmtest.databinding.ActivitySearchBinding
import com.and.mvvmtest.ui.base.BaseActivity
import com.and.mvvmtest.ui.search.viewModel.SearchViewModel
import com.example.apitestproject.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_search.*
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Named


class SearchActivity : BaseActivity<ActivitySearchBinding>() {

    @Inject lateinit var factory: ViewModelProvider.Factory
    @Inject lateinit var searchAdapter: SearchUiAdapter
    @Inject @Named("dataInput") lateinit var txtData: String

    private val viewModel by viewModels<SearchViewModel> { factory }

    override val layout: Int
        get() = R.layout.activity_search

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

        binding.tv.text = txtData

        binding.apply {
            myModel = viewModel
            recycle.adapter = searchAdapter
        }

        viewModel.setSearchData("")
        /*
         *  //1번 방식
         *         viewModel._searchData.observe(this, Observer<ArrayList<String>>{
                if(it !=null){
                Toast.makeText(this,""+it.toString(),Toast.LENGTH_LONG).show()
                    mSearchUiAdapter = SearchUiAdapter(it)

                    if(gridLayoutManager == null){
                        val gridLayoutManager = GridLayoutManager(this, 2)
                        recycle.layoutManager = gridLayoutManager
                        recycle.adapter = mSearchUiAdapter
                    }
                }
        })
         */


        edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("addTextChanged",s.toString())
                viewModel.setSearchData(s.toString())
            }
        })
    }
}