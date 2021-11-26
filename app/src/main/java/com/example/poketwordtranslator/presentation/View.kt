package com.example.poketwordtranslator.presentation

import com.example.poketwordtranslator.presentation.model.ViewState


interface View {
    fun renderData(viewState: ViewState)
}