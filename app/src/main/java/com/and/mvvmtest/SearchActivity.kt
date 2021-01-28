package com.and.mvvmtest

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.and.mvvmtest.adapter.SearchUiAdapter
import com.and.mvvmtest.databinding.ActivitySearchBinding
import com.and.mvvmtest.viewModel.SearchViewModel
import com.example.apitestproject.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_search.*
import rx.android.schedulers.AndroidSchedulers


class SearchActivity :AppCompatActivity() {
    private val retrofitClient = RetrofitClient.getInstance()
    lateinit var binding : ActivitySearchBinding
    lateinit var viewModel : SearchViewModel
    var txtData = ""
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        txtData = "데이터 입력"
        binding.tv.text = txtData
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(SearchViewModel::class.java)
        binding.apply {
            myModel = viewModel

        }

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
            override fun afterTextChanged(s: Editable?) {
                Log.d("afterTextChanged",s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("addTextChanged",s.toString())

                retrofitClient
                    .getGifImage(s.toString())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe ({
                        val mData = it.getAsJsonArray("data")
                        val cnt = mData.size()-1
                        val array: ArrayList<String> = ArrayList()
                        for (i in (0 until cnt)){
                            array.add(mData.asJsonArray.get(i).asJsonObject.get("images").asJsonObject.get("original").asJsonObject.get("url").asString)
                        }
                        // 1번 방식 : 옵저버로 _searchData 값을 바라보고 _searchData 데이타 변경시 UI그림
//                        viewModel.setData(array)
                        viewModel.setData(array)

                    },{
                        Log.d("##", "error : $it,")
                    })
            }
        })


    }

}