package com.and.mvvmtest.ui.search

import androidx.lifecycle.ViewModel
import com.and.mvvmtest.di.custom.ViewModelKey
import com.and.mvvmtest.ui.search.viewModel.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun bindViewModel(viewModel: SearchViewModel): ViewModel
}