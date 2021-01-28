package com.and.mvvmtest.ui.search

import com.and.mvvmtest.adapter.SearchUiAdapter
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    fun provideSearchAdapter(): SearchUiAdapter {
        return SearchUiAdapter()
    }
}