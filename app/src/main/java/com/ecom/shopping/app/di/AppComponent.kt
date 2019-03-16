package com.ecom.shopping.app.di

import android.app.Application
import com.ecom.shopping.ShoppingApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector

@Component
interface AppComponent: AndroidInjector<ShoppingApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder
        fun build(): AppComponent
    }
}