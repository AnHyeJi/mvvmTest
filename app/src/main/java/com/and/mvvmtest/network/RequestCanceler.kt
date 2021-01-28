package com.example.apitestproject.network

import java.util.ArrayList

class RequestCanceler {

    private val fetchers = ArrayList<DataFetcher<*>>()

    fun cancelOnDestroy(fetcher: DataFetcher<*>) {
        val removeList = ArrayList<DataFetcher<*>>(20)
        for (d in fetchers) {
            if (d.isFinish) {
                removeList.add(d)
            }
        }
        fetchers.removeAll(removeList)
        if (fetchers.contains(fetcher))
            return
        fetchers.add(fetcher)
    }

    fun cancel() {
        for (fetcher in fetchers) {
            fetcher.cancel()
        }
        fetchers.clear()
    }
}
