package com.ecom.shopping.viewmodels

import com.ecom.shopping.app.base.BaseViewModel
import com.ecom.shopping.app.rx.SchedulersFacade
import javax.inject.Inject

class ProductListViewModel @Inject constructor(schedulersFacade: SchedulersFacade):
    BaseViewModel(schedulersFacade) {

    }