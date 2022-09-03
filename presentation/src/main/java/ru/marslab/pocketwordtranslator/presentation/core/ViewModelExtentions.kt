package ru.marslab.pocketwordtranslator.presentation.core

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.kodein.di.compose.localDI
import org.kodein.di.instance
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

@Composable
inline fun <reified VM : BaseViewModel<*, *, *>> KodeinViewModel(
    content: @Composable (viewModel: VM) -> Unit
) {
    val viewModel by localDI().instance<VM>()
    content(
        viewModel.apply {
            setNavigator(LocalNavigator.currentOrThrow)
        }
    )
}
