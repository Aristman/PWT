package ru.marslab.pocketwordtranslator.presentation.views

import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

@Composable
fun WordSoundDialog(
    soundUri: Uri,
    setVisible: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val mediaItem = remember {
        MediaItem.fromUri(soundUri)
    }
    val player = remember {
        ExoPlayer.Builder(context).build()
    }
    player.apply {
        addMediaItem(mediaItem)
        prepare()
    }
    Dialog(onDismissRequest = { setVisible(false) }) {
        Box(
            modifier = Modifier
                .size(width = 200.dp, height = 80.dp)
                .background(MaterialTheme.colors.secondary.copy(alpha = 0.5f))
        ) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = {
                    PlayerView(context).apply {
                        setPlayer(player)
                        FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    }
                })
        }
    }
}

