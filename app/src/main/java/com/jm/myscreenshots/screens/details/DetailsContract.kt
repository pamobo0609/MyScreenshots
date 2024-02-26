package com.jm.myscreenshots.screens.details

import com.jm.myscreenshots.base.BaseEffect
import com.jm.myscreenshots.base.BaseEvent
import com.jm.myscreenshots.base.BaseState

sealed class DetailsEvent : BaseEvent() {
    data object Initialize : DetailsEvent()
}

sealed class DetailsState : BaseState() {
    data object Uninitialized : DetailsState()
    data object Loaded : DetailsState()
}

sealed class DetailsEffect : BaseEffect()