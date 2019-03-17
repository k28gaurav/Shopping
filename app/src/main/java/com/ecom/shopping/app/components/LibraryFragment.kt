package com.ecom.shopping.app.components

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ecom.shopping.R
import com.ecom.shopping.app.base.DaggerBaseFragment
import com.ecom.shopping.viewmodels.ShoppingCartViewModel
import kotlinx.android.synthetic.main.fragment_library.*
import java.lang.RuntimeException
import javax.inject.Inject

class LibraryFragment : DaggerBaseFragment<ShoppingCartViewModel>() {

    enum class NextFrag {
        ALL_DISCOUNT, ALL_ITEMS
    }

    companion object {
        fun newInstance(): LibraryFragment {
            return LibraryFragment()
        }
    }

    private lateinit var changeFragmentListener: ChangeFragmentListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is ChangeFragmentListener) {
            changeFragmentListener = context
        } else {
            throw RuntimeException("Must implement ChangeFragmentListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_library, container, false)
    }


    override fun initEventHandlers() {
        cl_all_discounts.setOnClickListener(this)
        cl_all_items.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            cl_all_discounts.id -> changeFragmentListener.onFragmentChange(NextFrag.ALL_DISCOUNT)
            cl_all_items.id -> changeFragmentListener.onFragmentChange(NextFrag.ALL_ITEMS)
        }
    }

    interface ChangeFragmentListener {
        fun onFragmentChange(fragmentName: NextFrag)
    }

}