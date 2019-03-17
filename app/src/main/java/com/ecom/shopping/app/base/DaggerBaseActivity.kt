package com.ecom.shopping.app.base

import dagger.android.support.DaggerAppCompatActivity

/**
 * this is base activity ,maintain the base behaviour
 */
abstract class DaggerBaseActivity<VM : BaseViewModel>: DaggerAppCompatActivity() {
    protected lateinit var viewModel: VM
}