package com.and.mvvmtest.di.component

import android.app.Application
import com.and.mvvmtest.MainApplication
import com.and.mvvmtest.di.module.ActivityBindingModule
import com.and.mvvmtest.di.module.FactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        ActivityBindingModule::class,
        FactoryModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}