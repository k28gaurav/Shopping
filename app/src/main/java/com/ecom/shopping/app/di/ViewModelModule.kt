package com.ecom.shopping.app.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ecom.shopping.app.common.ViewModelFactory
import com.ecom.shopping.app.common.ViewModelKey
import com.ecom.shopping.viewmodels.ProductListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel::class)
    abstract fun cartViewModel(viewModel: ProductListViewModel): ViewModel

}