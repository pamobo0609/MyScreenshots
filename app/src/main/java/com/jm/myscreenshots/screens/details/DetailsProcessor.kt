package com.jm.myscreenshots.screens.details

import com.jm.myscreenshots.base.BaseProcessor
import javax.inject.Inject
import com.jm.myscreenshots.screens.details.DetailsState.Uninitialized
import com.jm.myscreenshots.screens.details.DetailsState.Loaded
import com.jm.myscreenshots.screens.details.DetailsEvent.Initialize

class DetailsProcessor @Inject constructor() : BaseProcessor<DetailsEvent, DetailsState, DetailsEffect>() {

    override fun createInitialState() = Uninitialized

    override fun handleEvent(event: DetailsEvent) {
        when(event) {
            Initialize -> onInitialize()
        }
    }

    private fun onInitialize() {
        setState(Loaded)
    }
}