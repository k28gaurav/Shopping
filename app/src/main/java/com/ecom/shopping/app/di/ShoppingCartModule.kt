package com.ecom.shopping.app.di

import com.ecom.shopping.app.components.AllItemsFragment
import com.ecom.shopping.app.components.ShoppingCartFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ShoppingCartModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun shoppingCartFragment(): ShoppingCartFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun allItemsFragment(): AllItemsFragment
}