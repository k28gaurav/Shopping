package com.ecom.shopping.app.base

import android.os.Bundle
import android.view.View
import dagger.android.support.DaggerFragment

abstract class DaggerBaseFragment<VM: BaseViewModel>: DaggerFragment(),  View.OnClickListener {

    protected lateinit var viewModel: VM

    var isFragmentShowCalled = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()

    }

    protected open fun observeViewModel() {

    }

    protected open fun initViews() {
        initEventHandlers()
    }

    override fun onClick(view: View?) {

    }

    protected open fun initEventHandlers() {

    }
}