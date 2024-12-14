package com.mustafacan.coroutinesamples.ui.main

import com.mustafacan.coroutinesamples.ui.base.BaseViewModel

class MainScreenViewModel :
    BaseViewModel<MainScreenUiStateManager.MainScreenState, MainScreenUiStateManager.MainScreenEvent,
            MainScreenUiStateManager.MainScreenEffect>(
        initialState = MainScreenUiStateManager.MainScreenState.initial(),
        uiStateManager = MainScreenUiStateManager()
    ) {

}