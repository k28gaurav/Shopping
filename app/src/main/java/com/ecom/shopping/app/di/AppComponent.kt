package com.ecom.shopping.app.di

import android.app.Application
import com.ecom.shopping.ShoppingApplication
import com.ecom.shopping.data.di.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
    AndroidSupportInjectionModule::class,
    ViewModelModule::class,
    DataModule::class,
    AndroidInjectionModule::class,
    AppActivityBindingModule::class])
interface AppComponent: AndroidInjector<ShoppingApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder
        fun build(): AppComponent
    }
}