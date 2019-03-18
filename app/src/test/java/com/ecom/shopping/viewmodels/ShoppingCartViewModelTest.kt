package com.ecom.shopping.viewmodels

import com.ecom.shopping.app.rx.SchedulersFacade
import com.ecom.shopping.data.db.entities.Item
import com.ecom.shopping.data.models.Products
import com.ecom.shopping.domain.interactor.*
import com.ecom.shopping.utils.TrampolineSchedulerProvider
import io.reactivex.Single
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ShoppingCartViewModelTest {


    lateinit var schedulersFacade: TrampolineSchedulerProvider
    @Mock
    lateinit var getItemsUseCase: GetItemsUseCase
    @Mock
    lateinit var fetchItemListUseCase: FetchItemListUseCase
    @Mock
    lateinit var saveItemsUseCase: SaveItemsUseCase
    @Mock
    lateinit var clearCartItemUseCase: ClearCartItemUseCase
    @Mock
    lateinit var getCartItemsUseCase: GetCartItemUseCase
    @Mock
    lateinit var addCartItemUseCase: AddCartItemUseCase

    @Mock
    private lateinit var product: Products

    lateinit var shoppingCartViewModel:ShoppingCartViewModel

    @Test
    fun fetchItemList_success(){
        `when`(fetchItemListUseCase.execute()).thenReturn(Single.just(product))

        val execute = shoppingCartViewModel.fetchItemList()

        verify(fetchItemListUseCase, times(1)).execute()
        verify(saveItemsUseCase, times(1)).execute(listOf(Item(1,"","","",4.5f)))

    }

    @Before
    fun setUp() {
        schedulersFacade =  TrampolineSchedulerProvider()

        shoppingCartViewModel = ShoppingCartViewModel(schedulersFacade, getItemsUseCase,
            fetchItemListUseCase,saveItemsUseCase, clearCartItemUseCase, getCartItemsUseCase,
        addCartItemUseCase)
    }

    @After
    fun tearDown() {
    }
}