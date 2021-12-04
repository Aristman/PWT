package ru.marslab.pocketwordtranslator.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import ru.marslab.pocketwordtranslator.R

@Composable
fun SearchWordDialog(setVisible: (Boolean) -> Unit, searchWord: (word: String) -> Unit) {
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
                setVisible(false)
            }) {
                Text(text = stringResource(id = R.string.ok))
            }
        }
    )
}
