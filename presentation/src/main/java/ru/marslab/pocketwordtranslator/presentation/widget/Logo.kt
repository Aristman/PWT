package ru.marslab.pocketwordtranslator.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.marslab.pocketwordtranslator.presentation.R

@Composable
fun Logo() {
    Row {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(id = R.string.ruen).uppercase(),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight(1000),
                color = MaterialTheme.colors.primary
            )
        )
    }
}
