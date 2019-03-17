package com.ecom.shopping.app.di

import com.ecom.shopping.app.components.ShoppingCartActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [FragmentBindingModule::class])
abstract class AppActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector()
    abstract fun shoppingCartActivity(): ShoppingCartActivity
}