package com.ecom.shopping.app.di

import com.ecom.shopping.app.components.AllItemsFragment
import com.ecom.shopping.app.components.DiscountFragment
import com.ecom.shopping.app.components.LibraryFragment
import com.ecom.shopping.app.components.ShoppingCartFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindLibraryFragment(): LibraryFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindAllItemsFragment(): AllItemsFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindDiscountFragment(): DiscountFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindShoppingFragment(): ShoppingCartFragment
}