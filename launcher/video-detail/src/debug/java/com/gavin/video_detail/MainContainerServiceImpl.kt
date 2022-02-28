package com.gavin.video_detail

import android.content.Context
import androidx.compose.runtime.Composable
import com.alibaba.android.arouter.facade.annotation.Route
import com.gavin.common.MainContainerService
import com.gavin.common.constant.Constants
import com.google.accompanist.pager.ExperimentalPagerApi

@Route(path = Constants.ARouterPath.MAIN_CONTAINER)
class MainContainerServiceImpl : MainContainerService {

    @ExperimentalPagerApi
    @Composable
    override fun MainContainerComposable() {
        VideoDetailScreen()
    }

    override fun init(context: Context?) {

    }

}