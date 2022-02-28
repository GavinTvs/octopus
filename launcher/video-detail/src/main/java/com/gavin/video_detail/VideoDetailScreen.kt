package com.gavin.video_detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.gavin.common.utils.printLog
import com.gavin.video_detail.player.PlayerView
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@ExperimentalPagerApi
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun VideoDetailScreen() {
    val viewModel = VideoDetailViewModel()

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(0)

    val playerViewHeightPx =
        with(LocalDensity.current) { 200.dp.roundToPx().toFloat() }
    val playerOffsetHeightPx = remember { mutableStateOf(0f) }

    val nestedScrollFoldRatio = remember { mutableStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = playerOffsetHeightPx.value + delta
                playerOffsetHeightPx.value = newOffset.coerceIn(-playerViewHeightPx, 0f)
                nestedScrollFoldRatio.value = playerOffsetHeightPx.value / playerViewHeightPx
                printLog("nestedScrollFoldRatio = $nestedScrollFoldRatio")
                val offset = if (playerOffsetHeightPx.value.roundToInt() >= playerViewHeightPx) Offset.Zero else Offset(0f, delta)
                printLog("offset.y = ${offset.y}")
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .nestedScroll(nestedScrollConnection)
            .onGloballyPositioned {

            }
    ) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
        ) {

            PlayerView(
                modifier = Modifier
                    .height(200.dp)
                    .offset {
                        IntOffset(
                            0,
                            (playerViewHeightPx * nestedScrollFoldRatio.value).roundToInt()
                        )
                    }
            )

            Row(
                modifier = Modifier
                    .height(40.dp)
                    .offset {
                        IntOffset(
                            0,
                            (playerViewHeightPx * nestedScrollFoldRatio.value).roundToInt()
                        )
                    }

            ) {
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    viewModel.tabData.forEachIndexed { index, tabData ->
                        Tab(
                            selected = index == pagerState.currentPage,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.scrollToPage(index)
                                }
                            },
                            text = {
                                Text(text = tabData.title)
                            },
                        )
                    }
                }

                Text(
                    text = "弹幕",
                    modifier = Modifier
                        .width(150.dp)
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center
                )
            }

            HorizontalPager(
                count = 3,
                state = pagerState,
                modifier = Modifier
                    .weight(1f),
            ) {
                DetailIntroView(viewModel.tabData[pagerState.currentPage])
            }

        }

        TopAppBar(
            modifier = Modifier
                .height(50.dp)
                .alpha(1 - nestedScrollFoldRatio.value),
            title = { Text("红小豆") },
        )
    }
}


@Composable
fun DetailIntroView(tabData: TabData) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(50) { index ->
            Text(
                "I'm item ${tabData.title}-[$index]", modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

class VideoDetailViewModel {
    val tabData = listOf(
        TabData("简介", Icons.Filled.Home),
        TabData("评论（99+）", Icons.Filled.ShoppingCart),
        TabData("活动", Icons.Filled.AccountBox),
    )
}

data class TabData(val title: String, val icon: ImageVector)

class IntroViewModel {


}
