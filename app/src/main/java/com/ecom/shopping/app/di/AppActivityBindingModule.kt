package com.ecom.shopping.app.di

import com.ecom.shopping.app.components.ShoppingCartActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [ShoppingCartModule::class])
    abstract fun shoppingCartActivity(): ShoppingCartActivity
}