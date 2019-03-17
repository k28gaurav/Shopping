package com.ecom.shopping.app.base

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatDialogFragment
import android.view.View
import android.view.Window
import dagger.android.support.DaggerAppCompatDialogFragment
import java.lang.IllegalStateException

abstract class BaseDialogFragment : AppCompatDialogFragment() {

    var isFragmentShowCalled = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    protected open fun initViews() {
        initAnimations()
        initEventHandlers()
    }

    protected open fun initAnimations() {


    }

    protected open fun initEventHandlers() {

    }

    abstract fun getHeight(): Int

    abstract fun getWidth(): Int

    abstract fun getBackgroundDrawableRes(): Int

    override fun onStart() {
        super.onStart()
        val d = dialog
        if (d != null) {
            d.window.setLayout(getWidth(), getHeight())
            d.window.setBackgroundDrawableResource(getBackgroundDrawableRes())
        }
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        isFragmentShowCalled = false
    }

    override fun show(manager: FragmentManager?, tag: String?) {
        isFragmentShowCalled = true
        try {
            super.show(manager, tag)
        } catch (ex: IllegalStateException) {
            isFragmentShowCalled = false
        }
    }

    override fun show(transaction: FragmentTransaction?, tag: String?): Int {
        isFragmentShowCalled = true
        try {
            return super.show(transaction, tag)
        } catch (ex: IllegalStateException) {
            isFragmentShowCalled = false
        }
        return -1
    }

}