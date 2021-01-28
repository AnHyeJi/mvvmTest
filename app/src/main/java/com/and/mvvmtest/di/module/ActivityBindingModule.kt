package com.and.mvvmtest.di.module

import com.and.mvvmtest.ui.main.MainActivity
import com.and.mvvmtest.ui.search.SearchActivity
import com.and.mvvmtest.di.custom.ActivityScope
import com.and.mvvmtest.ui.search.SearchModule
import com.and.mvvmtest.ui.search.SearchViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun mainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [
            SearchViewModelModule::class,
            SearchModule::class
    ])
    abstract fun searchActivity(): SearchActivity
}