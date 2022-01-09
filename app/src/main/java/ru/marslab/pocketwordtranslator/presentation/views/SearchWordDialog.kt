package ru.marslab.pocketwordtranslator.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import ru.marslab.pocketwordtranslator.R

@Composable
fun SearchWordDialog(setVisible: (Boolean) -> Unit, searchWord: (word: String) -> Unit) {
    var searchText by remember { mutableStateOf("") }
    Dialog(onDismissRequest = { }) {
        Column(
            Modifier.background(
                color = MaterialTheme.colors.background,
                shape = MaterialTheme.shapes.large
            )
        ) {
            TextField(
                value = searchText,
                singleLine = true,
                onValueChange = { searchText = it },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.word_enter_title),
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(onClick = {
                    searchWord(searchText)
                    setVisible(false)
                }) {
                    Text(text = stringResource(id = R.string.ok))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchWordPreView() {
    val (_, setVisible) = remember {
        mutableStateOf(false)
    }
    SearchWordDialog(setVisible = setVisible, searchWord = {})
}
