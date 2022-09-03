package ru.marslab.pocketwordtranslator.presentation.widget

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VSpacerSmall() {
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun VSpacerMedium() {
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun VSpacerLarge() {
    Spacer(modifier = Modifier.height(40.dp))
}

@Composable
fun VSpacer(height: Int) {
    Spacer(modifier = Modifier.height(height.dp))
}

@Composable
fun HSpacerSmall() {
    Spacer(modifier = Modifier.width(10.dp))
}

@Composable
fun HSpacerMedium() {
    Spacer(modifier = Modifier.width(20.dp))
}

@Composable
fun HSpacerLarge() {
    Spacer(modifier = Modifier.width(40.dp))
}

@Composable
fun HSpacer(width: Int) {
    Spacer(modifier = Modifier.width(width.dp))
}
