package com.gavin.video_detail.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val PLAYER_VIEW_HEIGHT_DP = 200.dp

@Composable
fun PlayerView(modifier:Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
    ) {
        Spacer(
            Modifier
                .matchParentSize()
                .background(Color.LightGray)
        )

        Text(
            text = "player",
            modifier = Modifier.align(Alignment.Center),
            fontSize = 22.sp
        )
    }
}