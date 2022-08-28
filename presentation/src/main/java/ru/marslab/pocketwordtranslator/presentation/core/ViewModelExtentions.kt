package ru.marslab.pocketwordtranslator.presentation.core

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.marslab.pocketwordtranslator.core.BaseViewModel

@Composable
inline fun <reified VM : BaseViewModel<*, *, *>> MakeViewModel(
    content: @Composable (viewModel: VM) -> Unit
) {
    content(
        viewModel<VM>().apply {
            setNavigator(LocalNavigator.currentOrThrow)
        }
    )
}
