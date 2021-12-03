package ru.marslab.pocketwordtranslator.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import ru.marslab.pocketwordtranslator.R
import ru.marslab.pocketwordtranslator.presentation.viewmodels.AppActionViewModel

@Composable
fun SearchWordDialog(viewModel: AppActionViewModel, searchWord: (word: String) -> Unit) {
    var searchText by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = { },
        text = {
            Column {
                TextField(value = searchText, onValueChange = { searchText = it })
                Text(text = stringResource(id = R.string.word_enter_title))
            }
        },
        confirmButton = {
            Button(onClick = {
                searchWord(searchText)
                viewModel.viewHide()
            }) {
                Text(text = stringResource(id = R.string.ok))
            }
        }
    )
}