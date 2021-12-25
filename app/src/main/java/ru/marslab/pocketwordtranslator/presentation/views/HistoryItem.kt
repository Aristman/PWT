package ru.marslab.pocketwordtranslator.presentation.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection.EndToStart
import androidx.compose.material.DismissValue.Default
import androidx.compose.material.DismissValue.DismissedToEnd
import androidx.compose.material.DismissValue.DismissedToStart
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.marslab.pocketwordtranslator.R.drawable
import ru.marslab.pocketwordtranslator.presentation.model.HistoryUiState

private const val DELETE_WORD_DELAY = 3000L

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HistoryItem(
    item: HistoryUiState,
    onclickItem: (item: HistoryUiState) -> Unit,
    onDeleteItem: (item: HistoryUiState) -> Unit
) {
    val dismissState = rememberDismissState()
    val scope = rememberCoroutineScope()
    SwipeToDismiss(
        state = dismissState,
        directions = setOf(EndToStart),
        background = {
            val color by animateColorAsState(
                targetValue = when (dismissState.targetValue) {
                    Default -> MaterialTheme.colors.background
                    DismissedToEnd -> MaterialTheme.colors.background
                    DismissedToStart -> MaterialTheme.colors.error
                }
            )
            val scale by animateFloatAsState(
                if (dismissState.targetValue == Default) 0.5f else 1f
            )
            if (dismissState.isDismissed(EndToStart)) {
                LaunchedEffect(key1 = true) {
                    scope.launch {
                        dismissState.reset()
                        onDeleteItem(item)
                    }
                }
            }
            Box(
                Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Row {
                    Icon(
                        painterResource(id = drawable.ic_baseline_delete_sweep_24),
                        contentDescription = "Localized description",
                        modifier = Modifier.scale(scale)
                    )
                    IconButton(onClick = {
                        scope.launch {
                            dismissState.reset()
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = drawable.ic_baseline_undo_24),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    ) {
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .padding(2.dp),
            elevation = 4.dp,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onclickItem(item) }
            ) {
                Text(
                    text = item.word,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
