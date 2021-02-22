package com.and.mvvmtest.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.apitestproject.network.RetrofitClient
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class SearchRepository @Inject constructor() {

    private var _searchData: MutableLiveData<ArrayList<String>> = MutableLiveData()

    fun getApi(s: String): MutableLiveData<ArrayList<String>> {
        //jenkins test
        RetrofitClient.getInstance()
        .getGifImage(s)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe ({
            val mData = it.getAsJsonArray("data")
            val cnt = mData.size()-1
            val array: ArrayList<String> = ArrayList()
            for (i in (0 until cnt)){
                array.add(mData
                        .asJsonArray.get(i)
                        .asJsonObject.get("images")
                        .asJsonObject.get("original")
                        .asJsonObject.get("url").asString)
            }
            // 1번 방식 : 옵저버로 _searchData 값을 바라보고 _searchData 데이타 변경시 UI그림
//                        viewModel.setData(array)
            _searchData.postValue(array)
        },{
            Log.d("##", "error : $it,")
        })

        return _searchData
    }

    fun onCleared() {

    }
}